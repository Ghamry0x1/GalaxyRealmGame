package m2.gh.mo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player2 extends GameObject{
	
	private Handler handler;
	
    public Player2(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 105);
        y = Game.clamp(y, 25, Game.HEIGHT - 105);
        
        collision();
    }

    public void collision() {
        for(int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if(Game.multi == 1) {
            	if(tempObject.getID() == ID.Player1) {
                    if(getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH2 -= 1;
                    }
                }
            	if(tempObject.getID() == ID.Player1Bullet) {
                    if(getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH2 -= 5;
                        handler.removeObject(tempObject);
                    }
                }
            }
                
        }
    }
    
    public void render(Graphics g) {
    	Image fire14 = new ImageIcon("res/fire14.png").getImage();
    	g.drawImage(fire14, (int)x + 25, (int)y - 18, null);
    	
    	Image fire14_2 = new ImageIcon("res/fire14.png").getImage();
    	g.drawImage(fire14_2, (int)x + 60, (int)y - 18, null);
    	
        Image playerShip2_blue = new ImageIcon("res/playerShip1_blue.png").getImage();
    	g.drawImage(playerShip2_blue, (int)x, (int)y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 99, 75);
    }
    
}
