package com.github.Tor1ant.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StatisticsWriter {

    private final String pathToDirectory;

    public StatisticsWriter(String pathToDirectory) {
        this.pathToDirectory = pathToDirectory;
    }

    public void writeWin() {
        String wins;
        Path path = Path.of(pathToDirectory + "wins.txt");
        try {
            wins = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (wins.isBlank() || wins.isEmpty()) {
            try {
                Files.writeString(path, "1", StandardOpenOption.CREATE);
                System.out.println("Поздравляем с первой победой!");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        int winCount = Integer.parseInt(wins);
        winCount++;
        try {
            Files.writeString(path, String.valueOf(winCount), StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Поздравляем с победой номер " + winCount + "!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLose() {
        Path path = Path.of(pathToDirectory + "loses.txt");
        try {
            String loses = Files.readString(path);

            if (loses.isEmpty() || loses.isBlank()) {
                Files.writeString(path, "1", StandardOpenOption.CREATE);
                System.out.println("К сожалению, это первое поражение.");
                return;
            }
            int loseCount = Integer.parseInt(loses);
            loseCount++;
            Files.writeString(path, String.valueOf(loseCount), StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("К сожалению, это поражение номер " + loseCount + ".");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
