package repository.impl;

import dao.Dragon;
import jakarta.validation.ValidationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import repository.DragonRepository;

import javax.persistence.OptimisticLockException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DragonRepositoryImpl implements DragonRepository {
    private final SessionFactory sessionFactory;

    public DragonRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Dragon> getAll(Map<String, String> parameters, String stringQuery) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Dragon> dragons;

        try {

            Query query = session.createQuery(stringQuery);

            Iterator<Map.Entry<String, String >> iterator = parameters.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> currentElement = iterator.next();
                String key = currentElement.getKey();
                String value = currentElement.getValue();

                if (key.equals("dragon_id")) {
                    query.setParameter(key, Long.parseLong(value));
                } else if (key.equals("coordinate_y") || key.equals("dragon_age")) {
                    query.setParameter(key, Integer.parseInt(value));
                } else if (key.equals("coordinate_x")) {
                    query.setParameter(key, Double.parseDouble(value));
                } else if (key.equals("dragon_creation_date")) {
                    query.setParameter(key, LocalDateTime.parse(value));
                } else {
                    query.setParameter(key, value);
                }
            }

            dragons = query.list();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return dragons;
    }

    @Override
    public List<Dragon> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Dragon> dragons;

        try {
            dragons = session.createQuery("from DRAGONS").list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return dragons;
    }

    @Override
    public void save(Dragon dragon) throws ConstraintViolationException, ValidationException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            if (dragon.getKiller() != null) {
                session.save(dragon.getKiller());
            }
            session.save(dragon.getCoordinates());
            session.save(dragon);

            transaction.commit();
        } catch (ValidationException e) {
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
        } catch (ValidationException | OptimisticLockException e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public int removeElement(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        int updated;
        try {
            Dragon d = session.get(Dragon.class, id);

            session.remove(d);
            session.remove(d.getCoordinates());
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return 0;
        }
        return 1;
    }

    @Override
    public int removeElementByCharacter(String character) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        int updated;
        try {
            updated = session.createQuery("delete from dao.Dragon where character=:character")
                    .setParameter("character", character)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return updated;
    }

    @Override
    public Dragon getWithMinId() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Dragon> dragons;
        try {
            Query query = session.createQuery("from dao.Dragon d order by id");
            dragons = query.list();
            return dragons.get(0);
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
