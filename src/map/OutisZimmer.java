package map;

import java.io.File;
import java.net.MalformedURLException;

import event.CasesEvent;
import event.Choicebox;
import event.EventList;
import event.GameEvent;
import event.MapEvent;
import event.MoveEvent;
import event.SetVariableEvent;
import event.Teleport;
import event.Textbox;
import event.TurnEvent;
import event.TurnToPlayer;
import event.WaitEvent;
import game.Game;
import game.GameUtil;
import javafx.util.Pair;

public class OutisZimmer extends game.Map{
	
	public OutisZimmer() throws MalformedURLException{
		super(new File("res/maps/map_outis.txt"));
        tileset = Game.tilesets.get("outi_room");        
        
        mapEvents.add( new MapEvent("teleport1", 9, 12, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 7, 9, GameUtil.DOWN), new WaitEvent(10))));
        mapEvents.add( new MapEvent("teleport2", 10, 12, GameUtil.DOWN, null, true, null, new EventList( new Teleport("village", 7, 9, GameUtil.DOWN), new WaitEvent(10))));
        
        MapEvent outi = new MapEvent("outis",  11,4, GameUtil.DOWN, "business");
        
        GameEvent stage0 = new EventList( new TurnToPlayer(outi), 
        				new Textbox("Outis: Hier ist mein Zimmer!\n"
        							+ "Hier gibt es gratis Kakao!"), 
        				new Choicebox("Outis: Kannst du mir vielleicht\nKekse kaufen gehen?", 
        						new EventList( new Textbox("Vielen Dank!\nDer Business verkauft Kekse."), new Textbox("Outis gibt Peschti 1$."), new SetVariableEvent("KEKSE", 1)),
        						new Textbox("Outis: Das ist aber schade.")),
        				new event.TurnEvent(outi, GameUtil.DOWN));
        
        GameEvent stage1 = new EventList( new TurnToPlayer(outi), 
				new Textbox("Outis: Hier ist mein Zimmer!\n"
						+ "Hier gibt es gratis Kakao!"));
        
        GameEvent stage2 = new EventList( new TurnToPlayer(outi), 
        				new Textbox("Peschti gibt Outis die Kekse"),
        				new Textbox("Outis: Vielen Dank für die Kekse!"),
        				new SetVariableEvent( "KEKSE", 3),
        				new TurnEvent(outi, GameUtil.DOWN) );
        
        GameEvent stage3 = new EventList( new TurnToPlayer(outi), 
				new Textbox("Outis: Hier ist mein Zimmer!\n"
						+ "Hier gibt es gratis Kakao und Kekse!"));
        				
        
        outi.actionEvent = new CasesEvent( "KEKSE", stage0, new Pair(1,stage1), new Pair(2,stage2), new Pair(3, stage3));
        
        mapEvents.add( outi );
        
        mapEvents.add( new MapEvent( "bett", 6,4, GameUtil.DOWN, null, true, null, new EventList( new TurnEvent(outi, GameUtil.LEFT),
        				new Textbox("Outis: Was suchst du hinter meinem Bett?"), new TurnEvent(outi, GameUtil.DOWN), new MoveEvent("player", GameUtil.RIGHT)   )));
	}

}
