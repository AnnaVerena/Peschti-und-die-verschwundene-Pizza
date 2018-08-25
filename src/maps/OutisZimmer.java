package maps;

import java.io.File;
import java.net.MalformedURLException;

import events.EventList;
import events.MapEvent;
import events.Teleport;
import events.Textbox;
import events.TurnToPlayer;
import events.WaitEvent;
import game.Game;
import game.GameUtil;
import javafx.scene.image.Image;

public class OutisZimmer extends game.Map{
	
	public OutisZimmer() throws MalformedURLException{
		super(new File("res/map_outis.txt"));
        tileset = GameUtil.resample (new Image(new File("res/OutiZimmer.png").toURI().toURL().toString()),2);
        
        mapEvents.add(new MapEvent(9, 13, GameUtil.DOWN, null, false, null, new EventList( new Teleport(1, 7, 9, GameUtil.DOWN), new WaitEvent(10))));
        mapEvents.add(new MapEvent(10, 13, GameUtil.DOWN, null, false, null, new EventList( new Teleport(1, 7, 9, GameUtil.DOWN), new WaitEvent(10))));
        
        MapEvent outi = new MapEvent( 11,4, GameUtil.DOWN, GameUtil.resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        outi.actionEvent = new EventList( new TurnToPlayer(outi), new Textbox("Outis: Hier ist mein Zimmer!\n"
        		+ "Hier gibt es gratis Kekse und Kakao!"), new events.TurnEvent(outi, GameUtil.DOWN));
        
        mapEvents.add( outi );
        
	}

}
