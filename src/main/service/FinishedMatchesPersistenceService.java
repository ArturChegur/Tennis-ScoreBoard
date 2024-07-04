package main.service;

import main.entity.Match;

public class FinishedMatchesPersistenceService {
    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();

    private FinishedMatchesPersistenceService() {
    }

    public void saveMatch(Match match) {
        System.out.println("fdfdfffsdjfkjsdfkjsdfjhdsfjhjhdsfkjkdhfsjkdfjkhdfjhkdsfjhdfsjhkdfsjkhdfsjkhdsfjhkdsfjhkdfsjkhdfsjkhdfjskhjdfhsjkhdfsjhdsfjkhdsfj");
    }

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }
}
