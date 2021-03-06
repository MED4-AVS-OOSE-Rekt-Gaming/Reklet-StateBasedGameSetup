package nat.rectgaming.states.world;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import nat.rectgaming.Resources;
import nat.rectgaming.Window;

public class World {

	public static Image[][] solids;
	public static int WIDTH;
	public static int HEIGHT;
//	public static Image[][] spawns;
	
	public static void render(float xRender, float yRender) {
		int offset = 2;
		int xStart = (int) (xRender/Tile.SIZE) - offset;
		int yStart = (int) (yRender/Tile.SIZE) - offset;
		int xEnd = (Window.WIDTH/Tile.SIZE) + xStart + (offset*2);
		int yEnd = (Window.HEIGHT/Tile.SIZE) + yStart + (offset*2);
		
		for(int x = xStart; x< xEnd; x++) {
			for(int y = yStart; y< yEnd; y++) {
				if(solidTile(x,y)) {
					solids[x][y].draw(x*Tile.SIZE,y*Tile.SIZE,Tile.SIZE,Tile.SIZE);
				}
	//			if(spawnTile(x,y)) {
		//			spawns[x][y].draw(x*Tile.SIZE,y*Tile.SIZE,Tile.SIZE,Tile.SIZE);
			//	}
				
			}
		}
	}
	
	public static void load(String path) throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(path));
		JSONObject jobj = (JSONObject) obj;
		
		JSONArray layers = (JSONArray) jobj.get("layers");
		int amount = layers.size();
		
		for (int i = 0; i < amount; i++) {
			JSONObject layer = (JSONObject) layers.get(i);
			String type = (String) layer.get("name");
			
			if(type.equals("solids")) {
				WIDTH =(int) ((long)layer.get("width"));
				HEIGHT = (int) ((long)layer.get("height"));
				solids = parse((JSONArray) layer.get("data"));

			} else if(type.equals("spawns")) {
	//			WIDTH = (int) ((long)layer.get("width"));
		//		HEIGHT = (int) ((long)layer.get("height"));
			//	spawns = parse((JSONArray) layer.get("data"));
			}
		}
	}
	
	private static Image[][] parse(JSONArray arr) {
		Image[][] layer = new Image[WIDTH][HEIGHT];
		int i; // index
		
		for(int y = 0; y<WIDTH; y++) {
			for(int x = 0; x<HEIGHT; x++) {
				i = (int)((long)arr.get((y*WIDTH)+x));
				layer[x][y] = getSpriteImage(i);
			}
		}
		return layer;
	}
	
	private static Image getSpriteImage(int i) {
		if(i == 0) return null;
		i -=-1;
		
		SpriteSheet sheet = Resources.getSprite("tilesset");
		int vertical = sheet.getVerticalCount();
		int horizontal = sheet.getHorizontalCount();
		
		int y = (i/vertical);
		int x = (i % horizontal);
		
		return sheet.getSubImage(x, y);
	}
	
	public static boolean inBounds(int x, int y) {
		return (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT);
	}
	
	
	public static boolean solidTile(int x, int y) {
		return (inBounds(x,y) && solids[x][y] != null);
	}
//	public static boolean spawnTile(int x, int y) {
//		return (inBounds(x,y) && spawns[x][y] != null);
//	}
	
	public static boolean hitTest(float x, float y) {
		int xPoint = (int)(x/Tile.SCALE % Tile.SMALL_SIZE);
		int yPoint = (int)(y/Tile.SCALE % Tile.SMALL_SIZE);
		int xTile = (int)x/Tile.SIZE;
		int yTile = (int)y/Tile.SIZE;
		
		if(solidTile(xTile,yTile)) {
			return (solids[xTile][yTile].getColor(xPoint, yPoint).a > 0);
		}
		
		return false;
	}
	
}
