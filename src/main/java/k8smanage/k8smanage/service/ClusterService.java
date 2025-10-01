package k8smanage.k8smanage.service;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.ClusterEntity;

public interface ClusterService {
    ClusterEntity createCluster(ClusterEntity cluster);
    ClusterEntity updateCluster(Long id, ClusterEntity cluster);
    void deleteCluster(Long id);
    Optional<ClusterEntity> getClusterById(Long id);
    Optional<ClusterEntity> getClusterByName(String name);
    List<ClusterEntity> getClustersByTeamId(Long teamId);
    List<ClusterEntity> getClustersByStatus(String status);
    List<ClusterEntity> getAllClusters();
}
