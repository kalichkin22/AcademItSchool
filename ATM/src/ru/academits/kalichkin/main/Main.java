package ru.academits.kalichkin.main;

import ru.academits.kalichkin.atm.Account;
import ru.academits.kalichkin.atm.Banknotes;
import ru.academits.kalichkin.exception.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Добрый день!" + System.lineSeparator() +
                "Вас приветствует Банк Банков!");
        while (true) {
            try {
                System.out.println(
                        "1. Узнать баланс счета" + System.lineSeparator() +
                                "2. Пополнить баланс" + System.lineSeparator() +
                                "3. Снять деньги" + System.lineSeparator() +
                                "4. Выйти"
                );
                System.out.println("Выберете номер операции: ");
                int numberCommand = scanner.nextInt();

                switch (numberCommand) {
                    case 1:
                        System.out.println("Баланс составляет: " + account.getBalance());
                        System.out.println("Лимит банкомата на число купюр " + Account.MAX_COUNT_BANKNOTES);
                        countBanknote(account.getBanknotes());
                        System.out.println("Всего банкнот: " + account.getCountAllBanknotes());
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("Введите номинал банкноты: ");
                        int nominalDeposit = scanner.nextInt();

                        System.out.println("Введите количество банкнот: ");
                        int countDeposit = scanner.nextInt();

                        if (account.deposit(nominalDeposit, countDeposit)) {
                            System.out.println("Баланс: " + account.getBalance());
                        }
                        System.out.println("Лимит банкомата на число купюр: " + Account.MAX_COUNT_BANKNOTES);
                        countBanknote(account.getBanknotes());
                        System.out.println("Всего банкнот: " + account.getCountAllBanknotes());
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("Введите сумму выдачи кратную: " + account.getMinNominal());
                        int sum = scanner.nextInt();

                        System.out.println("Какими банкнотами произвести выдачу? ");
                        int banknote = scanner.nextInt();

                        banknoteWithDraw(account.withDraw(sum, banknote));
                        System.out.println("Баланс: " + account.getBalance());
                        System.out.println("Число доступных банкнот: " + account.getCountAllBanknotes() + System.lineSeparator());
                        break;
                    case 4:
                        System.out.println("До свидания!");
                        return;
                    default:
                        System.out.println("Неизвестная операция, попробуйте еще раз.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Неверная операция, можно вводить только цифры." + System.lineSeparator());
            } catch (NotSuchCountBanknoteException e) {
                System.out.println("К сожалению, недостаточно банкнот имеющегося номинала для выдачи суммы." + System.lineSeparator());
            } catch (NoSuchElementException e) {
                System.out.println("В банкомате нет банкнот такого номинала." + System.lineSeparator());
            } catch (TooMuchCountBanknoteException e) {
                System.out.println("Банкомат не может принять такое количество банкнот." + System.lineSeparator());
            } catch (TooMuchSumException e) {
                System.out.println("В банкомате нет такой суммы." + System.lineSeparator());
            } catch (NotSuchNominalException e) {
                System.out.println("Сумма должна быть кратна " + account.getMinNominal() + System.lineSeparator());
            } catch (NotHaveBanknotesException e) {
                System.out.println("В банкомате нет денег. Попробуйте позже." + System.lineSeparator());
            }
        }
    }


    private static void countBanknote(ArrayList<Banknotes> banknotes) {
        System.out.println("В банкомате имеются банкноты следюущих номиналов:");
        for (Banknotes banknote : banknotes) {
            System.out.printf("Номинал: %d, колличество: %d%n", banknote.getNominal(), banknote.getCount());
        }
    }


    private static void banknoteWithDraw(ArrayList<Banknotes> banknotes) {
        System.out.println("Выданы банкноты следюущих номиналов:");
        for (Banknotes banknote : banknotes) {
            System.out.printf("Номинал: %d, колличество: %d%n", banknote.getNominal(), banknote.getCount());
        }
    }
}


