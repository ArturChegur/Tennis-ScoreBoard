package main.service;

import main.entity.Match;

public class MatchScoreCalculationService {
    private static final String[] POINTS_SEQUENCE = {"0", "15", "30", "40", "AD"};
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    private MatchScoreCalculationService() {
    }

    public void addPoint(Match match, String player) {
        if (player.equals("first")) {
            if (isGameFinished(match, 1)) {
                addGame(match, 1);
                match.getMatchScore().setFirstPlayerScore(POINTS_SEQUENCE[0]);
                match.getMatchScore().setSecondPlayerScore(POINTS_SEQUENCE[0]);
            } else {
                if (isEqualMore(match, 1)) {
                    makeEqualScore(match);
                } else {
                    int currentScore = getPosition(match.getMatchScore().getFirstPlayerScore());
                    match.getMatchScore().setFirstPlayerScore(POINTS_SEQUENCE[currentScore + 1]);
                }
            }
        } else {
            if (isGameFinished(match, 2)) {
                addGame(match, 2);
                match.getMatchScore().setFirstPlayerScore(POINTS_SEQUENCE[0]);
                match.getMatchScore().setSecondPlayerScore(POINTS_SEQUENCE[0]);
            } else {
                if (isEqualMore(match, 2)) {
                    makeEqualScore(match);
                } else {
                    int currentScore = getPosition(match.getMatchScore().getSecondPlayerScore());
                    match.getMatchScore().setSecondPlayerScore(POINTS_SEQUENCE[currentScore + 1]);
                }
            }
        }
    }

    private void makeEqualScore(Match match) {
        match.getMatchScore().setFirstPlayerScore(POINTS_SEQUENCE[3]);
        match.getMatchScore().setSecondPlayerScore(POINTS_SEQUENCE[3]);
    }

    private boolean isEqualMore(Match match, int player) {
        int firstPlayerScore = getPosition(match.getMatchScore().getFirstPlayerScore());
        int secondPlayerScore = getPosition(match.getMatchScore().getSecondPlayerScore());
        if (player == 1) {
            return firstPlayerScore == 3 && secondPlayerScore == 4;
        } else {
            return firstPlayerScore == 4 && secondPlayerScore == 3;
        }
    }

    private boolean isGameFinished(Match match, int player) {
        int firstPlayerScore = getPosition(match.getMatchScore().getFirstPlayerScore());
        int secondPlayerScore = getPosition(match.getMatchScore().getSecondPlayerScore());
        if (player == 1) {
            return firstPlayerScore == 4 || firstPlayerScore >= (secondPlayerScore + 1) && firstPlayerScore == 3;
        } else {
            return secondPlayerScore == 4 || secondPlayerScore >= (firstPlayerScore + 1) && secondPlayerScore == 3;
        }
    }

    private void addGame(Match match, int player) {
        int currentGame;
        if (player == 1) {
            if (isSetFinished(match, 1)) {
                addSet(match, 1);
                match.getMatchScore().setFirstPlayerGame(0);
                match.getMatchScore().setSecondPlayerGame(0);
            } else {
                currentGame = match.getMatchScore().getFirstPlayerGame();
                match.getMatchScore().setFirstPlayerGame(currentGame + 1);
            }
        } else {
            if (isSetFinished(match, 2)) {
                addSet(match, 2);
                match.getMatchScore().setFirstPlayerGame(0);
                match.getMatchScore().setSecondPlayerGame(0);
            } else {
                currentGame = match.getMatchScore().getSecondPlayerGame();
                match.getMatchScore().setSecondPlayerGame(currentGame + 1);
            }
        }
    }

    private boolean isSetFinished(Match match, int player) {
        int firstPlayerGame = match.getMatchScore().getFirstPlayerGame();
        int secondPlayerGame = match.getMatchScore().getSecondPlayerGame();
        if (player == 1) {
            return firstPlayerGame == 6 || firstPlayerGame >= (secondPlayerGame + 1) && firstPlayerGame == 5;
        } else {
            return secondPlayerGame == 6 || secondPlayerGame >= (firstPlayerGame + 1) && secondPlayerGame == 5;
        }
    }

    private void addSet(Match match, int player) {
        int currentSet;
        if (player == 1) {
            if (isMatchFinished(match)) {
                defineWinner(match, 1);
                finishedMatchesPersistenceService.saveMatch(match);
            } else {
                currentSet = match.getMatchScore().getFirstPlayerSet();
                match.getMatchScore().setFirstPlayerSet(currentSet + 1);
            }
        } else {
            if (isMatchFinished(match)) {
                defineWinner(match, 2);
                finishedMatchesPersistenceService.saveMatch(match);
            }
            currentSet = match.getMatchScore().getSecondPlayerSet();
            match.getMatchScore().setSecondPlayerSet(currentSet + 1);
        }
    }

    private void defineWinner(Match match, int player) {
        int firstPlayerSet;
        int secondPlayerSet;
        if (player == 1) {
            firstPlayerSet = match.getMatchScore().getFirstPlayerSet() + 1;
            secondPlayerSet = match.getMatchScore().getSecondPlayerSet();
        } else {
            firstPlayerSet = match.getMatchScore().getFirstPlayerSet();
            secondPlayerSet = match.getMatchScore().getSecondPlayerSet() + 1;
        }
        if (firstPlayerSet > secondPlayerSet) {
            match.setWinner(match.getFirstPlayer());
        } else {
            match.setWinner(match.getSecondPlayer());
        }
    }

    private boolean isMatchFinished(Match match) {
        return match.getMatchScore().getFirstPlayerSet() + match.getMatchScore().getSecondPlayerSet() == 2;
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
