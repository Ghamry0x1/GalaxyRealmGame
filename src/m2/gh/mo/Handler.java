package m2.gh.mo;

//update, maintain and render all objects in screen

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	  
	public void tick() {
	      for(int i=0; i<object.size(); i++) {
	          GameObject tempObject = object.get(i);
	          tempObject.tick();
	      }
	  }
	  
	  public void render(Graphics g) {
	      for(int i=0; i<object.size(); i++) {
	          GameObject tempObject = object.get(i);
	          tempObject.render(g);
	      }
	  }
	  
	  public void clear() {
		  for(int i = 0; i<object.size(); i++) {
				  object.clear();
		  }
	  }
	  
	  public void addObject(GameObject object ) {
	      this.object.add(object);
	  }
	  
	  public void removeObject(GameObject object) {
	      this.object.remove(object);
	  }  
}