package game;

import event_system.GameEvent;
import event_system.MapMode;
import event_system.map_entities.Player;
import map.WorldMap;
import render.Image;
import render.Renderer;
import util.Direction;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Game {
	
	public static Map map;
	public static Player player;
	public static ArrayList<String> inputs = new ArrayList<>();
	
	
		
	public static Stack<GameEvent> eventStack = new Stack<>();
	
	public static Image textboxTileset;
	public static HashMap<String,Image> tilesets = new HashMap<>();
	public static HashMap<String,Image> charsets = new HashMap<>();
	public static HashMap<String, Integer> variables = new HashMap<>();

    //Inventory inventory = new Inventory();
	
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
		charsets.put("peschti", Image.loadImage(new File( "res/charsets/Peschti3.png")));
		charsets.put("peschti_small", Image.loadImage(new File( "res/charsets/small_peschti.png")));
		charsets.put("business", Image.loadImage(new File( "res/charsets/Business.png")));
		charsets.put("paula", Image.loadImage(new File( "res/charsets/Paula.png")));
		charsets.put("waterfall", Image.loadImage(new File( "res/charsets/waterfall.png")));
		charsets.put("waterfall_left", Image.loadImage(new File( "res/charsets/waterfall_left.png")));
		charsets.put("waterfall_right", Image.loadImage(new File( "res/charsets/waterfall_right.png")));
		charsets.put("door", Image.loadImage(new File( "res/charsets/door.png")));
		
		tilesets.put("world", Image.loadImage(new File("res/tilesets/world.png")));
		tilesets.put("shop", Image.loadImage(new File("res/tilesets/Tileset.png")));
		tilesets.put("village", Image.loadImage(new File("res/tilesets/Tileset2.png")));
		tilesets.put("outi_room", Image.loadImage(new File("res/tilesets/OutiZimmer.png")));

        Game.textboxTileset = Image.loadImage(new File("res/Textbox2.png"));
		player = new Player( 7, 9, Direction.DOWN, "peschti", "peschti_small");

        try {
            map = loadMap("village");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        startEvent( new MapMode());
	}

	public static Map loadMap(String mapId) throws Exception {
        return switch (mapId) {
            case "shop" -> new map.ShopMap();
            case "village" -> new map.VillageMap();
            case "outi_room" -> new map.OutisZimmer();
            case "peschti_room" -> new map.PeschtiZimmer();
            case "world" -> new WorldMap();
            default -> throw new Exception("unknown map id: " + mapId);
        };
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
