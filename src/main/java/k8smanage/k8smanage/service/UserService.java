package k8smanage.k8smanage.service;

import k8smanage.k8smanage.dto.reponse.LoginResponse;
import k8smanage.k8smanage.dto.reponse.UserResponse;
import k8smanage.k8smanage.dto.request.LoginRequest;
import k8smanage.k8smanage.dto.request.UserCreateRequest;

public interface UserService {
    UserResponse createUserFromRequest(UserCreateRequest request);

    LoginResponse login(LoginRequest request);
}


