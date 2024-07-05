package main.service;

import main.entity.Match;
import main.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private static final PlayerService playerService = PlayerService.getInstance();
    private static final ConcurrentHashMap<UUID, Match> currentMatches = new ConcurrentHashMap<>();

    private OngoingMatchesService() {
    }

    public UUID addMatch(String firstPlayer, String secondPlayer) {
        UUID uuid = UUID.randomUUID();
        Player first = playerService.addPlayer(firstPlayer);
        Player second = playerService.addPlayer(secondPlayer);
        Match match = Match.builder()
                .firstPlayer(first)
                .secondPlayer(second)
                .matchScore(new Match.MatchScore())
                .build();
        currentMatches.put(uuid, match);
        return uuid;
    }

    public Match readMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }

    public void deleteMatch(UUID uuid) {
        currentMatches.remove(uuid);
    }

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
}
