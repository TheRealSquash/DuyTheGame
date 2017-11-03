import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EmperorPenguin extends GameObject{
	
	//private BufferedImage egg_image;
	
	private Handler handler;
	int HP = 200;
	int velocity = 2;
	private BufferedImage[] emperorPenguin_image = new BufferedImage[3];
	private BufferedImage[] emperorPenguin_image_back = new BufferedImage[3];
	private BufferedImage[] emperorPenguin_image_right = new BufferedImage[3];
	private BufferedImage[] emperorPenguin_image_left = new BufferedImage[3];
	Animation anim;
	Animation animBack;
	Animation animLeft;
	Animation animRight;
	
	public static boolean tester = false;
	private SpriteSheetLvl1 ss;
	
	public EmperorPenguin(int x, int y, ID id, Handler handler, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.ss = ss;
		
		emperorPenguin_image[0] = ss.grabImage(11, 1, 64, 64);
		emperorPenguin_image[1] = ss.grabImage(13, 1, 64, 64);
		emperorPenguin_image[2] = ss.grabImage(15, 1, 64, 64);
		emperorPenguin_image_back[0] = ss.grabImage(13, 3, 64, 64);
		emperorPenguin_image_back[1] = ss.grabImage(15, 3, 64, 64);
		emperorPenguin_image_back[2] = ss.grabImage(17, 3, 64, 64);
		emperorPenguin_image_right[0] = ss.grabImage(7, 5, 64, 64);
		emperorPenguin_image_right[1] = ss.grabImage(9, 5, 64, 64);
		emperorPenguin_image_right[2] = ss.grabImage(11, 5, 64, 64);
		emperorPenguin_image_left[0] = ss.grabImage(1, 5, 64, 64);
		emperorPenguin_image_left[1] = ss.grabImage(3, 5, 64, 64);
		emperorPenguin_image_left[2] = ss.grabImage(5, 5, 64, 64);
		
		anim = new Animation(3, emperorPenguin_image[0], emperorPenguin_image[1], emperorPenguin_image[2]);
		animBack = new Animation(3, emperorPenguin_image_back[0], emperorPenguin_image_back[1], emperorPenguin_image_back[2]);
		animRight = new Animation(3, emperorPenguin_image_right[0], emperorPenguin_image_right[1], emperorPenguin_image_right[2]);
		animLeft = new Animation(3, emperorPenguin_image_left[0], emperorPenguin_image_left[1], emperorPenguin_image_left[2]);
	}

	public void tick() {
		y += velY;
		x += velX;
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Duy) {
				if(Duy.battle == true) {
					
					if(velY > 0 && velX == 0) {
						anim.runAnimation();
					}else if(velY < 0 && velX == 0) {
						animBack.runAnimation();
					}else if(velX > 0) {
						animRight.runAnimation();
					}else if(velX < 0) {
						animLeft.runAnimation();
					}
					
					
					velX = (int) Math.signum(tempObject.getX() - x);
					velY = (int) Math.signum(tempObject.getY() - y);
					if((int) (Math.random() * 250) == 1) {
						Game.egg = true;
						handler.addObject(new Egg(x + 32, y + 32, ID.Egg, handler, ss));
						Game.egg = false;
					}
				}else {
					velY = 0;
					velX = 0;
				}
				if(getBounds().intersects(tempObject.getBounds())) {
					y += velY * -1;
					x += velX * -1;
					Game.hp--;
				}
			} 
			if(tempObject.getId() == ID.Berg) {
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
			if(tempObject.getId() == ID.GodBolt) {
				if(getBounds().intersects(tempObject.getBounds())) {
				HP -= 5;
				handler.removeObject(tempObject);
				tester = true;
				}
			}
		}
		
		
		
		
		
		if(HP <= 0) {
			 handler.removeObject(this);
			 System.exit(0);
		}
	}

	public void render(Graphics g) {
		if(velY > 0 && velX == 0) {
			anim.drawAnimation(g, x, y, 0);
		}else if(velY < 0 && velX == 0) {
			animBack.drawAnimation(g, x, y, 0);
		}else if(velX > 0) {
			animRight.drawAnimation(g, x, y, 0);
		}else if(velX < 0) {
			animLeft.drawAnimation(g, x, y, 0);
		}
			g.setColor(Color.gray);
			g.fillRect(x+7, y-32, 50, 16);
			g.setColor(Color.red);
			g.fillRect(x+7, y-32, HP/4, 16);
			g.setColor(Color.black);
			g.drawRect(x+7, y-32, 50, 16);
			g.setColor(Color.white);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}
	
	
}
