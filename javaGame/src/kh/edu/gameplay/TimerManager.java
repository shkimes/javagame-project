package kh.edu.gameplay;

import javax.swing.*;
import java.util.TimerTask;
import java.util.Timer;

public class TimerManager {
    private int timeLeft = 60;
    private Timer timer;
    private final Runnable onTimeUpdate;
    private final Runnable onTimeEnd;

    public TimerManager(Runnable onTimeUpdate, Runnable onTimeEnd) {
        this.onTimeUpdate = onTimeUpdate;
        this.onTimeEnd = onTimeEnd;
    }

    public void start() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                SwingUtilities.invokeLater(onTimeUpdate);
                if (timeLeft == 0) {
                    timer.cancel();
                    SwingUtilities.invokeLater(onTimeEnd);
                }
            }
        }, 1000, 1000);
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
