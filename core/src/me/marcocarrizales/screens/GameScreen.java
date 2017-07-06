package me.marcocarrizales.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import me.marcocarrizales.gamecomponents.GameRenderer;
import me.marcocarrizales.gamecomponents.GameWorld;
import me.marcocarrizales.helpers.AssetsLoader;
import me.marcocarrizales.helpers.InputHandler;

/**
 * Created by marco on 3/15/2017.
 */

public class GameScreen implements Screen{

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public GameScreen() {

        world = new GameWorld();
        renderer = new GameRenderer(world);

        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
