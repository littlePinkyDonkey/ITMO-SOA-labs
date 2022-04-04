package andrei.teplykh.repository;

import andrei.teplykh.entity.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonRepository {

    public List<Person> getAll();

    public void save(final Person person);


}
