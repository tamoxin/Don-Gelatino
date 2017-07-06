package me.marcocarrizales.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import me.marcocarrizales.dongelatino.DonGelatino;
import me.marcocarrizales.helpers.AssetsLoader;
import me.marcocarrizales.tweenaccessors.SpriteAccessor;

/**
 * Created by marco on 3/15/2017.
 */

public class SplashScreen implements Screen{

    private TweenManager manager;
    private SpriteBatch batcher;
    private Sprite sprite;
    private DonGelatino game;

    public SplashScreen(DonGelatino game) {
        this.game = game;
    }

    @Override
    public void show() {
        sprite = AssetsLoader.logo;
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desireWidth = width * .6f;
        float scale = desireWidth / sprite.getWidth();

        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
            - (sprite.getWidth() / 2));
        setupTween();
        batcher = new SpriteBatch();
    }

    private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                // Show the menu after the SplashScreen
                //game.setScreen(new GameScreen());
                dispose();
                if (AssetsLoader.directionsCount() > 0)
                    game.setScreen(new DirectionsScreen(game));
                else game.setScreen(new GameScreen());
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, 1.5f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1,.4f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
    }

    @Override
    public void render(float delta) {
        manager.update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
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
        batcher.dispose();
    }
}
