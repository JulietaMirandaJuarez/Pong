package com.heurix.elements;

import com.badlogic.gdx.Gdx;
import com.heurix.network.ServerThread;
import com.heurix.players.Player;
import com.heurix.interfaces.GameController;
import com.heurix.utils.TimeThread;

import java.util.Random;

public class Ball extends Element {

    private boolean moving = false;
    private int speed = 3;
    private int xDirection = 1, yDirection = -1;
    private Map map;
    private Player[] players;
    private GameController gameController;

    public Ball(Map map, Player[] players, GameController gameController, ServerThread serverThread) {
        super(0,0, 20, 20, serverThread);
        this.centerXY();
        this.map = map;
        this.players = players;
        this.gameController = gameController;
    }

    public void update(){
        if(this.moving) {
            super.setX(super.getX() + (speed * xDirection));
            super.setY(super.getY() + (speed * yDirection));

            if(yDirection == 1){
                if(checkCollision(map.getTop())){
                    this.setY(Gdx.graphics.getHeight()-(int)this.getHeight());
                    this.yDirection = -1;
                }
            } else {
                if(checkCollision(map.getDown())){
                    this.setY(0);
                    this.yDirection = 1;
                }
            }

            if(xDirection == 1){
                if(checkCollision(map.getRight())){
                    this.gameController.isGoal(1);
//                    this.setX(Gdx.graphics.getWidth()-(int)this.getWidth());
//                    this.xDirection = -1;
                    reset();
                }

                if(checkCollision(players[1].getPad().getBounds())){
                    this.setX((int)players[1].getPad().getX() - (int)this.getWidth());
                    this.xDirection = -1;
                }
            } else {
                if(checkCollision(map.getLeft())){
                    this.gameController.isGoal(-1);
                    reset();
//                    this.setX(0);
//                    this.xDirection = 1;
                }

                if(checkCollision(players[0].getPad().getBounds())){
                    this.setX((int)players[0].getPad().getX() + (int)players[0].getPad().getWidth());
                    this.xDirection = 1;
                }
            }

            this.serverThread.sendMessageToAll("UpdatePosition:Ball:" + (int) this.getX() + ":" + (int) this.getY());
        }
    }

    private void reset() {
        this.moving = false;
        this.centerXY();
        TimeThread timeThread = new TimeThread( this.gameController);
        timeThread.start();
    }

    public void launch() {
        Random rand = new Random();
        this.xDirection = rand.nextInt(2) == 0 ? 1 : -1;
        this.yDirection = rand.nextInt(2) == 0 ? 1 : -1;
        this.moving = true;
    }
}
