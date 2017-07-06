package me.marcocarrizales.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by marco on 4/9/2017.
 */

public class CloudsScroller {
    private Clouds frontClouds;
    private Clouds backClouds;

    public CloudsScroller(float gameWidth, float gameHeight) {
        frontClouds = new Clouds(0, 0, (int) (gameWidth * 1.5), (int)(gameHeight*.35));
        backClouds = new Clouds(frontClouds.getTailX(), 0, (int) (gameWidth * 1.5), (int)(gameHeight*.35));
    }

    public void update(float delta) {
        frontClouds.update(delta);
        backClouds.update(delta);

        if(frontClouds.isScrolled()) {
            frontClouds.resetX(backClouds.getTailX());
        }
        else {
            if (backClouds.isScrolled())
                backClouds.resetX(frontClouds.getTailX());
        }
    }

    public void draw(Batch batch) {
        frontClouds.draw(batch);
        backClouds.draw(batch);
    }

    public void stop() {
        frontClouds.stop();
        backClouds.stop();
    }

    public void reset() {
        frontClouds.setPosition(0, 0);
        frontClouds.reset();
        backClouds.setPosition(frontClouds.getTailX(), 0);
        backClouds.reset();
    }
}
