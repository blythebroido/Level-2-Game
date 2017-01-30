import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;

public class DinoGamePanel extends JPanel implements ActionListener, KeyListener{

	ArrayList<Cactus> cacti =  new ArrayList<Cactus>();
	
	Timer timer;
	
	DinoGamePanel(){
		timer = new Timer(1000 / 60, this);
	}
	
	Dinosaur dinosaur = new Dinosaur(100, 100, 100, 100);
	Cactus cactus = new Cactus(800, 275, 100, 200);
	Background background = new Background(0, 0, 800, 500);
	Random r = new Random();

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		background.draw(g);
		dinosaur.draw(g);
		cactus.draw(g);
		for (Cactus c: cacti) {
			c.draw(g);
		}
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed :(");

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			dinosaur.jump();
		
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		repaint();
		dinosaur.update();
		cactus.update();
		for (Cactus c: cacti) {
			c.update();
		}
		if(r.nextInt(100)==0){
			addCactus();
		}
	}
	
	public void addCactus(){
		cacti.add(new Cactus(800, 150, 100, 200));
	}

}
