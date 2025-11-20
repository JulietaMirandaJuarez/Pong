package com.heurix.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class Render {

    public static SpriteBatch batch = new SpriteBatch();
    public static ShapeRenderer shapeRenderer = new ShapeRenderer();

    public static void clear(){
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
    }



}
