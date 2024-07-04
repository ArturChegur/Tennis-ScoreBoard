package main.service;

import main.dao.PlayerDao;
import main.entity.Player;
import main.exception.PlayerExistsException;

public class PlayerService {
    private static final PlayerService INSTANCE = new PlayerService();
    private static final PlayerDao playerDao = PlayerDao.getInstance();

    private PlayerService() {
    }

    public Player addPlayer(String name) {
        Player player = Player.builder()
                .name(name)
                .build();
        try {
            playerDao.add(player);
            player = playerDao.findByName(name);
        } catch (PlayerExistsException e) {
            player = playerDao.findByName(name);
        }
        return player;
    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
