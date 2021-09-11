package service.impl;

import dao.Person;
import repository.PersonRepository;
import repository.impl.PersonRepositoryImpl;
import service.DragonService;
import service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    private static volatile PersonServiceImpl instance;
    private PersonServiceImpl() {
        this.personRepository = new PersonRepositoryImpl();
    }

    public static PersonServiceImpl getInstance() {
        if (instance == null) {
            synchronized (PersonServiceImpl.class) {
                if (instance == null) {
                    instance = new PersonServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.getAll();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.getPersonById(id);
    }

    @Override
    public Person updateElement(Person newValue) {
        return personRepository.updateElement(newValue);
    }

    @Override
    public void removeElement(Long dragonId, Integer personId) {
        personRepository.removeElement(personId);
    }
}
