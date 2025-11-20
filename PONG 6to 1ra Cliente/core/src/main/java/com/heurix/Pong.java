package com.heurix;

import com.badlogic.gdx.Game;
import com.heurix.screens.GameScreen;
import com.heurix.screens.MenuScreen;
import com.heurix.utils.ScreenManager;

public class Pong extends Game {

    @Override
    public void create() {
        ScreenManager.setApplicationlistener(this);
        ScreenManager.setSceen(new MenuScreen());
    }

    @Override
    public void dispose() {
        this.getScreen().dispose();
    }
}
