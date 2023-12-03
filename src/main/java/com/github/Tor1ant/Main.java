package com.github.Tor1ant;

import com.github.Tor1ant.game.GameSettings;
import com.github.Tor1ant.game.StatisticsReader;

public class Main {

    public static void main(String[] args) {
        GameSettings gameSettings = new GameSettings(new StatisticsReader("src/main/resources/"));
        gameSettings.menu();
    }
}