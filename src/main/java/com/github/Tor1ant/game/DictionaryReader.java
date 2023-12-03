package com.github.Tor1ant.game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class DictionaryReader {

    private final String pathToDictionary;

    public DictionaryReader(String pathToDictionary) {
        this.pathToDictionary = pathToDictionary;
    }

    public String getRandomWord() {
        String randomWord;
        try {
            List<String> words = Files.readAllLines(Path.of(pathToDictionary), StandardCharsets.UTF_8);
            Random random = new Random();
            randomWord = words.get(random.nextInt(words.size()));
        } catch (IOException e) {
            System.out.println("Словарь не найден");
            throw new RuntimeException(e);
        }
        return randomWord.toLowerCase();
    }
}
