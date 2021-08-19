package repository.impl;

import dao.Dragon;
import dao.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {
    private final SessionFactory sessionFactory;

    public PersonRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Person> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Person> persons = new ArrayList<>();

        try {

            persons = session.createQuery("FROM PERSONS").list();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return persons;
    }

    @Override
    public void save(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(person);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Person getPersonById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Person person = null;

        try {
            person = session.get(Person.class, id);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        return person;
    }

    @Override
    public Person updateElement(Person newValue) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(newValue);
            transaction.commit();
            return newValue;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void removeElement(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.createQuery("delete from dao.Person where personId=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
