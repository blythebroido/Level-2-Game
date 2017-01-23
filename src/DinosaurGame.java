import javax.swing.JFrame;

public class DinosaurGame {
	JFrame frame = new JFrame();
	DinoGamePanel dinogamepanel = new DinoGamePanel();

	final int width = 800;
	final int height = 500;

	public static void main(String[] args) {
		DinosaurGame dinosaurgame = new DinosaurGame();
		dinosaurgame.setup();
	}

	void setup() {
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.add(dinogamepanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dinogamepanel.startGame();
	}
}