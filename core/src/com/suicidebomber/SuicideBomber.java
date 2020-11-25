package com.suicidebomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.suicidebomber.source.*;


public class SuicideBomber extends ApplicationAdapter {
	
	@Override
	public void create () {
		GameHelper.instance().create();
		ImageManager.instance().create();
		FontManager.instance().create();
		MapLoader.instance().create();
		SoundManager.instance().create();
		SceneManager.instance().create();
	}

	@Override
	public void render () {
		ImageManager.instance().clearScreen();
		ImageManager.batch.begin();
		SceneManager.instance().render();
		ImageManager.batch.end();
	}
	
	@Override
	public void dispose () {
		ImageManager.batch.dispose();
		ImageManager.instance().dispose();
		FontManager.instance().create();
		SoundManager.instance().dispose();
		SceneManager.instance().dispose();
	}
}
