package com.suicidebomber.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.suicidebomber.autoload.GameElement;
import com.suicidebomber.SuicideBomber;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SuicideBomber";
		config.width = (int) GameElement.windowsSize.x;
		config.height = (int) GameElement.windowsSize.y;
		config.forceExit = true;
		new LwjglApplication(new SuicideBomber(), config);
	}
}
