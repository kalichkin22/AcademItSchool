package ru.academits.kalichkin.main;

import ru.academits.kalichkin.range.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range interval = new Range(6, 9);
        Range interval2 = new Range(3, 5);

        Range interval3 = interval.getIntersection(interval2);
        if (interval3 == null) {
            System.out.println("Пересечений интервалов нет");
        } else {
            System.out.printf("Результат пересечения интервалов: [%.1f, %.1f]%n", interval3.getFrom(), interval3.getTo());
        }

        Range[] interval4 = interval.getAssociation(interval2);
        System.out.println("Результат объединения интервалов: " + Arrays.toString(interval4));

        Range [] interval5 = interval.getDifference(interval2);
        System.out.println("Результат разности интервалов: " + Arrays.toString(interval5));

    }
}