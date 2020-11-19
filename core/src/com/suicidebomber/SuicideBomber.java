package com.suicidebomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.suicidebomber.structure.GameElement;


public class SuicideBomber extends ApplicationAdapter {
	
	@Override
	public void create () {
		GameElement.batch = new SpriteBatch();
		GameElement.imageManager.create();
		GameElement.fontManager.create();
		GameElement.soundManager.create();
		GameElement.sceneManager.create();
	}

	@Override
	public void render () {
		GameElement.imageManager.clearScreen();
		GameElement.batch.begin();
		GameElement.sceneManager.render();
		GameElement.batch.end();
	}
	
	@Override
	public void dispose () {
		GameElement.batch.dispose();
		GameElement.imageManager.dispose();
		GameElement.fontManager.dispose();
		GameElement.soundManager.dispose();
		GameElement.sceneManager.dispose();
	}
}
