package map;

import java.io.File;
import java.net.MalformedURLException;

import event.AnimatedMapEvent;
import event.EventList;
import event.GameEvent;
import event.MapEvent;
import event.Teleport;
import event.WaitEvent;
import game.Game;
import game.GameUtil;
import render.Image;

public class VillageMap extends game.Map{
	
	public VillageMap() throws MalformedURLException{
		super(new File("res/maps/map_village_large.txt"));
        tileset = Game.tilesets.get("village");
        
        MapEvent doorBusiness = new MapEvent(18, 5, GameUtil.DOWN, "door");
        GameEvent teleportBusiness = new EventList( new Teleport("shop", 9, 12, GameUtil.UP) );
        doorBusiness.actionEvent = teleportBusiness;
        mapEvents.add(doorBusiness);
        
        MapEvent doorOutis = new MapEvent(7, 8, GameUtil.DOWN, "door");
        GameEvent teleportOutis = new EventList( new WaitEvent(10), new Teleport("outi_room", 9, 12, GameUtil.UP) );
        doorOutis.actionEvent = teleportOutis;
        mapEvents.add(doorOutis);
        
        mapEvents.add( new MapEvent(4, 29, GameUtil.DOWN, null, true, null, new EventList( new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        mapEvents.add( new MapEvent(5, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        mapEvents.add( new MapEvent(6, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport("world", 6, 6, GameUtil.DOWN)) ));
        
        MapEvent waterfall1 = new AnimatedMapEvent(30, 9, GameUtil.DOWN, "waterfall", 10);
        mapEvents.add(waterfall1);
        
        MapEvent waterfall2 = new AnimatedMapEvent(30, 11, GameUtil.UP, "waterfall", 10);
        mapEvents.add(waterfall2);
        
        MapEvent waterfall_left1 = new AnimatedMapEvent(29, 9, GameUtil.DOWN, "waterfall_left", 10);
        mapEvents.add(waterfall_left1);
        
        MapEvent waterfall_left2 = new AnimatedMapEvent(29, 11, GameUtil.UP, "waterfall_left", 10);
        mapEvents.add(waterfall_left2);
        
        MapEvent waterfall_right1 = new AnimatedMapEvent(31, 9, GameUtil.DOWN, "waterfall_right", 10);
        mapEvents.add(waterfall_right1);
        
        MapEvent waterfall_right2 = new AnimatedMapEvent(31, 11, GameUtil.UP, "waterfall_right", 10);
        mapEvents.add(waterfall_right2);
	}
	
	

}