package me.marcocarrizales.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.marcocarrizales.dongelatino.DonGelatino;
import me.marcocarrizales.helpers.AssetsLoader;

/**
 * Created by marco on 4/19/2017.
 */

public class DirectionsScreen implements Screen{

    private DonGelatino game;
    private OrthographicCamera camera;
    private Batch batch;
    private float width, height;
    private float life;
    private boolean flag;

    public DirectionsScreen(DonGelatino game) {
        life = 7;
        flag = false;

        this.game = game;
        width = AssetsLoader.GAMEWIDTH;
        height = AssetsLoader.GAMEHEIGHT;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, width, height);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetsLoader.bg_default, 0, 0, width, height);
        life -= Gdx.graphics.getDeltaTime();
        if (life > 5)
            batch.draw(AssetsLoader.directions1, 0, 0, width, height);
        else if (life > 3)
            batch.draw(AssetsLoader.directions2, 0, 0, width, height);
        else if (life > 0) {
            batch.draw(AssetsLoader.directions3, 0, 0, width, height);
            if (!flag) {
                flag = true;
                AssetsLoader.directionsCountDown();
            }

        }
        batch.end();

        if (life <= 0) {
            dispose();
            game.setScreen(new GameScreen());
        }
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
    }
}
