package me.marcocarrizales.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Pool;

import java.util.Random;

import me.marcocarrizales.helpers.AssetsLoader;

/**
 * Created by marco on 4/17/2017.
 */

public class FallingObjectsPool extends Pool<FallingObjects> {

    private Random random;

    public FallingObjectsPool() {
        super();
        random = new Random();
    }
    @Override
    protected FallingObjects newObject() {
        return new FallingObjects((int) (AssetsLoader.GAMEWIDTH * .13), (int) (AssetsLoader.GAMEHEIGHT * .11), raffleItem());
    }

    private int raffleItem() {
        return (random.nextInt(100) < 75) ? 0 : 1;
    }
}
