package fr.mosquitos.tilegame.states;

import java.awt.Graphics;

import fr.mosquitos.tilegame.Game;
import fr.mosquitos.tilegame.Handler;

public abstract class State {

	private static State currentState = null;
	protected static Game game = null;
	protected  Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public static void setState(State state) {
		currentState = state;
		
	}
	
	public static State getState() {
		return currentState;
	}
	
	
	//STATE
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
