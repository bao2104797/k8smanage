package k8smanage.k8smanage.controller;

import jakarta.validation.Valid;
import k8smanage.k8smanage.dto.reponse.UserTeamResponse;
import k8smanage.k8smanage.dto.request.UserTeamCreateRequest;
import k8smanage.k8smanage.service.UserTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/user-teams")
public class UserTeamController {

    private final UserTeamService userTeamService;

    public UserTeamController(UserTeamService userTeamService) {
        this.userTeamService = userTeamService;
    }

    @PostMapping
    public ResponseEntity<UserTeamResponse> addUserToTeam(@Valid @RequestBody UserTeamCreateRequest request) {
        UserTeamResponse response = userTeamService.addUserToTeamFromRequest(request);
        return ResponseEntity.created(URI.create("/api/user-teams/" + response.getId())).body(response);
    }

}
