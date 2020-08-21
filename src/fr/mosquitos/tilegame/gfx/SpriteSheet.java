package fr.mosquitos.tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height ) {
		return sheet.getSubimage(x,  y,  width,  height);
	}
	
	public BufferedImage buttonCrop(int x, int y, int nWidth, int nHeight, int width, int height ) {
		return sheet.getSubimage(x,  y,  nWidth*width,  nHeight*height);
	}
}
