package andrey.teplykh.repository.endpoint;

import andrey.teplykh.dto.PersonDto;
import andrey.teplykh.exception.BusinessException;

import java.util.List;

public interface MainServiceRepository {
    List<PersonDto> getAllKillers() throws BusinessException;

    PersonDto createKiller(final PersonDto dto) throws BusinessException;
}
