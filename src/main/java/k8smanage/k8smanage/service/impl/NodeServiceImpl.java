package k8smanage.k8smanage.service.impl;

import k8smanage.k8smanage.dto.reponse.NodeResponse;
import k8smanage.k8smanage.dto.request.NodeCreateRequest;
import k8smanage.k8smanage.entity.ClusterEntity;
import k8smanage.k8smanage.entity.NodeEntity;
import k8smanage.k8smanage.mapper.NodeMapper;
import k8smanage.k8smanage.repository.ClusterRepository;
import k8smanage.k8smanage.repository.NodeRepository;
import k8smanage.k8smanage.service.NodeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;
    private final ClusterRepository clusterRepository;
    private final PasswordEncoder passwordEncoder;
    private final NodeMapper nodeMapper;

    public NodeServiceImpl(NodeRepository nodeRepository, ClusterRepository clusterRepository,
                           PasswordEncoder passwordEncoder, NodeMapper nodeMapper) {
        this.nodeRepository = nodeRepository;
        this.clusterRepository = clusterRepository;
        this.passwordEncoder = passwordEncoder;
        this.nodeMapper = nodeMapper;
    }

    @Override
    public NodeResponse createNodeFromRequest(NodeCreateRequest request) {
        // Kiểm tra IP đã tồn tại chưa
        if (nodeRepository.existsByIp(request.getIp())) {
            throw new IllegalArgumentException("IP đã tồn tại: " + request.getIp());
        }

        // Kiểm tra cluster có tồn tại không
        ClusterEntity cluster = clusterRepository.findById(request.getClusterId())
                .orElseThrow(() -> new IllegalArgumentException("Cluster không tồn tại với ID: " + request.getClusterId()));

        // Tạo node entity
        NodeEntity nodeEntity = nodeMapper.toEntity(request);
        nodeEntity.setCluster(cluster);

        // Mã hóa password trước khi lưu
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            nodeEntity.setPassword(encodedPassword);
        }

        // Lưu node
        NodeEntity savedNode = nodeRepository.save(nodeEntity);

        return nodeMapper.toResponse(savedNode);
    }

}
