package me.marcocarrizales.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import me.marcocarrizales.helpers.AssetsLoader;

/**
 * Created by marco on 4/9/2017.
 */

public class Clouds {
    private int width;
    private int height;

    private Vector2 position;
    private boolean isScrolled;
    private float velocity;

    private TextureRegion textureRegion;

    public Clouds(float x, float y, int width, int height) {
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;

        isScrolled = false;
        velocity = 40;
        setTexture();
    }

    public void update(float delta) {
        position.x -= velocity * delta;

        if (position.x + width < 0) {
            isScrolled = true;
        }
    }

    protected void setTexture() {
        textureRegion = AssetsLoader.clouds;
    }

    public void resetX(float newX) {
        position.x = newX;
        isScrolled = false;
    }

    public float getTailX() {
        return position.x + width;
    }

    public boolean isScrolled() {
        return isScrolled;
    }

    public void draw(Batch batch) {
        batch.draw(textureRegion, position.x, position.y, width, height);
    }

    public void stop() {
        velocity = 0;
    }

    public void reset() {
        isScrolled = false;
        velocity = 40;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }
}
