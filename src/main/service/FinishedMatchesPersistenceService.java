package main.service;

import main.dao.MatchDao;
import main.entity.Match;
import main.entity.MatchScore;

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
        List<Match> matches = matchDao.findAll();
        int totalMatches = matches.size();
        int start = (currentPage - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, totalMatches);
        return matches.subList(start, end);
    }

    public List<Match> getPlayerMatches(int currentPage, String playerName) {
        List<Match> matches = matchDao.findByPlayerName(playerName);
        int totalMatches = matches.size();
        int start = (currentPage - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, totalMatches);
        return matches.subList(start, end);
    }

    public int getTotalPages(boolean isForPlayer, String playerName) {
        if (isForPlayer) {
            return (int) Math.ceil((double) matchDao.findByPlayerName(playerName).size() / PAGE_SIZE);
        } else {
            return (int) Math.ceil((double) matchDao.findAll().size() / PAGE_SIZE);
        }
    }

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }
}
