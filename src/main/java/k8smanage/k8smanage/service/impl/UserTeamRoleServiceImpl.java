package k8smanage.k8smanage.service.impl;

import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import k8smanage.k8smanage.repository.UserTeamRoleRepository;
import k8smanage.k8smanage.service.UserTeamRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserTeamRoleServiceImpl implements UserTeamRoleService {

    private final UserTeamRoleRepository userTeamRoleRepository;

    public UserTeamRoleServiceImpl(UserTeamRoleRepository userTeamRoleRepository) {
        this.userTeamRoleRepository = userTeamRoleRepository;
    }

    @Override
    public UserTeamRoleEntity createUserTeamRole(UserTeamRoleEntity userTeamRole) {
        // Kiểm tra roleName đã tồn tại chưa
        if (userTeamRoleRepository.existsByRoleName(userTeamRole.getRoleName())) {
            throw new IllegalArgumentException("Role name đã tồn tại: " + userTeamRole.getRoleName());
        }

        // Validate roleName chỉ cho phép OWNER hoặc MEMBER
        if (!userTeamRole.getRoleName().equals("OWNER") && !userTeamRole.getRoleName().equals("MEMBER")) {
            throw new IllegalArgumentException("Role name chỉ được phép là OWNER hoặc MEMBER");
        }

        return userTeamRoleRepository.save(userTeamRole);
    }

}
