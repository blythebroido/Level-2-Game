import java.awt.Color;
import java.awt.Graphics;

public class Background extends DinoGameObject {

	Background(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	public void update(){
		super.update();
	}
	
	public void draw(Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
	}
}
