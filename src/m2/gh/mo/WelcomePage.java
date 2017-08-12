package m2.gh.mo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import m2.gh.mo.Game.STATE;

public class WelcomePage {
	
	private int timer = 1500;
	private int timer2 = 3450;
	private int timer3 = 2000;
	
	public void tick() {
		
	}
	
    public void render(Graphics g) {
    	
    	if(timer2>0) {
    		g.setColor(Color.BLACK);
        	g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        	g.setColor(Color.WHITE);
        	
        	try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,30);
                Font font2 = font.deriveFont(Font.PLAIN,20);
                
                g.setFont(font);
                g.drawString("G A M E   L O A D I N G . . .", 195, 250); 
                
                g.setFont(font2);
                g.drawString("Use earphones for the best experience.", 150, 480);
                
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        	
        	timer2--;
    	}
    	
    	if(timer>=0 && timer2 == 0) {
    		g.setColor(Color.BLACK);
        	g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        	
    		Image logo = new ImageIcon("res/logo.png").getImage();
        	g.drawImage(logo, Game.WIDTH/2 - 155, Game.HEIGHT/5, null);
        	timer--;
    	}
    	
    	if(timer<=0 && timer2 == 0 && timer3 != 0){
    		g.setColor(Color.BLACK);
        	g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
    		Image gameLogo = new ImageIcon("res/gameLogo.png").getImage();
        	g.drawImage(gameLogo, 0, 0, null);
        	timer3--;
    	}
    	
    	if(timer <= 0 && timer2 <= 0 && timer3 <= 0){
    		Game.gameState = STATE.Menu;
    	}
    }
}