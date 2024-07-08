package main.service;

import main.entity.MatchScore;

public class MatchScoreCalculationService {
    private static final String[] POINTS_SEQUENCE = {"0", "15", "30", "40", "AD"};
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    private MatchScoreCalculationService() {
    }

    public void addPoint(MatchScore matchScore, String player) {
        if (player.equals("first")) {
            if (isGameFinished(matchScore, 1)) {
                addGame(matchScore, 1);
                matchScore.setFirstPlayerScore(POINTS_SEQUENCE[0]);
                matchScore.setSecondPlayerScore(POINTS_SEQUENCE[0]);
                return;
            }
            if (isEqualMore(matchScore, 1)) {
                makeEqualScore(matchScore);
                return;
            }
            int currentScore = getPosition(matchScore.getFirstPlayerScore());
            matchScore.setFirstPlayerScore(POINTS_SEQUENCE[currentScore + 1]);
        }
        if (player.equals("second")) {
            if (isGameFinished(matchScore, 2)) {
                addGame(matchScore, 2);
                matchScore.setFirstPlayerScore(POINTS_SEQUENCE[0]);
                matchScore.setSecondPlayerScore(POINTS_SEQUENCE[0]);
                return;
            }
            if (isEqualMore(matchScore, 2)) {
                makeEqualScore(matchScore);
                return;
            }
            int currentScore = getPosition(matchScore.getSecondPlayerScore());
            matchScore.setSecondPlayerScore(POINTS_SEQUENCE[currentScore + 1]);
        }
    }

    private void makeEqualScore(MatchScore matchScore) {
        matchScore.setFirstPlayerScore(POINTS_SEQUENCE[3]);
        matchScore.setSecondPlayerScore(POINTS_SEQUENCE[3]);
    }

    private boolean isEqualMore(MatchScore matchScore, int player) {
        int firstPlayerScore = getPosition(matchScore.getFirstPlayerScore());
        int secondPlayerScore = getPosition(matchScore.getSecondPlayerScore());
        if (player == 1) {
            return firstPlayerScore == 3 && secondPlayerScore == 4;
        }
        return firstPlayerScore == 4 && secondPlayerScore == 3;
    }

    private boolean isGameFinished(MatchScore matchScore, int player) {
        int firstPlayerScore = getPosition(matchScore.getFirstPlayerScore());
        int secondPlayerScore = getPosition(matchScore.getSecondPlayerScore());
        if (player == 1) {
            return firstPlayerScore == 4 || firstPlayerScore >= (secondPlayerScore + 1) && firstPlayerScore == 3;
        }
        return secondPlayerScore == 4 || secondPlayerScore >= (firstPlayerScore + 1) && secondPlayerScore == 3;
    }

    private void addGame(MatchScore matchScore, int player) {
        int currentGame;
        if (player == 1) {
            if (isSetFinished(matchScore, 1)) {
                addSet(matchScore, 1);
                matchScore.setFirstPlayerGame(0);
                matchScore.setSecondPlayerGame(0);
                return;
            }
            currentGame = matchScore.getFirstPlayerGame();
            matchScore.setFirstPlayerGame(currentGame + 1);
        }
        if (player == 2) {
            if (isSetFinished(matchScore, 2)) {
                addSet(matchScore, 2);
                matchScore.setFirstPlayerGame(0);
                matchScore.setSecondPlayerGame(0);
                return;
            }
            currentGame = matchScore.getSecondPlayerGame();
            matchScore.setSecondPlayerGame(currentGame + 1);
        }
    }

    private boolean isSetFinished(MatchScore matchScore, int player) {
        int firstPlayerGame = matchScore.getFirstPlayerGame();
        int secondPlayerGame = matchScore.getSecondPlayerGame();
        if (player == 1) {
            return firstPlayerGame == 6 || firstPlayerGame >= (secondPlayerGame + 1) && firstPlayerGame == 5;
        }
        return secondPlayerGame == 6 || secondPlayerGame >= (firstPlayerGame + 1) && secondPlayerGame == 5;
    }

    private void addSet(MatchScore matchScore, int player) {
        int currentSet;
        if (player == 1) {
            currentSet = matchScore.getFirstPlayerSet();
            matchScore.setFirstPlayerSet(currentSet + 1);
            if (isMatchFinished(matchScore, 1)) {
                defineWinner(matchScore, 1);
                finishedMatchesPersistenceService.saveMatch(matchScore);
            }
        }
        if (player == 2) {
            currentSet = matchScore.getSecondPlayerSet();
            matchScore.setSecondPlayerSet(currentSet + 1);
            if (isMatchFinished(matchScore, 2)) {
                defineWinner(matchScore, 2);
                finishedMatchesPersistenceService.saveMatch(matchScore);
            }
        }
    }

    private void defineWinner(MatchScore matchScore, int player) {
        int firstPlayerSet;
        int secondPlayerSet;
        if (player == 1) {
            firstPlayerSet = matchScore.getFirstPlayerSet() + 1;
            secondPlayerSet = matchScore.getSecondPlayerSet();
        } else {
            firstPlayerSet = matchScore.getFirstPlayerSet();
            secondPlayerSet = matchScore.getSecondPlayerSet() + 1;
        }
        if (firstPlayerSet > secondPlayerSet) {
            matchScore.setWinner(matchScore.getFirstPlayer());
        } else {
            matchScore.setWinner(matchScore.getSecondPlayer());
        }
    }

    private boolean isMatchFinished(MatchScore matchScore, int player) {
        if (player == 1) {
            return matchScore.getFirstPlayerSet() == 2;
        } else if (player == 2) {
            return matchScore.getSecondPlayerSet() == 2;
        }
        return false;
    }

    private int getPosition(String score) {
        for (int i = 0; i < 5; i++) {
            if (score.equals(POINTS_SEQUENCE[i])) {
                return i;
            }
        }
        return 0;
    }

    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }
}
