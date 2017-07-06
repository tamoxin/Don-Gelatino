package me.marcocarrizales.gamecomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Queue;

import me.marcocarrizales.gameobjects.CloudsScroller;
import me.marcocarrizales.gameobjects.FallingObjects;
import me.marcocarrizales.gameobjects.FallingObjectsPool;
import me.marcocarrizales.gameobjects.Gelatino;
import me.marcocarrizales.helpers.AssetsLoader;
import me.marcocarrizales.helpers.InputHandler;

/**
 * Created by marco on 3/15/2017.
 */

public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera camera;

    private float gameHeight;
    private float gameWidth;
    private String score;

    private TextureRegion background;
    private SpriteBatch batch;
    private GlyphLayout layout, top;
    //delete
    private ShapeRenderer shapeRenderer;

    private Gelatino gelatino;
    private CloudsScroller cloudsScroller;
    private FallingObjectsPool pool;
    private Queue<FallingObjects> queue;

    public GameRenderer(GameWorld gameWorld) {
        world = gameWorld;
        this.gameHeight = AssetsLoader.GAMEHEIGHT;
        this.gameWidth = AssetsLoader.GAMEWIDTH;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, gameWidth, gameHeight);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initAssets();
        initGameObjects();
    }

    private void initGameObjects() {
        gelatino = world.getGelatino();
        cloudsScroller = world.getCloudsScroller();
        pool = world.getPool();
        queue = world.getFallingObjectsQueue();
    }

    private void initAssets() {
        background = AssetsLoader.bg_default;
        layout = new GlyphLayout();
        top = new GlyphLayout();
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        drawBackground();

        if (world.isRunning()) {
            cloudsScroller.draw(batch);
            gelatino.draw(batch);
            drawQueue();
            drawScore();
        } else if (world.isMenu()) {
            gelatino.draw(batch);
            batch.draw(AssetsLoader.playBtnDown, (float) (gameWidth/2 - gameWidth*.15), (float) (gameHeight/2 - gameHeight*.15),
                    (float) (gameWidth*.3), (float) (gameHeight*.3));
        } else if (world.isGameOver() || world.isHighScore()) {
            cloudsScroller.draw(batch);
            gelatino.draw(batch);
            drawQueue();
            if (world.isGameOver())
                drawGameOver();
            else drawHighScore();
        }
        batch.end();

    }

    private void drawScore() {
        // Convert integer into String
        score = world.getScore() + "";
        layout.setText(AssetsLoader.font, score);
        AssetsLoader.font.draw(batch,
                layout, (gameWidth / 2) - (layout.width / 2), (float) ((gameHeight * .05) - (layout.height / 2)));//Center Text
    }

    private void drawBackground() {
        batch.disableBlending();
        batch.draw(background, 0, 0, gameWidth, gameHeight);
        batch.enableBlending();
    }

    private void drawQueue() {
        for (int i = 0; i < queue.size; i++) {
            queue.get(i).draw(batch);
        }
    }

    private void drawGameOver() {
        batch.draw(AssetsLoader.gameOver, 0, (float) (gameHeight*.03), gameWidth, gameHeight);
        AssetsLoader.font.draw(batch,
                layout, (gameWidth / 2) - (layout.width / 2), (float) ((gameHeight * .3) - (layout.height / 2)));//Center Text
        top.setText(AssetsLoader.font, "" + AssetsLoader.getHighScore());
        AssetsLoader.font.draw(batch,
                top, (gameWidth / 2) - (layout.width / 2), (float) ((gameHeight * .6) + (top.height)));

    }

    private void drawHighScore() {
        batch.draw(AssetsLoader.highScore, 0, (float) (-gameHeight*.1), gameWidth, gameHeight);
        top.setText(AssetsLoader.font, "" + AssetsLoader.getHighScore());
        AssetsLoader.font.draw(batch,
                top, (gameWidth / 2) - (layout.width / 2), (float) ((gameHeight * .3) - (top.height / 2)));//Center Text
    }

    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    public void dispose() {
        batch.dispose();
    }
}
