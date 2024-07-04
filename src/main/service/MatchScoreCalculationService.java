package main.service;

import main.entity.Match;

public class MatchScoreCalculationService {
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();

    private MatchScoreCalculationService() {
    }

    public void add(Match match) {
        match.getMatchScore().setFirstPlayerScore(match.getMatchScore().getFirstPlayerScore() + 10);
    }

    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }
}
