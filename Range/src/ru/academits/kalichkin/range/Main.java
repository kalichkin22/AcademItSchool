package ru.academits.kalichkin.range;

public class Main {
    public static void main(String[] args) {
        Range interval = new Range(-10, 8);
        System.out.println(interval.isInside(5.004));
    }
}
