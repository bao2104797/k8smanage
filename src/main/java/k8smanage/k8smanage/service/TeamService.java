package k8smanage.k8smanage.service;

import k8smanage.k8smanage.dto.reponse.TeamResponse;
import k8smanage.k8smanage.dto.request.TeamCreateRequest;

public interface TeamService {
    TeamResponse createTeamFromRequest(TeamCreateRequest request);
}
