import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DivineJuice extends GameObject{

	private BufferedImage divineJuice_image;
	
	public DivineJuice(int x, int y, ID id, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		divineJuice_image = ss.grabImage(6, 2, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(divineJuice_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
