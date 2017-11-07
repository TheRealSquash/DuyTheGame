import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Door extends GameObject{

	private BufferedImage door_image;
	private Handler handler;
	private double transparency = 1;
	
	public Door(int x, int y, ID id, Handler handler, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.handler = handler;
		door_image = ss.grabImage(10, 2, 32, 32);
	}

	public void tick() {
		if(Game.key == true) {
			transparency -= 0.01;
			if(transparency < 0) {
				handler.removeObject(this);
			}
			
		}
	}

	public void render(Graphics g) {
		float alpha = (float) transparency;
		int type = AlphaComposite.SRC_OVER; 
		AlphaComposite composite = AlphaComposite.getInstance(type, alpha);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(composite);
		g2d.drawImage(door_image, x, y, null);
		alpha = (float) 1;
		composite = AlphaComposite.getInstance(type, alpha);
		g2d.setComposite(composite);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
