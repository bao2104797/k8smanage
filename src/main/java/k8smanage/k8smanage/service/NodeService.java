package k8smanage.k8smanage.service;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.NodeEntity;

public interface NodeService {
    NodeEntity createNode(NodeEntity node);
    NodeEntity updateNode(Long id, NodeEntity node);
    void deleteNode(Long id);
    Optional<NodeEntity> getNodeById(Long id);
    Optional<NodeEntity> getNodeByName(String name);
    Optional<NodeEntity> getNodeByIp(String ip);
    List<NodeEntity> getNodesByClusterId(Long clusterId);
    List<NodeEntity> getNodesByStatus(String status);
    List<NodeEntity> getAllNodes();
}
