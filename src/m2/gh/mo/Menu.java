package m2.gh.mo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import m2.gh.mo.Game.STATE;

public class Menu extends MouseAdapter{

    private Handler handler;
	
	public Menu(Handler handler) {
        this.handler = handler;
    }
	
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
        int my = e.getY();
        
        if(Game.gameState == Game.STATE.Menu) {
            //play
            if(mouseOver(mx, my, 267, 190, 222, 39)) {
                Game.gameState = STATE.Select;
                return;
            }

            //help
            if(mouseOver(mx, my, 267, 260, 222, 39)) {
                Game.gameState = Game.STATE.Help;
                return;
            }
            
            //credits
            if(mouseOver(mx, my, 267, 330, 222, 39)) {
                Game.gameState = Game.STATE.Credits;
                return;
            }

            //exit
            if(mouseOver(mx, my, 267, 400, 222, 39)) {
                System.exit(1);
            }
        }
        
            //back for help
            if(Game.gameState == Game.STATE.Help) {
                if(mouseOver(mx, my, 275, 460, 222, 39)) {
                    Game.gameState = Game.STATE.Menu;
                    return;
                }
            }
            
            //back for credits
            if(Game.gameState == Game.STATE.Credits) {
                if(mouseOver(mx, my, 267, 460, 222, 39)) {
                    Game.gameState = Game.STATE.Menu;
                    return;
                }
            }
            
          //play again
            if(Game.gameState == Game.STATE.End) {
                if(mouseOver(mx, my, 275, 460, 222, 39)) {
                	
                    Game.gameState = Game.STATE.Game;
                    HUD.HEALTH = 100;
                    HUD.HEALTH2 = 100;
                    HUD.level = 1;
                    HUD.score2 = 0;
                    
                    if(Game.multi == 1) {
	                    handler.addObject(new Player1(Game.WIDTH/2 - 50, Game.HEIGHT - 130, ID.Player1, handler));
	                	handler.addObject(new Player2(Game.WIDTH/2 - 50, 50, ID.Player2, handler));
	                }
                    else if(Game.multi == 0) {
                    	handler.addObject(new Player1(Game.WIDTH/2 - 50, Game.HEIGHT - 130, ID.Player1, handler));
                    	handler.addObject(new Enemy1(Game.WIDTH/2 - 50, 40, ID.Enemy1, handler));
                    }
                }
            }
            
