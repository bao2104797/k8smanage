package k8smanage.k8smanage.service.impl;

import java.util.Optional;
import k8smanage.k8smanage.entity.UserTeamEntity;
import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import k8smanage.k8smanage.repository.UserTeamRepository;
import k8smanage.k8smanage.repository.UserTeamRoleRepository;
import k8smanage.k8smanage.service.UserTeamRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserTeamRoleServiceImpl implements UserTeamRoleService {

    private final UserTeamRoleRepository userTeamRoleRepository;
    private final UserTeamRepository userTeamRepository;

    public UserTeamRoleServiceImpl(UserTeamRoleRepository userTeamRoleRepository, UserTeamRepository userTeamRepository) {
        this.userTeamRoleRepository = userTeamRoleRepository;
        this.userTeamRepository = userTeamRepository;
    }

    @Override
    public UserTeamRoleEntity createUserTeamRole(UserTeamRoleEntity userTeamRole) {
        UserTeamEntity userTeam = userTeamRepository.findById(userTeamRole.getUserTeam().getId())
            .orElseThrow(() -> new IllegalArgumentException("UserTeam không tồn tại"));
        userTeamRole.setUserTeam(userTeam);
        return userTeamRoleRepository.save(userTeamRole);
    }

    @Override
    public UserTeamRoleEntity updateUserTeamRole(Long id, UserTeamRoleEntity userTeamRole) {
        UserTeamRoleEntity existing = userTeamRoleRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("UserTeamRole không tồn tại với ID: " + id));
        existing.setDescription(userTeamRole.getDescription());
        return userTeamRoleRepository.save(existing);
    }

    @Override
    public void deleteUserTeamRole(Long id) {
        userTeamRoleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserTeamRoleEntity> getUserTeamRoleById(Long id) {
        return userTeamRoleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserTeamRoleEntity> getUserTeamRoleByUserTeamId(Long userTeamId) {
        return userTeamRoleRepository.findByUserTeamId(userTeamId);
    }
}
