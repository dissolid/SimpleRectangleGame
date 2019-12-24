package javaJFarameDeneme;

import java.awt.Graphics;
import java.awt.Rectangle;

public class GameCanvas {

	int x, y;
	int width, height;

	Rectangle canvas;

	public GameCanvas(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		canvas = new Rectangle(this.x, this.y, this.width, this.height);
	}

	public void paint(Graphics g) {
		g.drawRect((int) canvas.getX(), (int) canvas.getY(), (int) canvas.getWidth(), (int) canvas.getHeight());

	}

}
