import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PenguinA extends GameObject{
	
	private Handler handler;
	int HP = 100;
	int velocity = 4;
	private BufferedImage[] penguinA_image = new BufferedImage[3];
	private BufferedImage[] penguinA_image_back = new BufferedImage[3];
	Animation anim;
	Animation animBack;
	private SpriteSheetLvl1 ss;
	private BufferedImage deadPenguin;
	public boolean isDead = false;
	
	public PenguinA(int x, int y, ID id, Handler handler, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.ss = ss;
		
		penguinA_image[0] = ss.grabImage(4, 1, 32, 32);
		penguinA_image[1] = ss.grabImage(5, 1, 32, 32);
		penguinA_image[2] = ss.grabImage(6, 1, 32, 32);
		penguinA_image_back[0] = ss.grabImage(10, 4, 32, 32);
		penguinA_image_back[1] = ss.grabImage(11, 4, 32, 32);
		penguinA_image_back[2] = ss.grabImage(12, 4, 32, 32);
		
		
		deadPenguin = ss.grabImage(19, 1, 32, 32);
		
		
		anim = new Animation(3, penguinA_image[0], penguinA_image[1], penguinA_image[2]);
		animBack = new Animation(3, penguinA_image_back[0], penguinA_image_back[1], penguinA_image_back[2]);
	}

	public void getPunched() {
		Game.playSound("/effects/punches/" + (int) (Math.random() * 8 + 1));
		HP -= 4;
	}
	
	public void tick() {
		y += velY;
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
	
			if(tempObject.getId() == ID.Berg) {
				if(getBounds().intersects(tempObject.getBounds())) {
					velocity *= -1;
					velY = velocity;
					velY *= -1;
				}
			} else if(tempObject.getId() != ID.Berg) {
				velY = velocity;
			}
			
			if(tempObject.getId() == ID.GodBolt) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HP -= 20;
					handler.removeObject(tempObject);
					Game.playSound("/effects/penguin/hit/" + Integer.toString((int) (Math.random() * 3 + 1)));
				}
			}
			
			if(tempObject.getId() == ID.Duy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					y += velY * -1;
					if(!Game.penguinDead) {
						Game.hp--;
					}
					
					getPunched();
				}
			}
		}
		if(velY > 0) {
			anim.runAnimation();
		}
		if(velY < 0) {
			animBack.runAnimation();
		}
		
		if(HP <= 0) {
			Game.penguinDead = true;
			if(y % 16 == 0) {
				handler.addObjectLast(new Blood(x, y, ID.Blood, handler, ss));
			}
			
			if(!Game.penguinSoundStarted) {
				Game.playSound("effects/penguin/dying");
				Game.penguinSoundStarted = true;
			}
			if(Game.penguinDeathSound) {
				velY = 0;
			}
			if(!Game.penguinDeathSound) {
				handler.removeObject(this);
				handler.addObjectLast(new Blood(x, y, ID.Blood, handler, ss));
				if((int) (Math.random() * 5) == 1) {
					handler.addObject(new Bandaid(x, y, ID.Bandaid, ss));
				}
				Game.penguinDeathSound = true;
				Game.penguinSoundStarted = false;
				Game.penguinDead = false;
			}

		}
	}

	public void render(Graphics g) {
			if(velY > 0) {
				anim.drawAnimation(g, x, y, 0);
			}
			if(velY < 0) {
				animBack.drawAnimation(g, x, y, 0);
			}else if(velY == 0) {
				g.drawImage(deadPenguin, x, y, null);
			}
			if(HP < 100 && HP > 0) {
				g.setColor(Color.gray);
				g.fillRect(x+3, y-16, 25, 8);
				g.setColor(Color.red);
				g.fillRect(x+3, y-16, HP/4, 8);
				g.setColor(Color.black);
				g.drawRect(x+3, y-16, 25, 8);
				g.setColor(Color.white);
			}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}
