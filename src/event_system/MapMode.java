package event_system;

import java.util.Collections;
import java.util.Comparator;

import event_system.control_flow.EventList;
import event_system.map_control.WaitForMapEntity;
import event_system.map_entities.MapEntity;
import event_system.map_entities.Player;
import game.Game;
import game.GameUtil;
import game.Map;
import render.Renderer;

public class MapMode extends GameEvent
{
	private Map map;
	private Player player;
	
	private Comparator<MapEntity> comparator = new Comparator<MapEntity>() {
        @Override
        public int compare(MapEntity o1, MapEntity o2) {
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
		if( !map.mapEntities.contains(player)) map.mapEntities.add(player);
		
		for( MapEntity x : map.mapEntities)
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
        
        for( MapEntity x : map.mapEntities) x.render();
        
        Renderer.renderMapLayer(map,  2);
	}
	
	public void update()
	{		
		handleInputs();
		for( MapEntity x : map.mapEntities)
			   x.update();
				
		Collections.sort(map.mapEntities, comparator);
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
			
			for(MapEntity me: Game.map.mapEntities)
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
			
			if( dir != -1 )
			{
				player.setDirection(dir);
				player.tryToMove(dir);
			}
			
			if( !player.isFinished() ) {				
				for(MapEntity me: Game.map.mapEntities)
				{
					if( me.getX() == player.getX() && me.getY() == player.getY() && me.belowPlayer && me.touchEvent != null)
					{
						Game.startEvent(new EventList( new WaitForMapEntity("player"), me.touchEvent));
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
