package main.dao;

import main.entity.Match;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MatchDao implements Dao<Match> {
    private static final MatchDao INSTANCE = new MatchDao();

    private MatchDao() {
    }

    @Override
    public void add(Match entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Match> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Match", Match.class).list();
        }
    }

    public List<Match> findByPlayerId(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Match> query = session.createQuery("from Match where firstPlayer.id = :playerId or secondPlayer.id = :playerId", Match.class);
            query.setParameter("playerId", id);
            return query.list();
        }
    }

    public static MatchDao getInstance() {
        return INSTANCE;
    }
}
