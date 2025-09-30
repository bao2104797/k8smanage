package k8smanage.k8smanage.mapper;

import k8smanage.k8smanage.dto.reponse.RoleResponse;
import k8smanage.k8smanage.dto.request.RoleCreateRequest;
import k8smanage.k8smanage.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleEntity toEntity(RoleCreateRequest request) {
        if (request == null) {
            return null;
        }
        return RoleEntity.builder()
            .code(request.getCode())
            .name(request.getName())
            .build();
    }

    public RoleResponse toResponse(RoleEntity entity) {
        if (entity == null) {
            return null;
        }
        return RoleResponse.builder()
            .id(entity.getId())
            .code(entity.getCode())
            .name(entity.getName())
            .build();
    }
}
