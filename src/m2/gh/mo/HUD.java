package m2.gh.mo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

public class HUD {
	private float greenValue = 255f;
	private float greenValue2 = 255f;
    private int score = 0;
    public static int score2 = 0;
    private int scoreCounter = 0;
    public static int level = 1;
    public static float HEALTH = 100;
    public static float HEALTH2 = 100;
    
    public void tick() {      
        HEALTH = (int) Game.clamp(HEALTH, 0, 100);
        greenValue = (int) Game.clamp(greenValue, 0, 255);
        greenValue = HEALTH *2;
        
        if(Game.multi == 0) {
        	
        	HEALTH2 = (int) Game.clamp(HEALTH2, 0, 100);
        	greenValue2 = (int) Game.clamp(greenValue2, 0, 255);
            greenValue2 = HEALTH2 *2;
        	
        	scoreCounter++;
            
            if(scoreCounter >= 70) {
            	scoreCounter = 0;
            	score++;
            	score2++;
            	if(score>=20) {
            		score = 0;
            		level++;
            	}
            }
        }
        
        else if(Game.multi == 1) {
        	HEALTH2 = (int) Game.clamp(HEALTH2, 0, 100);
        	greenValue2 = (int) Game.clamp(greenValue2, 0, 255);
            greenValue2 = HEALTH2 *2;
        }
    }
    
    public void render(Graphics g) {
    	
    	if(Game.multi == 0) {
    		g.setColor(Color.gray);
            g.fillRect(15, 15, 200, 20);
            
            g.setColor(new Color(75, (int) greenValue, 0));
            g.fillRect(15, 15, (int) (HEALTH * 2), 20);
            
            g.setColor(Color.white);
            g.drawRect(15, 15, 200, 20);
            
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,30);
                Font font2 = font.deriveFont(Font.PLAIN,20);

                g.setFont(font2);
                
                g.drawString("Player", 16, 60); 
                g.drawString("Enemy", Game.WIDTH - 90, 60); 
                
                g.drawString("Level: " + level, 17, 510); 
                g.drawString("Score: " + score2, 17, 490);
                
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            
            g.setColor(Color.gray);
            g.fillRect(530, 15, 200, 20);
            
            g.setColor(new Color(75, (int) greenValue2, 0));
            g.fillRect(530, 15, (int) (HEALTH2 * 2), 20);
            
            g.setColor(Color.white);
            g.drawRect(530, 15, 200, 20);
    	}
    	else if(Game.multi == 1) {
    		g.setColor(Color.gray);
            g.fillRect(15, 15, 200, 20);
            
            g.setColor(new Color(75, (int) greenValue, 0));
            g.fillRect(15, 15, (int) (HEALTH * 2), 20);
            
            g.setColor(Color.white);
            g.drawRect(15, 15, 200, 20);
            
            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,30);
                Font font2 = font.deriveFont(Font.PLAIN,18);
                
                g.setFont(font2);
                g.drawString("Player 1", 16, 60); 
                g.drawString("Player 2", Game.WIDTH - 112, 60); 
                
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            
            g.setColor(Color.gray);
            g.fillRect(530, 15, 200, 20);
            
            g.setColor(new Color(75, (int) greenValue2, 0));
            g.fillRect(530, 15, (int) (HEALTH2 * 2), 20);
            
            g.setColor(Color.white);
            g.drawRect(530, 15, 200, 20);
    	}
        
    }
    
    public void setScore(int score) {
        this.score=score;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getLevel() {
        return level;
    }
    
}
