package k8smanage.k8smanage.service;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.dto.reponse.RoleResponse;
import k8smanage.k8smanage.dto.request.RoleCreateRequest;
import k8smanage.k8smanage.entity.RoleEntity;

public interface RoleService {
    RoleResponse createRoleFromRequest(RoleCreateRequest request);
    RoleEntity createRole(RoleEntity role);
    RoleEntity updateRole(Long id, RoleEntity role);
    void deleteRole(Long id);
    Optional<RoleEntity> getRoleById(Long id);
    Optional<RoleEntity> getRoleByCode(String code);
    List<RoleEntity> getAllRoles();
}


