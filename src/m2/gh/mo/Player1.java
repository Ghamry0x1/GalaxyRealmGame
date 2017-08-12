package m2.gh.mo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player1 extends GameObject{
	
	private Handler handler;
	
    public Player1(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 105);
        y = Game.clamp(y, 1, Game.HEIGHT - 120);
        
        collision();
    }

    public void collision() {
        for(int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if(Game.multi == 0) {
            	if(tempObject.getID() == ID.Enemy1) {
                    if(getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH -= 5;
                    }
                }
            	if(tempObject.getID() == ID.Enemy1Bullet) {
                    if(getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH -= 5;
        			    handler.removeObject(tempObject);
                    }
                }
            }
            
            else if(Game.multi == 1) {
            	if(tempObject.getID() == ID.Player2) {
                    if(getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH -= 1;
                    }
                }
            	if(tempObject.getID() == ID.Player2Bullet) {
                    if(getBounds().intersects(tempObject.getBounds())) {
                        HUD.HEALTH -= 5;
        			    handler.removeObject(tempObject);
                    }
                }
            }
                
        }
    }
    private static float j = -5120;
    public void render(Graphics g) {
    	Image background = new ImageIcon("res/newbackground.png").getImage();
    	g.drawImage(background, 0, (int)j, null);
    	j+=0.7;
    	if(j>=0)
    		j=-5120;
    	
    	Image fire12 = new ImageIcon("res/fire12.png").getImage();
    	g.drawImage(fire12, (int)x + 25, (int)y + 55, null);
    	
    	Image fire12_2 = new ImageIcon("res/fire12.png").getImage();
    	g.drawImage(fire12_2, (int)x + 60, (int)y + 55, null);
    	
        Image playerShip1_orange = new ImageIcon("res/playerShip1_orange.png").getImage();
    	g.drawImage(playerShip1_orange, (int)x, (int)y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 99, 75);
    }
}
