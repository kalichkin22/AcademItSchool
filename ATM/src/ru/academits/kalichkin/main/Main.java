package ru.academits.kalichkin.main;

import ru.academits.kalichkin.atm.Account;
import ru.academits.kalichkin.exception.*;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
                System.out.println("Выбирете номер операции: ");
                int numberCommand = scanner.nextInt();

                switch (numberCommand) {
                    case 1:
                        System.out.println("Баланс составляет: " + account.getBalance());
                        System.out.println("Лимит банкомата на число купюр " + Account.getMaxBanknotes());
                        System.out.println("Число доступных купюр: " + account.getCountBanknotes() + System.lineSeparator());
                        break;
                    case 2:
                        System.out.println("Введите номинал банкноты: ");
                        int nominalDeposit = scanner.nextInt();

                        System.out.println("Введите количество банкнот: ");
                        int countDeposit = scanner.nextInt();

                        if (account.deposit(nominalDeposit, countDeposit)) {
                            System.out.println("Баланс: " + account.getBalance());
                        }
                        System.out.println("Лимит банкомата на число купюр " + Account.getMaxBanknotes());
                        System.out.println("Число доступных купюр: " + account.getCountBanknotes() + System.lineSeparator());
                        break;
                    case 3:
                        System.out.println("Введите сумму выдачи кратную: " + account.getMinNominal());
                        int sum = scanner.nextInt();

                        System.out.println("Какими банкнотами произвести выдачу? ");
                        int banknote = scanner.nextInt();

                        account.withDraw(sum, banknote);
                        System.out.println("Баланс: " + account.getBalance());
                        System.out.println("Лимит банкомата на число купюр " + account.getCountBanknotes());
                        System.out.println("Число доступных купюр: " + account.getCountBanknotes() + System.lineSeparator());
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
                System.out.println("Банкомат не знает таких банкнот." + System.lineSeparator());
            } catch (TooMuchCountBanknoteException e) {
                System.out.println("Банкомат не может принять такое количество банкнот" + System.lineSeparator());
            } catch (TooMuchSumException e) {
                System.out.println("В банкомате нет такой суммы." + System.lineSeparator());
            } catch (NotSuchNominalException e) {
                System.out.println("Сумма должна быть кратна " + account.getMinNominal() + System.lineSeparator());
            } catch (NotHaveBanknotesException e) {
                System.out.println("В банкомате нет денег. Попробуйте позже." + System.lineSeparator());
            }
        }
    }
}

