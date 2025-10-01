package k8smanage.k8smanage.mapper;

import k8smanage.k8smanage.dto.reponse.NodeResponse;
import k8smanage.k8smanage.dto.request.NodeCreateRequest;
import k8smanage.k8smanage.entity.NodeEntity;
import org.springframework.stereotype.Component;

@Component
public class NodeMapper {

    public NodeEntity toEntity(NodeCreateRequest request) {
        return NodeEntity.builder()
                .name(request.getName())
                .type(request.getType())
                .ip(request.getIp())
                .port(request.getPort())
                .username(request.getUsername())
                .password(request.getPassword())
                .description(request.getDescription())
                .status("ACTIVE") // Default status
                .build();
    }

    public NodeResponse toResponse(NodeEntity entity) {
        return NodeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .ip(entity.getIp())
                .port(entity.getPort())
                .username(entity.getUsername())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .clusterId(entity.getCluster() != null ? entity.getCluster().getId() : null)
                .clusterName(entity.getCluster() != null ? entity.getCluster().getName() : null)
                .build();
    }
}

