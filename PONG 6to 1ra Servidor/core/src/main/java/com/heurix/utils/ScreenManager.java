package com.heurix.utils;

import com.badlogic.gdx.Screen;
import com.heurix.Pong;

public abstract class ScreenManager {

    private static Pong app;

    public static void setApplicationlistener(Pong applicationListener){
        app = applicationListener;
    }


    public static void setSceen(Screen screen) {
        app.setScreen(screen);
    }
}
