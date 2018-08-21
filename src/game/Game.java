package game;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import events.GameEvent;
import events.MapEvent;
import events.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Translate;

public class Game {
	

	public final static int UP = 1;
	public final static int DOWN = 0;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;
	
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
	public static void draw(GraphicsContext gc) {
		gc.setTransform(new Affine());
		gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 640,480);
        gc.setTransform(new Affine(new Translate(-player.animation.getX()+19*16, -player.animation.getY()+7*32)));
        for( int x = 0; x < 20; x++ )
        	for( int y = 0; y < 15; y++ )
        	{
        		gc.drawImage( map.tileset, (map.getTile(0, x, y)%8)*32, (map.getTile(0, x, y)/8)*32, 32, 32, x*32, y*32, 32, 32 );
        	}
        
        for( int x = 0; x < 20; x++ )
        	for( int y = 0; y < 15; y++ )
        	{
        		gc.drawImage( map.tileset, (map.getTile(1, x, y)%8)*32, (map.getTile(1, x, y)/8)*32, 32, 32, x*32, y*32, 32, 32 );
        	}
          
        
        for( MapEvent x : map.mapEvents) x.draw(gc);
        
        
        for( int x = 0; x < 20; x++ )
        	for( int y = 0; y < 15; y++ )
        	{
        		gc.drawImage( map.tileset, (map.getTile(2, x, y)%8)*32, (map.getTile(2, x, y)/8)*32, 32, 32, x*32, y*32, 32, 32 );
        	}
        
        if(currentEvent != null)
        	currentEvent.draw(gc);
		
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
	
	/**
	 * Wir initialisieren die Parameter
	 * @throws MalformedURLException
	 */
	
	public static void init() throws MalformedURLException {

        Game.textboxTileset = resample (new Image(new File("res/Textbox.png").toURI().toURL().toString()),2);
		player = new Player( 5, 5, DOWN, resample (new Image(new File("res/Peschti.png").toURI().toURL().toString()),2));
		
		Game.maps.add( new maps.ShopMap() );
        Game.maps.add( new maps.VillageMap() );
        Game.maps.add(new maps.OutisZimmer());
        
        for( Map m : Game.maps ) m.mapEvents.add(player);
        
        Game.map = Game.maps.get(0);
	}
	
	public static void startEvent(GameEvent event )
	{
		Game.currentEvent = event;
		currentEvent.init();
	}
	
	public static Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;
        
        WritableImage output = new WritableImage(
          W * S,
          H * S
        );
        
        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();
        
        for (int y = 0; y < H; y++) {
          for (int x = 0; x < W; x++) {
            final int argb = reader.getArgb(x, y);
            for (int dy = 0; dy < S; dy++) {
              for (int dx = 0; dx < S; dx++) {
                writer.setArgb(x * S + dx, y * S + dy, argb);
              }
            }
          }
        }
        
        return output;
      }
	
}
