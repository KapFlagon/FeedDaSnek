package com.jpgd.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jpgd.game.FeedDaSnek;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = FeedDaSnek.TITLE;
		config.width = FeedDaSnek.V_WIDTH;
		config.height = FeedDaSnek.V_HEIGHT;
		new LwjglApplication(new FeedDaSnek(), config);
	}
}
