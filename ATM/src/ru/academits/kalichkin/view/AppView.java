package ru.academits.kalichkin.view;

import ru.academits.kalichkin.common.View;
import ru.academits.kalichkin.common.ViewListener;
import ru.academits.kalichkin.exception.*;
import ru.academits.kalichkin.model.Banknotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class AppView implements View {
    private final ArrayList<ViewListener> listeners = new ArrayList<>();

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
        Toolkit.getDefaultToolkit().getImage("atm.png");
        buttonCountBalance.addActionListener(e -> {
            listeners.forEach(ViewListener::needGetBanknote);
        });

        buttonBalance.addActionListener(e -> {
            listeners.forEach(ViewListener::needGetBalance);
        });

        buttonDeposit.addActionListener(e -> {
            try {
                Integer[] items = listeners.get(0).needGetNominalBanknote();
                int validCountBanknote = listeners.get(0).needValidCountBanknote();

                JComboBox<Integer> banknote = new JComboBox<>(items);
                JLabel count = new JLabel();
                JSlider slider = new JSlider(1, validCountBanknote);

                count.setFont(new Font("Helvetica", Font.PLAIN, 16));
                count.setHorizontalAlignment(SwingConstants.CENTER);
                count.setText(String.valueOf(slider.getValue()));

                slider.setPaintTrack(true);
                slider.setMinorTickSpacing(1);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);

                slider.addChangeListener(e1 -> count.setText(String.valueOf(slider.getValue())));

                Object[] command = {
                        "Выберете номинал банкноты:", banknote,
                        "Выберете количество банкнот:", count, slider
                };
                int option = JOptionPane.showConfirmDialog(frame, command, "Deposit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    int nominal = (int) banknote.getSelectedItem();
                    listeners.forEach(listener -> listener.needDeposit(nominal, slider.getValue()));
                    JOptionPane.showMessageDialog(frame, "Банкноты успешно внесены");
                }
            } catch (IllegalArgumentException el) {
                JOptionPane.showMessageDialog(frame, "К сожалению банкомат переполнен, попробуйте позже.");
            }
        });


        buttonWithDraw.addActionListener(e -> {
            try {
                int minNominal = listeners.get(0).needGetMinNominal();
                int balance = listeners.get(0).getBalance();
                Integer[] items = listeners.get(0).needGetNominalBanknote();

                JComboBox<Integer> banknote = new JComboBox<>(items);
                JTextField sum = new JTextField();
                JSlider slider = new JSlider(minNominal, balance);
                slider.setValueIsAdjusting(true);
                slider.setValue(minNominal);

                sum.setFont(new Font("Helvetica", Font.PLAIN, 16));
                sum.setHorizontalAlignment(SwingConstants.CENTER);
                sum.setText(String.valueOf(slider.getValue()));

                slider.setMajorTickSpacing(5000);
                slider.setPaintTicks(true);
                slider.addChangeListener(e12 -> sum.setText(String.valueOf(slider.getValue())));


                sum.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent ke) {
                        String typed = sum.getText();
                        slider.setValue(0);
                        if (!typed.matches("^\\d+$") || Integer.parseInt(sum.getText()) > balance) {
                            return;
                        }
                        slider.setValue(Integer.parseInt(typed));
                    }
                });


                Object[] command = {
                        "Введите сумму выдачи, кратную: " + minNominal,
                        "Баланс: " + balance, sum, slider,
                        "Выберете номинал банкноты:", banknote
                };

                int option = JOptionPane.showConfirmDialog(frame, command, "Withdraw", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    try {
                        int nominal = (int) banknote.getSelectedItem();
                        listeners.forEach(listener -> listener.needWithDraw(Integer.parseInt(sum.getText()), nominal));
                    } catch (NotSuchCountBanknoteException el) {
                        JOptionPane.showMessageDialog(frame, "К сожалению, недостаточно банкнот имеющегося номинала для выдачи суммы.");
                    } catch (TooMuchSumException el) {
                        JOptionPane.showMessageDialog(frame, "В банкомате нет такой суммы.");
                    } catch (NotSuchNominalException el) {
                        JOptionPane.showMessageDialog(frame, "Сумма должна быть кратна " + minNominal);
                    } catch (NoSuchElementException el) {
                        JOptionPane.showMessageDialog(frame, "В банкомате нет банкнот такого номинала.");
                    }
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
        buttonBalance.setBackground(Color.BLUE);

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
        JOptionPane.showMessageDialog(frame, label, "Баланс счета", 0, icon);

    }

    @Override
    public void onGetBanknote(ArrayList<Banknotes> list) {
        String title = "В банкомате имеются следующие банкноты:";
        createFrameCountBanknote(title, list);
    }

    @Override
    public void onWithDraw(ArrayList<Banknotes> list) {
        String title = "Выданы следующие банкноты:";
        createFrameCountBanknote(title, list);
    }


    @Override
    public void addViewListener(ViewListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }


    @Override
    public void removeViewListener(ViewListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    private void createFrameCountBanknote(String title, ArrayList<Banknotes> list) {
        JFrame frameCountBanknote = new JFrame(title);
        frameCountBanknote.setLocationRelativeTo(null);

        String[] columnNames = {"Номинал", "Количество"};

        Banknotes[] banknotes = list.toArray(new Banknotes[list.size()]);
        String[][] data = new String[banknotes.length][columnNames.length];

        for (int i = 0; i < banknotes.length; i++) {
            data[i][0] = Integer.toString(banknotes[i].getNominal());
            for (int j = 0; j < columnNames.length; j++) {
                data[i][j] = Integer.toString(banknotes[i].getCount());
                data[i][0] = Integer.toString(banknotes[i].getNominal());
            }
        }

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("TimesNewRoman", Font.PLAIN, 15));
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        frameCountBanknote.getContentPane().add(scrollPane);
        frameCountBanknote.setPreferredSize(new Dimension(400, 150));
        frameCountBanknote.pack();
        frameCountBanknote.setVisible(true);
    }
}
