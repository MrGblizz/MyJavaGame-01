package fr.mosquitos.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import fr.mosquitos.tilegame.display.Display;
import fr.mosquitos.tilegame.gfx.Assets;
import fr.mosquitos.tilegame.gfx.ImageLoader;
import fr.mosquitos.tilegame.gfx.SpriteSheet;
import fr.mosquitos.tilegame.input.MouseManager;
import fr.mosquitos.tilegame.states.CaracterState;
import fr.mosquitos.tilegame.states.CreditState;
import fr.mosquitos.tilegame.states.GameState;
import fr.mosquitos.tilegame.states.MenuState;
import fr.mosquitos.tilegame.states.SetingsState;
import fr.mosquitos.tilegame.states.State;

public class Game implements Runnable{
	
	private Display display;
	public int width, height; 
	private String title;
	
	private boolean renning;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//STATE
	public State gameState;
	public State menuState;
	public State creditState;
	public State setingsState;
	public State caracterState;
	
	//INPUT
	private MouseManager mouseManager;
	
	//HANDLER
	public Handler handler;
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		handler = new Handler(this);
		mouseManager = new MouseManager();
	}

	public State getGameState() {
		return gameState;
	}
	
	public State getMenuState() {
		return menuState;
	}
	
	public State getCreditState() {
		return creditState;
	}
	
	public State getSetingsState() {
		return setingsState;
	}
	
	public State getCaracterState() {
		return caracterState;
	}
	
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		creditState = new CreditState(handler);
		setingsState = new SetingsState(handler);
		caracterState = new CaracterState(handler);
		
		State.setState(menuState);
	}
	
	private void tick() {
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() {
		
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		 g = bs.getDrawGraphics();
		 //clear screen
		 g.clearRect(0, 0, width, height);
		 //draw here
		 
		 if(State.getState() != null)
				State.getState().render(g);
		 
		 //end drawing
		 bs.show();
		 g.dispose();
		 
	}
	
	
	@Override
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		
		
		while(renning) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1) {
			tick();
			render();
			delta--;
			}
		}
	
		stop();
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
		
	}
	
	
	public synchronized void start() {
		if(renning == true)
			return;
		renning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	public synchronized void stop() {
		if(!renning)
			return;
		renning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
