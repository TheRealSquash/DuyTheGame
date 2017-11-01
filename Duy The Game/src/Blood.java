import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Blood extends GameObject{

	private BufferedImage blood_image;
	
	public Blood(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		blood_image = ss.grabImage(18, 2, 32, 32);
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(blood_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
