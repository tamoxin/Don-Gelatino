package me.marcocarrizales.gamecomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Queue;

import me.marcocarrizales.gameobjects.CloudsScroller;
import me.marcocarrizales.gameobjects.FallingObjects;
import me.marcocarrizales.gameobjects.FallingObjectsPool;
import me.marcocarrizales.gameobjects.Gelatino;
import me.marcocarrizales.helpers.AssetsLoader;

/**
 * Created by marco on 3/15/2017.
 */

public class GameWorld {

    private float gameWidth, gameHeight;
    private Gelatino gelatino;
    private CloudsScroller cloudsScroller;
    private FallingObjectsPool pool;
    private FallingObjects falling;
    private Queue<FallingObjects> fallingObjectsQueue;
    private int score;

    private GameState currentState;

    public enum GameState {
        MENU, RUNNING, GAMEOVER, HIGHSCORE;

    }

    public GameWorld() {
        gameWidth = AssetsLoader.GAMEWIDTH;
        gameHeight = AssetsLoader.GAMEHEIGHT;
        currentState = GameState.MENU;
        gelatino = new Gelatino((float) ((gameWidth / 2) - (gameWidth * .085)), (float) (gameHeight - (gameHeight * .19)),
                (int) (gameWidth * .17), (int) (gameHeight * .17));
        cloudsScroller = new CloudsScroller(gameWidth, gameHeight);
        pool = new FallingObjectsPool();
        fallingObjectsQueue = new Queue<FallingObjects>();
        fallingObjectsQueue.addFirst(pool.obtain());
        score = 0;
    }


    public void update(float delta) {
        switch (currentState) {
            case MENU:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
        }
    }
    private void updateReady(float delta) {
        currentState = GameState.RUNNING;
    }

    public void updateRunning(float delta) {
        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
        if (delta > .15f) {
            delta = .15f;
        }

        gelatino.update(delta);
        cloudsScroller.update(delta);
        for (int i = 0; i < fallingObjectsQueue.size; i++) {
            fallingObjectsQueue.get(i).update(delta);

            if (fallingObjectsQueue.get(i).getSpawnFlag() == 1)
                fallingObjectsQueue.addLast(pool.obtain());

            checkForCollision(i);

            if (fallingObjectsQueue.get(i).hasFallen()) {
                freeObject(i);
            }
        }
    }

    private void checkForCollision(int i) {
        if (gelatino.isAlive() && fallingObjectsQueue.get(i).collides(gelatino)) {
            if (fallingObjectsQueue.get(i).isCactus()) {
                stopGame();
            }
            else if (!fallingObjectsQueue.get(i).isScored()){
                score++;
                fallingObjectsQueue.get(i).score();
                freeObject(i);
            }
        }
    }

    private void stopGame() {
        gelatino.stop();
        cloudsScroller.stop();
        stopFallingObjects();
        if (score > AssetsLoader.getHighScore()) {
            AssetsLoader.setHighScore(score);
            currentState = GameState.HIGHSCORE;
        } else currentState = GameState.GAMEOVER;
    }

    public void restart() {
        gelatino.reset();
        cloudsScroller.reset();
        resetFallingObjects();
        score = 0;
        currentState = GameState.RUNNING;
    }

    private void freeObject(int i) {
        falling = fallingObjectsQueue.removeIndex(i);
        pool.free(falling);
    }

    private void resetFallingObjects() {
        for (int i = 0; i < fallingObjectsQueue.size; i++)
            pool.free(fallingObjectsQueue.get(i));
        fallingObjectsQueue.clear();
        pool.clear();
        fallingObjectsQueue.addFirst(pool.obtain());
    }

    private void  stopFallingObjects() {
        for (int i = 0; i < fallingObjectsQueue.size; i++) {
            fallingObjectsQueue.get(i).stop();
        }
    }

    public Gelatino getGelatino() {
        return gelatino;
    }

    public CloudsScroller getCloudsScroller() {
        return cloudsScroller;
    }

    public FallingObjectsPool getPool() {
        return pool;
    }

    public Queue<FallingObjects> getFallingObjectsQueue() {
        return fallingObjectsQueue;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }
}