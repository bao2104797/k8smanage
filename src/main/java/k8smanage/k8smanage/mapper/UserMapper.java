package k8smanage.k8smanage.mapper;

import k8smanage.k8smanage.dto.reponse.UserResponse;
import k8smanage.k8smanage.dto.request.UserCreateRequest;
import k8smanage.k8smanage.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserEntity toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }
        return UserEntity.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .fullName(request.getFullName())
            .build();
    }

    public UserResponse toResponse(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return UserResponse.builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .fullName(entity.getFullName())
            .role(entity.getRole() != null ? roleMapper.toResponse(entity.getRole()) : null)
            .build();
    }
}
