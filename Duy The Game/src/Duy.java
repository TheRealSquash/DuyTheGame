import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Duy extends GameObject {

	Handler handler;
	Game game;
	private BufferedImage[] duy_image = new BufferedImage[3];
	private BufferedImage[] duy_image_back = new BufferedImage[3];
	private BufferedImage[] duy_image_right = new BufferedImage[3];
	private BufferedImage[] duy_image_left = new BufferedImage[3];
	
	public static Animation currentAnim = null;
	public static Animation anim;
	public static Animation animBack;
	public static Animation animRight;
	public static Animation animLeft;
	
	public static boolean battle = false;
	
	public Duy(int x, int y, ID id, Handler handler, Game game, SpriteSheetLvl1 ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		
		duy_image[0] = ss.grabImage(1, 1, 32, 48);
		duy_image[1] = ss.grabImage(2, 1, 32, 48);
		duy_image[2] = ss.grabImage(3, 1, 32, 48);
		duy_image_back[0] = ss.grabImage(7, 3, 32, 48);
		duy_image_back[1] = ss.grabImage(8, 3, 32, 48);
		duy_image_back[2] = ss.grabImage(9, 3, 32, 48);
		duy_image_right[0] = ss.grabImage(1, 3, 32, 48);
		duy_image_right[1] = ss.grabImage(2, 3, 32, 48);
		duy_image_right[2] = ss.grabImage(3, 3, 32, 48);
		duy_image_left[0] = ss.grabImage(4, 3, 32, 48);
		duy_image_left[1] = ss.grabImage(5, 3, 32, 48);
		duy_image_left[2] = ss.grabImage(6, 3, 32, 48);
		
		anim = new Animation(3, duy_image[0], duy_image[1], duy_image[2]);
		animBack = new Animation(3, duy_image_back[0], duy_image_back[1], duy_image_back[2]);
		animRight = new Animation(3, duy_image_right[0], duy_image_right[1], duy_image_right[2]);
		animLeft = new Animation(3, duy_image_left[0], duy_image_left[1], duy_image_left[2]);
	}

	public void tick() {
		
		x += velX;
		y += velY;
		
		game.duyX = x;
		game.duyY = y;
		
		collision();
		
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = 5;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight()) velX = 5;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX = -5;
		else if(!handler.isRight()) velX = 0;
		
		if(currentAnim != null) {
			currentAnim.runAnimation();
		}
		
		
		if(game.startButton) {
			game.hp = 100;
			game.divinity = 100;
		}
		
		if(game.divinity > 100) {
			game.divinity = 100;
		}
	}

	private void collision() {
		for(int i = 0; i < handler.objectLast.size(); i++) {
			GameObject tempObject = handler.objectLast.get(i);
			if(tempObject.getId() == ID.DivineBlood) {
				if(getBounds().intersects(tempObject.getBounds())){
					game.divinity += 20;
					handler.removeObjectLast(tempObject);
					Game.playSound("/effects/divine/" + Integer.toString((int) (Math.random() * 6 + 1)), 0);
				}
			}
		}
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Berg) {
				if(getBounds().intersects(tempObject.getBounds())){
					x += velX * -1;
					y += velY * -1;
				}
				
			}
			
			if(tempObject.getId() == ID.BabyPenguin) {
				if(getBounds().intersects(tempObject.getBounds())) {
					game.hp--;
					x += velX * -1;
					y += velY * -1;	
				}
			}
			
			if(tempObject.getId() == ID.DivineJuice) {
				if(getBounds().intersects(tempObject.getBounds())){
					//juice fully restores divinity
					game.divinity = 100;
					handler.removeObject(tempObject);
					Game.playSound("/effects/divine/" + Integer.toString((int) (Math.random() * 6 + 1)), 0);
				}
			}
			
			if(tempObject.getId() == ID.PenguinA) {
				if(getBoundsSmall().intersects(tempObject.getBounds())){
					//subtract 50 health
					if(!Game.penguinDead) {
						game.hp--;
					}
					
					x += velX * -1;
					y += velY * -1;
				}	
			}
			
			if(tempObject.getId() == ID.Water) {
				if(getBounds().intersects(tempObject.getBounds())){
					//subtract 50 health
					game.hp = 0;
				}	
			}
			
			if(tempObject.getId() == ID.Advance) {
				if(getBounds().intersects(tempObject.getBounds())){
					//subtract 50 health
					game.complete = true;
				}	
			}
			
			if(tempObject.getId() == ID.Coin) {
				if(getBounds().intersects(tempObject.getBounds())){
					//collect coin
					Game.coin++;
					game.coinTaken = true;
					handler.removeObject(tempObject);
					Game.playSound("/effects/coin", 0);
				}
			}
			
			if(tempObject.getId() == ID.Key) {
				if(getBounds().intersects(tempObject.getBounds())){
					//collect key
					handler.removeObject(tempObject);
					Game.playSound("effects/doorOpen");
					Game.focus = "door";
				}
			}
			
			if(tempObject.getId() == ID.Door) {
				if(getBounds().intersects(tempObject.getBounds())){
					x += velX * -1;
					y += velY * -1;
				}
			}
			
			if(tempObject.getId() == ID.BossBlock) {
				if(getBounds().intersects(tempObject.getBounds())){
					if(!battle) {
						Game.background.stop();
						Game.background = Game.playSound("emperorTheme", 6);
						Game.playSound("/effects/emperor_trumpet");
					}
					battle = true;
					
				}
			}
			
			if(tempObject.getId() == ID.EmperorPenguin) {
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}	
			}
			
			if(tempObject.getId() == ID.Bandaid) {
				if(getBounds().intersects(tempObject.getBounds())){
					//heals 10 health
					game.hp += 10;
					handler.removeObject(tempObject);
				}
			}
			

			
			if(game.hp > 100) {
				game.hp = 100;
			}
		}
	}
	public void render(Graphics g) {
		if(game.startButton) {
			g.setColor(Color.gray);
			g.fillRect(x - 34, y - 18, 100, 16);
			g.setColor(Color.black);
			g.drawRect(x - 34, y - 18, 100, 16);
			g.setColor(Color.white);
			g.drawString("START", x - 4, y - 5);
			g.drawString("START", x - 4, y - 5);
		}
		if(velX == 0 && velY == 0) {
			g.drawImage(duy_image[0], x, y, null);
		}
		else {
			currentAnim.drawAnimation(g, x, y, 0);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 48);
	}
	
	public Rectangle getBoundsSmall() {
		return new Rectangle(x + 4, y + 8, 24, 32);
	}
}
