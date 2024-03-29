import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private Camera camera;
	private Game game;
	private SpriteSheetLvl1 ss;
	
	public MouseInput(Handler handler, Camera camera, Game game, SpriteSheetLvl1 ss) {
		this.handler = handler;
		this.camera = camera;
		this.game = game;
		this.ss = ss;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
	

		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			
			
			if(tempObject.getId() == ID.Duy && game.divinity >= 1) {
				 handler.addObject(new GodBolt(tempObject.getX() + 16, tempObject.getY()+24, ID.GodBolt, handler, mx, my, ss));
				 Game.playSound("/effects/godBolt/" + Integer.toString((int) (Math.random() * 5 + 1)));
				 game.divinity -= 2;
			}
		}
		if(game.startButton) {
			game.mouseX = mx;
			game.mouseY = my;
			if(mx > game.duyX - 34 && mx < game.duyX + 66 && my > game.duyY - 18 && my < game.duyY - 2) {
				game.startButton = false;
				Game.background = Game.playSound("Background", -30);
				game.loadLevelOne();
				game.divinity = 100;
			}
		}
	}
}
