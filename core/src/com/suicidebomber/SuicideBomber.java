package com.suicidebomber;

import com.badlogic.gdx.ApplicationAdapter;
import com.suicidebomber.element.Node;


public class SuicideBomber extends ApplicationAdapter {

	private Testing test = new Testing();
	
	@Override
	public void create () {
		GameElement.imageManager.create();
		test.root._create();
	}

	@Override
	public void render () {
		GameElement.imageManager.clearScreen();
		GameElement.imageManager.startDraw();
//		for (int i = 0; i < GameElement.mapSize.x; i++) {
//			for (int j = 0; j < GameElement.mapSize.y; j++) {
//				String object = "player";
//				if (i == 0 || j == 0 || i == GameElement.mapSize.x || j == GameElement.mapSize.y) {
//					object = "wall";
//				}
//				GameElement.imageManager.drawImage(
//						object,
//						i * GameElement.blockSize.x + GameElement.mapPosition.x,
//						j * GameElement.blockSize.y + GameElement.mapPosition.y);
//			}
//		}
		test.root._render();

		GameElement.imageManager.endDraw();

	}
	
	@Override
	public void dispose () {
		GameElement.imageManager.dispose();
		test.root._dispose();
	}
}
