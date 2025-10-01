package k8smanage.k8smanage.service;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.UserTeamEntity;

public interface UserTeamService {
    UserTeamEntity addUserToTeam(UserTeamEntity userTeam);
    UserTeamEntity updateUserTeam(Long id, UserTeamEntity userTeam);
    void removeUserFromTeam(Long id);
    Optional<UserTeamEntity> getUserTeamById(Long id);
    Optional<UserTeamEntity> getUserTeamByUserIdAndTeamId(Long userId, Long teamId);
    List<UserTeamEntity> getUserTeamsByUserId(Long userId);
    List<UserTeamEntity> getUserTeamsByTeamId(Long teamId);
    List<UserTeamEntity> getAllUserTeams();
}
