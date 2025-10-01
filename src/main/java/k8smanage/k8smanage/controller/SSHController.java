package k8smanage.k8smanage.controller;

import jakarta.validation.Valid;
import k8smanage.k8smanage.dto.reponse.SSHCommandResponse;
import k8smanage.k8smanage.dto.reponse.SSHDirectCommandResponse;
import k8smanage.k8smanage.dto.request.SSHCommandRequest;
import k8smanage.k8smanage.dto.request.SSHConnectionRequest;
import k8smanage.k8smanage.dto.request.SSHDirectCommandRequest;
import k8smanage.k8smanage.service.SSHService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ssh")
public class SSHController {

    private final SSHService sshService;

    public SSHController(SSHService sshService) {
        this.sshService = sshService;
    }

    @PostMapping("/execute")
    public ResponseEntity<SSHCommandResponse> executeCommand(@Valid @RequestBody SSHCommandRequest request) {
        SSHCommandResponse response = sshService.executeCommand(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test/{nodeId}")
    public ResponseEntity<SSHCommandResponse> testConnection(@PathVariable Long nodeId) {
        SSHCommandResponse response = sshService.testConnection(nodeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test-direct")
    public ResponseEntity<SSHCommandResponse> testConnectionDirect(@Valid @RequestBody SSHConnectionRequest request) {
        SSHCommandResponse response = sshService.testConnectionDirect(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/execute-direct")
    public ResponseEntity<SSHDirectCommandResponse> executeDirectCommand(@Valid @RequestBody SSHDirectCommandRequest request) {
        SSHDirectCommandResponse response = sshService.executeDirectCommand(request);
        return ResponseEntity.ok(response);
    }
}
