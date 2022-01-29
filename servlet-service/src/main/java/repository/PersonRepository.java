package repository;

import entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

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

    public Person getPersonById(final Integer id) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        Person person = null;

        try {
            person = session.get(Person.class, id);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        return person;
    }

    public Person updateElement(final Person newValue) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.update(newValue);
            transaction.commit();
            return newValue;
        } catch (final Exception e) {
            transaction.rollback();
            return null;
        }
    }

    public void removeElement(final Integer id) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.createQuery("delete from entity.Person where personId=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
