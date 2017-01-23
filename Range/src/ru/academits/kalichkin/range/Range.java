package ru.academits.kalichkin.range;


class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom () {
        return from;
    }

    public void setFrom (double from) {
        this.from = from;
    }

    public double getTo () {
        return to;
    }

    public void setTo (double to) {
        this.to = to;
    }

    public boolean isInside(double number) {
        if (number > getTo()) {
            return false;
        } else if (number < getFrom()) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        Range interval = new Range(-10, 8);
        System.out.println(interval.isInside(-10));

    }
}