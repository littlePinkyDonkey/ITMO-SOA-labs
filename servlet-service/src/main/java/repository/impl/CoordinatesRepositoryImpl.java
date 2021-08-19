package repository.impl;

import dao.Coordinates;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.CoordinatesRepository;

public class CoordinatesRepositoryImpl implements CoordinatesRepository {
    private final SessionFactory sessionFactory;

    public CoordinatesRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
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
}
