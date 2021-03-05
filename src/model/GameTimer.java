package model;

public abstract class GameTimer {

    private int currentTicks;
    private int intervalTicks;
    private long loopCount;
    private final int resetTicks;

    public GameTimer( int interval ) {
        intervalTicks = interval;//frequenza del timer (ogni tot interval esegue action())
        resetTicks = interval;
    }

    public abstract void action();

    public void tick(int reps) {  //tick viene chiamato ad ogni repaint, reps = dopo quante chiamate di action() cala la frequenza

        currentTicks++;
        if (currentTicks >= intervalTicks) {
            action();
            loopCount++;
            currentTicks = 0;
            if(loopCount == reps) {
                intervalTicks--; //aumenta la frequenza del Timer (la difficoltà del gioco aumenta)
                loopCount = 0;
            }
        }

    }

    public void resetGame(){
        intervalTicks = resetTicks;
    }

}