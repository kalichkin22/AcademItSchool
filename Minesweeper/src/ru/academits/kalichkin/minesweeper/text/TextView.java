package ru.academits.kalichkin.minesweeper.text;

import ru.academits.kalichkin.minesweeper.common.*;
import ru.academits.kalichkin.minesweeper.controller.Controller;
import ru.academits.kalichkin.minesweeper.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TextView implements View {
    private ViewListener listener;
    private Scanner scanner = new Scanner(System.in);
    private TimerGame timerGame = new TimerGame();

    public static String USER_NAME;

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
                        printHighScores(listener.needReadScores(Controller.SCORES_FILE_NAME));
                    } catch (FileNotFoundException e) {
                        System.out.printf("Файл %s не найден", Controller.SCORES_FILE_NAME);
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
        System.out.print("   ");
        for (int i = 1; i < field.getFieldColumn() + 1; i++) {
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

    @Override
    public void onDefeat() {
        try {
            listener.needDraw();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Вы проиграли!");
        System.out.println("Угадано мин: " + listener.getCountFlagTrue() + System.lineSeparator());
    }

    @Override
    public String onIsWin() {
        String name = "";
        while (name.equals("")) {
            try {
                listener.needDraw();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Вы выиграли!" + System.lineSeparator());
            System.out.println("Введите свое имя: ");
            name = scanner.nextLine();
        }
        return name;
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
                    setLevel(4);
                }
                break;
            default:
                System.out.println("Такого уровня нет, попробуйте еще раз!");
        }
    }

    private void setClick() {
        Click click;
        try {
            do {
                listener.needDraw();
                System.out.println("Введите номер команды: " + System.lineSeparator() +
                        "0. Открыть ячейку" + System.lineSeparator() +
                        "1. Поставить флаг" + System.lineSeparator() +
                        "2. Открыть соседние ячейки");

                int button = scanner.nextInt();

                System.out.println("Введите кооридинаты ячейки: ");
                System.out.println("Если хотите отменить команду, введите -1");
                System.out.println("Строка: ");
                int row;
                int rowCancel = scanner.nextInt();
                if (rowCancel == -1) {
                    return;
                } else {
                    row = rowCancel - 1;
                }

                System.out.println("Столбец: ");
                int column;
                int columnCancel = scanner.nextInt();

                if (columnCancel == -1) {
                    return;
                } else {
                    column = columnCancel - 1;
                }

                Action action = listener.needAction(button);
                click = new Click(row, column, action);
                listener.needSetTimer(timerGame);
            } while (listener.needClick(click));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за границы поля, попробуйте еще раз");
            setClick();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printHighScores(List<PersonWin> list) {
        System.out.println("Таблица рекордов: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s - %s", i + 1, list.get(i).getName(), list.get(i).getTime() + System.lineSeparator());
        }
    }

}

