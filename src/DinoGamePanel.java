import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class DinoGamePanel extends JPanel implements KeyListener {

	Dinosaur dinosaur = new Dinosaur(100, 100, 100, 100);

	void startGame() {

	}

	public void paintComponent(Graphics g) {
		dinosaur.draw(g);
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed :(");

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			dinosaur.y -= 15;
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
