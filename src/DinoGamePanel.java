import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;

public class DinoGamePanel extends JPanel implements ActionListener, KeyListener {

	ArrayList<Cactus> cacti = new ArrayList<Cactus>();
	ArrayList<Bird> birds = new ArrayList<Bird>();

	Timer timer;
	long gameTimer=0;

	DinoGamePanel() {
		timer = new Timer(1000 / 60, this);
	}

	Dinosaur dinosaur = new Dinosaur(100, 10, 50, 50);
	ObjectManager objectmanager = new ObjectManager();
	Random r = new Random();
	Random rb = new Random();
	Boolean shouldAdd = true;
	long cactusTimer = 0;
	int minimumWait = 1000;
	Boolean shouldAddBird = true;
	long birdTimer = 0;
	int minimumWaitBird = 1000;
	final int easy = 0;
	final int medium = 1;
	final int hard = 2;
	int currentDifficulty = easy;
	long difficultyTimer = 0;
	int difficultyWaitTime = 30000;
	final int menuState = 0;
	final int gameState = 1;
	final int endState = 2;
	int currentState = menuState;
	
	public static BufferedImage dino;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;


	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		dinosaur.draw(g);
		for (Cactus c : cacti) {
			c.draw(g);
		}
		for (Bird b : birds) {
			b.draw(g);
		}

		if (currentState == menuState) {
			drawmenuState(g);
		} else if (currentState == endState) {
			drawendState(g);
		}
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("key pressed :(");

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			dinosaur.jump();

		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			currentState=currentState + 1;
			gameTimer=System.currentTimeMillis();
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		repaint();
		dinosaur.update();
		if (currentState == menuState) {
			updatemenuState();
		} 
		else if(currentState == gameState){
			updategameState();
		}
		else if (currentState == endState) {
			updateendState();
		}
		
		if(objectmanager.checkCollision(dinosaur, birds, cacti)){
			currentState=endState;
			gameTimer=System.currentTimeMillis()-gameTimer;
		}
		
	}

	public void addCactus() {
		if (shouldAdd == true) {
			if (currentDifficulty == easy) {
				cacti.add(new Cactus(900, 375, 50, 100, 7));
				shouldAddBird = false;
			} else if (currentDifficulty == medium) {
				cacti.add(new Cactus(900, 375, 50, 100, 10));
				minimumWait = 500;
			} else if (currentDifficulty == hard) {
				Random a = new Random();
				boolean addornot = a.nextBoolean();
				if (addornot == true) {
					cacti.add(new Cactus(900, 375, 50, 100, 13));
				} else {
					birds.add(new Bird(850, 420, 50, 30));
				}
				cactusTimer = 0;
				birdTimer = 0;
				minimumWait = 1000;
			}
			shouldAdd = false;
		}
	}

	void updatemenuState() {

	}

	void updategameState() {
		for (Cactus c : cacti) {
			c.update();
		}
		for (Bird b : birds) {
			b.update();
		}
		if (r.nextInt(100) == 0) {
			addCactus();
		}

		if (shouldAdd == false && cactusTimer == 0) {
			cactusTimer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - cactusTimer > minimumWait) {
			shouldAdd = true;
		}

		if (shouldAddBird == false && birdTimer == 0) {
			birdTimer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - birdTimer > minimumWaitBird) {

			birdTimer = 0;
		}
		if (difficultyTimer == 0) {
			difficultyTimer = System.currentTimeMillis();
		}
		if (System.currentTimeMillis() - difficultyTimer > difficultyWaitTime) {
			currentDifficulty++;
			difficultyTimer = 0;
		}
		if (currentDifficulty > 2) {
			currentDifficulty = 2;
		}

		System.out.println(currentDifficulty);

	}

	void updateendState() {

	}

	void drawmenuState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, 800, 500);
	}


	void drawendState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 800, 500);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 90, 100);
		g.setColor(Color.BLACK);
		g.drawString("You survived for  " + gameTimer/1000 + " seconds.", 140, 300);
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to enter the game where you left off. Quit and restart to start over", 85, 500);
	}
}
