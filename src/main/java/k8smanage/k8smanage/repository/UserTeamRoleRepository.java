package k8smanage.k8smanage.repository;

import java.util.Optional;
import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamRoleRepository extends JpaRepository<UserTeamRoleEntity, Long> {
    Optional<UserTeamRoleEntity> findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
}
