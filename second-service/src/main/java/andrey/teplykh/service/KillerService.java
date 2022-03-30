package andrey.teplykh.service;

import andrey.teplykh.dto.PersonDto;
import andrey.teplykh.dto.TeamDto;
import andrey.teplykh.exception.BusinessException;

import java.util.List;

public interface KillerService {
    PersonDto createKiller(final PersonDto killer) throws BusinessException;

    TeamDto createKillerTeam(final int teamId, final String teamName, final int teamSize, final int caveId) throws BusinessException;

    void sendKillers(final int teamId, final int startCaveId) throws BusinessException;

    List<TeamDto> getAllTeams();
}
