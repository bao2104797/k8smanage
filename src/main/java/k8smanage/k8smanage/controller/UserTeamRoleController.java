package k8smanage.k8smanage.controller;

import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import k8smanage.k8smanage.service.UserTeamRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/user-team-roles")
public class UserTeamRoleController {

    private final UserTeamRoleService userTeamRoleService;

    public UserTeamRoleController(UserTeamRoleService userTeamRoleService) {
        this.userTeamRoleService = userTeamRoleService;
    }

    @PostMapping
    public ResponseEntity<UserTeamRoleEntity> create(@RequestBody UserTeamRoleEntity userTeamRole) {
        UserTeamRoleEntity created = userTeamRoleService.createUserTeamRole(userTeamRole);
        return ResponseEntity.created(URI.create("/api/user-team-roles/" + created.getId())).body(created);
    }

}
