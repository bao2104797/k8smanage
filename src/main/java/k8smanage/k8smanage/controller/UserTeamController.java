package k8smanage.k8smanage.controller;

import java.net.URI;
import java.util.List;
import k8smanage.k8smanage.entity.UserTeamEntity;
import k8smanage.k8smanage.service.UserTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-teams")
public class UserTeamController {

    private final UserTeamService userTeamService;

    public UserTeamController(UserTeamService userTeamService) {
        this.userTeamService = userTeamService;
    }

    @PostMapping
    public ResponseEntity<UserTeamEntity> addUserToTeam(@RequestBody UserTeamEntity userTeam) {
        UserTeamEntity created = userTeamService.addUserToTeam(userTeam);
        return ResponseEntity.created(URI.create("/api/user-teams/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTeamEntity> update(@PathVariable Long id, @RequestBody UserTeamEntity userTeam) {
        return ResponseEntity.ok(userTeamService.updateUserTeam(id, userTeam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUserFromTeam(@PathVariable Long id) {
        userTeamService.removeUserFromTeam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTeamEntity> getById(@PathVariable Long id) {
        return userTeamService.getUserTeamById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserTeamEntity>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userTeamService.getUserTeamsByUserId(userId));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<UserTeamEntity>> getByTeamId(@PathVariable Long teamId) {
        return ResponseEntity.ok(userTeamService.getUserTeamsByTeamId(teamId));
    }

    @GetMapping("/search")
    public ResponseEntity<UserTeamEntity> getByUserAndTeam(
        @RequestParam Long userId, 
        @RequestParam Long teamId) {
        return userTeamService.getUserTeamByUserIdAndTeamId(userId, teamId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserTeamEntity>> getAll() {
        return ResponseEntity.ok(userTeamService.getAllUserTeams());
    }
}
