package event_system.map_entities;

import render.Renderer;
import util.Direction;

public class AnimatedMapEntity extends MapEntity {

	int duration;
	int timer;
	int frame;
	

	public AnimatedMapEntity(int posx, int posy, Direction dir, String charSetID, int duration) {
		this("", posx, posy, dir, charSetID, duration);
	}
	
	public AnimatedMapEntity(String eventID, int posx, int posy, Direction dir, String charSetID, int duration) {
		super(eventID, posx, posy, dir, charSetID);
		
		this.duration = duration;
		timer = 0;
		frame = 0;
	}
	
	public void init() {
		timer = 0;
		frame = 0;
	}
	
	public void update() {
		timer++;
		
		if( timer > duration ) {
			timer = 0;
			frame= (frame+1)%3;
		}
	}
	
	public void render()
	{
		Renderer.renderSubImage(charset, frame*16, dir.getDirectionId()*32, 16, 32, posx*16, (posy-1)*16, 16, 32);
	}

}
