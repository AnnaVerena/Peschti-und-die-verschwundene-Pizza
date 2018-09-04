package event;

import java.util.Collections;
import java.util.Comparator;

import game.Game;
import game.GameUtil;
import game.Map;
import render.Renderer;

public class MapMode extends GameEvent
{
	private Map map;
	private Player player;
	
	private Comparator<MapEvent> comparator = new Comparator<MapEvent>() {
        @Override
        public int compare(MapEvent o1, MapEvent o2) {
            return o1.getY() - o2.getY();
        }
    };
	
	public MapMode()
	{
		this.map = Game.map;
		this.player = Game.player;
	}
	
	public void init()
	{
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
		handleInputs();
		for( MapEvent x : map.mapEvents)
			   x.update();
				
		Collections.sort(map.mapEvents, comparator);
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
	
	private void handleInputs() {
		if(Game.inputs.contains("A"))
		{
			Game.inputs.remove("A");
			
			int x = player.getX();
			if(player.getDirection() == GameUtil.RIGHT) x++;
			if(player.getDirection() == GameUtil.LEFT) x--;
			int y = player.getY();
			if(player.getDirection() == GameUtil.UP) y--;
			if(player.getDirection() == GameUtil.DOWN) y++;
			
			for(MapEvent me: Game.map.mapEvents)
			{
				if( me.getX() == x && me.getY() == y && me.actionEvent != null)
				{
					Game.startEvent(me.actionEvent);
					break;
				}
			}
		}
		if( player.isFinished() ) //Player bewegt sich nicht
		{		
			int dir = -1;
			for( String tmp : Game.inputs)
			{
				if( tmp.equals("LEFT")) dir = GameUtil.LEFT;
				else if( tmp.equals("RIGHT")) dir = GameUtil.RIGHT;
				else if( tmp.equals("UP")) dir = GameUtil.UP;
				else if( tmp.equals("DOWN")) dir = GameUtil.DOWN;
			}
			
			if( dir != -1 ) player.tryToMove(dir);
			
			if( !player.isFinished() ) {				
				for(MapEvent me: Game.map.mapEvents)
				{
					if( me.getX() == player.getX() && me.getY() == player.getY() && me.belowPlayer && me.touchEvent != null)
					{
						Game.startEvent(new EventList( new WaitForMapEvent("player"), me.touchEvent));
						break;
					}
				}
			}
		}
	}
	
	public boolean isFinished() {
		return false;
	}
}
