package map;

import java.io.File;
import java.net.MalformedURLException;

import event.CasesEvent;
import event.Choicebox;
import event.DialogBox;
import event.EventList;
import event.GameEvent;
import event.MapEvent;
import event.OpenTextbox;
import event.SetVariableEvent;
import event.Teleport;
import event.Textbox;
import event.TurnToPlayer;
import event.WaitEvent;
import game.Game;
import game.GameUtil;
import util.Pair;

public class ShopMap extends game.Map{
	
	public ShopMap(){
		super(new File("res/maps/map_shop.txt"));
        tileset = Game.tilesets.get("shop");
              
        MapEvent busi = new MapEvent( "business", 8,5, GameUtil.DOWN, "business");
        busi.actionEvent = new EventList( new TurnToPlayer("business"), new OpenTextbox(), new DialogBox("Kaufen, kaufen, kaufen!\n"
        		+ "Ich habe die besten Preise!"), new event.TurnEvent("business", GameUtil.DOWN));
        
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
        
        GameEvent kekseDefault = new Textbox("Peschti hat kein Geld.");
        GameEvent kekseKaufen = new EventList( new Textbox("Peschti kauft Kekse für 1$."), new SetVariableEvent("KEKSE", 2));
        
        GameEvent cookieEvent = new Choicebox("Hmm ...\n"
        		+ "Die Kekse sehen sehr lecker aus!\nMöchtest du welche kaufen?", new CasesEvent("KEKSE", kekseDefault, new Pair<>(1, kekseKaufen) ), new Textbox("Du bist ja komisch."));
        mapEvents.add( new MapEvent(11, 7, GameUtil.DOWN, null, false, cookieEvent, null ));
        
        
        
        GameEvent kakaoEvent = new EventList( new OpenTextbox(), new Textbox("Kakao ist sehr lecker und stellt auch\n"
        		+ "Energie wieder her!") );
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
        
        mapEvents.add(new MapEvent(9, 12, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 18, 6, GameUtil.DOWN), new WaitEvent(10) )));
        mapEvents.add(new MapEvent(10, 12, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 18, 6, GameUtil.DOWN), new WaitEvent(10) )));
	}

}
