package k8smanage.k8smanage.controller;

import jakarta.validation.Valid;
import k8smanage.k8smanage.dto.reponse.RoleResponse;
import k8smanage.k8smanage.dto.request.RoleCreateRequest;
import k8smanage.k8smanage.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@Valid @RequestBody RoleCreateRequest request) {
        RoleResponse response = roleService.createRoleFromRequest(request);
        return ResponseEntity.created(URI.create("/api/roles/" + response.getId())).body(response);
    }

}
