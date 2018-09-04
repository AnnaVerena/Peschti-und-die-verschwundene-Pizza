package event;

import render.Renderer;

public class AnimatedMapEvent extends MapEvent{

	int duration;
	int timer;
	int frame;
	

	public AnimatedMapEvent(int posx, int posy, int dir, String charSetID, int duration) {
		this("", posx, posy, dir, charSetID, duration);
	}
	
	public AnimatedMapEvent(String eventID, int posx, int posy, int dir, String charSetID, int duration) {
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
		Renderer.renderSubImage(charset, frame*16, dir*32, 16, 32, posx*16, (posy-1)*16, 16, 32);
	}

}
