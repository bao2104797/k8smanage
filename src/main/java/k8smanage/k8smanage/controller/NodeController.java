package k8smanage.k8smanage.controller;

import java.net.URI;
import java.util.List;
import k8smanage.k8smanage.entity.NodeEntity;
import k8smanage.k8smanage.service.NodeService;
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
@RequestMapping("/api/nodes")
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping
    public ResponseEntity<NodeEntity> create(@RequestBody NodeEntity node) {
        NodeEntity created = nodeService.createNode(node);
        return ResponseEntity.created(URI.create("/api/nodes/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NodeEntity> update(@PathVariable Long id, @RequestBody NodeEntity node) {
        return ResponseEntity.ok(nodeService.updateNode(id, node));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nodeService.deleteNode(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NodeEntity> getById(@PathVariable Long id) {
        return nodeService.getNodeById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cluster/{clusterId}")
    public ResponseEntity<List<NodeEntity>> getByClusterId(@PathVariable Long clusterId) {
        return ResponseEntity.ok(nodeService.getNodesByClusterId(clusterId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NodeEntity>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(nodeService.getNodesByStatus(status));
    }

    @GetMapping
    public ResponseEntity<List<NodeEntity>> getAll() {
        return ResponseEntity.ok(nodeService.getAllNodes());
    }
}
