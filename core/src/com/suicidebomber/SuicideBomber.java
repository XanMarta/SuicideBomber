package com.suicidebomber;

import com.badlogic.gdx.ApplicationAdapter;


public class SuicideBomber extends ApplicationAdapter {
	
	@Override
	public void create () {
		GameElement.imageManager.create();
	}

	@Override
	public void render () {
		GameElement.imageManager.clearScreen();
		GameElement.imageManager.startDraw();
		for (int i = 0; i < GameElement.mapSize.x; i++) {
			for (int j = 0; j < GameElement.mapSize.y; j++) {
				GameElement.imageManager.drawImage(
						"player",
						i * GameElement.blockSize.x + GameElement.mapPosition.x,
						j * GameElement.blockSize.y + GameElement.mapPosition.y);
			}
		}
		GameElement.imageManager.endDraw();
	}
	
	@Override
	public void dispose () {
		GameElement.imageManager.dispose();
	}
}
