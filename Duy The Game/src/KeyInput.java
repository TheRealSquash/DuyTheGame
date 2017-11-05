import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Duy){
				if(key == KeyEvent.VK_W) {
					if(!handler.isLeft() && !handler.isRight()) {
						Duy.currentAnim = Duy.animBack;
					}
					handler.setUp(true);
				}
				if(key == KeyEvent.VK_S) {
					if(!handler.isLeft() && !handler.isRight()) {
						Duy.currentAnim = Duy.anim;
					}
					handler.setDown(true);
				}
				if(key == KeyEvent.VK_A) {
					Duy.currentAnim = Duy.animLeft;
					handler.setLeft(true);
				}
				if(key == KeyEvent.VK_D) {
					Duy.currentAnim = Duy.animRight;
					handler.setRight(true);
				}
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Duy){
				if(key == KeyEvent.VK_W) {
					handler.setUp(false);
				}
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) {
					if(handler.isUp()) {
						Duy.currentAnim = Duy.animBack;
					}
					else if(handler.isDown()){
						Duy.currentAnim = Duy.anim;
					}
					handler.setLeft(false);
				}
				if(key == KeyEvent.VK_D) {
					if(handler.isUp()) {
						Duy.currentAnim = Duy.animBack;
					}
					else if(handler.isDown()){
						Duy.currentAnim = Duy.anim;
					}
					
					handler.setRight(false);
				}
			}
		}		
		
		
	}
}
