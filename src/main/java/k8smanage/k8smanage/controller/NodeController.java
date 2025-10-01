package k8smanage.k8smanage.controller;

import java.net.URI;
import jakarta.validation.Valid;
import k8smanage.k8smanage.dto.reponse.NodeResponse;
import k8smanage.k8smanage.dto.request.NodeCreateRequest;
import k8smanage.k8smanage.service.NodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nodes")
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping
    public ResponseEntity<NodeResponse> create(@Valid @RequestBody NodeCreateRequest request) {
        NodeResponse response = nodeService.createNodeFromRequest(request);
        return ResponseEntity.created(URI.create("/api/nodes/" + response.getId())).body(response);
    }

}
