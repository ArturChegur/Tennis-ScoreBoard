package main.service;

import main.dao.MatchDao;
import main.entity.Match;

public class FinishedMatchesPersistenceService {
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
    private static final MatchDao matchDao = MatchDao.getInstance();

    private FinishedMatchesPersistenceService() {
    }

    public void saveMatch(Match match) {
        matchDao.add(match);
    }

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }
}
