package andrei.teplykh.repository;

import andrei.teplykh.entity.Coordinates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
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
}
