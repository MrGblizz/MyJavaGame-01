package fr.mosquitos.tilegame;

import fr.mosquitos.tilegame.input.MouseManager;

public class Handler {

	private Game game;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
}
