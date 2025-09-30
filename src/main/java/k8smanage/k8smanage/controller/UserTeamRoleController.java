package k8smanage.k8smanage.controller;

import java.net.URI;
import k8smanage.k8smanage.entity.UserTeamRoleEntity;
import k8smanage.k8smanage.service.UserTeamRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("/{id}")
    public ResponseEntity<UserTeamRoleEntity> update(@PathVariable Long id, @RequestBody UserTeamRoleEntity userTeamRole) {
        return ResponseEntity.ok(userTeamRoleService.updateUserTeamRole(id, userTeamRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userTeamRoleService.deleteUserTeamRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTeamRoleEntity> getById(@PathVariable Long id) {
        return userTeamRoleService.getUserTeamRoleById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user-team/{userTeamId}")
    public ResponseEntity<UserTeamRoleEntity> getByUserTeamId(@PathVariable Long userTeamId) {
        return userTeamRoleService.getUserTeamRoleByUserTeamId(userTeamId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
