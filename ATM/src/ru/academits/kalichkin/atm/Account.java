package ru.academits.kalichkin.atm;

import ru.academits.kalichkin.exception.NotSuchCountBanknote;
import ru.academits.kalichkin.exception.NotSuchNominal;
import ru.academits.kalichkin.exception.TooMuchException;
import ru.academits.kalichkin.exception.TooMuchSum;

import java.util.*;

public class Account {
    private LinkedList<Banknotes> cash;
    private static final int MAX_BANKNOTES = 100;

    public Account() {
        cash = new LinkedList<>(Arrays.asList(new Banknotes(50, 10),
                new Banknotes(100, 10), new Banknotes(500, 10),
                new Banknotes(1000, 10), new Banknotes(5000, 10)));
    }

    public int getFirst() {
        return cash.peekFirst().getNominal();
    }

    public int getBalance() {
        int balance = 0;
        for (Banknotes aCash : cash) {
            balance += aCash.getNominal() * aCash.getCount();
        }
        return balance;
    }

    private int getCountBanknotes() {
        int count = 0;
        for (Banknotes aCash : cash) {
            count += aCash.getCount();
        }
        return count;
    }

    public boolean deposit(int nominal, int count) {
        int multipleNominal = 50;
        if (nominal % multipleNominal != 0) {
            throw new NoSuchElementException();
        }

        int maxCount = MAX_BANKNOTES - getCountBanknotes();
        if (count + getCountBanknotes() > MAX_BANKNOTES) {
            throw new TooMuchException();
        }

        if (!cash.contains(new Banknotes(nominal, 0))) {
            if (nominal == 50 || nominal == 100 || nominal == 500 || nominal == 1000 || nominal == 5000) {
                cash.add(new Banknotes(nominal, 0));
            } else {
                throw new NoSuchElementException();
            }
        }

        for (Banknotes banknote : cash) {
            if (nominal == banknote.getNominal()) {
                banknote.setCount(banknote.getCount() + count);
                return true;
            }
        }
        return false;
    }


    public void withDraw(int sum, int nominal) {
        if (sum > getBalance()) {
            throw new TooMuchSum();
        }

        if (sum % cash.peekFirst().getNominal() != 0) {
            throw new NotSuchNominal();
        }

        if (nominal % cash.peekFirst().getNominal() != 0) {
            throw new NoSuchElementException();
        }

        if ((sum - cash.getFirst().getCount() * cash.getFirst().getNominal()) % 100 == cash.getFirst().getNominal()
                && nominal == cash.getFirst().getNominal()) {
            throw new NotSuchCountBanknote();
        }

        int newNominal = nominal;
        int countBanknote;

        while (sum != 0) {
            for (int i = 0; i < cash.size(); i++) {
                if (newNominal == cash.get(i).getNominal()) {
                    countBanknote = 0;
                    countBanknote += sum / newNominal;

                    if (countBanknote <= cash.get(i).getCount()) {
                        cash.get(i).setCount(cash.get(i).getCount() - countBanknote);
                        sum = sum - cash.get(i).getNominal() * countBanknote;
                        if (sum != 0 && sum < newNominal && cash.get(i).getNominal() != cash.peekFirst().getNominal()) {
                            newNominal = cash.get(i - 1).getNominal();
                        } else if (sum > 0 && sum < newNominal) {
                            throw new NotSuchCountBanknote();
                        }
                    } else {
                        int count = cash.get(i).getCount();
                        sum = sum - cash.get(i).getNominal() * count;
                        cash.get(i).setCount(0);
                        cash.remove(cash.get(i));
                        newNominal = cash.peekLast().getNominal();
                    }
                }
            }
        }
    }
}








