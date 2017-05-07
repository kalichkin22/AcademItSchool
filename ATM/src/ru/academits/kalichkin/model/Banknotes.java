package ru.academits.kalichkin.model;


public class Banknotes {
    private int nominal;
    private int count;

    public Banknotes(int nominal, int count) {
        this.nominal = nominal;
        this.count = count;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString() {
        return "(" + nominal + ", " + count + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Banknotes p = (Banknotes) o;

        return nominal == p.nominal && count == p.count;
    }
}
