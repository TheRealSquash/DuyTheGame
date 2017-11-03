import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Door extends GameObject{

	private BufferedImage door_image;
	private Handler handler;
	
	public Door(int x, int y, ID id, Handler handler, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.handler = handler;
		door_image = ss.grabImage(10, 2, 32, 32);
	}

	public void tick() {
		if(Game.key == true)handler.removeObject(this);
	}

	public void render(Graphics g) {
		g.drawImage(door_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
