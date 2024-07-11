package service;

import dao.PlayerDao;
import entity.Player;
import org.hibernate.exception.ConstraintViolationException;

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
        } catch (ConstraintViolationException e) {
            player = playerDao.findByName(name);
        }
        return player;
    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }
}
