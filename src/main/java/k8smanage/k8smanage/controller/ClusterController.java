package k8smanage.k8smanage.controller;

import java.net.URI;
import java.util.List;
import k8smanage.k8smanage.entity.ClusterEntity;
import k8smanage.k8smanage.service.ClusterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clusters")
public class ClusterController {

    private final ClusterService clusterService;

    public ClusterController(ClusterService clusterService) {
        this.clusterService = clusterService;
    }

    @PostMapping
    public ResponseEntity<ClusterEntity> create(@RequestBody ClusterEntity cluster) {
        ClusterEntity created = clusterService.createCluster(cluster);
        return ResponseEntity.created(URI.create("/api/clusters/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClusterEntity> update(@PathVariable Long id, @RequestBody ClusterEntity cluster) {
        return ResponseEntity.ok(clusterService.updateCluster(id, cluster));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clusterService.deleteCluster(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClusterEntity> getById(@PathVariable Long id) {
        return clusterService.getClusterById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<ClusterEntity>> getByTeamId(@PathVariable Long teamId) {
        return ResponseEntity.ok(clusterService.getClustersByTeamId(teamId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ClusterEntity>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(clusterService.getClustersByStatus(status));
    }

    @GetMapping
    public ResponseEntity<List<ClusterEntity>> getAll() {
        return ResponseEntity.ok(clusterService.getAllClusters());
    }
}
