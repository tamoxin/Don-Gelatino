package me.marcocarrizales.dongelatino;

import com.badlogic.gdx.Game;

import me.marcocarrizales.helpers.AssetsLoader;
import me.marcocarrizales.screens.GameScreen;
import me.marcocarrizales.screens.SplashScreen;

public class DonGelatino extends Game {
	@Override
	public void create() {
		AssetsLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetsLoader.dispose();
	}
}
