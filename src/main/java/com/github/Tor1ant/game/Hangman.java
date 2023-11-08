package com.github.Tor1ant.game;

public class Hangman {

    private final DictionaryReader dictionaryReader;
    private String hiddenWord;

    public Hangman(DictionaryReader dictionaryReader) {
        this.dictionaryReader = dictionaryReader;
    }

    public void play() {
        hiddenWord = dictionaryReader.getRandomWord();
        System.out.println("Hidden word: " + hiddenWord);
        printStartTable();
    }

    public void printStartTable() {
        System.out.printf("Начнем игру! \n Я загадал слово из %s букв. Попробуй его угадать\n", hiddenWord.length());
        for (int i = 0; i < hiddenWord.length(); i++) {
            System.out.print("_");
        }
    }
}
