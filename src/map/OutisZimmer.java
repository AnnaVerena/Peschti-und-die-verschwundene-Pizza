package map;

import event_system.*;
import event_system.control_flow.CasesEvent;
import event_system.control_flow.EventList;
import event_system.dialog.Choicebox;
import event_system.dialog.Textbox;
import event_system.map_control.MoveEvent;
import event_system.map_control.Teleport;
import event_system.map_control.TurnToPlayer;
import event_system.map_control.WaitForMapEntity;
import event_system.map_entities.MapEntity;
import event_system.map_entities.RandomWalkNPC;
import game.Game;
import game.GameUtil;
import util.Direction;
import util.Pair;

import java.io.File;

public class OutisZimmer extends game.Map{
	
	public OutisZimmer(){
		super(new File("res/maps/map_outis.txt"));
        tileset = Game.tilesets.get("outi_room");        
        
        mapEntities.add( new MapEntity("teleport1", 9, 12, Direction.DOWN, null, true, null, new EventList( new Teleport("village", 7, 9, Direction.DOWN), new WaitEvent(10))));
        mapEntities.add( new MapEntity("teleport2", 10, 12, Direction.DOWN, null, true, null, new EventList( new Teleport("village", 7, 9, Direction.DOWN), new WaitEvent(10))));
        
        MapEntity outi = new RandomWalkNPC("outi",  11,4, Direction.DOWN, "business");
        
        GameEvent stage0 = new EventList( new TurnToPlayer("outi"),
        				new Textbox("Outis: Hier ist mein Zimmer!\n"
        							+ "Hier gibt es gratis Kakao!"), 
        				new Choicebox("Outis: Kannst du mir vielleicht\nKekse kaufen gehen?",
        						new EventList( new Textbox("Vielen Dank!\nDer Business verkauft Kekse."), new Textbox("Outis gibt Peschti 1$."), new SetVariableEvent("KEKSE", 1)),
        						new Textbox("Outis: Das ist aber schade.")));
        
        GameEvent stage1 = new EventList( new TurnToPlayer("outi"), 
				new Textbox("Outis: Hier ist mein Zimmer!\n"
						+ "Hier gibt es gratis Kakao!"));
        
        GameEvent stage2 = new EventList( new TurnToPlayer("outi"), 
        				new Textbox("Peschti gibt Outis die Kekse"),
        				new Textbox("Outis: Vielen Dank für die Kekse!"),
        				new SetVariableEvent( "KEKSE", 3));
        
        GameEvent stage3 = new EventList( new TurnToPlayer("outi"), 
				new Textbox("Outis: Hier ist mein Zimmer!\n"
						+ "Hier gibt es gratis Kakao und Kekse!"));
        				
        
        outi.actionEvent = new EventList( new WaitForMapEntity("outi") ,  new CasesEvent( "KEKSE", stage0, new Pair<>(1,stage1), new Pair<>(2,stage2), new Pair<>(3, stage3)));
        
        mapEntities.add( outi );
        
        mapEntities.add( new MapEntity( "bett", 6,4, Direction.DOWN, null, true, null, new EventList( new TurnToPlayer("outi"),
        				new Textbox("Outis: Was suchst du hinter meinem Bett?"), new TurnToPlayer("outi"), new MoveEvent("player", Direction.RIGHT)   )));
	}

}
