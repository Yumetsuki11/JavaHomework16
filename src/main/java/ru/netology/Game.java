package ru.netology;

import java.util.ArrayList;

public class Game {
    ArrayList<Player> players = new ArrayList<>();

    public void register(Player player) {
        players.add(player);
    }

    public Player findByName(String name) {
        for (Player player : players) {
            if (player.getName() == name) {
                return player;
            }
        }
        throw new NotRegisteredException("The player" + name + "is not registered");
    }

    public byte round(String playerName1, String playerName2) {
        int s1 = findByName(playerName1).getStrength();
        int s2 = findByName(playerName2).getStrength();

        if (s1 > s2) {
            return 1;
        } else if (s2 > s1) {
            return 2;
        } else {
            return 0;
        }
    }
}
