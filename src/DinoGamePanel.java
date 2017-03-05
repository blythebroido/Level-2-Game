import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DinoGamePanel extends JPanel implements ActionListener, KeyListener {

	ArrayList<Cactus> cacti = new ArrayList<Cactus>();
	ArrayList<Bird> birds = new ArrayList<Bird>();

	Timer timer;
	long gameTimer = 0;
	boolean sleep = false;

	DinoGamePanel() {
		timer = new Timer(1000 / 60, this);
		try {
			dino = ImageIO.read(this.getClass().getResourceAsStream("dinoimage.png"));
			cactus = ImageIO.read(this.getClass().getResourceAsStream("cactusimage.png"));
			bird = ImageIO.read(this.getClass().getResourceAsStream("birdimage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Dinosaur dinosaur = new Dinosaur(100, 10, 50, 50);
	Background background = new Background(0,0, 800,500);
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

	Font titleFont = new Font("Times New Roman", Font.BOLD, 48);
	Font instructionFont = new Font("Times New Roman", Font.PLAIN, 24);

	public static BufferedImage dino;
	public static BufferedImage cactus;
	public static BufferedImage bird;

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		background.draw(g);
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

		if (currentState == menuState) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				currentState = currentState + 1;
				gameTimer = System.currentTimeMillis();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_P){
			System.exit(0);
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
		} else if (currentState == gameState) {
			updategameState();
		} else if (currentState == endState) {
			updateendState();
		}

		if (currentState == gameState) {
			if (objectmanager.checkCollision(dinosaur, birds, cacti)) {
				currentState = endState;
				gameTimer = System.currentTimeMillis() - gameTimer;

			}
		}
		if (currentState == gameState) {
			if (dinosaur.y < 0) {
				currentState = endState;
				gameTimer = System.currentTimeMillis() - gameTimer;
			}
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
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Dinosaur Game!", 250, 100);
		g.setFont(instructionFont);
		g.setColor(Color.WHITE);
		g.drawString("Press the up arrow to jump. Avoid cacti and birds, and don't hit the roof!", 50, 200);
		g.setColor(Color.WHITE);
		g.drawString("Press ENTER to start!", 275, 300);
		g.setColor(Color.WHITE);
		g.drawString("Press P at any time to exit", 275, 400);

	}

	void drawendState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, 800, 500);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 250, 100);
		g.setFont(instructionFont);
		g.setColor(Color.BLACK);
		g.drawString("You died! You survived for  " + gameTimer / 1000 + " seconds.", 200, 200);
		g.setColor(Color.BLACK);
		g.drawString("You reached level " + currentDifficulty + "!", 200, 300);
		g.setFont(instructionFont);
		g.setColor(Color.BLACK);
		g.drawString("Quit and restart to start over", 200, 400);

	}

}