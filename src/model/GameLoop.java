package model;

import view.MainGUI;

public class GameLoop implements Runnable {

    private Thread gameloop;
    private final MainGUI main;

    public GameLoop(MainGUI m) {
        main = m;
    }


    @Override
    public void run() {

        Thread t = Thread.currentThread();
        while (t == gameloop) {
            main.updateGame();
            timeWarp();
        }

    }

    private void timeWarp() {
        long sleepTime = 30000000; //nanoseconds
        long now = System.nanoTime();
        long diff;
        while ((diff = System.nanoTime() - now) < sleepTime) {
            if (diff < sleepTime * 0.8) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException exc) {
                }
            } else {
                Thread.yield();
            }
        }
    }

    public void start() {
        gameloop = new Thread(this);
        gameloop.start();
        run();
    }

}
