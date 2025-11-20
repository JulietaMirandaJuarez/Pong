package com.heurix.players;

import com.badlogic.gdx.Gdx;
import com.heurix.elements.Pad;

public class Player {

    private int id;
    private Pad pad;

    public Player(int id) {
        this.id = id;
        if(id == 1) {
            this.pad = new Pad(20, 200, 1);
        } else {
            this.pad = new Pad(Gdx.graphics.getWidth() - 45, 200, 2);
        }
    }

    public Pad getPad() {
        return this.pad;
    }

}
