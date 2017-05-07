package ru.academits.kalichkin.common;


public interface ViewListener {
    void needGetBalance();

    int getBalance();

    void needGetBanknote();

    void needDeposit(int nominalDeposit, int countDeposit);

    void needWithDraw(int sum, int banknote);

    int needGetMinNominal();

    Integer[] needGetNominalBanknote();

    int needValidCountBanknote();
}
