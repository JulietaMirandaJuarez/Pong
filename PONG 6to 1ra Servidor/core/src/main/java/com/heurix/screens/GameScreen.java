package com.heurix.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.heurix.elements.Ball;
import com.heurix.elements.Map;
import com.heurix.elements.Text;
import com.heurix.io.KeyListener;
import com.heurix.network.ServerThread;
import com.heurix.players.Player;
import com.heurix.utils.Render;
import com.heurix.interfaces.GameController;
import com.heurix.utils.TimeThread;

import java.sql.Time;

public class GameScreen implements Screen, GameController {

    private final int MAX_SCORE = 5;
    private final int NUM_PLAYERS = 2;
    private Text score;
    private Text winner;
    private String scoreStr;
    private Player[] players = new Player[NUM_PLAYERS];
    private KeyListener keyListener = new KeyListener();
    private Ball ball;
    private Map map;
    private boolean playing = false;
    private boolean endGame = false;
    private ServerThread serverThread;

    @Override
    public void show() {
        serverThread = new ServerThread(this);

        map = new Map();
        for(int i = 0; i < this.players.length; i++) {
            this.players[i] = new Player(i + 1, serverThread);
        }
        this.scoreStr = getFormattedScore();
        this.score = new Text(10, Gdx.graphics.getHeight() - 15, 40, this.scoreStr);
        this.score.setColor(Color.GRAY);
        this.score.centerX();
        this.winner = new Text(10, Gdx.graphics.getHeight() - 15, 40, "asd", Color.GREEN);
        this.winner.centerXY();
        Gdx.input.setInputProcessor(this.keyListener);
        ball = new Ball(map, players, this, serverThread);

        serverThread.start();
    }

    private String getFormattedScore() {
        this.scoreStr = "";
        for (int i = 0; i < this.NUM_PLAYERS; i++) {
            this.scoreStr += formatScore(this.players[i].getScore());
            if(i < this.NUM_PLAYERS - 1) {
                this.scoreStr += " ";
            }
        }
        return this.scoreStr;
    }

    private String formatScore(int score) {
        if(score < 10) {
            return "0" + score;
        } else {
            return String.valueOf(score);
        }
    }

    @Override
    public void render(float delta) {
        Render.clear();
        update();
        this.map.draw();
        this.score.draw();
        for (Player player : this.players) {
            player.getPad().draw();
        }

        if(this.endGame) {
            this.winner.draw();
        } else {
            ball.draw();
        }
    }

    public void update() {

        if(!this.endGame) {
            this.ball.update();
        }

//        if(!this.playing && this.keyListener.isSpaceJustPressed() && !this.endGame){
//            this.playing = true;
//            this.ball.launch();
//        }
//
//        if(this.keyListener.isUp()){
//            this.players[0].getPad().moveUp(this.map);
//        }
//
//        if(this.keyListener.isDown()){
//            this.players[0].getPad().moveDown(this.map);
//        }
//
//        if(this.keyListener.isUp2()){
//            this.players[1].getPad().moveUp(this.map);
//        }
//
//        if(this.keyListener.isDown2()){
//            this.players[1].getPad().moveDown(this.map);
//        }

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
        this.serverThread.terminate();
        System.out.println("Disposing GameScreen");
    }

    @Override
    public void isGoal(int direction) {
        this.playing = false;
        int numPlayer = direction == 1 ? 0 : 1;
        this.players[numPlayer].addScore();
        String scoreStr = getFormattedScore();
        this.score.setText(scoreStr);
        this.serverThread.sendMessageToAll("UpdateScore:"+scoreStr);

        if(this.players[numPlayer].getScore() >= this.MAX_SCORE) {
            this.endGame = true;
            this.winner.setText("Player " + (numPlayer + 1) + " wins");
            this.winner.centerXY();
            this.serverThread.sendMessageToAll("EndGame:"+(numPlayer+1));
            (new TimeThread(this)).start();
        }
    }

    @Override
    public void move(int numPlayer, int direction) {
        if(direction==1)this.players[numPlayer-1].getPad().moveUp(this.map);
        if(direction==-1)this.players[numPlayer-1].getPad().moveDown(this.map);
    }

    @Override
    public void startGame() {
        this.playing = true;
        this.ball.launch();
    }

    @Override
    public void timeOut() {
        if(!this.endGame){
            this.launchBall();
        } else {
            this.serverThread.disconnectClients();
        }
    }

    public void launchBall() {
        this.ball.launch();
    }
}
