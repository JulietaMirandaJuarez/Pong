package com.heurix.players;

import com.badlogic.gdx.Gdx;
import com.heurix.elements.Pad;
import com.heurix.network.ServerThread;

public class Player {

    private int id;
    private int score = 0;
    private Pad pad;

    public Player(int id, ServerThread serverThread) {
        this.id = id;
        if(id == 1) {
            this.pad = new Pad(20, 200, 1,serverThread);
        } else {
            this.pad = new Pad(Gdx.graphics.getWidth() - 45, 200, 2, serverThread);
        }
    }

    public Pad getPad() {
        return this.pad;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore() {
        this.score++;
    }
}
