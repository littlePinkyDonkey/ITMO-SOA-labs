package andrey.teplykh.repository;

import andrey.teplykh.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    TeamEntity getTeamEntityByTeamId(final Integer teamId);
}
