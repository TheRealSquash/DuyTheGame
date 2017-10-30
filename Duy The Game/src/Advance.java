import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Advance extends GameObject {

	private BufferedImage advance_image;
	
	public Advance(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		advance_image = ss.grabImage(4, 2, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(advance_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}
