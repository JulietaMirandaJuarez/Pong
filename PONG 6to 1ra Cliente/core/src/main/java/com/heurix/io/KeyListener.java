package com.heurix.io;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class KeyListener implements InputProcessor {
    private boolean up = false, down = false, up2 = false, down2 = false;
    private boolean space = false, enter = false;

    private boolean escape = false;
    private boolean escapeJustPressed = false;

    private boolean upJustPressed = false, downJustPressed = false;
    private boolean up2JustPressed = false, down2JustPressed = false;
    private boolean spaceJustPressed = false, enterJustPressed = false;

    @Override
    public boolean keyDown(int keycode) {

        if(keycode==Keys.ESCAPE){
            this.escape = true;
            this.escapeJustPressed = true;
        }

        if(keycode==Keys.SPACE) {
            this.space = true;
            this.spaceJustPressed = true;
        }

        if(keycode==Keys.ENTER) {
            this.enter = true;
            this.enterJustPressed = true;
        }

        if(keycode==Keys.DOWN) {
            this.down = true;
            this.downJustPressed = true;
        }

        if(keycode==Keys.UP) {
            this.up = true;
            this.upJustPressed = true;
        }

        if(keycode==Keys.S) {
            this.down2 = true;
            this.down2JustPressed = true;
        }

        if(keycode==Keys.W) {
            this.up2 = true;
            this.up2JustPressed = true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode==Keys.ESCAPE){
            this.escape = false;
        }

        if(keycode==Keys.DOWN) {
            this.down = false;
        }

        if(keycode==Keys.UP) {
            this.up = false;
        }

        if(keycode==Keys.S) {
            this.down2 = false;
        }

        if(keycode==Keys.W) {
            this.up2 = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }


    public boolean isUp() {
        return this.up;
    }

    public boolean isDown() {
        return this.down;
    }

    public boolean isUp2() {
        return this.up2;
    }

    public boolean isDown2() {
        return this.down2;
    }

    public boolean isEnter() {
        return this.enter;
    }

    public boolean isSpace() {
        return this.space;
    }

    public boolean isEscape() {
        return escape;
    }

    public boolean isUpJustPressed() {
        if (this.upJustPressed) {
            this.upJustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isDownJustPressed() {
        if (this.downJustPressed) {
            this.downJustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isUp2JustPressed() {
        if (this.up2JustPressed) {
            this.up2JustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isDown2JustPressed() {
        if (this.down2JustPressed) {
            this.down2JustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isSpaceJustPressed() {
        if (this.spaceJustPressed) {
            this.spaceJustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isEnterJustPressed() {
        if (this.enterJustPressed) {
            this.enterJustPressed = false;
            return true;
        }
        return false;
    }

    public boolean isEscapeJustPressed() {
        return escapeJustPressed;
    }
}
