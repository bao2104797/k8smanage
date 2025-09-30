package k8smanage.k8smanage.service.impl;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.ClusterEntity;
import k8smanage.k8smanage.entity.TeamEntity;
import k8smanage.k8smanage.repository.ClusterRepository;
import k8smanage.k8smanage.repository.TeamRepository;
import k8smanage.k8smanage.service.ClusterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClusterServiceImpl implements ClusterService {

    private final ClusterRepository clusterRepository;
    private final TeamRepository teamRepository;

    public ClusterServiceImpl(ClusterRepository clusterRepository, TeamRepository teamRepository) {
        this.clusterRepository = clusterRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public ClusterEntity createCluster(ClusterEntity cluster) {
        if (clusterRepository.existsByName(cluster.getName())) {
            throw new IllegalArgumentException("Tên cluster đã tồn tại: " + cluster.getName());
        }
        
        // Kiểm tra team tồn tại
        if (cluster.getTeam() != null && cluster.getTeam().getId() != null) {
            TeamEntity team = teamRepository.findById(cluster.getTeam().getId())
                .orElseThrow(() -> new IllegalArgumentException("Team không tồn tại với ID: " + cluster.getTeam().getId()));
            cluster.setTeam(team);
        }
        
        return clusterRepository.save(cluster);
    }

    @Override
    public ClusterEntity updateCluster(Long id, ClusterEntity cluster) {
        ClusterEntity existing = clusterRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cluster không tồn tại với ID: " + id));
        
        existing.setName(cluster.getName());
        existing.setDescription(cluster.getDescription());
        existing.setStatus(cluster.getStatus());
        
        // Cập nhật team nếu có
        if (cluster.getTeam() != null && cluster.getTeam().getId() != null) {
            TeamEntity team = teamRepository.findById(cluster.getTeam().getId())
                .orElseThrow(() -> new IllegalArgumentException("Team không tồn tại với ID: " + cluster.getTeam().getId()));
            existing.setTeam(team);
        }
        
        return clusterRepository.save(existing);
    }

    @Override
    public void deleteCluster(Long id) {
        clusterRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClusterEntity> getClusterById(Long id) {
        return clusterRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClusterEntity> getClusterByName(String name) {
        return clusterRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClusterEntity> getClustersByTeamId(Long teamId) {
        return clusterRepository.findByTeamId(teamId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClusterEntity> getClustersByStatus(String status) {
        return clusterRepository.findByStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClusterEntity> getAllClusters() {
        return clusterRepository.findAll();
    }
}
