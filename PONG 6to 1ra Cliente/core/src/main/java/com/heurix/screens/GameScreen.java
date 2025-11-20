package com.heurix.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.heurix.elements.Ball;
import com.heurix.elements.Text;
import com.heurix.io.KeyListener;
import com.heurix.network.ClientThread;
import com.heurix.players.Player;
import com.heurix.utils.Render;
import com.heurix.interfaces.GameController;
import com.heurix.utils.ScreenManager;

public class GameScreen implements Screen, GameController {

    private final int NUM_PLAYERS = 2;
    private Text score;
    private Text winner;
    private Text waiting;
    private Player[] players = new Player[NUM_PLAYERS];
    private KeyListener keyListener = new KeyListener();
    private Ball ball;
    private boolean startGame = false;
    private boolean endGame = false;
    private ClientThread clientThread;
    private int numPlayer;

    @Override
    public void show() {
        for(int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player(i + 1);
        }
        this.score = new Text(10, Gdx.graphics.getHeight() - 15, 40, "00 00");
        this.score.setColor(Color.GRAY);
        this.score.centerX();
        this.winner = new Text(10, Gdx.graphics.getHeight() - 15, 40, "asd", Color.GREEN);
        this.winner.centerXY();
        this.waiting = new Text(10, Gdx.graphics.getHeight() - 15, 35, "Esperando al rival", Color.GREEN);
        this.waiting.centerXY();
        Gdx.input.setInputProcessor(this.keyListener);
        ball = new Ball();
        ball.centerXY();
        clientThread = new ClientThread(this);
        clientThread.start();
        clientThread.sendMessage("Connect");
    }



    @Override
    public void render(float delta) {
        Render.clear();
        if(!startGame) {
            this.waiting.draw();
            if(this.keyListener.isEscapeJustPressed()){
                ScreenManager.setSceen(new MenuScreen());
            }
        } else {
            update();
            this.score.draw();
            for (Player player : this.players) {
                player.getPad().draw();
            }

            if (this.endGame) {
                this.winner.draw();
            } else {
                ball.draw();
            }
        }
    }

    public void update() {

        if(this.keyListener.isEscapeJustPressed()){
            ScreenManager.setSceen(new MenuScreen());
        }

        if(this.keyListener.isUp()){
            this.clientThread.sendMessage("Move:1");
            this.players[this.numPlayer-1].getPad().moveUp();
        }

        if(this.keyListener.isDown()){
            this.clientThread.sendMessage("Move:-1");
            this.players[this.numPlayer-1].getPad().moveDown();
        }
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
        this.clientThread.terminate();
    }

    @Override
    public void isGoal(int direction) {

    }

    @Override
    public void connect(int numPlayer) {
        this.numPlayer = numPlayer;
    }

    @Override
    public void start() {
        this.startGame = true;
    }

    @Override
    public void updatePadPosition(int numPlayer, int y) {
        this.players[numPlayer-1].getPad().setY(y);
    }

    @Override
    public void updateBallPosition(int x, int y) {
        this.ball.setPosition(x, y);
    }

    @Override
    public void updateScore(String score) {
        this.score.setText(score);
    }

    @Override
    public void endGame(int winner) {
        this.endGame = true;
        this.winner.setText("Player " + winner + " wins");
        this.winner.centerXY();
    }

    @Override
    public void backToMenu() {
//        ScreenManager.setSceen(new MenuScreen());
    }
}
