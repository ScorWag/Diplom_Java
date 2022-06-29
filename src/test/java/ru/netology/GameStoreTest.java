package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));

    }

    @Test
    public void shouldСontainsGameM() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Онлайн", "Шутер");
        Game game3 = store.publishGame("Нетология Баттл", "Стратегия");

        assertTrue(store.containsGame(game3));
    }
    @Test
    public void shouldСontainsGameNo() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Онлайн", "Шутер");
        Game game3 = new Game("Нетология Баттл", "Стратегия", store);

        assertFalse(store.containsGame(game3));
    }


        @Test
    public void shouldGetMostPlayerIfOneHour() {
        GameStore store = new GameStore();
        store.addPlayTime("Vlad", 1);
        store.addPlayTime("Oleg", 0);
        store.addPlayTime("Sol", 0);

        String actual = store.getMostPlayer();

        assertEquals("Vlad", actual);
    }

    @Test
    public void shouldGetMostPlayerIfZeroHours() {
        GameStore store = new GameStore();
        store.addPlayTime("Vlad", 0);
        store.addPlayTime("Oleg", 0);
        store.addPlayTime("Sol", 0);

        String actual = store.getMostPlayer();

        assertEquals(null, actual);
    }

    @Test
    public void shouldGetMostPlayerIfMoreThenOneHour() {
        GameStore store = new GameStore();
        store.addPlayTime("Vlad", 2);
        store.addPlayTime("Oleg", 4);
        store.addPlayTime("Sol", 3);

        String actual = store.getMostPlayer();

        assertEquals("Oleg", actual);
    }

    @Test
    public void shouldGetSumPlayedTime() {
        GameStore store = new GameStore();
        store.addPlayTime("Vlad", 2);
        store.addPlayTime("Oleg", 5);
        store.addPlayTime("Sol", 7);

        int actual = store.getSumPlayedTime();

        assertEquals(2 + 5 + 7, actual);
    }
    @Test
    public void shouldSumPlayedTime() {
        GameStore store = new GameStore();
        store.addPlayTime("Vlad", 5);
        store.addPlayTime("Oleg", 7);
        store.addPlayTime("Vlad", 6);

        String actual = store.getMostPlayer();

        assertEquals("Vlad", actual);
    }

}