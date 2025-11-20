package com.heurix.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.heurix.network.ServerThread;
import com.heurix.utils.Render;

import java.awt.*;

public class Element {

    private Rectangle bounds;
    protected Rectangle collisionBox;
    protected ServerThread serverThread;

    public Element(int x, int y, int width, int height, ServerThread serverThread) {
        this.bounds = new Rectangle(x, y, width, height);
        this.serverThread = serverThread;
    }

    public void draw(){
        Render.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Render.shapeRenderer.rect(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
        Render.shapeRenderer.end();
    }

    protected boolean checkCollisionBox(Rectangle rectangle) {
        return collisionBox.intersects(rectangle);
    }

    protected boolean checkCollision(Rectangle rectangle) {
        return getBounds().intersects(rectangle);
    }

    public void setPosition(int x, int y) {
        this.bounds.setLocation(x, y);
    }

    public void setX(int x) {
        this.bounds.x = x;
        if(this.collisionBox != null) this.collisionBox.setLocation(x, this.bounds.y);
    }

    public void setY(int y) {
        this.bounds.y = y;
        if(this.collisionBox != null) this.collisionBox.setLocation(bounds.x,y);
    }

    public int getY(){
        return this.bounds.y;
    }

    public int getX(){
        return this.bounds.x;
    }

    public void setSize(int width, int height) {
        this.bounds.setSize(width, height);
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    public float getWidth() {
        return this.bounds.width;
    }

    public float getHeight() {
        return this.bounds.height;
    }

    public void centerX(){
        int screenWidth = Gdx.graphics.getWidth();
        this.setX((screenWidth / 2) - ((int)this.getWidth() / 2));
    }

    public void centerY() {
        int screenHeight = Gdx.graphics.getHeight();
        this.setY((screenHeight / 2) - ((int)this.getHeight() / 2));
    }

    public void centerXY(){
        centerX();
        centerY();
    }

}
