package service;

import dto.PersonDto;
import entity.Dragon;
import entity.Person;
import expetions.BusinessException;
import mapeprs.PersonMapper;
import repository.DragonRepository;
import repository.PersonRepository;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    @Inject
    private PersonRepository personRepository;

    @Inject
    private DragonRepository dragonRepository;

    @Inject
    private PersonMapper personMapper;

    public List<PersonDto> getAll() {
        final List<Person> persons = personRepository.getAll();
        final List<PersonDto> dtos = new ArrayList<>();

        for (Person person : persons) {
            dtos.add(personMapper.entityToDto(person));
        }
        return dtos;
    }

    public void save(final PersonDto person) throws BusinessException {
        try {
            personRepository.save(personMapper.dtoToEntity(person));
        } catch (final Exception e) {
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

//    public void killDragon(final KillDragonDto dto) throws BusinessException {
//        final Dragon dragon = dragonRepository.getDragonById(dto.getDragonId());
//        if (dragon == null) {
//            throw new BusinessException(Response.Status.BAD_REQUEST, "No dragon with such id");
//        }
//
//        final Person newKiller = personRepository.getPersonById(dto.getKillerId());
//        if (newKiller == null) {
//            throw new BusinessException(Response.Status.BAD_REQUEST, "No killer with such id");
//        }
//
//        dragon.setKiller(newKiller);
//        dragonRepository.updateElement(dragon);
//    }

    public Person getPersonById(final Integer id) {
        return personRepository.getPersonById(id);
    }

    public Person updateElement(final Person newValue) {
        return personRepository.updateElement(newValue);
    }

    public void removeElement(final Long dragonId, final Integer personId) {
        personRepository.removeElement(personId);
    }
}
