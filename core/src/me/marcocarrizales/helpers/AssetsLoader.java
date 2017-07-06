package me.marcocarrizales.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by marco on 3/15/2017.
 */

public class AssetsLoader {

    // To save the highest score
    public static Preferences preferences;

    public static float GAMEWIDTH = 720;
    public static float GAMEHEIGHT = 1280;

    //Directions Assets
    public static Texture directionsTexture1;
    public static Texture directionsTexture2;
    public static TextureRegion directions1;
    public static TextureRegion directions2;
    public static TextureRegion directions3;

    //Game over assets
    public static Texture end;
    public static TextureRegion gameOver;
    public static TextureRegion highScore;

    //Game Assets
    public static Texture textures;
    public static Sprite logo;
    public static BitmapFont font;
    public static TextureRegion bg_default;
    public static TextureRegion bg_playa;
    public static TextureRegion clouds;
    public static TextureRegion gelatino;
    public static TextureRegion gelatino_dead;
    public static TextureRegion cactus;
    public static TextureRegion gel_green;
    public static TextureRegion gel_red;
    public static TextureRegion gel_pink;
    public static TextureRegion gel_blue;
    public static TextureRegion playBtnUp;
    public static TextureRegion playBtnDown;
    public static TextureRegion resetBtnUp;
    public static TextureRegion resetBtnDown;

    public static void load() {

        textures = new Texture(Gdx.files.internal("assets.png"));
        textures.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        directionsTexture1 = new Texture((Gdx.files.internal("directions.png")));
        directionsTexture1.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        directionsTexture2 = new Texture((Gdx.files.internal("directions3.png")));
        directionsTexture2.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        end = new Texture(Gdx.files.internal("end.png"));
        end.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        // splash screen assets
        logo = new Sprite(textures, 1446, 1534, 514, 512);

        // directionsTexture1 screen
        directions1 = new TextureRegion(directionsTexture1, 2, 2, 720, 1280);
        directions1.flip(false, true);
        directions2 = new TextureRegion(directionsTexture1, 724, 2, 720, 1280);
        directions2.flip(false, true);
        directions3 = new TextureRegion(directionsTexture2);
        directions3.flip(false, true);

        // menu assets
        playBtnDown = new TextureRegion(textures, 1849, 1412, 99, 120);
        playBtnDown.flip(false, true);
        playBtnUp = new TextureRegion(textures, 1849, 1290, 99, 120);
        playBtnUp.flip(false, true);
        resetBtnUp = new TextureRegion(textures, 1751, 956, 62, 62);
        resetBtnUp.flip(false, true);
        resetBtnDown = new TextureRegion(textures, 1962, 1984, 62, 62);
        resetBtnDown.flip(false, true);

        // game over
        gameOver = new TextureRegion(end, 2, 2, 720, 1280);
        gameOver.flip(false, true);
        highScore = new TextureRegion(end, 724, 2, 720, 1280);
        highScore.flip(false, true);

        // game assets
        bg_default = new TextureRegion(textures, 2, 766, 720, 1280);
        bg_default.flip(true, true);
        bg_playa = new TextureRegion(textures, 724, 766, 720, 1280);
        bg_playa.flip(true, true);
        cactus = new TextureRegion(textures, 1446, 592, 303, 426);
        cactus.flip(true, true);
        clouds = new TextureRegion(textures, 2, 363, 1080, 401);
        clouds.flip(true,true);
        gelatino = new TextureRegion(textures, 1446, 1020, 401, 512);
        gelatino.flip(true, true);
        gelatino_dead = new TextureRegion(textures, 1084, 252, 331, 512);
        gelatino_dead.flip(true, true);
        gel_blue = new TextureRegion(textures, 137, 2, 132, 248);
        gel_blue.flip(true, true);
        gel_green = new TextureRegion(textures, 271, 2, 132, 248);
        gel_green.flip(true, true);
        gel_pink = new TextureRegion(textures, 405, 2, 132, 248);
        gel_pink.flip(true, true);
        gel_red = new TextureRegion(textures, 2, 2, 133, 248);
        gel_red.flip(true, true);

        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.getData().setScale(2f, -2f);

        // Create (or retrieve existing) preferences file
        preferences = Gdx.app.getPreferences("Don Gelatino");

        // Provide default high score of 0 and total score of 0
        if (!preferences.contains("highScore"))
            preferences.putInteger("highScore", 0);
        if (!preferences.contains("total"))
            preferences.putInteger("total", 0);
        if (!preferences.contains("soundEnabled"))
            preferences.putBoolean("soundEnabled", true);
        if (!preferences.contains("directions"))
            preferences.putInteger("directions", 3);
    }

    public static void setHighScore(int val) {
        preferences.putInteger("highScore", val);
        preferences.flush();
    }

    public static void setSound(boolean soundEnabled) {
        preferences.putBoolean("soundEnabled", true);
        preferences.flush();
    }

    public static boolean isSoundEnabled() {
        return preferences.getBoolean("soundEnabled");
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return preferences.getInteger("highScore");
    }

    public static int directionsCount() {
        return preferences.getInteger("directions");
    }

    public static void directionsCountDown() {
        int count = preferences.getInteger("directions");
        count --;
        preferences.putInteger("directions", count);
        preferences.flush();
    }

    public static void dispose() {
        textures.dispose();
        directionsTexture1.dispose();
        directionsTexture2.dispose();
        end.dispose();
        font.dispose();
    }
}
