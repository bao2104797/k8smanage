package k8smanage.k8smanage.repository;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.ClusterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClusterRepository extends JpaRepository<ClusterEntity, Long> {
    Optional<ClusterEntity> findByName(String name);
    List<ClusterEntity> findByTeamId(Long teamId);
    List<ClusterEntity> findByStatus(String status);
    boolean existsByName(String name);
}
