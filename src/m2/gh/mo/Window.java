package m2.gh.mo;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	
	private static final long serialVersionUID = 7345683457352199120L;

	public Window(int width, int height, String title, Game game) {
        JFrame f = new JFrame(title);
        f.setSize(width, height);
        f.setPreferredSize(new Dimension(width, height));
        f.setMaximumSize(new Dimension(width, height));
        f.setMinimumSize(new Dimension(width, height));
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(game);
        f.setVisible(true);
        game.start();
    }
	
}
