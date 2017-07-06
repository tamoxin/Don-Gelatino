package me.marcocarrizales.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import me.marcocarrizales.gamecomponents.GameRenderer;
import me.marcocarrizales.gamecomponents.GameWorld;
import me.marcocarrizales.gameobjects.Gelatino;

/**
 * Created by marco on 3/15/2017.
 */

public class InputHandler implements InputProcessor{

    private GameWorld world;
    private Gelatino gelatino;

    public InputHandler(GameWorld world) {
        this.world = world;
        gelatino = world.getGelatino();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (world.isRunning()){
            if (screenX < Gdx.graphics.getWidth() / 2) {
                gelatino.leftClick(true);
            } else {
                gelatino.rightClick(true);
            }
        }else if (world.isMenu()) {
            world.start();
            return true;
        }
        else if (world.isGameOver() || world.isHighScore()) {
            world.restart();
            return true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (world.isRunning()) {
            gelatino.leftClick(false);
            gelatino.rightClick(false);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
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
    public boolean scrolled(int amount) {
        return false;
    }
}
