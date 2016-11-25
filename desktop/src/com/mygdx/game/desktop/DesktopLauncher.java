package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BullsEyes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = BullsEyes.APP_TITLE + " v " + BullsEyes.APP_VERSION ;
		config.width = BullsEyes.APP_DESKTOP_WIDTH;
		config.height = BullsEyes.APP_DESKTOP_HEIGHT;
		config.backgroundFPS = BullsEyes.APP_FPS;
		config.foregroundFPS = BullsEyes.APP_FPS;



		new LwjglApplication(new BullsEyes(), config);
	}
}
