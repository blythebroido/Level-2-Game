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
	ArrayList<Bird> birds = new ArrayList<Bird>();
 	
	Timer timer;
	
	DinoGamePanel(){
		timer = new Timer(1000 / 60, this);
	}
	
	Dinosaur dinosaur = new Dinosaur(100, 10, 50, 50);
	Background background = new Background(0, 0, 800, 500);
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
	

	void startGame() {
		timer.start();
	}

	public void paintComponent(Graphics g) {
		background.draw(g);
		dinosaur.draw(g);
		for (Cactus c: cacti) {
			c.draw(g);
		}
		for (Bird b: birds) {
			b.draw(g);
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
		for (Cactus c: cacti) {
			c.update();
		}
		for(Bird b: birds){
			b.update();
		}
		if(r.nextInt(100)==0){
			addCactus();
		}
		if(rb.nextInt(100)==0){
			if(currentDifficulty == hard){
			addBird();
			}
		}
		if(shouldAdd==false && cactusTimer ==0){
			cactusTimer = System.currentTimeMillis();
		}
		if(System.currentTimeMillis()-cactusTimer > minimumWait){
				shouldAdd = true;
				cactusTimer=0;
		}
		
		if(shouldAddBird==false && birdTimer ==0){
			birdTimer = System.currentTimeMillis();
		}
		if(System.currentTimeMillis()-birdTimer > minimumWaitBird){
				shouldAddBird = true;
				birdTimer=0;
		}
		
		if(difficultyTimer==0){
			difficultyTimer = System.currentTimeMillis();
		}
		if(System.currentTimeMillis()-difficultyTimer > difficultyWaitTime){
			currentDifficulty++;
			difficultyTimer = 0;
		}
		if(currentDifficulty > 2){
			currentDifficulty = 2;
		}
		
		System.out.println(currentDifficulty);
	}
	
	public void addCactus(){
		if(shouldAdd == true){
			if(currentDifficulty == easy){
				cacti.add(new Cactus(900, 375, 50, 100, 7));
			}
			else if(currentDifficulty == medium){
				cacti.add(new Cactus(900, 375, 50, 100, 10));
				minimumWait = 500;
			}
			else if(currentDifficulty == hard){
				cacti.add(new Cactus(900, 375, 50, 100, 13));
			}
		shouldAdd = false;
		}
	}
	
	public void addBird(){
		if(shouldAddBird == true){
			birds.add(new Bird(850, 420, 50, 30));
			shouldAddBird = false;
		}
	}

	void drawEasy(){
		
	}
	void drawMedium(){
		
	}
	void drawHard(){
		
	}
}
