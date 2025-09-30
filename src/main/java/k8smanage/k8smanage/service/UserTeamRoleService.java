package k8smanage.k8smanage.service;

import java.util.Optional;
import k8smanage.k8smanage.entity.UserTeamRoleEntity;

public interface UserTeamRoleService {
    UserTeamRoleEntity createUserTeamRole(UserTeamRoleEntity userTeamRole);
    UserTeamRoleEntity updateUserTeamRole(Long id, UserTeamRoleEntity userTeamRole);
    void deleteUserTeamRole(Long id);
    Optional<UserTeamRoleEntity> getUserTeamRoleById(Long id);
    Optional<UserTeamRoleEntity> getUserTeamRoleByUserTeamId(Long userTeamId);
}
