package map;

import java.io.File;
import java.net.MalformedURLException;

import event.EventList;
import event.MapEvent;
import event.Teleport;
import event.Textbox;
import event.TurnEvent;
import event.TurnToPlayer;
import event.WaitEvent;
import game.Game;
import game.GameUtil;

public class OutisZimmer extends game.Map{
	
	public OutisZimmer() throws MalformedURLException{
		super(new File("res/maps/map_outis.txt"));
        tileset = Game.tilesets.get("outi_room");        
        
        mapEvents.add(new MapEvent(9, 13, GameUtil.DOWN, null, false, null, new EventList( new Teleport("village", 7, 9, GameUtil.DOWN), new WaitEvent(10))));
        mapEvents.add(new MapEvent(10, 13, GameUtil.DOWN, null, false, null, new EventList( new Teleport("village", 7, 9, GameUtil.DOWN), new WaitEvent(10))));
        
        MapEvent outi = new MapEvent( 11,4, GameUtil.DOWN, "business");
        outi.actionEvent = new EventList( new TurnToPlayer(outi), new Textbox("Outis: Hier ist mein Zimmer!\n"
        		+ "Hier gibt es gratis Kekse und Kakao!"), new event.TurnEvent(outi, GameUtil.DOWN));
        
        mapEvents.add( outi );
        
        mapEvents.add( new MapEvent( 6,4, GameUtil.DOWN, null, true, null, new EventList( new TurnEvent(outi, GameUtil.LEFT),
        				new Textbox("Outis: Was suchst du hinter meinem Bett?"), new TurnEvent(outi, GameUtil.DOWN)  )));
	}

}
