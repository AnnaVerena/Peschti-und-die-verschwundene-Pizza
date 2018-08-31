package game;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import event.GameEvent;
import event.MapEvent;
import event.MapMode;
import event.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;
import map.WorldMap;
import render.Image;
import render.Renderer;

public class Game {
	
	public static Map map;
	public static Player player;
	public static ArrayList<String> inputs = new ArrayList<String>();
	public static ArrayList<Map> maps = new ArrayList<Map>();
	
	public static Stack<GameEvent> eventStack = new Stack<GameEvent>();
	
	public static Image textboxTileset;
	
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

        Game.textboxTileset = Image.loadImage(new File("res/Textbox2.png"));
		player = new Player( 7, 9, GameUtil.DOWN, Image.loadImage(new File("res/charsets/Peschti.png")), Image.loadImage(new File("res/charsets/small_peschti.png")));
		
		try {
			Game.maps.add( new map.ShopMap() );
			Game.maps.add( new map.VillageMap() );
			Game.maps.add(new map.OutisZimmer());
			Game.maps.add(new WorldMap());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        
        startEvent( new MapMode( maps.get(1) ));
	}
	
	public static void startEvent(GameEvent event )
	{
		eventStack.push( event );
		event.init();
	}
	
	
	
}
