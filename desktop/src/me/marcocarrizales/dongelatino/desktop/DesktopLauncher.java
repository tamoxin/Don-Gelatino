package me.marcocarrizales.dongelatino.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.marcocarrizales.dongelatino.DonGelatino;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Don Gelatino";
		config.height = 408;
		config.width = 272;
		new LwjglApplication(new DonGelatino(), config);
	}
}
