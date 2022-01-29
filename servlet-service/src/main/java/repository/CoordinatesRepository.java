package repository;

import entity.Coordinates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesRepository {
    private final SessionFactory sessionFactory;

    public CoordinatesRepository() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Coordinates> getAll() {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final List<Coordinates> coo;

        try {

            coo = session.createQuery("FROM COORDINATES").list();

            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw e;
        }
        return coo;
    }

    public void save(final Coordinates coordinates) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.save(coordinates);

            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public Coordinates getCoordinatesById(final Integer id) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final Coordinates coordinates;

        try {
            coordinates = session.get(Coordinates.class, id);

            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            return null;
        }

        return coordinates;
    }

    public Coordinates updateElement(final Coordinates newValue) {
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
            session.createQuery("delete from entity.Coordinates where coordinatesId=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
