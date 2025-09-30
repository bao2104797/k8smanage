package k8smanage.k8smanage.service.impl;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.TeamEntity;
import k8smanage.k8smanage.repository.TeamRepository;
import k8smanage.k8smanage.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamEntity createTeam(TeamEntity team) {
        if (teamRepository.existsByName(team.getName())) {
            throw new IllegalArgumentException("Tên team đã tồn tại: " + team.getName());
        }
        return teamRepository.save(team);
    }

    @Override
    public TeamEntity updateTeam(Long id, TeamEntity team) {
        TeamEntity existing = teamRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Team không tồn tại với ID: " + id));
        existing.setName(team.getName());
        existing.setDescription(team.getDescription());
        return teamRepository.save(existing);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeamEntity> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeamEntity> getTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamEntity> getAllTeams() {
        return teamRepository.findAll();
    }
}
