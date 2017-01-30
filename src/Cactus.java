import java.awt.Color;
import java.awt.Graphics;

public class Cactus extends DinoGameObject{

	Cactus(int x, int y, int width, int height) {
		super(x, y, width, height);

	}
	
	public void update(){
		x-=5;
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.GREEN);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

}
