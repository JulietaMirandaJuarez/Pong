package com.heurix.elements;

import com.heurix.network.ServerThread;

import java.awt.*;

public class Pad extends Element {

    private int speed = 5;
    private int id;

    public Pad(int x, int y, int id, ServerThread serverThread) {
        super(x, y, 25,100, serverThread);
        this.id = id;
        super.centerY();
        int posX = (id==1) ? (int) super.getX() + (int) super.getWidth() - 2 : (int) super.getX() + 2;
        this.collisionBox = new Rectangle(posX, super.getY(), 2, 100);
    }

    public void moveUp(Map map) {
        super.setY(super.getY()+speed);
        if(checkCollision(map.getTop())){
            super.setY((int)map.getTop().getY()- (int)super.getHeight());
        }
        super.serverThread.sendMessageToAll("UpdatePosition:Pad:"+id+":"+super.getY());
    }

    public void moveDown(Map map) {
        super.setY(super.getY()-speed);
        if(checkCollision(map.getDown())){
            super.setY((int)map.getDown().getY());
        }
        super.serverThread.sendMessageToAll("UpdatePosition:Pad:"+id+":"+super.getY());
    }
}
