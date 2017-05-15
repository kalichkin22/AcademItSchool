package ru.academits.kalichkin.minesweeper.text;

import ru.academits.kalichkin.minesweeper.common.View;
import ru.academits.kalichkin.minesweeper.common.ViewListener;
import ru.academits.kalichkin.minesweeper.model.Click;
import ru.academits.kalichkin.minesweeper.model.Field;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Text implements View {
    private ViewListener listener;

    private void initEvents() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                try {
                    listener.needDraw();

                    System.out.println("Введите кооридинаты ячейки: ");
                    System.out.println("Строка: ");

                    int row = scanner.nextInt() - 1;

                    System.out.println("Столбец: ");
                    int column = scanner.nextInt() - 1;

                    System.out.println("Если хотите открыть ячейку введите 0, если поставить флаг, то 1");
                    int button = scanner.nextInt();

                    Click click = new Click(row, column, button);

                    listener.needClick(click);

                    if (listener.needCheckDefeat(click)) {
                        listener.needShowAll();
                        listener.needDraw();
                        System.out.println("Вы проиграли!");
                        System.out.println("Угадано мин: " + listener.getCountFlagTrue());
                        return;
                    }

                    if (listener.needCheckWin()) {
                        listener.needDraw();
                        System.out.println("Вы проиграли!");
                        return;
                    }


                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Выход за границы поля, попробуйте еще раз.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Номер клавиши должен быть 0 или 1");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Координаты ячейки должны быть цифрой");
        }
    }


    @Override
    public void startApplication() {
        listener.needSetMines();
        listener.needSetNumberMinesNear();
        initEvents();
    }


    @Override
    public void onDraw(Field field) {
        for (int i = 0; i < field.size() + 1; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < field.size(); i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < field.size(); j++) {
                System.out.print(field.getCell(i, j));
            }
            System.out.println();
        }
    }


    @Override
    public boolean onCheckDefeat(Click click) {
        return listener.needGetCell(click.row, click.column).isMine()
                && !listener.needGetCell(click.row, click.column).isFlag() && listener.needGetCell(click.row, click.column).isOpen();
    }


    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
    }


}

