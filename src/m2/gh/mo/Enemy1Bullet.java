package m2.gh.mo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1Bullet extends GameObject{

	private Handler handler;
	private boolean visible;
	
	public Enemy1Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		visible = true;
		
		velX = 0;
        velY = 5;
	}

	public void tick() {
		y += velY;
		
		if(visible == false)
			handler.removeObject(this);
	}

	public void render(Graphics g) {
		Image laserGreen13 = new ImageIcon("res/laserGreen13.png").getImage();
    	g.drawImage(laserGreen13, (int)x+46, (int)y+80, null);
	}

	public Rectangle getBounds() {
        return new Rectangle((int)x+46, (int)y+80, 9, 37);
    }
}