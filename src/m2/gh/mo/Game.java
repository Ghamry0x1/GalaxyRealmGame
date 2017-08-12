package m2.gh.mo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -4509004282829072065L;
	
	public static final int WIDTH = 750, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
    private boolean running = false;
    
    private Handler handler;
    private HUD hud;
    private WelcomePage welcome;
    private Menu menu;
    
    public static Clip clip;
    
    public static int multi = 0;
    
    public enum STATE {Welcome, Menu, Select, Help, Credits, Game, End};
    public static STATE gameState = STATE.Welcome;
    
	public Game() {
		handler = new Handler();
        hud = new HUD();
        menu = new Menu(handler);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
        
        new Window(WIDTH, HEIGHT, "Galaxy Realm", this);
        
        if(gameState == STATE.Welcome) {
	        welcome = new WelcomePage();
	        try{
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/AMemoryAway.wav").getAbsoluteFile());
                Game.clip = AudioSystem.getClip();
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(10);
                clip.start();
                }
                catch(Exception ex){
                    System.err.println("no sound");
                }
        }
	}
	
	public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop() {
       try {
           thread.join();
           running = false;
       } catch(Exception e) {
           e.printStackTrace();
       	 }
    }
    
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while(delta >=1) {
                tick();
                delta--;
            }
            
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }
    
    public void tick() {
        handler.tick();
        
        if(Game.gameState == STATE.Game) {
        	hud.tick();
        }
        
        else if(Game.gameState == STATE.Menu || Game.gameState == STATE.Select || Game.gameState == STATE.Help || 
        		Game.gameState == STATE.Credits || Game.gameState == STATE.End) {
        	menu.tick();
        }
    }
    
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        
        handler.render(g);
        
        if(Game.gameState == STATE.Welcome) {
        	welcome.render(g);
        }
        
        else if(Game.gameState == STATE.Menu || Game.gameState == STATE.Select || Game.gameState == STATE.Help ||
        		Game.gameState == STATE.Credits || Game.gameState == STATE.End) {        	
        	menu.render(g);
        }
        
        else if(Game.gameState == STATE.Game) {
        	if(multi == 0) {
        		hud.render(g);
        		
        		
        		if(HUD.HEALTH <= 0 || HUD.HEALTH2 <=0) {
        			handler.clear();
        			gameState = STATE.End;
        		}
        	}
        	else if(multi == 1) {
        		hud.render(g);
        		
        		if(HUD.HEALTH <= 0 || HUD.HEALTH2 <= 0) {
        			handler.clear();
        			gameState = STATE.End;
        		}
        	}
        	
        }
        
        g.dispose();
        bs.show();
    }
    
    public static float clamp(float var, float min, float max) {
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        return var;
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}