package main.dao;

import main.entity.Player;

public class PlayerDao implements Dao<Player> {
    @Override
    public void add(Player entity) {

    }

    @Override
    public Player findById(Integer id) {
        return null;
    }

    public Player findByName(String name) {
        return null;
    }
}
