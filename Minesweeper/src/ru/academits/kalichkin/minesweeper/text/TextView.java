package ru.academits.kalichkin.minesweeper.text;

import ru.academits.kalichkin.minesweeper.common.HighScores;
import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.Action;
import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;


import java.util.InputMismatchException;
import java.util.Scanner;

public class TextView implements View {
    private ViewListener listener;
    private Click click;

    private void initEvents() {
        Scanner scanner = new Scanner(System.in);
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


                    int number = scanner.nextInt();
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
                            return;
                    }

                    try {
                        do {
                            try {
                                listener.needDraw();

                                System.out.println("Введите кооридинаты ячейки: ");
                                System.out.println("Строка: ");

                                int row = scanner.nextInt() - 1;

                                System.out.println("Столбец: ");
                                int column = scanner.nextInt() - 1;

                                System.out.println("Введите номер команды: " + System.lineSeparator() +
                                        "0. Открыть ячейку" + System.lineSeparator() +
                                        "1. Поставить флаг" + System.lineSeparator() +
                                        "2. Открыть соседние ячейки");
                                int button = scanner.nextInt();

                                Action action = listener.needAction(button);
                                click = new Click(row, column, action);

                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Выход за границы поля, попробуйте еще раз.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Номер клавиши должен быть 0 или 1");
                            }

                        } while (!listener.needClick(click));
                    } catch (InputMismatchException e) {
                        System.out.println("Координаты ячейки должны быть цифрой");
                    }
                    break;
                case 2:
                    System.out.println(HighScores.readScores());
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
        for (int i = 0; i < field.getFieldRow(); i++) {
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
            System.out.println("Введите свое имя: ");
            String name = scanner.nextLine();
            //HighScores.writeScores(name, listener.getCountFlagTrue());
            isFinish = true;
        }

        if (listener.needCheckWin()) {
            listener.needShowAll();
            listener.needDraw();
            System.out.println("Вы выиграли!" + System.lineSeparator());
            System.out.println("Введите свое имя: ");
            String name = scanner.nextLine();
            //HighScores.writeScores(name, listener.getCountFlagTrue());
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

}

