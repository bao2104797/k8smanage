package k8smanage.k8smanage.repository;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, Long> {
    Optional<NodeEntity> findByName(String name);
    Optional<NodeEntity> findByIp(String ip);
    List<NodeEntity> findByClusterId(Long clusterId);
    List<NodeEntity> findByStatus(String status);
    boolean existsByName(String name);
    boolean existsByIp(String ip);
}
