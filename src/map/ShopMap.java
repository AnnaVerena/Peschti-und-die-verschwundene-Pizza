package map;

import event_system.GameEvent;
import event_system.SetVariableEvent;
import event_system.WaitEvent;
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
import util.Direction;
import util.Pair;

import java.io.File;

public class ShopMap extends game.Map{
	
	public ShopMap(){
		super(new File("res/maps/map_shop.txt"));
        tileset = Game.tilesets.get("shop");
              
        MapEntity busi = new MapEntity( "business", 8,5, Direction.DOWN, "business");
        busi.actionEvent = new EventList( new TurnToPlayer("business"), new OpenTextbox(), new DialogBox("Kaufen, kaufen, kaufen!\n"
        		+ "Ich habe die besten Preise!"), new TurnEvent("business", Direction.DOWN));
        
        mapEntities.add( busi );
        
        GameEvent bookEvent = new Textbox("Bücher...\n"
        		+ "Peschti mag nicht lesen.");        
        mapEntities.add( new MapEntity(10, 4, Direction.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(11, 4, Direction.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(14, 4, Direction.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(15, 4, Direction.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(14, 7, Direction.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(15, 7, Direction.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(14, 10, Direction.DOWN, null, false, bookEvent, null ));
        mapEntities.add( new MapEntity(15, 10, Direction.DOWN, null, false, bookEvent, null ));
        
        GameEvent kekseDefault = new Textbox("Peschti hat kein Geld.");
        GameEvent kekseKaufen = new EventList( new Textbox("Peschti kauft Kekse für 1$."), new SetVariableEvent("KEKSE", 2));
        
        GameEvent cookieEvent = new Choicebox("Hmm ...\n"
        		+ "Die Kekse sehen sehr lecker aus!\nMöchtest du welche kaufen?", new CasesEvent("KEKSE", kekseDefault, new Pair<>(1, kekseKaufen) ), new Textbox("Du bist ja komisch."));
        mapEntities.add( new MapEntity(11, 7, Direction.DOWN, null, false, cookieEvent, null ));
        
        
        
        GameEvent kakaoEvent = new EventList( new OpenTextbox(), new Textbox("Kakao ist sehr lecker und stellt auch\n"
        		+ "Energie wieder her!") );
        mapEntities.add( new MapEntity(9, 7, Direction.DOWN, null, false, kakaoEvent, null ));
        
        GameEvent energyEvent = new Textbox("Energietrunk!\n"
        		+ "Dieses Getränk liefert eine erhebliche\n"
        		+ "Menge Energie. Es ist grün\n"
        		+ "und leuchtet bei Dunkelheit.");

        mapEntities.add( new MapEntity(10, 7, Direction.DOWN, null, false, energyEvent, null ));
        
        GameEvent pizzaDefault = new Textbox("Peschti hat kein Geld.");
        GameEvent pizzaKaufen = new EventList( new Textbox("Peschti kauft Pizza für 10P$."), new SetVariableEvent("PIZZA", 2));

        GameEvent pizzaEvent = new Choicebox("Diese Pizza sieht ... merkwürdig aus.\n"
        		+ "Möchtest du sie kaufen?", new CasesEvent("PIZZA", pizzaDefault, new Pair<>(1, pizzaKaufen) ), new Textbox("Ist auch besser so."));
        mapEntities.add( new MapEntity(11, 7, Direction.DOWN, null, false, pizzaEvent, null ));
        mapEntities.add( new MapEntity(8, 7, Direction.DOWN, null, false, pizzaEvent, null ));
        
        GameEvent pictureEvent = new Textbox("Ein Bild vom Outis.");
        mapEntities.add( new MapEntity(9, 3, Direction.DOWN, null, false, pictureEvent, null ));
        
        mapEntities.add(new MapEntity(9, 12, Direction.DOWN, null, true, null, new EventList( new Teleport("village", 18, 6, Direction.DOWN), new WaitEvent(10) )));
        mapEntities.add(new MapEntity(10, 12, Direction.DOWN, null, true, null, new EventList( new Teleport("village", 18, 6, Direction.DOWN), new WaitEvent(10) )));
	}

}
