package k8smanage.k8smanage.service.impl;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.TeamEntity;
import k8smanage.k8smanage.entity.UserEntity;
import k8smanage.k8smanage.entity.UserTeamEntity;
import k8smanage.k8smanage.repository.TeamRepository;
import k8smanage.k8smanage.repository.UserRepository;
import k8smanage.k8smanage.repository.UserTeamRepository;
import k8smanage.k8smanage.service.UserTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserTeamServiceImpl implements UserTeamService {

    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    public UserTeamServiceImpl(UserTeamRepository userTeamRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.userTeamRepository = userTeamRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public UserTeamEntity addUserToTeam(UserTeamEntity userTeam) {
        // Kiểm tra user và team tồn tại
        UserEntity user = userRepository.findById(userTeam.getUser().getId())
            .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        TeamEntity team = teamRepository.findById(userTeam.getTeam().getId())
            .orElseThrow(() -> new IllegalArgumentException("Team không tồn tại"));
        
        // Kiểm tra đã tồn tại chưa
        if (userTeamRepository.existsByUserIdAndTeamId(user.getId(), team.getId())) {
            throw new IllegalArgumentException("User đã là thành viên của team này");
        }
        
        userTeam.setUser(user);
        userTeam.setTeam(team);
        return userTeamRepository.save(userTeam);
    }

    @Override
    public UserTeamEntity updateUserTeam(Long id, UserTeamEntity userTeam) {
        UserTeamEntity existing = userTeamRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("UserTeam không tồn tại với ID: " + id));
        existing.setRoleInTeam(userTeam.getRoleInTeam());
        existing.setIsActive(userTeam.getIsActive());
        return userTeamRepository.save(existing);
    }

    @Override
    public void removeUserFromTeam(Long id) {
        userTeamRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserTeamEntity> getUserTeamById(Long id) {
        return userTeamRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserTeamEntity> getUserTeamByUserIdAndTeamId(Long userId, Long teamId) {
        return userTeamRepository.findByUserIdAndTeamId(userId, teamId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTeamEntity> getUserTeamsByUserId(Long userId) {
        return userTeamRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTeamEntity> getUserTeamsByTeamId(Long teamId) {
        return userTeamRepository.findByTeamId(teamId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTeamEntity> getAllUserTeams() {
        return userTeamRepository.findAll();
    }
}
