import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Berg extends GameObject {

	private BufferedImage berg_image;
	
	public Berg(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		berg_image = ss.grabImage(5, 2, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(berg_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}
