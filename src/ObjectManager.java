import java.util.ArrayList;

public class ObjectManager {

	public boolean checkCollision(Dinosaur d, ArrayList<Bird> b, ArrayList<Cactus> c) {
		for (Bird bi:b) {
			if(d.collisionBox.intersects(bi.collisionBox)){
				return true;
			}
		}
		for (Cactus ca:c) {
			if(d.collisionBox.intersects(ca.collisionBox)){
				return true;
			}
		}
		
		return false;
	}

}
