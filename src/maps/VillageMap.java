package maps;

import java.io.File;
import java.net.MalformedURLException;

import events.EventList;
import events.GameEvent;
import events.MapEvent;
import events.SetStepEvent;
import events.Teleport;
import events.WaitEvent;
import game.Game;
import javafx.scene.image.Image;

public class VillageMap extends game.Map{
	
	public VillageMap() throws MalformedURLException{
		super(new File("res/map_village.txt"));
        tileset = Game.resample (new Image(new File("res/Tileset2.png").toURI().toURL().toString()),2);
        
        MapEvent doorBusiness = new MapEvent(13, 5, Game.DOWN, Game.resample (new Image(new File("res/door.png").toURI().toURL().toString()),2));
        GameEvent teleportBusiness = new EventList( new SetStepEvent(doorBusiness, 1), new WaitEvent(10), new Teleport(0, 9, 12, Game.UP) );
        doorBusiness.actionEvent = teleportBusiness;
        mapEvents.add(doorBusiness);
        
        MapEvent doorOutis = new MapEvent(4, 7, Game.DOWN, Game.resample (new Image(new File("res/door.png").toURI().toURL().toString()),2));
        GameEvent teleportOutis = new EventList( new SetStepEvent(doorOutis, 1), new WaitEvent(10), new Teleport(2, 9, 12, Game.UP) );
        doorOutis.actionEvent = teleportOutis;
        mapEvents.add(doorOutis);
	}
	
	

}