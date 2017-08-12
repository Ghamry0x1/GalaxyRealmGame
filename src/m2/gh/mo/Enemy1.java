package m2.gh.mo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy1 extends GameObject{

	private Handler handler;
	private int timer = 30;
    private int timer2 = 50;
    private Random r = new Random();
	
	public Enemy1(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
        velY = 2;
	}

	public void tick() {
		x += velX;
        y += velY;
        

        if(timer<=0) {
            velY=0;
        }
        else timer--;
        
        if(timer <= 0) {
            timer2--;
        }
        if(timer2<=0) {
            if(velX == 0)
                velX = 2;

            if(velX > 0)
                velX+= 0.005f;
            else if (velX < 0)
                velX-= 0.005f;
            
            velX = Game.clamp(velX, -10, 10);
            int spawn = r.nextInt(10);
            if(spawn == 0)
                handler.addObject(new Enemy1Bullet((int)x, (int)y, ID.Enemy1Bullet, handler));
        }
        if(x <= 0 || x>= Game.WIDTH - 100)
            velX*= -1;
        
        collision();
	}
	
	public void collision() {
        for(int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if(Game.multi == 0) {
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
		
		Image enemyBlack1 = new ImageIcon("res/enemyBlack1.png").getImage();
    	g.drawImage(enemyBlack1, (int)super.x, (int)super.y, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 93, 84);
	}
	
}
