package com.heurix.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.heurix.utils.Render;

import java.awt.*;


public class Map {

    private Rectangle top, down, left, right;

    public Map() {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        this.top = new Rectangle(0, screenHeight, screenWidth, 10);
        this.down = new Rectangle(0, -10, screenWidth, 10);
        this.left = new Rectangle(-70, 0, 10, screenHeight);
        this.right = new Rectangle(screenWidth+60, 0, 10, screenHeight);
    }

    public void draw(){
        Render.shapeRenderer.setColor(Color.YELLOW);
        Render.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Render.shapeRenderer.rect(this.top.x, this.top.y, this.top.width, this.top.height);
        Render.shapeRenderer.rect(this.down.x, this.down.y, this.down.width, this.down.height);
        Render.shapeRenderer.rect(this.left.x, this.left.y, this.left.width, this.left.height);
        Render.shapeRenderer.rect(this.right.x, this.right.y, this.right.width, this.right.height);
        Render.shapeRenderer.end();
        Render.shapeRenderer.setColor(Color.WHITE);
    }

    public Rectangle getDown() {
        return down;
    }

    public Rectangle getTop() {
        return top;
    }

    public Rectangle getLeft() {
        return left;
    }

    public Rectangle getRight() {
        return right;
    }
}
