import java.awt.Color;
import java.awt.Graphics;

public class Dinosaur extends DinoGameObject {
	
	int gravity = 1;
	int jumpPower = 20;
	int yVelocity = 0;
	int ground = 425;
	
	public Dinosaur(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.setColor(Color.RED);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	public void update() {
		super.update();		
		yVelocity += gravity;
		y += yVelocity;
		if(y>ground){
			y = ground;
			yVelocity = 0;
		}
		else if(y<0){
			y = ground;
			yVelocity = 0;
		}
		
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void jump(){
		yVelocity -= jumpPower;
	}
}
