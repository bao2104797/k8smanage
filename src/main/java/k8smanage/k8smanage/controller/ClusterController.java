package k8smanage.k8smanage.controller;

import java.net.URI;
import jakarta.validation.Valid;
import k8smanage.k8smanage.dto.reponse.ClusterResponse;
import k8smanage.k8smanage.dto.request.ClusterCreateRequest;
import k8smanage.k8smanage.service.ClusterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clusters")
public class ClusterController {

    private final ClusterService clusterService;

    public ClusterController(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @PostMapping
    public ResponseEntity<ClusterResponse> create(@Valid @RequestBody ClusterCreateRequest request) {
        ClusterResponse response = clusterService.createClusterFromRequest(request);
        return ResponseEntity.created(URI.create("/api/clusters/" + response.getId())).body(response);
    }

}
