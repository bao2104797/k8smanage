package k8smanage.k8smanage.service.impl;

import k8smanage.k8smanage.dto.reponse.TeamResponse;
import k8smanage.k8smanage.dto.request.TeamCreateRequest;
import k8smanage.k8smanage.entity.TeamEntity;
import k8smanage.k8smanage.entity.UserEntity;
import k8smanage.k8smanage.entity.UserTeamEntity;
import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import k8smanage.k8smanage.mapper.TeamMapper;
import k8smanage.k8smanage.repository.TeamRepository;
import k8smanage.k8smanage.repository.UserRepository;
import k8smanage.k8smanage.repository.UserTeamRepository;
import k8smanage.k8smanage.repository.UserTeamRoleRepository;
import k8smanage.k8smanage.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final UserTeamRepository userTeamRepository;
    private final UserTeamRoleRepository userTeamRoleRepository;
    private final TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, UserRepository userRepository,
                           UserTeamRepository userTeamRepository, UserTeamRoleRepository userTeamRoleRepository,
                           TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.userTeamRepository = userTeamRepository;
        this.userTeamRoleRepository = userTeamRoleRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamResponse createTeamFromRequest(TeamCreateRequest request) {
        // Kiểm tra user có tồn tại không
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại với ID: " + request.getUserId()));

        // Tạo team
        TeamEntity teamEntity = teamMapper.toEntity(request);
        TeamEntity savedTeam = teamRepository.save(teamEntity);

        // Tìm hoặc tạo UserTeamRole OWNER
        UserTeamRoleEntity ownerRole = userTeamRoleRepository.findByRoleName("OWNER")
                .orElseGet(() -> {
                    UserTeamRoleEntity newRole = UserTeamRoleEntity.builder()
                            .roleName("OWNER")
                            .description("Owner of the team")
                            .build();
                    return userTeamRoleRepository.save(newRole);
                });

        // Tạo UserTeam với user là OWNER
        UserTeamEntity userTeam = UserTeamEntity.builder()
                .user(user)
                .team(savedTeam)
                .userTeamRole(ownerRole)
                .isActive(true)
                .build();
        userTeamRepository.save(userTeam);

        return teamMapper.toResponse(savedTeam);
    }

}
