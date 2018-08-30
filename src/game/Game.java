package game;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import event.GameEvent;
import event.MapEvent;
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
	public static GameEvent currentEvent;
	public static Image textboxTileset;
	
	public static Camera camera = new Camera(0,0);
	/**
	 * Hier malen wir die Karte
	 * 
	 * @param gc braucht man zum Malen
	 */
	public static void render() {
		Renderer.clearScreen();
        
        int tx = -player.getScrX()+19*8;
        if( tx > 0 ) tx = 0;
        if(tx < -map.getWidth()*16 + 20*16) tx = -map.getWidth()*16 + 20*16;
        
        int ty = -player.getScrY()+7*16;
        if( ty > 0 ) ty = 0;
        if( ty < -map.getHeight()*16 + 15*16 ) ty = -map.getHeight()*16 + 15*16;
        
        camera.setPos(tx,ty);
        Renderer.setView(camera.view);
        /*
		for( int x = 0; x < map.getWidth(); x++ )
        	for( int y = 0; y < map.getHeight(); y++ )
        	{
        		Renderer.renderSubImage( map.tileset, (map.getTile(0, x, y)%8)*16, (map.getTile(0, x, y)/8)*16, 16, 16, x*16, y*16, 16, 16 );
        	}
        
        for( int x = 0; x < map.getWidth(); x++ )
        	for( int y = 0; y < map.getHeight(); y++ )
        	{
        		Renderer.renderSubImage( map.tileset, (map.getTile(1, x, y)%8)*16, (map.getTile(1, x, y)/8)*16, 16, 16, x*16, y*16, 16, 16 );
        	}
          */
		Renderer.renderMapLayer(map, 0);
		Renderer.renderMapLayer(map,  1);
        
        for( MapEvent x : map.mapEvents) x.render();
        
        Renderer.renderMapLayer(map,  2);
        /*
        for( int x = 0; x < map.getWidth(); x++ )
        	for( int y = 0; y < map.getHeight(); y++ )
        	{
        		Renderer.renderSubImage( map.tileset, (map.getTile(2, x, y)%8)*16, (map.getTile(2, x, y)/8)*16, 16, 16, x*16, y*16, 16, 16 );
        	}
        */
        if(currentEvent != null)
        	currentEvent.render();
		
	}
	
	/**
	 * wir updaten die MpEvents und sortieren sie
	 */	
	public static void update() {
		
		for( MapEvent x : map.mapEvents) 
			if(currentEvent == null)
			   x.update();
			else break;
		if(currentEvent != null)
			{currentEvent.update();
			if(currentEvent.isFinished())
				currentEvent = null;
			}
		Collections.sort(map.mapEvents, new Comparator<MapEvent>() {
            @Override
            public int compare(MapEvent o1, MapEvent o2) {
                return o1.getY() - o2.getY();
            }
        });
		
	}
	
	//Die Gameloop. Wird vom automatisch aufgerufen (vom Renderer).
	public static void loop() {
		update();
		render();
	}
	
	/**
	 * Wir initialisieren die Parameter
	 * @throws MalformedURLException
	 */
	
	public static void init(){

        Game.textboxTileset = Image.loadImage(new File("res/Textbox.png"));
		player = new Player( 7, 9, GameUtil.DOWN, Image.loadImage(new File("res/charsets/Peschti.png")));
		
		try {
			Game.maps.add( new map.ShopMap() );
			Game.maps.add( new map.VillageMap() );
			Game.maps.add(new map.OutisZimmer());
			Game.maps.add(new WorldMap());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        
        for( Map m : Game.maps ) m.mapEvents.add(player);
        
        Game.map = Game.maps.get(1);
	}
	
	public static void startEvent(GameEvent event )
	{
		Game.currentEvent = event;
		currentEvent.init();
	}
	
	
	
}
