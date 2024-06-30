package main.dao;

import main.entity.Match;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDao implements Dao<Match> {
    @Override
    public void add(Match entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Match findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Match match = session.get(Match.class, id);
        }
        return null;
    }

    public List<Match> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Match", Match.class).list();
        }
    }
}
