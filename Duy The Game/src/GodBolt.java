import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GodBolt extends GameObject{
	private Handler handler;
	
	public GodBolt(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.id = id;
		this.handler = handler;
		velX = (mx - x) / 10;
		velY = (my - y) / 10;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Berg) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 10, 10);
		g.setColor(Color.magenta);
		g.fillOval(x+1, y+1, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}
}
