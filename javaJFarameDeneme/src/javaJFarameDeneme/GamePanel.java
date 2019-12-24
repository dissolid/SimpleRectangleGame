package javaJFarameDeneme;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	GameCanvas canvas;
	Dortgen actor;
	Dortgen[] otherActor;
	Timer timer;

	JLabel gameEnd;
	JPanel textPanel;

	int otherActorAmount = 20;
	int currentOtherActor = 0;
	int otherActorSpeed = 1;
	int canvasX = 0, canvasY = 0, canvasWidth = 1020, canvasHeight = 780;
	int width = 60, height = 60;
	int x = canvasWidth / 2 - (width / 2), y = canvasHeight - width - 10;
	boolean gameRunning = true;

	int increase = 10;
	int rectRange = 80;
	int random = 0;

	public GamePanel() {
		super();
		canvas = new GameCanvas(canvasX, canvasY, canvasWidth, canvasHeight); // CANVAS SETUP
		actor = new Dortgen(x, y, width, height);
		actor.collision = true;

		otherActor = new Dortgen[otherActorAmount];
		timer = new Timer(03, this);
		timer.start();

		gameEnd = new JLabel("Game Over!");

		textPanel = new JPanel();
		textPanel.add(gameEnd);
		textPanel.setVisible(false);

		add(textPanel);

		do {
			random = (int) (150 * Math.random());
			if (currentOtherActor < 1) {
				otherActor[currentOtherActor] = new Dortgen(random, 0, 50, 50);
				otherActor[currentOtherActor].collision = true;

			} else {
				otherActor[currentOtherActor] = new Dortgen(otherActor[currentOtherActor - 1].getX()
						+ otherActor[currentOtherActor - 1].getWidth() + random, random * 3, 50, 50);
				otherActor[currentOtherActor].collision = true;

			}
			currentOtherActor++;

		} while (currentOtherActor < otherActor.length);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		canvas.paint(g);
		// oA.paint(g);
		actor.paint(g);

		for (int i = 0; i < otherActorAmount; i++) {
			otherActor[i].paint(g);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (actor.userController) {

			int tmpX = actor.x, tmpY = actor.y, tmpW = actor.width, tmpH = actor.height;
			// System.out.println(tmpX + "-" + tmpY + "-" + tmpW + "-" + tmpH + "-");
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				actor.move(increase, 0);
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				actor.move(-increase, 0);
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				actor.move(0, -increase);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				actor.move(0, increase);
			} else if (e.getKeyCode() == 107) { // 107 is numpad + button
				actor.transform(increase, increase);
			} else if (e.getKeyCode() == 109) { // 109 is numpad - button

				if (actor.getWidth() > increase * 2 && actor.getHeight() > increase * 2) { // actor ün minimum
																							// küçülebileceği değer
					actor.transform(-increase, -increase);
				}
			}

			for (int i = 0; i < otherActorAmount; i++) {
				if (actor.checkActorCollision(otherActor[i].rectangle)) { // diğer aktörlere çarpma durumuna göre
																			// lokasyonu
					// güncelle
					actor.updateAll(tmpX, tmpY, tmpW, tmpH);
				}
			}

			if (actor.setBounds(actor.getWidth(), actor.getHeight(), canvas) == 0) { // 0 x ekseninde canvasa değiyor
																						// demek
				actor.updateAll(tmpX, tmpY, tmpW, tmpH);
			} else if (actor.setBounds(actor.getWidth(), actor.getHeight(), canvas) == 1) { // 1 y ekseninde canvasa
																							// değiyor
																							// // demek
				actor.updateAll(tmpX, tmpY, tmpW, tmpH);
			}

		}
		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (gameRunning) {
			for (Dortgen dortgen : otherActor) {

				dortgen.rectangle.setLocation((int) dortgen.rectangle.getX(),
						(int) dortgen.rectangle.getY() + otherActorSpeed);
				if ((int) dortgen.rectangle.getY() >= canvasHeight - dortgen.rectangle.getHeight()) {

					dortgen.rectangle.setLocation((int) dortgen.rectangle.getX() + (int) (300 * Math.random()), -60);
				} else if ((int) dortgen.rectangle.getX() >= canvasWidth - dortgen.rectangle.getWidth()) {

					dortgen.rectangle.setLocation((int) (300 * Math.random()), (int) dortgen.rectangle.getY());
				}
				if (actor.checkActorCollision(dortgen.rectangle)) {
					System.out.println("game over");
					gameRunning = false;
					timer.stop();
					actor.userController = false;
					textPanel.setVisible(true);
				}
			}

		}
		repaint();

	}

}
