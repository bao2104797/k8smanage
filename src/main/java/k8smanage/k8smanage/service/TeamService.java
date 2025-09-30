package k8smanage.k8smanage.service;

import java.util.List;
import java.util.Optional;
import k8smanage.k8smanage.entity.TeamEntity;

public interface TeamService {
    TeamEntity createTeam(TeamEntity team);
    TeamEntity updateTeam(Long id, TeamEntity team);
    void deleteTeam(Long id);
    Optional<TeamEntity> getTeamById(Long id);
    Optional<TeamEntity> getTeamByName(String name);
    List<TeamEntity> getAllTeams();
}
