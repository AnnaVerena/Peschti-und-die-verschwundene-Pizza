package maps;

import java.io.File;
import java.net.MalformedURLException;

import events.AnimatedMapEvent;
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
		super(new File("res/map_village_large.txt"));
        tileset = Game.resample (new Image(new File("res/Tileset2.png").toURI().toURL().toString()),2);
        
        MapEvent doorBusiness = new MapEvent(18, 5, Game.DOWN, Game.resample (new Image(new File("res/door.png").toURI().toURL().toString()),2));
        GameEvent teleportBusiness = new EventList( new SetStepEvent(doorBusiness, 1), new WaitEvent(10), new Teleport(0, 9, 12, Game.UP) );
        doorBusiness.actionEvent = teleportBusiness;
        mapEvents.add(doorBusiness);
        
        MapEvent doorOutis = new MapEvent(7, 8, Game.DOWN, Game.resample (new Image(new File("res/door.png").toURI().toURL().toString()),2));
        GameEvent teleportOutis = new EventList( new SetStepEvent(doorOutis, 1), new WaitEvent(10), new Teleport(2, 9, 12, Game.UP) );
        doorOutis.actionEvent = teleportOutis;
        mapEvents.add(doorOutis);
        
        MapEvent waterfall1 = new AnimatedMapEvent(30, 9, Game.DOWN, Game.resample (new Image(new File("res/waterfall.png").toURI().toURL().toString()),2), 10);
        mapEvents.add(waterfall1);
        
        MapEvent waterfall2 = new AnimatedMapEvent(30, 11, Game.UP, Game.resample (new Image(new File("res/waterfall.png").toURI().toURL().toString()),2), 10);
        mapEvents.add(waterfall2);
        
        MapEvent waterfall_left1 = new AnimatedMapEvent(29, 9, Game.DOWN, Game.resample (new Image(new File("res/waterfall_left.png").toURI().toURL().toString()),2), 10);
        mapEvents.add(waterfall_left1);
        
        MapEvent waterfall_left2 = new AnimatedMapEvent(29, 11, Game.UP, Game.resample (new Image(new File("res/waterfall_left.png").toURI().toURL().toString()),2), 10);
        mapEvents.add(waterfall_left2);
        
        MapEvent waterfall_right1 = new AnimatedMapEvent(31, 9, Game.DOWN, Game.resample (new Image(new File("res/waterfall_right.png").toURI().toURL().toString()),2), 10);
        mapEvents.add(waterfall_right1);
        
        MapEvent waterfall_right2 = new AnimatedMapEvent(31, 11, Game.UP, Game.resample (new Image(new File("res/waterfall_right.png").toURI().toURL().toString()),2), 10);
        mapEvents.add(waterfall_right2);
	}
	
	

}