import java.awt.Color;
import java.awt.Graphics;

public class Bird extends DinoGameObject{

	Bird(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void update(){
		super.update();
		x-=13;
		collisionBox.setBounds(x, y, width, height);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

}
