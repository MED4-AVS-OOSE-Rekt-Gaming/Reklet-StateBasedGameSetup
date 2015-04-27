package nat.rectgaming.states;

import java.util.ArrayList;

import nat.rectgaming.Resources;
import nat.rectgaming.states.world.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
//import org.newdawn.slick.tiled.*;

public class GameState extends BasicGameState {
	//private TiledMap testMap;
	@Override
	public void init(GameContainer gc, StateBasedGame s) throws SlickException {
/*		try {
			World.load("res/maps/testmap.json");
			//testMap = new TiledMap("resources/maps/MAIN_TEST.tmx");
		} catch (Exception e) {
			System.err.println("The Map is missing Check Resource folder");
			//System.exit(0);
		}*/
	}

	@Override
	public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
		//g.drawString("GameState", 350, 50);
		//g.drawString("Press Enter to return to menu", 450, 25);
		
		Resources.getSpriteImage("tilesset", 0, 0).draw();
		g.translate(0, 0);
		World.render(0, 0);
		g.resetTransform();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame s, int delta) throws SlickException {
		if(gc.getInput().isKeyPressed(Input.KEY_RETURN)) s.enterState(States.MENU);
			
	}

	@Override
	public int getID() {
		return States.GAME;
	}

	
}
