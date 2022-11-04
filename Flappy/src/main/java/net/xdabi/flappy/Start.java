package net.xdabi.flappy;

import net.xdabi.flappy.kernel.Application;

public class Start {

    public static void main(String[] args) {
        Thread gameThread = new Thread(new Application(), "Flappy Bird");
        gameThread.start();
    }
}
