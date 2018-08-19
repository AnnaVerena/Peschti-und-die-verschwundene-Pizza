package maps;

import java.io.File;
import java.net.MalformedURLException;

import game.Choicebox;
import game.Game;
import game.GameEvent;
import game.MapEvent;
import game.Teleport;
import game.Textbox;
import javafx.scene.image.Image;

public class ShopMap extends game.Map{
	
	public ShopMap() throws MalformedURLException{
		super(new File("res/map_shop.txt"));
        tileset = Game.resample (new Image(new File("res/Tileset.png").toURI().toURL().toString()),2);
        
        MapEvent busi = new MapEvent( 8,5, Game.DOWN, Game.resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        busi.actionEvent = new Textbox("Kaufen, kaufen, kaufen!\n"
        		+ "Ich habe die besten Preise!");
        mapEvents.add( busi );
        
        GameEvent bookEvent = new Textbox("Buecher...\n"
        		+ "Peschti mag nicht lesen.");        
        mapEvents.add( new MapEvent(10, 4, Game.DOWN, null, bookEvent ));
        mapEvents.add( new MapEvent(11, 4, Game.DOWN, null, bookEvent ));
        mapEvents.add( new MapEvent(14, 4, Game.DOWN, null, bookEvent ));
        mapEvents.add( new MapEvent(15, 4, Game.DOWN, null, bookEvent ));
        mapEvents.add( new MapEvent(14, 7, Game.DOWN, null, bookEvent ));
        mapEvents.add( new MapEvent(15, 7, Game.DOWN, null, bookEvent ));
        mapEvents.add( new MapEvent(14, 10, Game.DOWN, null, bookEvent ));
        mapEvents.add( new MapEvent(15, 10, Game.DOWN, null, bookEvent ));
        
        GameEvent cookieEvent = new Choicebox("Hmm ...\n"
        		+ "Die Kekse sehen \nsehr lecker aus!\nMöchtest du einen kaufen?", new Textbox("Ein Keks kosten 1 $."), new Textbox("Du bist ja komisch."));
        mapEvents.add( new MapEvent(11, 7, Game.DOWN, null, cookieEvent ));
        
        
        
        GameEvent kakaoEvent = new Textbox("Kakao ist sehr lecker und stellt auch\n"
        		+ "Energie wieder her!");
        mapEvents.add( new MapEvent(9, 7, Game.DOWN, null, kakaoEvent ));
        
        GameEvent energyEvent = new Textbox("Energietrunk!\n"
        		+ "Dieses Getraenk liefert eine erhebliche\n"
        		+ "Menge Energie. Es ist gruen\n"
        		+ "und leuchtet bei Dunkelheit.");
        mapEvents.add( new MapEvent(10, 7, Game.DOWN, null, energyEvent ));

        GameEvent pizzaEvent = new Textbox("Leider ist diese Pizza mit Oliven belegt.\n"
        		+ "Wer macht sowas nur?");
        mapEvents.add( new MapEvent(8, 7, Game.DOWN, null, pizzaEvent ));
        
        GameEvent pictureEvent = new Textbox("Ein Bild vom Outis.");
        mapEvents.add( new MapEvent(9, 3, Game.DOWN, null, pictureEvent ));
        
        mapEvents.add(new MapEvent(9, 13, Game.DOWN, null, new Teleport(1, 13, 6, Game.DOWN)));
        mapEvents.add(new MapEvent(10, 13, Game.DOWN, null, new Teleport(1, 13, 6, Game.DOWN)));
	}

}
