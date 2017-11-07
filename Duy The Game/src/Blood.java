import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Blood extends GameObject{

	private BufferedImage blood_image;
	private double transparency = 1.0;
	private Handler handler;
	
	public Blood(int x, int y, ID id, Handler handler, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.handler = handler;
		blood_image = ss.grabImage(18, 2, 32, 32);
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		float alpha = (float) transparency;
		int type = AlphaComposite.SRC_OVER; 
		AlphaComposite composite = AlphaComposite.getInstance(type, alpha);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(composite);
		g2d.drawImage(blood_image, x, y, null);
		alpha = (float) 1;
		composite = AlphaComposite.getInstance(type, alpha);
		g2d.setComposite(composite);
		if(transparency > 0.001) {
			transparency -= 0.001;
		}else {
			handler.removeObject(this);
		}
		
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
