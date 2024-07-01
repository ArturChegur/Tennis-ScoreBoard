package main.dao;

import main.entity.Player;
import main.util.HibernateUtil;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerDao implements Dao<Player> {
    private static final PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {
    }

    @Override
    public void add(Player player) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Player> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Player ", Player.class).list();
        }
    }

    public Player findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Player.class, id);
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
