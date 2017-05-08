package ru.academits.kalichkin.controller;


import ru.academits.kalichkin.common.View;
import ru.academits.kalichkin.common.ViewListener;
import ru.academits.kalichkin.model.Account;

public class Controller implements ViewListener {
    private final View view;
    private final Account account;

    public Controller(Account account, View view) {
        this.view = view;
        this.account = account;
    }


    @Override
    public void needGetBalance() {
        view.onBalance(account.getBalance());
    }

    @Override
    public int getBalance() {
        return account.getBalance();
    }

    @Override
    public void needGetBanknote() {
        view.onGetBanknote(account.getBanknotes());
    }

    @Override
    public void needDeposit(int nominalDeposit, int countDeposit) {
        account.deposit(nominalDeposit, countDeposit);
    }

    @Override
    public void needWithDraw(int sum, int banknote) {
        view.onWithDraw(account.withDraw(sum, banknote));
    }

    @Override
    public int needGetMinNominal() {
        return account.getMinNominal();
    }

    @Override
    public Integer[] needGetNominalBanknote() {
        return account.getNominalBanknote();
    }

    @Override
    public int needValidCountBanknote() {
        return Account.MAX_COUNT_BANKNOTES - account.getCountAllBanknotes();
    }


}
