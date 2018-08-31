package event;

import java.util.Collections;
import java.util.Comparator;

import game.Game;
import game.Map;
import render.Renderer;

public class MapMode extends GameEvent
{
	private Map map;
	private Player player;
	
	public MapMode( Map map )
	{
		this.map = map;
		this.player = Game.player;
	}
	
	public void init()
	{
		Game.map = map;
		
		if( !map.mapEvents.contains(player)) map.mapEvents.add(player);
		
		for( MapEvent x : map.mapEvents)
			   x.init();	
		
		if( map.isWorldMap() ) player.charset = player.charsetSmall;
		else player.charset = player.charsetLarge;
	}
	
	public void render()
	{
		updateCamera();
		Renderer.setView(Game.camera.view);
		
		Renderer.renderMapLayer(map, 0);
		Renderer.renderMapLayer(map,  1);
        
        for( MapEvent x : map.mapEvents) x.render();
        
        Renderer.renderMapLayer(map,  2);
	}
	
	public void update()
	{		
		for( MapEvent x : map.mapEvents)
			   x.update();
				
		Collections.sort(map.mapEvents, new Comparator<MapEvent>() {
            @Override
            public int compare(MapEvent o1, MapEvent o2) {
                return o1.getY() - o2.getY();
            }
        });
	}
	
	private void updateCamera() {
		
		int tx = -player.getScrX()+19*8;
        if( tx > 0 ) tx = 0;
        if(tx < -map.getWidth()*16 + 20*16) tx = -map.getWidth()*16 + 20*16;
        
        int ty = -player.getScrY()+7*16;
        if( ty > 0 ) ty = 0;
        if( ty < -map.getHeight()*16 + 15*16 ) ty = -map.getHeight()*16 + 15*16;
        
        Game.camera.setPos(tx,ty);
	}
	
	public boolean isFinished() {
		return false;
	}
}
