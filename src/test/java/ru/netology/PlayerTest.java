package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PlayerTest {

    private GameStore store = new GameStore();
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Arcanum", "RPG");
    Game game3 = store.publishGame("Cyberpunk 2077", "RPG");
    Game game4 = store.publishGame("Bayonetta", "Аркады");
    Game game5 = store.publishGame("Total War Saga: Troy", "Strategy");

    @Test
    public void shouldSumGenreIfOneGame() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralGameTwoDifferentGenre() {
        Player player = new Player("Petya");

        player.installGame(game5);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game5, 3);
        player.play(game3, 5);
        player.play(game2, 2);

        int expected = 7;
        int actual = player.sumGenre(game3.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralGameThreeDifferentGenre() {
        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game5, 3);
        player.play(game3, 5);
        player.play(game2, 2);
        player.play(game1, 4);
        player.play(game4, 1);

        int expected = 5;
        int actual = player.sumGenre(game4.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCountTimeStatisticIfOneGameOnePlay() {
        Player player = new Player("Jony");
        player.installGame(game1);

        int expected = 1;
        int actual = player.play(game1, 1);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldCountTimeStatisticIfOneGameTwoPlay() {
        Player player = new Player("Jony");
        player.installGame(game1);

        int firstPlay = player.play(game1, 1);

        int expected = 3;
        int actual = player.play(game1, 2);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldCountTimeStatisticIfOneGameThreePlay() {
        Player player = new Player("Jony");
        player.installGame(game1);

        int firstPlay = player.play(game1, 1);
        int secondPlay = player.play(game1, 5);

        int expected = 8;
        int actual = player.play(game1, 2);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldCountTimeStatisticIfThreeGameOnePlay() {
        Player player = new Player("Jony");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        int expected = 3;
        int actual = player.play(game3, 3);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldCountTimeStatisticIfThreeGameThreePlay() {
        Player player = new Player("Jony");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        int firstPlay = player.play(game1, 4);
        int secondPlay = player.play(game1, 5);

        int expected = 12;
        int actual = player.play(game1, 3);

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWithUninstalledGameEmptyGamesList() {
        Player player = new Player("Jony");

        Assertions.assertThrows(RuntimeException.class, () -> {player.play(game1, 2);} );
    }

    @Test
    public void shouldThrowRuntimeExceptionWithUninstalledGameNotEmptyGamesList() {
        Player player = new Player("Jony");
        player.installGame(game2);
        player.installGame(game3);

        Assertions.assertThrows(RuntimeException.class, () -> {player.play(game1, 2);} );
    }

    @Test
    public void shouldReturnGameOfMostPlayerByGenreIfInstallOneGame() {
        Player player = new Player("Jony");

        player.installGame(game1);

        player.play(game1, 5);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre(game1.getGenre());

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnNullOfMostPlayerByGenreButNoSuchGameInstallOneGame() {
        Player player = new Player("Jony");

        player.installGame(game1);

        player.play(game1, 5);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Strategy");

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnNullOfMostPlayerByGenreButGameNotPlayedInstallOneGame() {
        Player player = new Player("Jony");

        player.installGame(game1);

        player.play(game1, 0);

        Game expected = null;
        Game actual = player.mostPlayerByGenre(game1.getGenre());

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void shouldReturnGameOfMostPlayerByGenreIfInstallSeveralGameAndOneGameSuchGenre() {
        Player player = new Player("Jony");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game5, 2);
        player.play(game3, 3);
        player.play(game2, 7);
        player.play(game1, 4);
        player.play(game4, 6);

        Game expected = game5;
        Game actual = player.mostPlayerByGenre(game5.getGenre());

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnGameOfMostPlayerByGenreIfInstallSeveralGameAndSeveralGamesSuchGenre() {
        Player player = new Player("Jony");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game5, 5);
        player.play(game3, 3);
        player.play(game2, 7);
        player.play(game1, 4);
        player.play(game4, 6);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre(game3.getGenre());

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnNullOfMostPlayerByGenreIfInstallSeveralGameAndNoGamesSuchGenre() {
        Player player = new Player("Jony");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game5, 5);
        player.play(game3, 3);
        player.play(game2, 7);
        player.play(game1, 4);
        player.play(game4, 6);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Action");

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnNullOfMostPlayerByGenreIfInstallSeveralGameAndOneNotPlayedGameSuchGenre() {
        Player player = new Player("Jony");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game5, 0);
        player.play(game3, 3);
        player.play(game2, 7);
        player.play(game1, 4);
        player.play(game4, 6);

        Game expected = null;
        Game actual = player.mostPlayerByGenre(game5.getGenre());

        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnNullOfMostPlayerByGenreIfInstallSeveralGameAndSeveralNotPlayedGameSuchGenre() {
        Player player = new Player("Jony");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.installGame(game4);
        player.installGame(game5);

        player.play(game5, 8);
        player.play(game3, 3);
        player.play(game2, 7);
        player.play(game1, 0);
        player.play(game4, 0);

        Game expected = null;
        Game actual = player.mostPlayerByGenre(game4.getGenre());

        Assertions.assertEquals(expected,actual);
    }

    // другие ваши тесты
}
