package net.xdabi.flappy;

import net.xdabi.flappy.content.Level;

public class Start {

    public static void main(String[] args) {
        FlappyApplication app = FlappyApplication.getInstance();
        app.getRenderer().getRenderList().add(new Level());
        app.run();
    }
}
