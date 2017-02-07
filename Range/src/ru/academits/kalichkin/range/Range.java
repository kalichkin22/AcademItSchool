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

    public double getLength(double from, double to) {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= getFrom() && number <= getTo();
    }

    public String toString() {
        return "(" + from + "," + to + ")";
    }

    public Range getIntersection(Range range) {
        if (Math.min(to, range.to) < Math.max(from, range.from)) {
            return null;
        } else {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }
    }

    public Range[] getAssociation(Range range) {
        if (Math.min(to, range.to) < Math.max(from, range.from)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.min(to, range.to)),
                    new Range(Math.max(from, range.from), Math.max(to, range.to))};
        } else {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }
    }

    public Range[] getDifference(Range range) {
        if (range.from < from && to < range.to) {
            return null;
        } else if (from > range.from && to > range.to && from < range.to) {
            return new Range[]{new Range(range.to, to)};
        } else if (range.from < from && range.to < to) {
            return new Range[]{new Range(from, to)};
        } else if (to < range.from) {
            return new Range[]{new Range(from, to)};
        } else if (range.from < to && range.to > to) {
            return new Range[]{new Range(from, range.from)};
        } else {
            return new Range[]{(new Range(from, range.from)), new Range(range.to, to)};
        }
    }
}