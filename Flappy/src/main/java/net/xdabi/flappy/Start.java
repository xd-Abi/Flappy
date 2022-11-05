package net.xdabi.flappy;


import net.xdabi.flappy.content.Level;
import net.xdabi.flappy.kernel.Application;

public class Start {

    public static void main(String[] args) {

        Application application = Application.getInstance();
        application.getRenderer().addObject("defLevel", new Level());
        application.run();
    }
}
