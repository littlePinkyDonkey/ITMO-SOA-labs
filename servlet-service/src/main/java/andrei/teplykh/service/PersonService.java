package andrei.teplykh.service;

import andrei.teplykh.dto.PersonDto;
import andrei.teplykh.expetions.BusinessException;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PersonService {
    List<PersonDto> getAll();

    void save(final PersonDto person) throws BusinessException;
}
