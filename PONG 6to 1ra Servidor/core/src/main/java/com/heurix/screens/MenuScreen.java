package com.heurix.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.heurix.elements.Text;
import com.heurix.io.KeyListener;
import com.heurix.utils.ScreenManager;

import java.util.ArrayList;

public class MenuScreen implements Screen {

    private Text title, copyright;
    private String[] options = {"Play", "Exit"};
    private ArrayList<Text> menuOptions = new ArrayList<Text>();
    private int selected = 0;
    private KeyListener keyListener = new KeyListener();

    @Override
    public void show() {
        this.title = new Text(10, Gdx.graphics.getHeight() - 30, 50,"PONG");
        this.title.centerX();

        int startY = Gdx.graphics.getHeight() - 180;
        int optionY = startY;
        for (int i = 0; i < this.options.length; i++) {
            this.menuOptions.add(new Text(10, optionY, 40,this.options[i]));
            this.menuOptions.get(i).centerX();
            optionY -= 70;
        }
        this.menuOptions.get(this.selected).setColor(Color.YELLOW);
        this.copyright = new Text(10, 40, 20,"Copyright 2025 Pablo Jasinski");
        this.copyright.centerX();

        Gdx.input.setInputProcessor(this.keyListener);
    }

    @Override
    public void render(float delta) {
        udpate();
        this.title.draw();
        for (Text option : this.menuOptions) {
            option.draw();
        }
        this.copyright.draw();
    }

    private void udpate() {
        if(this.keyListener.isUpJustPressed()) {
            manageSelection(-1);
        }
        if(this.keyListener.isDownJustPressed()) {
            manageSelection(1);
        }
        if(this.keyListener.isEnterJustPressed()){
            if(this.options[this.selected].equals("Play")) {
                ScreenManager.setSceen(new GameScreen());
            }
            if(this.options[this.selected].equals("Exit")) {
                Gdx.app.exit();
            }
        }
    }

    private void manageSelection(int i) {
        this.menuOptions.get(this.selected).setColor(Color.WHITE);
        this.selected += i;
        if(this.selected<0) this.selected = this.options.length-1;
        if(this.selected>=this.options.length) this.selected = 0;
        this.menuOptions.get(this.selected).setColor(Color.YELLOW);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
