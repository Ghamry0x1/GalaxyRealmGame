package m2.gh.mo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player2Bullet extends GameObject{

	private Handler handler;
	private boolean visible;
	
	public Player2Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		visible = true;
		
		velX = 0;
        velY = 5;
	}

	public void tick() {
		y += velY;
		
		if(visible == false) {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		Image laserBlue07 = new ImageIcon("res/laserBlue07.png").getImage();
    	g.drawImage(laserBlue07, (int)x, (int)y, null);
	}

	public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 9, 37);
    }
}
