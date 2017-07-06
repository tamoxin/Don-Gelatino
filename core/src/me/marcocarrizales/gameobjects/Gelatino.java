package me.marcocarrizales.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import me.marcocarrizales.helpers.AssetsLoader;

/**
 * Created by marco on 4/3/2017.
 */

public class Gelatino {
    private Vector2 position;

    private int width;
    private int height;

    private float velocity;
    private float originalX;

    private boolean isAlive;
    private boolean isGoingRight;
    private boolean isGoingLeft;

    private Rectangle head, body, upLeg, downLeg;

    private TextureRegion textureRegion;

    public Gelatino(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        originalX = x;
        isAlive = true;
        isGoingLeft = false;
        isGoingRight = false;
        velocity = 600;
        textureRegion = AssetsLoader.gelatino;

        setRectangles(x, y);
    }

    public void update(float delta) {
        if(isGoingLeft) {
            if (!textureRegion.isFlipX())
                textureRegion.flip(true, false);
            position.x -= velocity * delta;

            if(position.x < 0)
                position.x = 0;

            head.setPosition(position.x, position.y);
            body.setPosition((float) (position.x + width * .30), (float) (position.y + height * .5));
            upLeg.setPosition((float) (position.x + width * .75), (float) (position.y + height * .57));
            downLeg.setPosition((float) (position.x + width * .4), (float) (position.y + height * .75));
        }else if(isGoingRight) {
            if (textureRegion.isFlipX())
                textureRegion.flip(true, false);
            position.x += velocity * delta;

            if(position.x > 720 - width)
                position.x = 720 - width;

            head.setPosition((float) (position.x + width * .34), position.y);
            body.setPosition((float) (position.x +  width * .25), (float) (position.y + height * .5));
            upLeg.setPosition(position.x, (float) (position.y + height * .57));
            downLeg.setPosition((float) (position.x + width * .34), (float) (position.y + height * .75));
        }

    }

    public void draw(Batch batch) {
        batch.draw(textureRegion, position.x, position.y, width, height);
    }

    private void setRectangles(float x, float y) {
        this.head = new Rectangle(x, y, (float) (width * .66), (float) (height * .5));
        this.body = new Rectangle((float) (x + width * .30), (float) (y + height * .5),
                (float) (width * .45), (float) (height * .26));
        this.upLeg = new Rectangle((float) (x + width * .75), (float) (y + height * .57),
                (float) (width * .25), (float) (height * .13));
        this.downLeg = new Rectangle((float) (x + width * .4), (float) (y + height * .75),
                (float) (width * .25), (float) (height * .24));
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void rightClick(boolean b) {
        isGoingRight = b;
    }

    public void leftClick(boolean b) {
        isGoingLeft = b;
    }

    public Rectangle getHead() {
        return head;
    }

    public Rectangle getBody() {
        return body;
    }

    public Rectangle getUpLeg() {
        return upLeg;
    }

    public Rectangle getDownLeg() {
        return downLeg;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void stop() {
        velocity = 0;
        textureRegion = AssetsLoader.gelatino_dead;
        isAlive = false;
    }

    public void reset() {
        isAlive = true;
        isGoingLeft = false;
        isGoingRight = false;
        velocity = 600;
        textureRegion = AssetsLoader.gelatino;
        position.x = originalX;
        setRectangles(position.x, position.y);

    }
}