            if(Game.gameState == STATE.Select) {
            	if(mouseOver(mx, my, 267, 200, 222, 39)) {
            		Game.gameState = Game.STATE.Game;
                    
                    Game.multi = 0;
                    try{
                    	Game.clip.close();
                    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/intro.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.loop(10);
                        clip.start();
                        }
                        catch(Exception ex){
                            System.err.println("no sound");
                        }
                    handler.addObject(new Player1(Game.WIDTH/2 - 50, Game.HEIGHT - 130, ID.Player1, handler));
                    handler.addObject(new Enemy1(Game.WIDTH/2 - 50, 40, ID.Enemy1, handler));
                    
                    return;
            	}
            	else if(mouseOver(mx, my, 267, 290, 222, 39)) {
            		Game.gameState = Game.STATE.Game;

                    Game.multi = 1;
                    try{
                    	Game.clip.close();
                    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/multi.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.loop(10);
                        clip.start();
                        }
                        catch(Exception ex){
                            System.err.println("no sound");
                        }
                    handler.addObject(new Player1(Game.WIDTH/2 - 50, Game.HEIGHT - 130, ID.Player1, handler));
                    handler.addObject(new Player2(Game.WIDTH/2 - 50, 50, ID.Player2, handler));
                    
                    return;
            	}
            	else if(mouseOver(mx, my, 267, 460, 222, 39)) {
            		Game.gameState = STATE.Menu;
            	}
            }
        
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            if(my > y && my < y + height)
                return true;
            return false;
        }
        return false;
    }
	
	public void tick() {
		
	}
	
	private static float t = -1280;
	
	public void render(Graphics g) {
		Image black = new ImageIcon("res/black.png").getImage();
    	g.drawImage(black, 0, (int)t, null);
    	t+=1;
    	if(t>=0)
    		t=-1280;
    	
    	if(Game.gameState == STATE.Menu) {
    		
    		try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,60);
                Font font2 = font.deriveFont(Font.PLAIN,30);
                
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString("MENU", 300, 105);
                g.setColor(Color.WHITE);
                g.drawString("MENU", 295, 100);
                
                g.setColor(Color.BLACK);
                g.setFont(font2);
            	
                Image buttonRed = new ImageIcon("res/buttonRed.png").getImage();
                g.drawImage(buttonRed, 267, 190, null);
                g.setColor(Color.RED);
                g.drawString("PLAY", 336, 221);
                g.setColor(Color.BLACK);
                g.drawString("PLAY", 335, 220);
                
                Image buttonBlue = new ImageIcon("res/buttonBlue.png").getImage();
                g.drawImage(buttonBlue, 267, 260, null);
                g.setColor(Color.BLUE);
                g.drawString("HELP", 341, 291);
                g.setColor(Color.BLACK);
                g.drawString("HELP", 340, 290);
                
                Image buttonGreen = new ImageIcon("res/buttonGreen.png").getImage();
                g.drawImage(buttonGreen, 267, 330, null);
                g.setColor(Color.GREEN);
                g.drawString("CREDITS", 316, 361);
                g.setColor(Color.BLACK);
                g.drawString("CREDITS", 315, 360);
                
                Image buttonYellow = new ImageIcon("res/buttonYellow.png").getImage();
                g.drawImage(buttonYellow, 267, 400, null);
                g.setColor(Color.YELLOW);
                g.drawString("QUIT", 346, 431);
                g.setColor(Color.BLACK);
                g.drawString("QUIT", 345, 430);
                
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
    		
    	}
    	
    	else if(Game.gameState == STATE.Help) {
    		try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,50);
                Font font2 = font.deriveFont(Font.PLAIN,35);
                Font font3 = font.deriveFont(Font.PLAIN,30);
                Font font4 = font.deriveFont(Font.PLAIN,25);
                
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString("HELP", 310, 65);
                g.setColor(Color.WHITE);
                g.drawString("HELP", 305, 60);
                
                g.setFont(font2);
                g.setColor(Color.BLACK);
                g.drawString("CONTROLS", 60, 155);
                g.setColor(Color.WHITE);
                g.drawString("CONTROLS", 55, 150);
                
                g.setFont(font3);
                g.setColor(Color.BLACK);
                g.drawString("PLAYER 1", 313, 203);
                g.setColor(Color.WHITE);
                g.drawString("PLAYER 1", 310, 200);
                
                g.setColor(Color.BLACK);
                g.drawString("PLAYER 2", 553, 203);
                g.setColor(Color.WHITE);
                g.drawString("PLAYER 2", 550, 200);
                
                g.setColor(Color.BLACK);
                g.drawString("Move", 110, 253);
                g.setColor(Color.WHITE);
                g.drawString("Move", 107, 250);
                
                
                g.setColor(Color.BLACK);
                g.drawString("FIRE", 117, 393);
                g.setColor(Color.WHITE);
                g.drawString("FIRE", 114, 390);
                
                g.setFont(font4);
                g.drawString("W", 370, 253);
                g.drawString("A", 370, 283);
                g.drawString("S", 370, 313);
                g.drawString("D", 370, 343);
                
                g.drawString("UP", 610, 253);
                g.drawString("LEFT", 593, 283);
                g.drawString("DOWN", 590, 313);
                g.drawString("RIGHT", 590, 343);
                
                g.drawString("SPACE", 335, 390);
                g.drawString("ENTER", 585, 390);
                
                Image buttonBlue = new ImageIcon("res/buttonBlue.png").getImage();
                g.drawImage(buttonBlue, 275, 460, null);
                g.setFont(font2);
                g.setColor(Color.BLUE);
                g.drawString("BACK", 341, 490);
                g.setColor(Color.BLACK);
                g.drawString("BACK", 340, 490);
                
                
    		} catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
    	}
    	
    	else if(Game.gameState == STATE.Credits) {
    		try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,50);
                Font font2 = font.deriveFont(Font.PLAIN,35);
                Font font3 = font.deriveFont(Font.PLAIN,30);
                Font font4 = font.deriveFont(Font.PLAIN,40);
                
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString("CREDITS", 275, 65);
                g.setColor(Color.WHITE);
                g.drawString("CREDITS", 270, 60);
                
                g.setFont(font4);
                g.setColor(Color.BLACK);
                g.drawString("So-CESS  /   CSE126", 170, 155);
                g.setColor(Color.WHITE);
                g.drawString("So-CESS  /   CSE126", 165, 150);
                
                g.setFont(font2);
                g.setColor(Color.BLACK);
                g.drawString("MOHAMED ELGHAMRY", 185, 245);
                g.setColor(Color.WHITE);
                g.drawString("MOHAMED ELGHAMRY", 180, 240);
                
                g.setColor(Color.BLACK);
                g.drawString("MOSTAFA HAZEM", 235, 355);
                g.setColor(Color.WHITE);
                g.drawString("MOSTAFA HAZEM", 230, 350);
                
                g.setFont(font3);
                g.setColor(Color.BLACK);
                g.drawString("15P6019", 314, 289);
                g.setColor(Color.WHITE);
                g.drawString("15P6019", 310, 285);
                
                g.setColor(Color.BLACK);
                g.drawString("15P6017", 314, 394);
                g.setColor(Color.WHITE);
                g.drawString("15P6017", 310, 390);
                
                Image buttonGreen = new ImageIcon("res/buttonGreen.png").getImage();
                g.drawImage(buttonGreen, 267, 460, null);
                g.setFont(font2);
                g.setColor(Color.GREEN);
                g.drawString("BACK", 331, 491);
                g.setColor(Color.BLACK);
                g.drawString("BACK", 330, 490);
                
    		} catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
    	}
    	
    	else if(Game.gameState == STATE.End) {
    		try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,50);
                Font font2 = font.deriveFont(Font.PLAIN,40);
                Font font3 = font.deriveFont(Font.PLAIN,30);
                
                if(Game.multi == 0) {
                	g.setFont(font);
                    g.setColor(Color.BLACK);
                    g.drawString("GAMEOVER", 245, 65);
                    g.setColor(Color.WHITE);
                    g.drawString("GAMEOVER", 240, 60);
                    
                    g.setFont(font2);
                    
                    if(HUD.HEALTH2 <= 0) {
                    	g.setColor(Color.BLACK);
                        g.drawString("YOU WIN!", 306, 276);
                        g.setColor(Color.RED);
                        g.drawString("YOU WIN!", 303, 273);
                        g.setColor(Color.BLUE);
                        g.drawString("YOU WIN!", 300, 272);
                        g.setColor(Color.WHITE);
                        g.drawString("YOU WIN!", 300, 270);
                    }
                    
                    else if(HUD.HEALTH <= 0) {
                    	g.setColor(Color.BLACK);
                        g.drawString("YOU LOSE", 286, 276);
                        g.setColor(Color.RED);
                        g.drawString("YOU LOSE", 283, 273);
                        g.setColor(Color.BLUE);
                        g.drawString("YOU LOSE", 280, 272);
                        g.setColor(Color.WHITE);
                        g.drawString("YOU LOSE", 280, 270);
                    }
                }
                
                else if(Game.multi == 1) {
                	g.setFont(font);
                    g.setColor(Color.BLACK);
                    g.drawString("GAMEOVER", 245, 65);
                    g.setColor(Color.WHITE);
                    g.drawString("GAMEOVER", 240, 60);
                    
                    g.setFont(font2);
                    
                    if(HUD.HEALTH2 <= 0) {
                    	g.setColor(Color.BLACK);
                        g.drawString("PLAYER 1 WINS", 236, 266);
                        g.setColor(Color.RED);
                        g.drawString("PLAYER 1 WINS", 233, 263);
                        g.setColor(Color.BLUE);
                        g.drawString("PLAYER 1 WINS", 230, 262);
                        g.setColor(Color.WHITE);
                        g.drawString("PLAYER 1 WINS", 230, 260);
                    }
                    
                    else if(HUD.HEALTH <= 0) {
                    	g.setColor(Color.BLACK);
                        g.drawString("PLAYER 2 WINS", 236, 266);
                        g.setColor(Color.RED);
                        g.drawString("PLAYER 2 WINS", 233, 263);
                        g.setColor(Color.BLUE);
                        g.drawString("PLAYER 2 WINS", 230, 262);
                        g.setColor(Color.WHITE);
                        g.drawString("PLAYER 2 WINS", 230, 260);
                    }
                }
                
                Image buttonRed = new ImageIcon("res/buttonRed.png").getImage();
                g.drawImage(buttonRed, 275, 460, null);
                g.setFont(font3);
                g.setColor(Color.RED);
                g.drawString("PLAY AGAIN", 299, 490);
                g.setColor(Color.BLACK);
                g.drawString("PLAY AGAIN", 298, 490);
                
    		} catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
    	}
    	
    	else if(Game.gameState == STATE.Select) {
    		try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/kenvector_future_thin.ttf"));
                font = font.deriveFont(Font.PLAIN,50);
                Font font3 = font.deriveFont(Font.PLAIN,25);
                
                g.setFont(font);
                g.setColor(Color.BLACK);
                g.drawString("SELECT MODE", 215, 65);
                g.setColor(Color.WHITE);
                g.drawString("SELECT MODE", 210, 60);
                
                g.setFont(font3);
                
                Image buttonBlue = new ImageIcon("res/buttonBlue.png").getImage();
                g.drawImage(buttonBlue, 267, 200, null);
                g.setColor(Color.BLUE);
                g.drawString("SINGLE PLAYER", 276, 229);
                g.setColor(Color.BLACK);
                g.drawString("SINGLE PLAYER", 275, 228);
                
                Image buttonGreen = new ImageIcon("res/buttonGreen.png").getImage();
                g.drawImage(buttonGreen, 267, 290, null);
                g.setColor(Color.GREEN);
                g.drawString("MULTIPLAYER", 288, 319);
                g.setColor(Color.BLACK);
                g.drawString("MULTIPLAYER", 287, 318);
                
                Image buttonRed = new ImageIcon("res/buttonRed.png").getImage();
                g.drawImage(buttonRed, 267, 460, null);
                g.setFont(font3);
                g.setColor(Color.RED);
                g.drawString("BACK", 341, 490);
                g.setColor(Color.BLACK);
                g.drawString("BACK", 340, 490);
                
                
    		} catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
    	}
    	
	}
}