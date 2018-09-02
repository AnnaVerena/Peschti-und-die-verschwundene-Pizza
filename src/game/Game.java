package game;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import event.GameEvent;
import event.MapMode;
import event.Player;
import map.WorldMap;
import render.Image;
import render.Renderer;

public class Game {
	
	public static Map map;
	public static Player player;
	public static ArrayList<String> inputs = new ArrayList<String>();
	
	
		
	public static Stack<GameEvent> eventStack = new Stack<GameEvent>();
	
	public static Image textboxTileset;
	public static HashMap<String,Image> tilesets = new HashMap<String,Image>();
	public static HashMap<String,Image> charsets = new HashMap<String,Image>();
	
	public static HashMap<String,Map> maps = new HashMap<String,Map>();
	
	public static Camera camera = new Camera(0,0);
	
	public static void render() {
		Renderer.clearScreen();
        
		for( GameEvent x: eventStack)
			x.render();		
	}
	
	public static void update() 
	{
		while( eventStack.peek().isFinished() ) eventStack.pop();
		
		eventStack.peek().update();
	}
	
	//Die Gameloop. Wird vom automatisch aufgerufen (vom Renderer).
	public static void loop() {
		update();
		render();
	}
	
	public static void init(){
		charsets.put("peschti", Image.loadImage(new File( "res/charsets/Peschti.png")));
		charsets.put("peschti_small", Image.loadImage(new File( "res/charsets/small_peschti.png")));
		charsets.put("business", Image.loadImage(new File( "res/charsets/Business.png")));
		charsets.put("waterfall", Image.loadImage(new File( "res/charsets/waterfall.png")));
		charsets.put("waterfall_left", Image.loadImage(new File( "res/charsets/waterfall_left.png")));
		charsets.put("waterfall_right", Image.loadImage(new File( "res/charsets/waterfall_right.png")));
		charsets.put("door", Image.loadImage(new File( "res/charsets/door.png")));
		
		tilesets.put("world", Image.loadImage(new File("res/tilesets/world.png")));
		tilesets.put("shop", Image.loadImage(new File("res/tilesets/Tileset.png")));
		tilesets.put("village", Image.loadImage(new File("res/tilesets/Tileset2.png")));
		tilesets.put("outi_room", Image.loadImage(new File("res/tilesets/OutiZimmer.png")));

        Game.textboxTileset = Image.loadImage(new File("res/Textbox2.png"));
		player = new Player( 7, 9, GameUtil.DOWN, "peschti", "peschti_small");
		
		try {
			Game.maps.put( "shop", new map.ShopMap() );
			Game.maps.put( "village", new map.VillageMap() );
			Game.maps.put("outi_room", new map.OutisZimmer());
			Game.maps.put("world", new WorldMap());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        
		map = maps.get("village");
        startEvent( new MapMode());
	}
	
	public static void startEvent(GameEvent event )
	{
		eventStack.push( event );
		event.init();
	}
	
	public static void main( String... args)
	{
		Game.init();
		Renderer.run();
	}
	
}
