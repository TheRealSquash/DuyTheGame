import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BossBlock extends GameObject {
	
	public BossBlock(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}
