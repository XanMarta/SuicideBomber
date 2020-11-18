package com.suicidebomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;
import com.suicidebomber.game.PlayGround;


public class SuicideBomber extends ApplicationAdapter {

	private PlayGround playground;
	
	@Override
	public void create () {
		playground = new PlayGround();
		GameElement.batch = new SpriteBatch();
		GameElement.imageManager.create();
		GameElement.fontManager.create();
		GameElement.soundManager.create();
		playground.root._create();
	}

	@Override
	public void render () {
		GameElement.imageManager.clearScreen();
		GameElement.batch.begin();

		playground.root._render();

		GameElement.batch.end();
	}
	
	@Override
	public void dispose () {
		GameElement.batch.dispose();
		GameElement.imageManager.dispose();
		GameElement.fontManager.dispose();
		GameElement.soundManager.dispose();
		playground.root._dispose();
	}
}
