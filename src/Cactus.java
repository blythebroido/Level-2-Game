import java.awt.Color;
import java.awt.Graphics;

public class Cactus extends DinoGameObject{
int speed;
	Cactus(int x, int y, int width, int height, int speed) {
		super(x, y, width, height);
		this.speed = speed;
	}
	
	public void update(){
		super.update();
		x-=speed;
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.GREEN);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

}
