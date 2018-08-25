package maps;

import java.io.File;
import java.net.MalformedURLException;

import events.Choicebox;
import events.GameEvent;
import events.MapEvent;
import events.Teleport;
import events.Textbox;
import events.TurnToPlayer;
import events.WaitEvent;
import events.EventList;
import game.Game;
import game.GameUtil;
import javafx.scene.image.Image;

public class ShopMap extends game.Map{
	
	public ShopMap() throws MalformedURLException{
		super(new File("res/map_shop.txt"));
        tileset = GameUtil.resample (new Image(new File("res/Tileset.png").toURI().toURL().toString()),2);
        
        MapEvent busi = new MapEvent( 8,5, GameUtil.DOWN, GameUtil.resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        busi.actionEvent = new EventList( new TurnToPlayer(busi), new Textbox("Kaufen, kaufen, kaufen!\n"
        		+ "Ich habe die besten Preise!"), new events.TurnEvent(busi, GameUtil.DOWN));
        
        mapEvents.add( busi );
        
        GameEvent bookEvent = new Textbox("Bücher...\n"
        		+ "Peschti mag nicht lesen.");        
        mapEvents.add( new MapEvent(10, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEvents.add( new MapEvent(11, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEvents.add( new MapEvent(14, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEvents.add( new MapEvent(15, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEvents.add( new MapEvent(14, 7, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEvents.add( new MapEvent(15, 7, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEvents.add( new MapEvent(14, 10, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEvents.add( new MapEvent(15, 10, GameUtil.DOWN, null, false, bookEvent, null ));
        
        GameEvent cookieEvent = new Choicebox("Hmm ...\n"
        		+ "Die Kekse sehen sehr lecker aus!\nMöchtest du einen kaufen?", new Textbox("Ein Keks kostet 1$."), new Textbox("Du bist ja komisch."));
        mapEvents.add( new MapEvent(11, 7, GameUtil.DOWN, null, false, cookieEvent, null ));
        
        
        
        GameEvent kakaoEvent = new Textbox("Kakao ist sehr lecker und stellt auch\n"
        		+ "Energie wieder her!");
        mapEvents.add( new MapEvent(9, 7, GameUtil.DOWN, null, false, kakaoEvent, null ));
        
        GameEvent energyEvent = new Textbox("Energietrunk!\n"
        		+ "Dieses Getränk liefert eine erhebliche\n"
        		+ "Menge Energie. Es ist grün\n"
        		+ "und leuchtet bei Dunkelheit.");
        mapEvents.add( new MapEvent(10, 7, GameUtil.DOWN, null, false, energyEvent, null ));

        GameEvent pizzaEvent = new Textbox("Leider ist diese Pizza mit Oliven belegt.\n"
        		+ "Wer macht sowas nur?");
        mapEvents.add( new MapEvent(8, 7, GameUtil.DOWN, null, false, pizzaEvent, null ));
        
        GameEvent pictureEvent = new Textbox("Ein Bild vom Outis.");
        mapEvents.add( new MapEvent(9, 3, GameUtil.DOWN, null, false, pictureEvent, null ));
        
        mapEvents.add(new MapEvent(9, 13, GameUtil.DOWN, null, true, null, new EventList( new Teleport(1, 18, 6, GameUtil.DOWN), new WaitEvent(10) )));
        mapEvents.add(new MapEvent(10, 13, GameUtil.DOWN, null, true, null, new EventList( new Teleport(1, 18, 6, GameUtil.DOWN), new WaitEvent(10) )));
	}

}
