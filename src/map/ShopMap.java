package map;

import event_system.*;
import event_system.control_flow.CasesEvent;
import event_system.control_flow.EventList;
import event_system.dialog.Choicebox;
import event_system.dialog.DialogBox;
import event_system.dialog.OpenTextbox;
import event_system.dialog.Textbox;
import event_system.map_control.Teleport;
import event_system.map_control.TurnEvent;
import event_system.map_control.TurnToPlayer;
import event_system.map_entities.MapEntity;
import game.Game;
import game.GameUtil;
import util.Pair;

import java.io.File;

public class ShopMap extends game.Map{
	
	public ShopMap(){
		super(new File("res/maps/map_shop.txt"));
        tileset = Game.tilesets.get("shop");
              
        MapEntity busi = new MapEntity( "business", 8,5, GameUtil.DOWN, "business");
        busi.actionEvent = new EventList( new TurnToPlayer("business"), new OpenTextbox(), new DialogBox("Kaufen, kaufen, kaufen!\n"
        		+ "Ich habe die besten Preise!"), new TurnEvent("business", GameUtil.DOWN));
        
        mapEntities.add( busi );
        
        GameEvent bookEvent = new Textbox("Bücher...\n"
        		+ "Peschti mag nicht lesen.");        
        mapEntities.add( new MapEntity(10, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(11, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(14, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(15, 4, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(14, 7, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(15, 7, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(14, 10, GameUtil.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(15, 10, GameUtil.DOWN, null, false, bookEvent, null ));
        
        GameEvent kekseDefault = new Textbox("Peschti hat kein Geld.");
        GameEvent kekseKaufen = new EventList( new Textbox("Peschti kauft Kekse für 1$."), new SetVariableEvent("KEKSE", 2));
        
        GameEvent cookieEvent = new Choicebox("Hmm ...\n"
        		+ "Die Kekse sehen sehr lecker aus!\nMöchtest du welche kaufen?", new CasesEvent("KEKSE", kekseDefault, new Pair<>(1, kekseKaufen) ), new Textbox("Du bist ja komisch."));
        mapEntities.add( new MapEntity(11, 7, GameUtil.DOWN, null, false, cookieEvent, null ));
        
        
        
        GameEvent kakaoEvent = new EventList( new OpenTextbox(), new Textbox("Kakao ist sehr lecker und stellt auch\n"
        		+ "Energie wieder her!") );
        mapEntities.add( new MapEntity(9, 7, GameUtil.DOWN, null, false, kakaoEvent, null ));
        
        GameEvent energyEvent = new Textbox("Energietrunk!\n"
        		+ "Dieses Getränk liefert eine erhebliche\n"
        		+ "Menge Energie. Es ist grün\n"
        		+ "und leuchtet bei Dunkelheit.");
        mapEntities.add( new MapEntity(10, 7, GameUtil.DOWN, null, false, energyEvent, null ));

        GameEvent pizzaEvent = new Textbox("Leider ist diese Pizza mit Oliven belegt.\n"
        		+ "Wer macht sowas nur?");
        mapEntities.add( new MapEntity(8, 7, GameUtil.DOWN, null, false, pizzaEvent, null ));
        
        GameEvent pictureEvent = new Textbox("Ein Bild vom Outis.");
        mapEntities.add( new MapEntity(9, 3, GameUtil.DOWN, null, false, pictureEvent, null ));
        
        mapEntities.add(new MapEntity(9, 12, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 18, 6, GameUtil.DOWN), new WaitEvent(10) )));
        mapEntities.add(new MapEntity(10, 12, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 18, 6, GameUtil.DOWN), new WaitEvent(10) )));
	}

}
