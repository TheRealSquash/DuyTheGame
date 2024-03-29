import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BabyPenguin extends GameObject{

	private Handler handler;
	Random r = new Random();
	int choose = 0;
	int hp = 100;
	private BufferedImage babyPenguin_image;
	private SpriteSheetLvl1 ss;
	
	public BabyPenguin(int x, int y, ID id, Handler handler, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		Game.babyPenguinCount ++;
		this.handler = handler;
		this.ss = ss;
		babyPenguin_image = ss.grabImage(18, 1, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		choose = r.nextInt(10);
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Berg){
				if(getBoundsBig().intersects(tempObject.getBounds())){
					x += (velX*5) * -1;
					y += (velY*5) * -1;
					velX *= -1;
					velY *= -1;
				}else if(choose == 0){
					velX =(r.nextInt(4 - -4) + -4);
					velY =(r.nextInt(4 - -4) + -4);
				}
			}
			
			if(tempObject.getId() == ID.GodBolt){
				if(getBounds().intersects(tempObject.getBounds())){
					hp -= 50;
					handler.removeObject(tempObject);
				}
				
			}
		}
		
		
		if(hp <= 0) {
			handler.removeObject(this);
			Game.babyPenguinCount --;
			if((int) (Math.random() * 2) == 1) {
				handler.addObjectLast(new Blood(x, y, ID.Blood, handler, ss));
			}else {
				handler.addObjectLast(new DivineBlood(x, y, ID.DivineBlood, ss));
			}
		}
		
	}

	public void render(Graphics g) {
		g.drawImage(babyPenguin_image, x, y, null);
		if(hp < 100) {
			g.setColor(Color.gray);
			g.fillRect(x+3, y-16, 25, 8);
			g.setColor(Color.red);
			g.fillRect(x+3, y-16, hp/4, 8);
			g.setColor(Color.black);
			g.drawRect(x+3, y-16, 25, 8);
			g.setColor(Color.white);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	public Rectangle getBoundsBig() {
		return new Rectangle(x-16, y-16, 64, 64);
	}

}
