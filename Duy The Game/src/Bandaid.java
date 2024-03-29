import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bandaid extends GameObject{

	private BufferedImage bandaid_image;
	
	public Bandaid(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		bandaid_image = ss.grabImage(17, 1, 32, 32);
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(bandaid_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
