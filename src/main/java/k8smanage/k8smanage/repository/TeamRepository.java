package k8smanage.k8smanage.repository;

import java.util.Optional;
import k8smanage.k8smanage.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    Optional<TeamEntity> findByName(String name);
    boolean existsByName(String name);
}
