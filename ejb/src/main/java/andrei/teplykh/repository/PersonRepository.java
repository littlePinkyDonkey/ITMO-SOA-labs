package andrei.teplykh.repository;

import andrei.teplykh.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PersonRepository {
    private final SessionFactory sessionFactory;

    public PersonRepository() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Person> getAll() {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final List<Person> persons;

        try {

            persons = session.createQuery("FROM PERSONS").list();

            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw e;
        }
        return persons;
    }

    public void save(final Person person) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.save(person);
            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
