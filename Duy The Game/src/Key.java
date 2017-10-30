import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Key extends GameObject{

	private BufferedImage key_image;
	
	public Key(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		key_image = ss.grabImage(8, 2, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(key_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
