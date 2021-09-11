package service;

import dao.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();

    void save(Person person);

    Person getPersonById(Integer id);

    Person updateElement(Person newValue);

    void removeElement(Long dragonId, Integer personId);
}
