package service;

import entity.Person;
import repository.PersonRepository;

import javax.inject.Inject;
import java.util.List;

public class PersonService {

    @Inject
    private PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.getAll();
    }

    public void save(final Person person) {
        personRepository.save(person);
    }

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
