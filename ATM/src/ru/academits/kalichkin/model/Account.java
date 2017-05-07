package ru.academits.kalichkin.model;

import ru.academits.kalichkin.exception.*;

import java.lang.reflect.Array;
import java.util.*;

public class Account {
    private final ArrayList<Banknotes> cash;
    public static final int MAX_COUNT_BANKNOTES = 100;

    public Account() {
        cash = new ArrayList<>(Arrays.asList(new Banknotes(10, 10), new Banknotes(50, 10),
                new Banknotes(100, 10), new Banknotes(500, 10),
                new Banknotes(1000, 10), new Banknotes(5000, 10)));
    }


    public ArrayList<Banknotes> getBanknotes() {
        return new ArrayList<>(cash);
    }


    private void validateNominal(ArrayList<Banknotes> cash, int nominal) {
        for (Banknotes banknote : cash) {
            if (banknote.getNominal() == nominal) {
                return;
            }
        }
        throw new NoSuchElementException();
    }


    public int getMinNominal() {
        for (Banknotes banknote : cash) {
            if (banknote.getCount() != 0) {
                return banknote.getNominal();
            }
        }
        throw new NotHaveBanknotesException();
    }

    public int getCountMinNominal() {
        int count = 0;
        for (Banknotes banknote : cash) {
            if (banknote.getCount() != 0) {
                count = banknote.getCount();
                return count;
            }
        }
        return count;
    }

    public Integer[] getNominalBanknote() {
        Integer[] items = new Integer[cash.size()];
        for (int i = 0; i < cash.size(); i++) {
            items[i] = cash.get(i).getNominal();
        }
        return items;
    }


    public int getBalance() {
        int balance = 0;
        for (Banknotes banknote : cash) {
            balance += banknote.getNominal() * banknote.getCount();
        }
        return balance;
    }


    public int getCountAllBanknotes() {
        int count = 0;
        for (Banknotes aCash : cash) {
            count += aCash.getCount();
        }
        return count;
    }


    private Banknotes findBanknote(int nominalBanknote) {
        Banknotes banknote = null;
        for (Banknotes nominal : cash) {
            if (nominalBanknote == nominal.getNominal()) {
                banknote = nominal;
            }
        }
        return banknote;
    }


    public boolean deposit(int nominal, int count) {
        if (count + getCountAllBanknotes() > MAX_COUNT_BANKNOTES) {
            throw new TooMuchCountBanknoteException();
        }

        validateNominal(cash, nominal);

        for (Banknotes banknote : cash) {
            if (nominal == banknote.getNominal()) {
                banknote.setCount(banknote.getCount() + count);
                return true;
            }
        }
        return false;
    }


    public ArrayList<Banknotes> withDraw(int sum, int nominal) {
        if (getBalance() == 0) {
            throw new NotHaveBanknotesException();
        }

        validateNominal(cash, nominal);

        Banknotes banknote = new Banknotes(nominal, 0);
        if (cash.contains(banknote)) {
            throw new NotSuchCountBanknoteException();
        }

        if (sum > getBalance()) {
            throw new TooMuchSumException();
        }

        int firstBanknoteNominal = cash.get(0).getNominal();
        if (sum % firstBanknoteNominal != 0) {
            throw new NotSuchNominalException();
        }


        int newNominal = nominal;
        ArrayList<Banknotes> cashWithDraw = new ArrayList<>();

        while (sum != 0) {
            for (int i = 0; i < cash.size(); i++) {
                if (newNominal == cash.get(i).getNominal()) {
                    if (sum / nominal > cash.get(i).getNominal()) {
                        throw new NotSuchCountBanknoteException();
                    }
                    int countBanknote = 0;
                    countBanknote += sum / newNominal;

                    if (countBanknote <= cash.get(i).getCount()) {
                        cash.get(i).setCount(cash.get(i).getCount() - countBanknote);
                        sum = sum - cash.get(i).getNominal() * countBanknote;
                        if (sum != 0 && sum < newNominal && cash.get(i).getNominal() != firstBanknoteNominal) {
                            newNominal = cash.get(i - 1).getNominal();
                        } else if (sum > 0 && sum < newNominal) {
                            throw new NotSuchCountBanknoteException();
                        }
                        if (countBanknote == 0) {
                            break;
                        }
                        Banknotes banknoteWithDraw = new Banknotes(cash.get(i).getNominal(), countBanknote);
                        cashWithDraw.add(banknoteWithDraw);

                    } else {
                        if (cash.get(i).getCount() == 0 && cash.get(i).getNominal() != firstBanknoteNominal) {
                            newNominal = cash.get(i - 1).getNominal();
                            break;
                        }
                        sum = sum - cash.get(i).getNominal() * cash.get(i).getCount();
                        Banknotes banknoteWithDraw = new Banknotes(cash.get(i).getNominal(), cash.get(i).getCount());
                        newNominal = cash.get(cash.size() - 1).getNominal();
                        cash.get(i).setCount(0);
                        cashWithDraw.add(banknoteWithDraw);
                    }
                }
            }
        }
        return cashWithDraw;
    }

    public String toString() {
        return Arrays.toString(cash.toArray());
    }
}








