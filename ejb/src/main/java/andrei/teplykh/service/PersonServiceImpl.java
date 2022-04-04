package andrei.teplykh.service;

import andrei.teplykh.dto.PersonDto;
import andrei.teplykh.entity.Person;
import andrei.teplykh.exception.BusinessException;
import andrei.teplykh.mappers.PersonMapper;
import andrei.teplykh.repository.PersonRepository;
import andrei.teplykh.repository.PersonRepositoryImpl;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
//@Remote(PersonService.class)
public class PersonServiceImpl implements PersonService {

    @EJB
    private PersonRepository personRepositoryImpl;

    private PersonMapper personMapper = new PersonMapper();

    public List<PersonDto> getAll() {
        final List<Person> persons = personRepositoryImpl.getAll();
        final List<PersonDto> dtos = new ArrayList<>();

        for (Person person : persons) {
            dtos.add(personMapper.entityToDto(person));
        }
        return dtos;
    }

    public void save(final PersonDto person) throws BusinessException {
        try {
            personRepositoryImpl.save(personMapper.dtoToEntity(person));
        } catch (final Exception e) {
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }
}
