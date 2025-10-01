package k8smanage.k8smanage.service.impl;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.ClusterEntity;
import k8smanage.k8smanage.entity.NodeEntity;
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

    public NodeServiceImpl(NodeRepository nodeRepository, ClusterRepository clusterRepository, PasswordEncoder passwordEncoder) {
        this.nodeRepository = nodeRepository;
        this.clusterRepository = clusterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public NodeEntity createNode(NodeEntity node) {
        if (nodeRepository.existsByName(node.getName())) {
            throw new IllegalArgumentException("Tên node đã tồn tại: " + node.getName());
        }
        
        if (nodeRepository.existsByIp(node.getIp())) {
            throw new IllegalArgumentException("IP đã tồn tại: " + node.getIp());
        }
        
        // Kiểm tra cluster tồn tại
        if (node.getCluster() != null && node.getCluster().getId() != null) {
            ClusterEntity cluster = clusterRepository.findById(node.getCluster().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cluster không tồn tại với ID: " + node.getCluster().getId()));
            node.setCluster(cluster);
        }
        
        // Mã hóa password trước khi lưu
        if (node.getPassword() != null && !node.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(node.getPassword());
            node.setPassword(encodedPassword);
        }
        
        return nodeRepository.save(node);
    }

    @Override
    public NodeEntity updateNode(Long id, NodeEntity node) {
        NodeEntity existing = nodeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Node không tồn tại với ID: " + id));
        
        existing.setName(node.getName());
        existing.setIp(node.getIp());
        existing.setPort(node.getPort());
        existing.setUsername(node.getUsername());
        existing.setStatus(node.getStatus());
        existing.setDescription(node.getDescription());
        
        // Cập nhật password nếu có
        if (node.getPassword() != null && !node.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(node.getPassword());
            existing.setPassword(encodedPassword);
        }
        
        // Cập nhật cluster nếu có
        if (node.getCluster() != null && node.getCluster().getId() != null) {
            ClusterEntity cluster = clusterRepository.findById(node.getCluster().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cluster không tồn tại với ID: " + node.getCluster().getId()));
            existing.setCluster(cluster);
        }
        
        return nodeRepository.save(existing);
    }

    @Override
    public void deleteNode(Long id) {
        nodeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NodeEntity> getNodeById(Long id) {
        return nodeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NodeEntity> getNodeByName(String name) {
        return nodeRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NodeEntity> getNodeByIp(String ip) {
        return nodeRepository.findByIp(ip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NodeEntity> getNodesByClusterId(Long clusterId) {
        return nodeRepository.findByClusterId(clusterId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NodeEntity> getNodesByStatus(String status) {
        return nodeRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NodeEntity> getAllNodes() {
        return nodeRepository.findAll();
    }
}
