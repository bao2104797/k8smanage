package k8smanage.k8smanage.service.impl;

import k8smanage.k8smanage.dto.reponse.UserTeamResponse;
import k8smanage.k8smanage.dto.request.UserTeamCreateRequest;
import k8smanage.k8smanage.entity.TeamEntity;
import k8smanage.k8smanage.entity.UserEntity;
import k8smanage.k8smanage.entity.UserTeamEntity;
import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import k8smanage.k8smanage.mapper.UserTeamMapper;
import k8smanage.k8smanage.repository.TeamRepository;
import k8smanage.k8smanage.repository.UserRepository;
import k8smanage.k8smanage.repository.UserTeamRepository;
import k8smanage.k8smanage.repository.UserTeamRoleRepository;
import k8smanage.k8smanage.service.UserTeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserTeamServiceImpl implements UserTeamService {

    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final UserTeamRoleRepository userTeamRoleRepository;
    private final UserTeamMapper userTeamMapper;

    public UserTeamServiceImpl(UserTeamRepository userTeamRepository, UserRepository userRepository,
                               TeamRepository teamRepository, UserTeamRoleRepository userTeamRoleRepository,
                               UserTeamMapper userTeamMapper) {
        this.userTeamRepository = userTeamRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.userTeamRoleRepository = userTeamRoleRepository;
        this.userTeamMapper = userTeamMapper;
    }

    @Override
    public UserTeamResponse addUserToTeamFromRequest(UserTeamCreateRequest request) {
        // Kiểm tra user tồn tại
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại với ID: " + request.getUserId()));

        // Kiểm tra team tồn tại
        TeamEntity team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team không tồn tại với ID: " + request.getTeamId()));

        // Kiểm tra user đã là thành viên của team chưa
        if (userTeamRepository.existsByUserIdAndTeamId(request.getUserId(), request.getTeamId())) {
            throw new IllegalArgumentException("User đã là thành viên của team này");
        }

        // Validate roleName phải là OWNER hoặc MEMBER
        if (!request.getRoleName().equals("OWNER") && !request.getRoleName().equals("MEMBER")) {
            throw new IllegalArgumentException("Role name phải là OWNER hoặc MEMBER");
        }

        // Tìm UserTeamRole từ database
        UserTeamRoleEntity role = userTeamRoleRepository.findByRoleName(request.getRoleName())
                .orElseThrow(() -> new IllegalArgumentException("Role không tồn tại trong hệ thống: " + request.getRoleName()));

        // Tạo UserTeam entity
        UserTeamEntity userTeam = userTeamMapper.toEntity(request);
        userTeam.setUser(user);
        userTeam.setTeam(team);
        userTeam.setUserTeamRole(role);

        // Lưu vào database
        UserTeamEntity saved = userTeamRepository.save(userTeam);

        return userTeamMapper.toResponse(saved);
    }

}
