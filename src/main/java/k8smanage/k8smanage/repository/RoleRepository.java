package k8smanage.k8smanage.repository;

import java.util.Optional;
import k8smanage.k8smanage.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByCode(String code);
    boolean existsByCode(String code);
}


