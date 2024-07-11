package service;

import dao.MatchDao;
import entity.Match;
import entity.MatchScore;

import java.util.List;

public class FinishedMatchesPersistenceService {
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
    private static final MatchDao matchDao = MatchDao.getInstance();
    private static final int PAGE_SIZE = 5;

    private FinishedMatchesPersistenceService() {
    }

    public void saveMatch(MatchScore matchScore) {
        Match match = Match.builder()
                .firstPlayer(matchScore.getFirstPlayer())
                .secondPlayer(matchScore.getSecondPlayer())
                .winner(matchScore.getWinner())
                .build();
        matchDao.add(match);
    }

    public List<Match> getAllMatches(int currentPage) {
        int offset = (currentPage - 1) * PAGE_SIZE;
        return matchDao.findAllWithPagination(PAGE_SIZE, offset);
    }

    public List<Match> getPlayerMatches(int currentPage, String playerName) {
        int offset = (currentPage - 1) * PAGE_SIZE;
        return matchDao.findByPlayerNameWithPagination(playerName, PAGE_SIZE, offset);
    }

    public int getTotalPages(boolean isForPlayer, String playerName) {
        long totalMatches;
        if (isForPlayer) {
            totalMatches = matchDao.countMatchesByPlayerName(playerName);
        } else {
            totalMatches = matchDao.countAllMatches();
        }
        return (int) Math.ceil((double) totalMatches / PAGE_SIZE);
    }

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }
}
