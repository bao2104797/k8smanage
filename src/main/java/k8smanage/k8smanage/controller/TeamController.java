package k8smanage.k8smanage.controller;

import jakarta.validation.Valid;
import k8smanage.k8smanage.dto.reponse.TeamResponse;
import k8smanage.k8smanage.dto.request.TeamCreateRequest;
import k8smanage.k8smanage.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamCreateRequest request) {
        TeamResponse response = teamService.createTeamFromRequest(request);
        return ResponseEntity.created(URI.create("/api/teams/" + response.getId())).body(response);
    }

}
