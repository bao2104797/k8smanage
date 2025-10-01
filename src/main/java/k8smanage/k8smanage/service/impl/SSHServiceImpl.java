package k8smanage.k8smanage.service.impl;

import k8smanage.k8smanage.dto.reponse.SSHCommandResponse;
import k8smanage.k8smanage.dto.reponse.SSHDirectCommandResponse;
import k8smanage.k8smanage.dto.request.SSHCommandRequest;
import k8smanage.k8smanage.dto.request.SSHConnectionRequest;
import k8smanage.k8smanage.dto.request.SSHDirectCommandRequest;
import k8smanage.k8smanage.entity.NodeEntity;
import k8smanage.k8smanage.repository.NodeRepository;
import k8smanage.k8smanage.service.SSHService;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class SSHServiceImpl implements SSHService {

    private final NodeRepository nodeRepository;
    private final PasswordEncoder passwordEncoder;

    public SSHServiceImpl(NodeRepository nodeRepository, PasswordEncoder passwordEncoder) {
        this.nodeRepository = nodeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SSHCommandResponse executeCommand(SSHCommandRequest request) {
        long startTime = System.currentTimeMillis();

        // Lấy thông tin node
        NodeEntity node = nodeRepository.findById(request.getNodeId())
                .orElseThrow(() -> new IllegalArgumentException("Node không tồn tại với ID: " + request.getNodeId()));

        SSHClient ssh = new SSHClient();

        try {
            // Cấu hình SSH client
            ssh.addHostKeyVerifier(new PromiscuousVerifier());

            // Kết nối
            ssh.connect(node.getIp(), node.getPort());

            // Xác thực - Lưu ý: password trong DB đã được mã hóa, cần lưu password gốc hoặc giải mã
            // Ở đây ta giả sử password trong entity là plaintext cho mục đích demo
            // Trong production, bạn cần cơ chế lưu password an toàn hơn
            ssh.authPassword(node.getUsername(), node.getPassword());

            // Thực thi lệnh
            Session session = ssh.startSession();
            try {
                Session.Command cmd = session.exec(request.getCommand());

                // Đặt timeout
                int timeout = request.getTimeout() != null ? request.getTimeout() : 30;
                cmd.join(timeout, TimeUnit.SECONDS);

                // Đọc output
                String output = IOUtils.readFully(cmd.getInputStream()).toString();
                String error = IOUtils.readFully(cmd.getErrorStream()).toString();
                Integer exitCode = cmd.getExitStatus();

                long executionTime = System.currentTimeMillis() - startTime;

                return SSHCommandResponse.builder()
                        .success(exitCode == 0)
                        .output(output)
                        .error(error)
                        .exitCode(exitCode)
                        .executionTimeMs(executionTime)
                        .message(exitCode == 0 ? "Lệnh thực thi thành công" : "Lệnh thực thi thất bại")
                        .build();

            } finally {
                session.close();
            }

        } catch (IOException e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return SSHCommandResponse.builder()
                    .success(false)
                    .output("")
                    .error(e.getMessage())
                    .exitCode(-1)
                    .executionTimeMs(executionTime)
                    .message("Lỗi kết nối SSH: " + e.getMessage())
                    .build();
        } finally {
            try {
                ssh.disconnect();
            } catch (IOException e) {
                // Log error
            }
        }
    }

    @Override
    public SSHCommandResponse testConnection(Long nodeId) {
        long startTime = System.currentTimeMillis();

        NodeEntity node = nodeRepository.findById(nodeId)
                .orElseThrow(() -> new IllegalArgumentException("Node không tồn tại với ID: " + nodeId));

        SSHClient ssh = new SSHClient();

        try {
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            ssh.connect(node.getIp(), node.getPort());
            ssh.authPassword(node.getUsername(), node.getPassword());

            long executionTime = System.currentTimeMillis() - startTime;

            return SSHCommandResponse.builder()
                    .success(true)
                    .output("Kết nối thành công")
                    .error("")
                    .exitCode(0)
                    .executionTimeMs(executionTime)
                    .message("Kết nối SSH thành công tới " + node.getIp() + ":" + node.getPort())
                    .build();

        } catch (IOException e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return SSHCommandResponse.builder()
                    .success(false)
                    .output("")
                    .error(e.getMessage())
                    .exitCode(-1)
                    .executionTimeMs(executionTime)
                    .message("Không thể kết nối SSH: " + e.getMessage())
                    .build();
        } finally {
            try {
                ssh.disconnect();
            } catch (IOException e) {
                // Log error
            }
        }
    }

    @Override
    public SSHCommandResponse testConnectionDirect(SSHConnectionRequest request) {
        long startTime = System.currentTimeMillis();

        SSHClient ssh = new SSHClient();

        try {
            ssh.addHostKeyVerifier(new PromiscuousVerifier());
            ssh.connect(request.getIp(), request.getPort());
            ssh.authPassword(request.getUsername(), request.getPassword());

            long executionTime = System.currentTimeMillis() - startTime;

            return SSHCommandResponse.builder()
                    .success(true)
                    .output("Kết nối thành công")
                    .error("")
                    .exitCode(0)
                    .executionTimeMs(executionTime)
                    .message("Kết nối SSH thành công tới " + request.getIp() + ":" + request.getPort())
                    .build();

        } catch (IOException e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return SSHCommandResponse.builder()
                    .success(false)
                    .output("")
                    .error(e.getMessage())
                    .exitCode(-1)
                    .executionTimeMs(executionTime)
                    .message("Không thể kết nối SSH: " + e.getMessage())
                    .build();
        } finally {
            try {
                ssh.disconnect();
            } catch (IOException e) {
                // Log error
            }
        }
    }

    @Override
    public SSHDirectCommandResponse executeDirectCommand(SSHDirectCommandRequest request) {
        long startTime = System.currentTimeMillis();

        SSHClient ssh = new SSHClient();

        try {
            // Cấu hình SSH client
            ssh.addHostKeyVerifier(new PromiscuousVerifier());

            // Kết nối
            ssh.connect(request.getIp(), request.getPort());

            // Xác thực
            ssh.authPassword(request.getUsername(), request.getPassword());

            // Thực thi lệnh
            Session session = ssh.startSession();
            try {
                Session.Command cmd = session.exec(request.getCommand());

                // Đặt timeout
                int timeout = request.getTimeout() != null ? request.getTimeout() : 30;
                cmd.join(timeout, TimeUnit.SECONDS);

                // Đọc output
                String output = IOUtils.readFully(cmd.getInputStream()).toString();
                String error = IOUtils.readFully(cmd.getErrorStream()).toString();
                Integer exitCode = cmd.getExitStatus();

                long executionTime = System.currentTimeMillis() - startTime;

                return SSHDirectCommandResponse.builder()
                        .success(exitCode == 0)
                        .output(output)
                        .error(error)
                        .exitCode(exitCode)
                        .executionTimeMs(executionTime)
                        .message(exitCode == 0 ? "Lệnh thực thi thành công" : "Lệnh thực thi thất bại")
                        .ip(request.getIp())
                        .port(request.getPort())
                        .command(request.getCommand())
                        .build();

            } finally {
                session.close();
            }

        } catch (IOException e) {
            long executionTime = System.currentTimeMillis() - startTime;
            return SSHDirectCommandResponse.builder()
                    .success(false)
                    .output("")
                    .error(e.getMessage())
                    .exitCode(-1)
                    .executionTimeMs(executionTime)
                    .message("Lỗi kết nối SSH: " + e.getMessage())
                    .ip(request.getIp())
                    .port(request.getPort())
                    .command(request.getCommand())
                    .build();
        } finally {
            try {
                ssh.disconnect();
            } catch (IOException e) {
                // Log error
            }
        }
    }
}
