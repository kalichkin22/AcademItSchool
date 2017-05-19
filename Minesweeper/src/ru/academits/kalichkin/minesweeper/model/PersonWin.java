package ru.academits.kalichkin.minesweeper.model;

import java.util.Objects;


public class PersonWin {
    private String name;
    private String time;

    PersonWin(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return name + " " + time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PersonWin p = (PersonWin) obj;

        return Objects.equals(name, p.name) && Objects.equals(time, p.time);
    }
}
