package ru.academits.kalichkin.main;
import ru.academits.kalichkin.range.*;

public class Main {
    public static void main(String[] args) {
        Range interval = new Range(1, 3);
        Range interval2 = new Range (2, 6);
        Range interval3 = new Range (0, 0);
        System.out.println(interval3.intersectionInterval(interval,interval2));

    }
}