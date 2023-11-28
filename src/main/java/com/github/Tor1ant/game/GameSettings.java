package com.github.Tor1ant.game;

import java.util.Scanner;

public class GameSettings {

    private final Hangman hangman;
    private final StatisticsReader statisticsReader;

    public GameSettings(StatisticsReader statisticsReader) {
        this.statisticsReader = statisticsReader;
        hangman = new Hangman(
                new DictionaryReader("src/main/resources/longwords.txt"),
                new StatisticsWriter("src/main/resources/"),
                statisticsReader);
    }

    public void menu() {
        System.out.println("Здравствуйте! Хотите сыграть в виселицу? y/n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("Выберите режим игры:");
                System.out.println("1) показать статистику");
                System.out.println("2) играть в игру\n");
                System.out.println("Введите n, чтобы выйти из игры");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("1")) {
                    statisticsReader.printWins();
                    statisticsReader.printLoses();
                } else if (input.equalsIgnoreCase("2")) {
                    printBaseGameRules();
                    hangman.play();
                } else if (input.equalsIgnoreCase("n")) {
                    System.out.println("Очень жаль, до встречи!");
                    break;
                } else {
                    System.out.println("такого режима не существует.");
                }
            }
        } else {
            System.out.println("Очень жаль, до встречи!");
        }
    }

    public void printBaseGameRules() {
        System.out.println("Висельник загадывает слово, которое вам необходимо отгадать за 9 попыток. Если игрок "
                + "отгадывает букву, то игровая доска обновляется, буква открывается на том месте, на котором она "
                + "находится в слове, а игрок не теряет одну попытку на отгадывание слова. Если игроку удаётся угадать "
                + "слово, информация об этом запишется в файл статистики. Если слово угадать не удастся, информация об "
                + "этом так же запишется в статистику. Удачи!");
    }
}
