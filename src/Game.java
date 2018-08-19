import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Game {
	

	final static int UP = 1;
	final static int DOWN = 0;
	final static int LEFT = 2;
	final static int RIGHT = 3;
	
	public static Map map;
	public static Player player;
	public static ArrayList<String> inputs = new ArrayList<String>();
	public static ArrayList<Map> maps = new ArrayList<Map>();
	public static GameEvent currentEvent;
	public static Image textboxTileset;
	
	/**
	 * Hier malen wir die Karte
	 * 
	 * @param gc braucht man zum Malen
	 */
	public static void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 640,480);
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
		
		player = new Player( 5, 5, DOWN, resample (new Image(new File("res/Peschti.png").toURI().toURL().toString()),2));
		
		//SHOP
		Map shop = new Map(new File("res/map_shop.txt"));        
        shop.mapEvents.add( player );
        
        MapEvent busi = new MapEvent( 8,5, DOWN, resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        busi.actionEvent = new Textbox("Kaufen, kaufen, kaufen!\n"
        		+ "Ich habe die besten Preise!");
        shop.mapEvents.add( busi );
        
        GameEvent bookEvent = new Textbox("Buecher...\n"
        		+ "Peschti mag nicht lesen.");        
        shop.mapEvents.add( new MapEvent(10, 4, DOWN, null, bookEvent ));
        shop.mapEvents.add( new MapEvent(11, 4, DOWN, null, bookEvent ));
        shop.mapEvents.add( new MapEvent(14, 4, DOWN, null, bookEvent ));
        shop.mapEvents.add( new MapEvent(15, 4, DOWN, null, bookEvent ));
        shop.mapEvents.add( new MapEvent(14, 7, DOWN, null, bookEvent ));
        shop.mapEvents.add( new MapEvent(15, 7, DOWN, null, bookEvent ));
        shop.mapEvents.add( new MapEvent(14, 10, DOWN, null, bookEvent ));
        shop.mapEvents.add( new MapEvent(15, 10, DOWN, null, bookEvent ));
        
        GameEvent cookieEvent = new Choicebox("Hmm ...\n"
        		+ "Die Kekse sehen \nsehr lecker aus!\nMöchtest du einen kaufen?", new Textbox("Ein Keks kosten 1 $."), new Textbox("Du bist ja komisch."));
        shop.mapEvents.add( new MapEvent(11, 7, DOWN, null, cookieEvent ));
        
        
        
        GameEvent kakaoEvent = new Textbox("Kakao ist sehr lecker und stellt auch\n"
        		+ "Energie wieder her!");
        shop.mapEvents.add( new MapEvent(9, 7, DOWN, null, kakaoEvent ));
        
        GameEvent energyEvent = new Textbox("Energietrunk!\n"
        		+ "Dieses Getraenk liefert eine erhebliche\n"
        		+ "Menge Energie. Es ist gruen\n"
        		+ "und leuchtet bei Dunkelheit.");
        shop.mapEvents.add( new MapEvent(10, 7, DOWN, null, energyEvent ));

        GameEvent pizzaEvent = new Textbox("Leider ist diese Pizza mit Oliven belegt.\n"
        		+ "Wer macht sowas nur?");
        shop.mapEvents.add( new MapEvent(8, 7, DOWN, null, pizzaEvent ));
        
        GameEvent pictureEvent = new Textbox("Ein Bild vom Outis.");
        shop.mapEvents.add( new MapEvent(9, 3, DOWN, null, pictureEvent ));
        
        shop.mapEvents.add(new MapEvent(9, 13, DOWN, null, new Teleport(1, 13, 6, DOWN)));
        shop.mapEvents.add(new MapEvent(10, 13, DOWN, null, new Teleport(1, 13, 6, DOWN)));
        
        shop.tileset = resample (new Image(new File("res/Tileset.png").toURI().toURL().toString()),2);
        
        Game.maps.add(shop);
        
        //VILLAGE
        Map village = new Map(new File("res/map_village.txt"));        
        village.mapEvents.add( player );
        village.tileset = resample (new Image(new File("res/Tileset2.png").toURI().toURL().toString()),2);
        
        village.mapEvents.add(new MapEvent(13, 5, DOWN, null, new Teleport(0, 9, 12, UP)));
        
        Game.maps.add(village);
        
        Game.map = shop;
        Game.textboxTileset = resample (new Image(new File("res/Textbox.png").toURI().toURL().toString()),2);
        
        currentEvent = new Textbox("Willkommen zur Outiwoch!\nWillkommen im Shop!\nPeschti!\nOutis!");
	}
	
	public static void startEvent(GameEvent event )
	{
		Game.currentEvent = event;
		currentEvent.init();
	}
	
	private static Image resample(Image input, int scaleFactor) {
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
