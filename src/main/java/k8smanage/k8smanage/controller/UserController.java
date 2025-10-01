package k8smanage.k8smanage.controller;

import jakarta.validation.Valid;
import k8smanage.k8smanage.dto.reponse.LoginResponse;
import k8smanage.k8smanage.dto.reponse.UserResponse;
import k8smanage.k8smanage.dto.request.LoginRequest;
import k8smanage.k8smanage.dto.request.UserCreateRequest;
import k8smanage.k8smanage.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreateRequest request) {
        UserResponse response = userService.createUserFromRequest(request);
        return ResponseEntity.created(URI.create("/api/users/" + response.getId())).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

}


