package com.heurix.interfaces;

public interface GameController {
    void isGoal(int direction);
    void move(int numPlayer, int direction);
    void startGame();
    void timeOut();
}
