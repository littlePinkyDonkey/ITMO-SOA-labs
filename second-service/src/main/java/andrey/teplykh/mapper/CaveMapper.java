package andrey.teplykh.mapper;

import andrey.teplykh.dto.CaveDto;
import andrey.teplykh.dto.TeamDto;
import andrey.teplykh.entities.CaveEntity;
import andrey.teplykh.entities.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CaveMapper {

    @Autowired
    private TeamMapper teamMapper;

    public CaveDto entityToDto(final CaveEntity entity) {
        final List<TeamDto> teams = new ArrayList<>();

        for (TeamEntity team : entity.getTeams()) {
            teams.add(teamMapper.entityToDto(team));
        }

        return CaveDto.builder()
                .caveId(entity.getCaveId())
                .teams(teams)
                .build();
    }
}
