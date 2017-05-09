package ru.academits.kalichkin.view;

import ru.academits.kalichkin.common.View;
import ru.academits.kalichkin.common.ViewListener;
import ru.academits.kalichkin.exception.*;
import ru.academits.kalichkin.model.Banknotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AppView implements View {
    private ViewListener listener;
    private final JFrame frame = new JFrame("ATM");
    private final JButton buttonBalance = new JButton("Узнать баланс счета");
    private final JButton buttonDeposit = new JButton("Пополнить баланс");
    private final JButton buttonWithDraw = new JButton("Снять деньги");
    private final JButton buttonCountBalance = new JButton("Количество банкнот");

    private final static boolean SHOULD_WEIGHT_X = true;

    private void createFrame() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void initEvents() {
        buttonCountBalance.addActionListener(e -> listener.needGetBanknote());

        buttonBalance.addActionListener(e -> listener.needGetBalance());

        buttonDeposit.addActionListener((ActionEvent e) -> {
            try {
                DepositDialog dialog = new DepositDialog(listener);
                if (dialog.show(frame)) {
                    listener.needDeposit(dialog.getBanknoteNominal(), dialog.getCountBanknote());
                    JOptionPane.showMessageDialog(frame, "Банкноты успешно внесены");
                }
            } catch (IllegalArgumentException el) {
                JOptionPane.showMessageDialog(frame, "К сожалению банкомат переполнен, попробуйте позже.");
            }
        });

        buttonWithDraw.addActionListener(e -> {
            try {
                WithdrawDialog dialog = new WithdrawDialog(listener);
                try {
                    if (dialog.show(frame)) {
                        listener.needWithDraw(dialog.getSum(), dialog.getBanknoteNominal());
                    }
                } catch (NotSuchCountBanknoteException el) {
                    JOptionPane.showMessageDialog(frame, "К сожалению, недостаточно банкнот имеющегося номинала для выдачи суммы.");
                } catch (TooMuchSumException el) {
                    JOptionPane.showMessageDialog(frame, "В банкомате нет такой суммы.");
                } catch (NotSuchNominalException el) {
                    JOptionPane.showMessageDialog(frame, "Сумма должна быть кратна " + listener.needGetMinNominal());
                } catch (NoSuchElementException el) {
                    JOptionPane.showMessageDialog(frame, "В банкомате нет банкнот такого номинала.");
                }

            } catch (NotHaveBanknotesException el) {
                JOptionPane.showMessageDialog(frame, "В банкомате нет денег. Попробуйте позже.");
            } catch (NumberFormatException el) {
                JOptionPane.showMessageDialog(frame, "Сумма должна быть цифрой");
            }
        });
    }

    private void addComponentsToPanel(Container contentPane) {
        GridBagLayout gbl = new GridBagLayout();
        contentPane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();

        if (SHOULD_WEIGHT_X) {
            c.weightx = 0.5;
        }

        Font font = new Font(null, Font.BOLD, 15);
        c.insets = new Insets(0, 185, 10, 0);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        JLabel label = new JLabel("Вас приветствует Банк Банков!");
        label.setFont(font);
        contentPane.add(label, c);

        c.insets = new Insets(10, 200, 10, 0);
        c.gridy = GridBagConstraints.RELATIVE;
        contentPane.add(new JLabel("Выберете необходимое действие:"), c);

        c.ipady = 50;
        c.ipadx = 100;
        c.gridwidth = 1;
        c.insets = new Insets(0, 10, 0, 20);

        c.anchor = GridBagConstraints.NORTH;
        c.gridy = GridBagConstraints.RELATIVE;
        contentPane.add(buttonBalance, c);
        buttonBalance.setFont(font);

        c.insets = new Insets(0, 20, 0, 10);
        c.anchor = GridBagConstraints.WEST;
        c.gridy = 2;
        c.gridx = GridBagConstraints.RELATIVE;
        contentPane.add(buttonDeposit, c);
        buttonDeposit.setFont(font);

        c.insets = new Insets(30, 10, 0, 20);
        c.anchor = GridBagConstraints.PAGE_END;

        c.anchor = GridBagConstraints.NORTH;
        c.gridy = 3;
        contentPane.add(buttonWithDraw, c);
        buttonWithDraw.setFont(font);

        c.insets = new Insets(30, 20, 0, 10);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = GridBagConstraints.RELATIVE;
        contentPane.add(buttonCountBalance, c);
        buttonCountBalance.setFont(font);
    }


    @Override
    public void startApplication() {
        SwingUtilities.invokeLater(() -> {
            createFrame();
            addComponentsToPanel(frame);
            initEvents();
        });
    }


    @Override
    public void onBalance(int balance) {
        JLabel label = new JLabel();
        label.setText(String.valueOf(balance));
        label.setFont(new Font("Helvetica", Font.PLAIN, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Icon icon = new ImageIcon("balance.png");
        JOptionPane.showMessageDialog(frame, label, "Баланс счета", JOptionPane.ERROR_MESSAGE, icon);

    }

    @Override
    public void onGetBanknote(ArrayList<Banknotes> list) {
        String title = "В банкомате имеются следующие банкноты:";
        CountBanknotesDialog dialog = new CountBanknotesDialog(frame, title, list);
        dialog.createDialog();
    }

    @Override
    public void onWithDraw(ArrayList<Banknotes> list) {
        String title = "Выданы следующие банкноты:";
        CountBanknotesDialog dialog = new CountBanknotesDialog(frame, title, list);
        dialog.createDialog();
    }


    @Override
    public void setViewListener(ViewListener listener) {
        this.listener = listener;
    }
}
