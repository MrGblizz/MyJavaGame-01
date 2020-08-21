package fr.mosquitos.tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import fr.mosquitos.tilegame.Game;
import fr.mosquitos.tilegame.Handler;
import fr.mosquitos.tilegame.gfx.Assets;

public class MenuState extends State{

	
	public MenuState(Handler handler) {
		super(handler);

	}
	
	
	@Override
	public void tick() {
		System.out.println("MousX : " + handler.getMouseManager().getMouseX() +" 	MouseY : " + handler.getMouseManager().getMouseY());
		     
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 5, 5);
	}


	

}
