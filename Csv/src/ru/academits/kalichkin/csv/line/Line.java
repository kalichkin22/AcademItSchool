package ru.academits.kalichkin.csv.line;

public class Line {

    private String firstCell;
    private String secondCell;
    private String thirdCell;

    public String getFirstCell() {
        return firstCell;
    }

    public void setFirstCell(String firstCell) {
        this.firstCell = firstCell;
    }

    public String getSecondCell() {
        return secondCell;
    }

    public void setSecondCell(String secondCell) {
        this.secondCell = secondCell;
    }

    public String getThirdCell() {
        return thirdCell;
    }

    public void setThirdCell(String thirdCell) {
        this.thirdCell = thirdCell;
    }


    @Override
    public String toString() {
        return System.lineSeparator() + "<tr>" + "<td>" +
                getFirstCell() + "</td>" + "<td>" + getSecondCell() + "</td>" + "<td>"
                + getThirdCell() + "</td>" + "</tr>" + "<br/>";
    }
}