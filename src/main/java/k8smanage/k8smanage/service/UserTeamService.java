package k8smanage.k8smanage.service;

import k8smanage.k8smanage.dto.reponse.UserTeamResponse;
import k8smanage.k8smanage.dto.request.UserTeamCreateRequest;

public interface UserTeamService {
    UserTeamResponse addUserToTeamFromRequest(UserTeamCreateRequest request);
}
