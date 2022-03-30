package andrey.teplykh.mapper;

import andrey.teplykh.dto.TeamDto;
import andrey.teplykh.entities.TeamEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public TeamDto entityToDto(final TeamEntity entity) {
        return TeamDto.builder()
                .teamId(entity.getTeamId())
                .persons(entity.getPersons())
                .startCave(entity.getCave().getCaveId())
                .teamName(entity.getTeamName())
                .teamSize(entity.getTeamSize())
                .build();
    }
}
