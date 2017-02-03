package ru.academits.kalichkin.range;


public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double lengthInterval(double from, double to) {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= getFrom() && number <= getTo();
    }

    public Range intersectionInterval(Range range, Range range2) {

        Range range3 = new Range(0,0);

        if (range.getTo() < range2.getFrom() || range2.getTo() < range.getFrom()) {
            return null;
        }

        if (range.getTo() >= range2.getFrom() && range2.getFrom() > range.getFrom()) {
            range3.setFrom(range2.getFrom());
        }
        else if (range.getTo() >= range2.getFrom() && range2.getTo() > range.getTo()) {
            range3.setTo(range.getTo());
        }
        return range3;
    }
}