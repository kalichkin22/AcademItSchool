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

        if (number > getTo() || number < getFrom()) {
            return false;
        }
        return true;
    }
}