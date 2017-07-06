package me.marcocarrizales.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

import java.util.Random;

import me.marcocarrizales.helpers.AssetsLoader;

/**
 * Created by marco on 4/17/2017.
 */

public class FallingObjects implements Pool.Poolable{

    private Vector2 position;
    private final int width;
    private final int height;

    private Random random;
    private TextureRegion textureRegion;
    private int id;
    private int spawnFlag;
    private float velocity;
    private boolean started;
    private boolean fell;
    private boolean isScored;

    private Rectangle collider;

    public FallingObjects(int width, int height, int id) {
        random = new Random();
        this.position = new Vector2(getRandomNumber((int) (AssetsLoader.GAMEWIDTH - width)), -height);
        this.width = width;
        this.height = height;
        this.id = id;
        textureRegion = setTextureRegion();

        velocity = 600;
        spawnFlag = 0;
        started = false;
        fell =false;
        isScored = false;

        this.collider = new Rectangle();
    }

    public void update(float delta) {
        position.y += velocity * delta;

        if (position.y > height / 2) {
            if (spawnFlag <= 1) {
                spawnFlag ++;
            }
        }
        if (position.y > AssetsLoader.GAMEHEIGHT)
            fell = true;
        collider.set(position.x, position.y, width, height);
    }

    public void draw(Batch batch) {
        batch.draw(textureRegion, position.x, position.y, width, height);
    }

    @Override
    public void reset() {
        position.x = getRandomNumber((int) (AssetsLoader.GAMEWIDTH - width));
        position.y = -height;
        spawnFlag = 0;
        velocity = 600;
        fell = false;
        isScored = false;
        started = false;
        id = getRandomNumber(2);
        textureRegion = setTextureRegion();
    }

    protected TextureRegion setTextureRegion() {
        if (this.id == 1) {
            switch (getRandomNumber(4)) {
                case 0:
                    return AssetsLoader.gel_blue;
                case 1:
                    return AssetsLoader.gel_green;
                case 2:
                    return AssetsLoader.gel_pink;
                case 3:
                    return AssetsLoader.gel_red;
            }
        }
            return AssetsLoader.cactus;
    }

    protected int getRandomNumber(int range) {
        return random.nextInt(range);
    }

    public boolean hasFallen() {
        return fell;
    }

    public int getSpawnFlag() {
        return spawnFlag;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public boolean collides(Gelatino gelatino) {
        if (position.x < gelatino.getX() + gelatino.getWidth())
            return (Intersector.overlaps(gelatino.getHead(), collider) || Intersector.overlaps(gelatino.getBody(), collider)
                    || Intersector.overlaps(gelatino.getDownLeg(), collider) || Intersector.overlaps(gelatino.getUpLeg(), collider));
        return false;
    }

    public void stop() {
        velocity = 0;
    }

    public boolean isCactus() {
        return id == 0;
    }

    public boolean isScored() {
        return isScored;
    }

    public void score() {
        isScored = true;
    }
}
