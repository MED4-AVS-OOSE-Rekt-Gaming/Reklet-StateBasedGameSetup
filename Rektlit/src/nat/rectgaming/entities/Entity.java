package nat.rectgaming.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import nat.rectgaming.states.world.World;
import nat.rectgaming.util.*;

public abstract class Entity extends Box {

	public Image image;
	public Color color;
	
	public Entity() {
		init();
	}
	
	public abstract void init();
	
	public void render(GameContainer gc, Graphics g) {
		if(image != null) {
			image.draw(x,y,width,height,color);
		}
	}
	
	public abstract void update(GameContainer gc, int delta);
	
	public boolean testLeft() {
		return (World.hitTest(x, getCenterY()));
	} 
	public boolean testRight() {
		return (World.hitTest(getEndX(), getCenterY()));
	} 
	public boolean testUp() {
		return (World.hitTest(getCenterX(), y));
	} 
	public boolean testDown() {
		return (World.hitTest(getCenterX(), getEndY()));
	} 
}
