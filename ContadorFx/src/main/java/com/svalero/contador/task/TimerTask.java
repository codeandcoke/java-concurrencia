package com.svalero.contador.task;

import javafx.concurrent.Task;

public class TimerTask extends Task<Integer> {

    private int count;

    public TimerTask(int count) {
        this.count = count;
    }

    @Override
    protected Integer call() throws Exception {
        for (int i = 0; i <= count; i++) {
            Thread.sleep(1000);
            updateValue(i);
            updateProgress(i, count);
        }

        return null;
    }
}
