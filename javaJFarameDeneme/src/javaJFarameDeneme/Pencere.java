package javaJFarameDeneme;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Pencere {
	public static void main(String[] args) {

		Dimension screenResulation = Toolkit.getDefaultToolkit().getScreenSize(); // current screen resulation

		JFrame mainFrame = new JFrame(); // main frame
		GamePanel yeniPanel = new GamePanel();

		mainFrame.add(yeniPanel);
		mainFrame.addKeyListener(yeniPanel);
		mainFrame.setVisible(true);
		mainFrame.setSize(1017, 800);
		mainFrame.setResizable(false);

		mainFrame.setLocation((screenResulation.width / 2) - (mainFrame.getSize().width / 2),
				(screenResulation.height / 2) - (mainFrame.getSize().height / 2));
		//System.out.println("x:" + mainFrame.getLocation().x + "y: " + mainFrame.getLocation().y);

	}

}
