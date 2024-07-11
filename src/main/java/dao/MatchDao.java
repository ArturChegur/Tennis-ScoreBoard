package dao;

import entity.Match;
import util.HibernateUtil;
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

    public List<Match> findByPlayerName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Match> query = session.createQuery("from Match where firstPlayer.name = :playerName or secondPlayer.name = :playerName", Match.class);
            query.setParameter("playerName", name);
            return query.list();
        }
    }

    public List<Match> findAllWithPagination(int limit, int offset) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Match", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .list();
        }
    }

    public List<Match> findByPlayerNameWithPagination(String name, int limit, int offset) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Match> query = session.createQuery("from Match where firstPlayer.name = :playerName or secondPlayer.name = :playerName", Match.class);
            query.setParameter("playerName", name);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.list();
        }
    }

    public long countAllMatches() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(m) from Match m", Long.class);
            return query.uniqueResult();
        }
    }

    public long countMatchesByPlayerName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(m) from Match m where m.firstPlayer.name = :playerName or m.secondPlayer.name = :playerName", Long.class);
            query.setParameter("playerName", name);
            return query.uniqueResult();
        }
    }

    public static MatchDao getInstance() {
        return INSTANCE;
    }
}
