import java.awt.Rectangle;

public class DinoGameObject {
	public int x;
	public int y;
	public int width;
	public int height;
	boolean isAlive = true;
	Rectangle collisionBox;

	DinoGameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		collisionBox(x, y, width, height);
	}

	void setX(int xVal) {
		this.x = xVal;
	}

	void setY(int yVal) {
		this.y = yVal;
	}

	void collisionBox(int x, int y, int width, int height) {
		collisionBox = new Rectangle(x, y, width, height);
	}

	public void update() {
		// TODO Auto-generated method stub
		collisionBox.setBounds(x, y, width, height);

	}
}
