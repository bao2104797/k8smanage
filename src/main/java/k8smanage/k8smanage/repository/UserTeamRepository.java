package k8smanage.k8smanage.repository;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeamEntity, Long> {
    Optional<UserTeamEntity> findByUserIdAndTeamId(Long userId, Long teamId);
    List<UserTeamEntity> findByUserId(Long userId);
    List<UserTeamEntity> findByTeamId(Long teamId);
    boolean existsByUserIdAndTeamId(Long userId, Long teamId);
}
