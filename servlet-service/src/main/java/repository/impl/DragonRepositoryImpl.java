package repository.impl;

import dao.Dragon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.DragonRepository;

import java.util.ArrayList;
import java.util.List;

public class DragonRepositoryImpl implements DragonRepository {
    private final SessionFactory sessionFactory;

    public DragonRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Dragon> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Dragon> dragons = new ArrayList<>();

        try {

            dragons = session.createQuery("FROM DRAGONS").list();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return dragons;
    }

    @Override
    public void save(Dragon dragon) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(dragon);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
