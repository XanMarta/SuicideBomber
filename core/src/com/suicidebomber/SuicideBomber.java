package com.suicidebomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.suicidebomber.game.GameElement;
import com.suicidebomber.game.PlayGround;


public class SuicideBomber extends ApplicationAdapter {

	private PlayGround playground;
	
	@Override
	public void create () {
		playground = new PlayGround();
		GameElement.imageManager.create();
		playground.root._create();
	}

	@Override
	public void render () {
		GameElement.imageManager.clearScreen();
		GameElement.imageManager.startDraw();

		playground.root._render();

		GameElement.imageManager.endDraw();

	}
	
	@Override
	public void dispose () {
		GameElement.imageManager.dispose();
		playground.root._dispose();
	}
}
