package k8smanage.k8smanage.controller;

import java.net.URI;
import java.util.List;
import k8smanage.k8smanage.entity.TeamEntity;
import k8smanage.k8smanage.service.TeamService;
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
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamEntity> create(@RequestBody TeamEntity team) {
        TeamEntity created = teamService.createTeam(team);
        return ResponseEntity.created(URI.create("/api/teams/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamEntity> update(@PathVariable Long id, @RequestBody TeamEntity team) {
        return ResponseEntity.ok(teamService.updateTeam(id, team));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamEntity> getById(@PathVariable Long id) {
        return teamService.getTeamById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TeamEntity>> getAll() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
}
