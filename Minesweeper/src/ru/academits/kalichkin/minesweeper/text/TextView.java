package ru.academits.kalichkin.minesweeper.text;

import ru.academits.kalichkin.minesweeper.common.*;
import ru.academits.kalichkin.minesweeper.model.PersonWin;
import ru.academits.kalichkin.minesweeper.model.Action;
import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;

import java.io.FileNotFoundException;
import java.util.*;

public class TextView implements View {
    private ViewListener listener;
    private Scanner scanner = new Scanner(System.in);
    private final String scoresFileName = "scores.txt";

    private void initEvents() {
        while (true) {
            System.out.println(
                    "1. Начать игру" + System.lineSeparator() +
                            "2. Таблица рекордов" + System.lineSeparator() +
                            "3. Выйти"
            );
            System.out.println("Выберете номер операции: ");
            int numberCommand = scanner.nextInt();

            switch (numberCommand) {
                case 1:
                    System.out.println("Выберете уровень игры: ");
                    System.out.println("1. Новичек " + System.lineSeparator() +
                            "2. Любитель" + System.lineSeparator() +
                            "3. Профессионал" + System.lineSeparator() +
                            "4. Пользовательский");
                    setLevel(scanner.nextInt());
                    setClick();
                    break;
                case 2:
                    try {
                        printHighScores(listener.needReadScores(scoresFileName));
                    } catch (FileNotFoundException e) {
                        System.out.printf("Файл %s не найден", listener.needScoresFilename());
                    }
                    System.out.println();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неизвестная операция, попробуйте еще раз.");
            }
        }
    }


    @Override
    public void startApplication() {
        initEvents();
    }


    @Override
    public void onDraw(Field field) {
        for (int i = 0; i < field.getFieldColumn() + 1; i++) {
            if (i < 10) {
                System.out.printf("%2d ", i);
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();
        for (int i = 0; i < field.getFieldRow(); i++) {
            if (i < 9) {
                System.out.print(i + 1 + "  ");
            } else {
                System.out.print(i + 1 + " ");
            }
            for (int j = 0; j < field.getFieldColumn(); j++) {
                System.out.print(field.getCell(i, j));
            }
            System.out.println();
        }
    }


    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
    }


    @Override
    public boolean onCheckFinish(Click click) {
        boolean isFinish = false;
        Scanner scanner = new Scanner(System.in);
        if (listener.needCheckDefeat(click)) {
            listener.needShowAll();
            listener.needDraw();
            System.out.println("Вы проиграли!");
            System.out.println("Угадано мин: " + listener.getCountFlagTrue() + System.lineSeparator());
            isFinish = true;
        }

        if (listener.needCheckWin()) {
            String time = listener.needStopTimer();
            listener.needShowAll();
            listener.needDraw();
            System.out.println("Вы выиграли!" + System.lineSeparator());
            System.out.println("Введите свое имя: ");
            String name = scanner.nextLine();
            try {
                listener.needWriteScores(scoresFileName, name, time);
            } catch (FileNotFoundException e) {
                System.out.printf("Файл %s не найден", listener.needScoresFilename());
            }
            isFinish = true;
        }
        return isFinish;
    }


    @Override
    public Action onAction(int button) {
        if (button == 0) {
            return Action.OPEN;
        } else if (button == 1) {
            return Action.SET_MARKED;
        } else if (button == 2) {
            return Action.OPEN_AROUND;
        }
        return Action.NOT_ACTION;
    }


    private void setLevel(int number) {
        switch (number) {
            case 1:
                listener.needBeginnerLevel();
                break;
            case 2:
                listener.needIntermediateLevel();
                break;
            case 3:
                listener.needExpertLevel();
                break;
            case 4:
                System.out.println("Введите количество строк, не менее 8 и не более 24: ");
                int row = scanner.nextInt();

                System.out.println("Введите количество столбцов, не менее 8 и не более 30: ");
                int column = scanner.nextInt();

                System.out.println("Введите количество мин: ");
                int numberOfMines = scanner.nextInt();
                try {
                    listener.needUserLevel(row, column, numberOfMines);
                } catch (IllegalArgumentException e) {
                    System.out.println("Границы поля должны быть не меньше, чем 8х8 и не больше, чем 24х30");
                    return;
                }
                break;
            default:
                System.out.println("Такого уровня нет, попробуйте еще раз!");
        }
    }

    private void setClick() {
        Click click;
        do {
            listener.needDraw();
            listener.needStartTimer();

            System.out.println("Введите номер команды: " + System.lineSeparator() +
                    "0. Открыть ячейку" + System.lineSeparator() +
                    "1. Поставить флаг" + System.lineSeparator() +
                    "2. Открыть соседние ячейки");
            int button = scanner.nextInt();
            System.out.println("Если хотите отменить выбор, нажмите 0, если нет, то любую цифру");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Введите номер команды: " + System.lineSeparator() +
                        "0. Открыть ячейку" + System.lineSeparator() +
                        "1. Поставить флаг" + System.lineSeparator() +
                        "2. Открыть соседние ячейки");
                button = scanner.nextInt();
            }
            System.out.println("Введите кооридинаты ячейки: ");
            System.out.println("Строка: ");

            int row = scanner.nextInt() - 1;

            System.out.println("Столбец: ");
            int column = scanner.nextInt() - 1;


            Action action = listener.needAction(button);
            click = new Click(row, column, action);


        } while (!listener.needClick(click));
    }

    private void printHighScores(List<PersonWin> list) {
        System.out.println("Таблица рекордов: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s - %s", i + 1, list.get(i).getName(), list.get(i).getTime() + System.lineSeparator());
        }
    }

}

