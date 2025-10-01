package k8smanage.k8smanage.service;

import k8smanage.k8smanage.dto.reponse.RoleResponse;
import k8smanage.k8smanage.dto.request.RoleCreateRequest;

public interface RoleService {
    RoleResponse createRoleFromRequest(RoleCreateRequest request);
}


