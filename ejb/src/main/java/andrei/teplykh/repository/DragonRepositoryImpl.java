package andrei.teplykh.repository;

import andrei.teplykh.entity.Dragon;
import andrei.teplykh.entity.enums.DragonCharacter;
import andrei.teplykh.exception.BusinessException;
import jakarta.validation.ValidationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Stateless
@Local(DragonRepository.class)
public class DragonRepositoryImpl implements DragonRepository {

    private SessionFactory sessionFactory;

    public DragonRepositoryImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public List<Dragon> getAll(final Map<String, String> parameters, final String stringQuery) throws BusinessException {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final List<Dragon> dragons;

        try {

            final Query query = session.createQuery(stringQuery);

            final Iterator<Map.Entry<String, String >> iterator = parameters.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<String, String> currentElement = iterator.next();
                final String key = currentElement.getKey();
                final String value = currentElement.getValue();

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
        } catch (final Exception e) {
            transaction.rollback();
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
        return dragons;
    }

    public List<Dragon> getAll() throws BusinessException {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final List<Dragon> dragons;

        try {
            dragons = session.createQuery("from DRAGONS").list();
            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
        return dragons;
    }

    public void save(final Dragon dragon) throws ConstraintViolationException, ValidationException, BusinessException {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            if (dragon.getKiller() != null) {
                session.save(dragon.getKiller());
            }
            session.save(dragon.getCoordinates());
            session.save(dragon);

            transaction.commit();
        } catch (final ValidationException e) {
            transaction.rollback();
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    public Dragon getDragonById(final Long id) throws BusinessException {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        Dragon dragon = null;

        try {
            dragon = session.get(Dragon.class, id);

            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }

        return dragon;
    }

    public Dragon updateElement(final Dragon newValue) throws BusinessException {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.update(newValue);
            transaction.commit();
            return newValue;
        } catch (final ValidationException | OptimisticLockException e) {
            transaction.rollback();
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }

    public int removeElement(final Long id) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            final Dragon d = session.get(Dragon.class, id);

            session.remove(d);
            session.remove(d.getCoordinates());
            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            return 0;
        }
        return 1;
    }

    public int removeElementByCharacter(final DragonCharacter character) throws BusinessException {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        int updated;
        try {
            updated = session.createQuery("delete from entity.Dragon where character=:character")
                    .setParameter("character", character)
                    .executeUpdate();
            transaction.commit();
        } catch (final Exception e) {
            transaction.rollback();
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
        return updated;
    }

    public Dragon getWithMinId() throws BusinessException {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        final List<Dragon> dragons;
        try {
            final Query query = session.createQuery("from entity.Dragon d order by id");
            dragons = query.list();
            return dragons.get(0);
        } catch (final Exception e) {
            transaction.rollback();
            throw new BusinessException(Response.Status.BAD_REQUEST, e.getMessage());
        }
    }
}
