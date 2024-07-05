package main.dao;

import main.entity.Player;
import main.exception.PlayerExistsException;
import main.util.HibernateUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.hibernate.Session;

import java.util.List;

public class PlayerDao implements Dao<Player> {
    private static final PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {
    }

    @Override
    public void add(Player player) throws PlayerExistsException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            throw new PlayerExistsException();
        }
    }

    @Override
    public List<Player> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Player ", Player.class).list();
        }
    }

    public Player findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Player> query = session.createQuery("from Player where name = :name", Player.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        }
    }

    public static PlayerDao getInstance() {
        return INSTANCE;
    }
}
