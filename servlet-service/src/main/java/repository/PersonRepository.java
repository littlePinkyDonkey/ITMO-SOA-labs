package repository;

import dao.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAll();

    void save(Person person);

    Person getPersonById(Integer id);

    Person updateElement(Person newValue);

    void removeElement(Integer id);
}
