package repository.impl;

import dao.Coordinates;
import dao.Dragon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.CoordinatesRepository;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesRepositoryImpl implements CoordinatesRepository {
    private final SessionFactory sessionFactory;

    public CoordinatesRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Coordinates> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Coordinates> coo = new ArrayList<>();

        try {

            coo = session.createQuery("FROM COORDINATES").list();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return coo;
    }

    public void save(Coordinates coordinates) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(coordinates);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public Coordinates getCoordinatesById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Coordinates coordinates = null;

        try {
            coordinates = session.get(Coordinates.class, id);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        return coordinates;
    }

    @Override
    public Coordinates updateElement(Coordinates newValue) {
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
            session.createQuery("delete from dao.Coordinates where coordinatesId=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
