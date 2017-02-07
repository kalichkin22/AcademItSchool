package ru.academits.kalichkin.main;

import ru.academits.kalichkin.range.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range interval = new Range(1, 5);
        Range interval2 = new Range(3, 10);

        Range interval3 = interval.getIntersection(interval2);
        if (interval3 == null) {
            System.out.println("Пересечений интервалов нет");
        } else {
            System.out.printf("Результат пересечения интервалов: [%.1f, %.1f]%n", interval3.getFrom(), interval3.getTo());
        }

        Range[] interval4 = interval.getAssociation(interval2);
        System.out.println("Результат объединения интервалов: " + Arrays.toString(interval4));

        Range[] interval5 = interval.getDifference(interval2);
        if (interval5 == null) {
            System.out.println("Результат разности интервалов равен нулю");
        } else {
            System.out.println("Результат разности интервалов: " + Arrays.toString(interval5));

        }
    }
}