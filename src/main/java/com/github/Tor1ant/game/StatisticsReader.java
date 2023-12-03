package com.github.Tor1ant.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StatisticsReader {

    private final String pathToDirectory;

    public StatisticsReader(String pathToDirectory) {
        this.pathToDirectory = pathToDirectory;
    }

    public void printWins() {
        String wins;
        try {
            wins = Files.readString(Path.of(pathToDirectory + "wins.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (wins.isBlank() || wins.isEmpty()) {
            System.out.println("Вы ещё не побеждали");
            return;
        }
        System.out.printf("Вы выиграли %s раз", wins);
    }

    public void printLoses() {
        String loses;
        try {
            loses = Files.readString(Path.of(pathToDirectory + "loses.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (loses.isBlank() || loses.isEmpty()) {
            System.out.println("Вы ещё не проигрывали");
            return;
        }
        System.out.printf("Вы проиграли %s раз\n", loses);
    }
}
