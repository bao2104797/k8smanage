package k8smanage.k8smanage.mapper;

import k8smanage.k8smanage.dto.reponse.TeamResponse;
import k8smanage.k8smanage.dto.request.TeamCreateRequest;
import k8smanage.k8smanage.entity.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public TeamEntity toEntity(TeamCreateRequest request) {
        return TeamEntity.builder()
            .name(request.getName())
            .description(request.getDescription())
            .build();
    }

    public TeamResponse toResponse(TeamEntity entity) {
        return TeamResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .description(entity.getDescription())
            .build();
    }
}
