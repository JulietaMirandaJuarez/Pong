package com.heurix.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.heurix.utils.Render;

public class Text {

	BitmapFont font;
	private float x=0,y=0;
	private String text ="";
	GlyphLayout layout;

	public Text(String path, int size, Color color, boolean shadow) {
		createText(path, size, color, shadow);
	}

    public Text(String path, int size, Color color) {
        createText(path, size, color, false);
    }

    public Text(int size, Color color, boolean shadow) {
        createText("fonts/8-BIT WONDER.TTF", size, color, shadow);
    }

    public Text(int size, Color color) {
        createText("fonts/8-BIT WONDER.TTF", size, color, false);
    }

    public Text(int size) {
        createText("fonts/8-BIT WONDER.TTF", size, Color.WHITE, false);
    }

    public Text(int x, int y, int size, String text) {
        createText("fonts/8-BIT WONDER.TTF", size, Color.WHITE, false);
        this.setText(text);
        this.x = x;
        this.y = y;
    }

    public Text(int x, int y, int size, String text, Color color) {
        createText("fonts/8-BIT WONDER.TTF", size, color, false);
        this.setText(text);
        this.x = x;
        this.y = y;
    }

    public Text(int x, int y, String text) {
        createText("fonts/8-BIT WONDER.TTF", 12, Color.WHITE, false);
        this.setText(text);
        this.x = x;
        this.y = y;
    }



	private void createText(String path, int size, Color color, boolean shadow) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
		FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
		params.size = size;
		params.color = color;
		if (shadow) {
			params.shadowColor = Color.BLACK;
			params.shadowOffsetX = 1;
			params.shadowOffsetY = 1;
		}
        this.font = generator.generateFont(params);
        this.layout = new GlyphLayout();
	}

	public void draw() {
        Render.batch.begin();
        this.font.draw(Render.batch, this.text, this.x, this.y);
        Render.batch.end();
	}

	public void setColor(Color color) {
        this.font.setColor(color);
	}

	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
        this.layout.setText(font, text);
	}

	public float getWidth() {
		return this.layout.width;
	}

	public float getHeight() {
		return this.layout.height;
	}

	public Vector2 getDimension() {
		return new Vector2(this.layout.width,this.layout.height);
	}

	public Vector2 getPosition() {
		return new Vector2(this.x,this.y);
	}


    public void centerX() {
        this.x = (Gdx.graphics.getWidth() - getWidth()) / 2f;
    }

    public void centerY(){
        this.y = (Gdx.graphics.getHeight() + getHeight()) / 2f;
    }

    public void centerXY(){
        centerX();
        centerY();
    }

    public BitmapFont getFont() {
        return font;
    }
}
