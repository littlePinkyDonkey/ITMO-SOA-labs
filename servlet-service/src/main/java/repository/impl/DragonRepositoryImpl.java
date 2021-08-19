package repository.impl;

import dao.Dragon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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

    @Override
    public Dragon getDragonById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Dragon dragon = null;

        try {
            dragon = session.get(Dragon.class, id);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }

        return dragon;
    }

    @Override
    public Dragon updateElement(Dragon newValue) {
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
    public void removeElement(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.createQuery("delete from dao.Dragon where id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
