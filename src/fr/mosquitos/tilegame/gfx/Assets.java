package fr.mosquitos.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 64 , height = 64;
	
	public static BufferedImage player,button;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/testesheet.png"));
		player = sheet.crop(0, 0, width, height);
		button = sheet.buttonCrop(width, 0, 2, 4, width, height);
	}
	
	
}
