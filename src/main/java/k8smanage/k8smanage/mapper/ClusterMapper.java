package k8smanage.k8smanage.mapper;

import k8smanage.k8smanage.dto.reponse.ClusterResponse;
import k8smanage.k8smanage.dto.request.ClusterCreateRequest;
import k8smanage.k8smanage.entity.ClusterEntity;
import org.springframework.stereotype.Component;

@Component
public class ClusterMapper {

    public ClusterEntity toEntity(ClusterCreateRequest request) {
        return ClusterEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .status("ACTIVE")
                .build();
    }

    public ClusterResponse toResponse(ClusterEntity entity) {
        return ClusterResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .teamId(entity.getTeam() != null ? entity.getTeam().getId() : null)
                .teamName(entity.getTeam() != null ? entity.getTeam().getName() : null)
                .build();
    }
}
