import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Water extends GameObject {

	private BufferedImage water_image;
	
	public Water(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		water_image = ss.grabImage(7, 2, 32, 32);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(water_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}