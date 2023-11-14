package Thread;

import java.util.Random;

public class Letter {
    public static final int ALIVE=0;
    public static final int DEAD=1;
    int w, h;
    int x, y;
    char letter;
    int step=10,gap=100;
    int status=ALIVE;
    LetterPanel.Callable callable;
    public Letter(int w, int h, LetterPanel.Callable callable) {
        this.w=w;
        this.h=h;
        this.callable=callable;
        letter=(char)('A'+new Random().nextInt(25));
        x=30+new Random().nextInt(w+60);
        y=-30;
    }
    public void start(){
        new Thread(){
            public void run(){
                while(status!=DEAD){
                    falling();
                    callable.show();
                    try {
                        Thread.sleep(gap);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }
    public void falling(){
        y=y+step;
        if(y>h){
            y=-30;
            status=DEAD;
        }
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
