package andrey.teplykh.service;

import andrey.teplykh.dto.PersonDto;
import andrey.teplykh.dto.TeamDto;
import andrey.teplykh.entities.CaveEntity;
import andrey.teplykh.entities.TeamEntity;
import andrey.teplykh.exception.BusinessException;
import andrey.teplykh.mapper.TeamMapper;
import andrey.teplykh.repository.CaveRepository;
import andrey.teplykh.repository.endpoint.MainServiceRepository;
import andrey.teplykh.repository.TeamRepository;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class KillerServiceImpl implements KillerService {

    private final MainServiceRepository mainServiceRepository;
    private final TeamRepository teamRepository;
    private final CaveRepository caveRepository;
    private final Gson gson;
    private final TeamMapper teamMapper;

    @Override
    public PersonDto createKiller(PersonDto killer) throws BusinessException {
        return mainServiceRepository.createKiller(killer);
    }

    @Override
    public TeamDto createKillerTeam(int teamId, String teamName, int teamSize, int startCaveId) throws BusinessException {
        final List<PersonDto> killers = mainServiceRepository.getAllKillers();
        if (teamSize > killers.size()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Team size is too big. There is no such quantity of killers");
        }
        final CaveEntity cave = caveRepository.getCaveEntityByCaveId(startCaveId);

        if (cave == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "No cave with such ID");
        }

        final TeamEntity team = TeamEntity.builder()
                .teamId(teamId)
                .teamName(teamName)
                .teamSize(teamSize)
                .cave(cave)
                .persons(gson.toJson(killers.subList(0, teamSize)))
                .build();

        teamRepository.save(team);

        return teamMapper.entityToDto(team);
    }

    @Override
    public void sendKillers(int teamId, int caveId) throws BusinessException {
        final CaveEntity cave = caveRepository.getCaveEntityByCaveId(caveId);
        final TeamEntity team = teamRepository.getTeamEntityByTeamId(teamId);

        if (cave == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "No cave with such ID");
        }

        if (team == null) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "No team with such ID");
        }

        team.setCave(cave);

        teamRepository.save(team);
    }

    @Override
    public List<TeamDto> getAllTeams() {
        final List<TeamEntity> teams = teamRepository.findAll();
        final List<TeamDto> response = new ArrayList<>();

        for (TeamEntity team : teams) {
            response.add(teamMapper.entityToDto(team));
        }

        return response;
    }
}
