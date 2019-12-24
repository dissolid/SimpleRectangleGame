package javaJFarameDeneme;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Dortgen {

	Rectangle rectangle;
	int x, y;
	int width, height;
	boolean collision = false; // Dortgenin çarmışmalara açık olup olmayacağını belirleyen değişken.
	boolean userController = true;

	public Dortgen() {

	}

	public Dortgen(int x, int y, int width, int height) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rectangle = new Rectangle(this.x, this.y, this.width, this.height);

	}

	public void paint(Graphics g) {
		g.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(),
				(int) rectangle.getHeight());
		// g.setColor(c);

	}

	public void move(int x, int y) {
		// Bu metod verilen parametre değerleri kadar dörtgeni hareket ettirir.
		this.x = (int) rectangle.getX() + x;
		this.y = (int) rectangle.getY() + y;
		rectangle.setLocation(this.x, this.y);

	}

	public void transform(int widthAmount, int heightAmount) {
		// Bu metod dortgenin boyutlarını değiştirmenizi sağlar
		this.width = (int) rectangle.getWidth() + (widthAmount * 2);
		this.height = (int) rectangle.getHeight() + (heightAmount * 2);
		this.x -= widthAmount;
		this.y -= heightAmount;
		rectangle.setLocation(this.x, this.y);
		rectangle.setSize(width, height);
	}

	public void updateAll(int x, int y, int width, int height) {
		// bu metod dortgenin koordinatlarını ve boyutunu düzeltmenizi sağlar
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rectangle.setLocation(this.x, this.y);
		rectangle.setSize(this.width, this.height);
	}

	public void updateLocation(int x, int y) {
		// bu metod dortgenin koordinatlarını ve boyutunu düzeltmenizi sağlar
		this.x = x;
		this.y = y;

		rectangle.setLocation(this.x, this.y);

	}

	// COLLISION
	public boolean checkActorCollision(Rectangle r) {
		// bu metod dortgenin çarpışma durumunu kontrol eder

		if (this.collision) {
			if (rectangle.intersects(r)) {
				// System.out.println("collision");
				return true;
			} else {
				// System.out.println("no collision");
				return false;
			}
		} else {
			System.out.println("The collision parameter is off");
			return false;
		}
	}

	public int setBounds(int objectWidth, int objectHeight, GameCanvas canvas) {
		// 0 is x _____ 1 is y _____ -1 is not

		int calculatedX = (int) rectangle.getX() + objectWidth;
		int calculatedY = (int) rectangle.getY() + objectHeight;

		if (calculatedX >= canvas.width || rectangle.getX() < 0) {
			return 0;
		} else if (calculatedY >= canvas.height || rectangle.getY() < 0) {
			return 1;
		} else {
			return -1;
		}

	}

	// GETTERS SETTERS
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
