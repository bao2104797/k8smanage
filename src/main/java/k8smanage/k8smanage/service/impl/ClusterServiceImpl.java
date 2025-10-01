package k8smanage.k8smanage.service.impl;

import k8smanage.k8smanage.dto.reponse.ClusterResponse;
import k8smanage.k8smanage.dto.request.ClusterCreateRequest;
import k8smanage.k8smanage.entity.ClusterEntity;
import k8smanage.k8smanage.entity.TeamEntity;
import k8smanage.k8smanage.mapper.ClusterMapper;
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
    private final ClusterMapper clusterMapper;

    public ClusterServiceImpl(ClusterRepository clusterRepository, TeamRepository teamRepository,
                              ClusterMapper clusterMapper) {
        this.clusterRepository = clusterRepository;
        this.teamRepository = teamRepository;
        this.clusterMapper = clusterMapper;
    }

    @Override
    public ClusterResponse createClusterFromRequest(ClusterCreateRequest request) {
        // Kiểm tra tên cluster đã tồn tại chưa
        if (clusterRepository.existsByName(request.getName())) {
            throw new IllegalArgumentException("Tên cluster đã tồn tại: " + request.getName());
        }

        // Kiểm tra team có tồn tại không
        TeamEntity team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team không tồn tại với ID: " + request.getTeamId()));

        // Tạo cluster entity
        ClusterEntity clusterEntity = clusterMapper.toEntity(request);
        clusterEntity.setTeam(team);

        // Lưu cluster
        ClusterEntity savedCluster = clusterRepository.save(clusterEntity);

        return clusterMapper.toResponse(savedCluster);
    }

}
