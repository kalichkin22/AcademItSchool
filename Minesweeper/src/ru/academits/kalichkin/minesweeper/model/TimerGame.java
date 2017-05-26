package ru.academits.kalichkin.minesweeper.model;


import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerGame extends JLabel {
    private Timer timer = new Timer();
    private String timeStop;


    public TimerGame() {
        TimerTask timerTask = new TimerTask() {
            volatile int time;
            Runnable refresher = new Runnable() {
                public void run() {
                    int multiplyTime = 60;
                    TimerGame.this.setText(String.format("%02d:%02d", time / multiplyTime, time % multiplyTime));
                    timeStop = String.format("%02d:%02d", time / multiplyTime, time % multiplyTime);
                }
            };

            public void run() {
                time++;
                SwingUtilities.invokeLater(refresher);
            }
        };
        int period = 1000;
        timer.scheduleAtFixedRate(timerTask, 0, period);
    }

    public String stopTimer() {
        timer.cancel();
        return timeStop;
    }
}
