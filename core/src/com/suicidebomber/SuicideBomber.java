package com.suicidebomber;

import com.badlogic.gdx.ApplicationAdapter;


public class SuicideBomber extends ApplicationAdapter {

	private Testing test;
	
	@Override
	public void create () {
		test = new Testing();
		GameElement.imageManager.create();
		test.root._create();
	}

	@Override
	public void render () {
		GameElement.imageManager.clearScreen();
		GameElement.imageManager.startDraw();

		test.root._render();

		GameElement.imageManager.endDraw();

	}
	
	@Override
	public void dispose () {
		GameElement.imageManager.dispose();
		test.root._dispose();
	}
}
