package com.github.Tor1ant.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Hangman {

    private final DictionaryReader dictionaryReader;
    private final StatisticsWriter statisticsWriter;
    private final StatisticsReader statisticsReader;
    private String hiddenWord;
    private int wrongAnswerCounter = 0;
    private int correctAnswers = 0;
    private Map<Character, List<Integer>> charsWithPositions;

    public Hangman(DictionaryReader dictionaryReader, StatisticsWriter statisticsWriter,
            StatisticsReader statisticsReader) {
        this.dictionaryReader = dictionaryReader;
        this.statisticsWriter = statisticsWriter;
        this.statisticsReader = statisticsReader;
    }

    public void play() {
        List<Character> guessedChars = new ArrayList<>();
        Set<Character> wrongAnswers = new HashSet<>();
        hiddenWord = dictionaryReader.getRandomWord();
        printStartTable();
        while (true) {
            char answer = readChar();
            if (!hiddenWord.contains(String.valueOf(answer))) {
                if (!wrongAnswers.contains(answer)) {
                    wrongAnswers.add(answer);
                    wrongAnswerCounter++;
                }
                System.out.println(
                        "Такой буквы нет в загаданном слове. У вас осталось " + (9 - wrongAnswerCounter) + " попыток");
                if (wrongAnswerCounter == 9) {
                    System.out.println("Вы проиграли");
                    System.out.println("Загаданное слово: " + hiddenWord);
                    statisticsWriter.writeLose();
                    statisticsReader.printLoses();
                    break;
                }
            }

            if (hiddenWord.contains(String.valueOf(answer))) {
                guessedChars.add(answer);
                charsWithPositions = getCharPositions(guessedChars);
                correctAnswers += charsWithPositions.get(answer).size();
                if (correctAnswers == hiddenWord.length()) {
                    System.out.printf("Поздравляем! Вы угадали слово: \"%s\" ", hiddenWord);
                    statisticsWriter.writeWin();
                    statisticsReader.printWins();
                    System.out.println("Желаете сыграть ещё раз? \"y\"\\\"n\"");
                    String playAgain = String.valueOf(readChar());
                    if (playAgain.equalsIgnoreCase("y")) {
                        play();
                    } else {
                        System.out.println("Спасибо за игру! До свидания!");
                        System.exit(0);
                    }
                }
                System.out.println("Вы угадали одну из букв:");
            }
            printTable(correctAnswers, charsWithPositions);
        }
    }

    public void printStartTable() {
        System.out.printf("Начнем игру! \n Я загадал слово из %s букв. Попробуй его угадать!\n",
                hiddenWord.length());
        printTable(correctAnswers, charsWithPositions);
    }

    private void printTable(int correctAnswers, Map<Character, List<Integer>> charsWithPositions) {
        String hiddenChars;
        StringBuilder wordToPrint;

        hiddenChars = IntStream.range(0, hiddenWord.length())
                .mapToObj(i -> "_")
                .collect(Collectors.joining());

        if (correctAnswers == 0) {
            System.out.println(hiddenChars);
            return;
        }

        wordToPrint = new StringBuilder(hiddenChars);
        charsWithPositions.forEach((character, positions) -> {
            for (int i = 0; i < hiddenWord.length(); i++) {
                if (positions.contains(i)) {
                    wordToPrint.replace(i, i + 1, character.toString());
                }
            }
        });

        System.out.println(wordToPrint);
    }

    private Map<Character, List<Integer>> getCharPositions(List<Character> guessedChars) {
        Map<Character, List<Integer>> characterIntegerMap = new HashMap<>(hiddenWord.length());
        guessedChars.forEach(character -> {
            List<Integer> position = new ArrayList<>();
            char[] charArray = hiddenWord.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                if (character != null) {
                    if (character == charArray[i]) {
                        position.add(i);
                        characterIntegerMap.put(character, position);
                    }
                }
            }
        });
        return characterIntegerMap;
    }

    private Character readChar() {
        Scanner scanner = new Scanner(System.in);
        String s;
        System.out.println("Введите 1 букву");
        while (true) {
            s = scanner.nextLine();

            if (s.length() > 1) {
                System.out.println("Вы ввели больше одной буквы, попробуйте ещё раз.");

            } else if (!Character.isAlphabetic(s.charAt(0))) {
                System.out.println("Вы ввели символ, который не является буквой.");
            } else {
                break;
            }
        }
        return s.charAt(0);
    }
}
