package map;

import java.io.File;
import java.net.MalformedURLException;

import event.AnimatedMapEvent;
import event.EventList;
import event.GameEvent;
import event.MapEvent;
import event.SetFrameEvent;
import event.Teleport;
import event.WaitEvent;
import game.Game;
import game.GameUtil;
import render.Image;

public class VillageMap extends game.Map{
	
	public VillageMap() throws MalformedURLException{
		super(new File("res/maps/map_village_large.txt"));
        tileset = Image.loadImage(new File("res/tilesets/Tileset2.png"));
        
        MapEvent doorBusiness = new MapEvent(18, 5, GameUtil.DOWN, Image.loadImage(new File("res/charsets/door.png")));
        GameEvent teleportBusiness = new EventList( new SetFrameEvent(doorBusiness, 1), new WaitEvent(10), new Teleport(0, 9, 12, GameUtil.UP) );
        doorBusiness.actionEvent = teleportBusiness;
        mapEvents.add(doorBusiness);
        
        MapEvent doorOutis = new MapEvent(7, 8, GameUtil.DOWN, Image.loadImage(new File("res/charsets/door.png")));
        GameEvent teleportOutis = new EventList( new SetFrameEvent(doorOutis, 1), new WaitEvent(10), new Teleport(2, 9, 12, GameUtil.UP) );
        doorOutis.actionEvent = teleportOutis;
        mapEvents.add(doorOutis);
        
        mapEvents.add( new MapEvent(4, 29, GameUtil.DOWN, null, true, null, new EventList( new Teleport(3, 6, 6, GameUtil.DOWN)) ));
        mapEvents.add( new MapEvent(5, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport(3, 6, 6, GameUtil.DOWN)) ));
        mapEvents.add( new MapEvent(6, 29, GameUtil.DOWN, null, true, null, new EventList(  new Teleport(3, 6, 6, GameUtil.DOWN)) ));
        
        MapEvent waterfall1 = new AnimatedMapEvent(30, 9, GameUtil.DOWN, Image.loadImage(new File("res/charsets/waterfall.png")), 10);
        mapEvents.add(waterfall1);
        
        MapEvent waterfall2 = new AnimatedMapEvent(30, 11, GameUtil.UP, Image.loadImage(new File("res/charsets/waterfall.png")), 10);
        mapEvents.add(waterfall2);
        
        MapEvent waterfall_left1 = new AnimatedMapEvent(29, 9, GameUtil.DOWN, Image.loadImage(new File("res/charsets/waterfall_left.png")), 10);
        mapEvents.add(waterfall_left1);
        
        MapEvent waterfall_left2 = new AnimatedMapEvent(29, 11, GameUtil.UP, Image.loadImage(new File("res/charsets/waterfall_left.png")), 10);
        mapEvents.add(waterfall_left2);
        
        MapEvent waterfall_right1 = new AnimatedMapEvent(31, 9, GameUtil.DOWN, Image.loadImage(new File("res/charsets/waterfall_right.png")), 10);
        mapEvents.add(waterfall_right1);
        
        MapEvent waterfall_right2 = new AnimatedMapEvent(31, 11, GameUtil.UP, Image.loadImage(new File("res/charsets/waterfall_right.png")), 10);
        mapEvents.add(waterfall_right2);
	}
	
	

}