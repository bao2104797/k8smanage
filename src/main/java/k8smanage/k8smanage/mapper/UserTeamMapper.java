package k8smanage.k8smanage.mapper;

import k8smanage.k8smanage.dto.reponse.UserTeamResponse;
import k8smanage.k8smanage.dto.request.UserTeamCreateRequest;
import k8smanage.k8smanage.entity.UserTeamEntity;
import org.springframework.stereotype.Component;

@Component
public class UserTeamMapper {

    public UserTeamEntity toEntity(UserTeamCreateRequest request) {
        return UserTeamEntity.builder()
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .build();
    }

    public UserTeamResponse toResponse(UserTeamEntity entity) {
        return UserTeamResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .username(entity.getUser() != null ? entity.getUser().getUsername() : null)
                .teamId(entity.getTeam() != null ? entity.getTeam().getId() : null)
                .teamName(entity.getTeam() != null ? entity.getTeam().getName() : null)
                .roleInTeam(entity.getUserTeamRole() != null ? entity.getUserTeamRole().getRoleName() : null)
                .isActive(entity.getIsActive())
                .build();
    }
}
