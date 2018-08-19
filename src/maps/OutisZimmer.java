package maps;

import java.io.File;
import java.net.MalformedURLException;

import events.EventList;
import events.MapEvent;
import events.Teleport;
import events.Textbox;
import events.TurnToPlayer;
import game.Game;
import javafx.scene.image.Image;

public class OutisZimmer extends game.Map{
	
	public OutisZimmer() throws MalformedURLException{
		super(new File("res/map_outis.txt"));
        tileset = Game.resample (new Image(new File("res/OutiZimmer.png").toURI().toURL().toString()),2);
        
        mapEvents.add(new MapEvent(5, 10, Game.DOWN, null, false, null, new Teleport(1, 4, 8, Game.DOWN)));
        mapEvents.add(new MapEvent(6, 10, Game.DOWN, null, false, null, new Teleport(1, 4, 8, Game.DOWN)));
        
        MapEvent outi = new MapEvent( 8,5, Game.DOWN, Game.resample (new Image(new File("res/Business.png").toURI().toURL().toString()),2));
        outi.actionEvent = new EventList( new TurnToPlayer(outi), new Textbox("Outis: Hier ist mein Zimmer!\n"
        		+ "Hier gibt es gratis Kekse und Kakao!"), new events.TurnEvent(outi, Game.DOWN));
        
        mapEvents.add( outi );
        
	}

}
