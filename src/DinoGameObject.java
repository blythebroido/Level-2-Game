import java.awt.Rectangle;

public class DinoGameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	Rectangle collisionBox;
	
	DinoGameObject(){
		collisionBox = new Rectangle(x, y, width, height);
	}
}
