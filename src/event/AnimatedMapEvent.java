package event;

import render.Image;

public class AnimatedMapEvent extends MapEvent{

	int duration;
	int timer;
	
	public AnimatedMapEvent(int posx, int posy, int dir, Image charSet, int duration) {
		super(posx, posy, dir, charSet);
		
		this.duration = duration;
		timer = 0;
	}
	
	public void init() {
		timer = 0;
	}
	
	public void update() {
		timer++;
		
		if( timer > duration ) {
			timer = 0;
			frame++;
			frame %= 3;
		}
	}

}