import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Egg extends GameObject{
	private Handler handler;
	private BufferedImage egg_image;
	public int dx = 0, dy = 0;
	private SpriteSheetLvl1 ss;
	
	public Egg(int x, int y, ID id, Handler handler, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.id = id;
		this.ss = ss;
		this.handler = handler;
		egg_image = ss.grabImage(17, 2, 32, 32);
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Duy) {
				dx = tempObject.getX();
				dy = tempObject.getY();
			}
			velX = (dx - x) / 20;
			velY = (dy - y) / 20;
		}
	}
	
	public void crack() {
		handler.removeObject(this);
		Game.playSound("/effects/glass/" + Integer.toString((int) (Math.random() * 4 + 1)));
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Duy) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					crack();
					Game.hp -= 20;

				}
			}
			
			if(tempObject.getId() == ID.Berg) {
				if(getBoundsBig().intersects(tempObject.getBounds())) {
					crack();
					handler.addObject(new BabyPenguin(x, y, ID.BabyPenguin, handler, ss));
				}
			}
			
		}
	}

	public void render(Graphics g) {
		g.drawImage(egg_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle(x, y, 48, 48);
	}
}