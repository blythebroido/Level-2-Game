import java.awt.Color;
import java.awt.Graphics;

public class Dinosaur extends DinoGameObject {

	public Dinosaur(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.setColor(Color.RED);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	void update() {
		super.update();
	}
}
