package m2.gh.mo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import m2.gh.mo.Game.STATE;

public class KeyInput extends KeyAdapter implements KeyListener{
	private Handler handler;
    private boolean[] keyDown = new boolean[8];
    
    public KeyInput(Handler handler) {
        this.handler = handler;
        
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
        
        keyDown[4] = false;
        keyDown[5] = false;
        keyDown[6] = false;
        keyDown[7] = false;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        //System.out.println(key);
        
        for(int i=0; i<handler.object.size(); i++) {
            
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getID() == ID.Player1) {
                if(key == KeyEvent.VK_W) {
                    tempObject.setVelY(-5);
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_S) {
                    tempObject.setVelY(5);
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                    keyDown[3] = true;
                }
                if(key == KeyEvent.VK_SPACE) {
                	handler.addObject(new Player1Bullet(tempObject.x + 45, tempObject.y - 37, ID.Player1Bullet, handler));
                }
            }
            
            if(tempObject.getID() == ID.Player2) {
                if(key == KeyEvent.VK_UP) {
                    tempObject.setVelY(-5);
                    keyDown[4] = true;
                }
                if(key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                    keyDown[5] = true;
                }
                if(key == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(5);
                    keyDown[6] = true;
                }
                if(key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                    keyDown[7] = true;
                }
                if(key == KeyEvent.VK_ENTER) {
                	handler.addObject(new Player2Bullet(tempObject.x + 45, tempObject.y + 75, ID.Player2Bullet, handler));
                }
            }
            
            if(key == KeyEvent.VK_M)
            	Game.gameState = STATE.Menu;
            
            if(key == KeyEvent.VK_ESCAPE)
                System.exit(1);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        //System.out.println(key);
        
        for(int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getID() == ID.Player1) {
                if(key == KeyEvent.VK_W) {
                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_A) {
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_S) {
                    keyDown[2] = false;
                }
                if(key == KeyEvent.VK_D) {
                    keyDown[3] = false;
                }
                //vertical movement
                if(!keyDown[0] && !keyDown[2]) {
                    tempObject.setVelY(0);
                }
                //horizontal movement
                if(!keyDown[1] && !keyDown[3]) {
                    tempObject.setVelX(0);
                }
                
            }
            if(tempObject.getID() == ID.Player2) {
                if(key == KeyEvent.VK_UP) {
                    keyDown[4] = false;
                }
                if(key == KeyEvent.VK_LEFT) {
                    keyDown[5] = false;
                }
                if(key == KeyEvent.VK_DOWN) {
                    keyDown[6] = false;
                }
                if(key == KeyEvent.VK_RIGHT) {
                    keyDown[7] = false;
                }
                //vertical movement
                if(!keyDown[4] && !keyDown[6]) {
                    tempObject.setVelY(0);
                }
                //horizontal movement
                if(!keyDown[5] && !keyDown[7]) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}
