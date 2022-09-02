package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameTest {
    Game game = new Game();

    @Test
    public void shouldRegisterTheFirstPlayer() {
        Player player1 = new Player(1, "Вениамин", 1337);

        game.register(player1);
        ArrayList<Player> expected = new ArrayList<>();
        expected.add(player1);

        Assertions.assertEquals(expected, game.players);
    }

    @Test
    public void shouldRegisterTheFollowingPlayers() {
        Player player1 = new Player(1, "Вениамин", 1337);
        Player player2 = new Player(2, "Эмилия", 161660);

        game.register(player1);
        game.register(player2);
        ArrayList<Player> expected = new ArrayList<>();
        expected.add(player1);
        expected.add(player2);

        Assertions.assertEquals(expected, game.players);
    }

    @Test
    public void shouldFindByNameWhenOneRegistered() {
        Player player1 = new Player(1, "Вениамин", 1337);

        game.register(player1);

        Assertions.assertEquals(player1, game.findByName("Вениамин"));
    }

    @Test
    public void shouldFindByNameWhenSeveralRegistered() {
        Player player1 = new Player(1, "Вениамин", 1337);
        Player player2 = new Player(2, "Эмилия", 161660);
        Player player3 = new Player(3, "Арслан", 42);

        game.register(player1);
        game.register(player2);
        game.register(player3);

        Assertions.assertEquals(player2, game.findByName("Эмилия"));
    }

    @Test
    public void shouldThrowExceptionWhenPlayerNotFound() {
        Player player1 = new Player(1, "Вениамин", 1337);
        Player player2 = new Player(2, "Эмилия", 161660);
        Player player3 = new Player(3, "Арслан", 42);

        game.register(player1);
        game.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () ->
        {
            game.findByName("Арслан");
        });
    }

    @Test
    public void shouldNotFindWhenNameMatchesPartly() {
        Player player1 = new Player(1, "Вениамин", 1337);
        Player player4 = new Player(4, "Анна-Мария", 1);

        game.register(player1);
        game.register(player4);

        Assertions.assertThrows(NotRegisteredException.class, () ->
        {
            game.findByName("Анна");
        });
    }

    @Test
    public void shouldReturn1WhenFirstPlayerWins() {
        Player player1 = new Player(1, "Вениамин", 1337);
        Player player2 = new Player(2, "Эмилия", 161660);

        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(1, game.round("Эмилия", "Вениамин"));
    }

    @Test
    public void shouldReturn2WhenSecondPlayerWins() {
        Player player1 = new Player(1, "Вениамин", 1337);
        Player player2 = new Player(2, "Эмилия", 161660);

        game.register(player1);
        game.register(player2);

        Assertions.assertEquals(2, game.round("Вениамин", "Эмилия"));
    }

    @Test
    public void shouldReturn0WhenTieAndDirectOrder() {
        Player player4 = new Player(4, "Анна-Мария", 1);
        Player player5 = new Player(5, "Брячислав", 1);

        game.register(player4);
        game.register(player5);

        Assertions.assertEquals(0, game.round("Анна-Мария", "Брячислав"));
    }

    @Test
    public void shouldReturn0WhenTieAndReversedOrder() {
        Player player4 = new Player(4, "Анна-Мария", 1);
        Player player5 = new Player(5, "Брячислав", 1);

        game.register(player4);
        game.register(player5);

        Assertions.assertEquals(0, game.round("Брячислав", "Анна-Мария"));
    }
}
