package event_system;

import game.Game;

public class SetVariableEvent extends GameEvent {
		
	boolean finished;
	String intVarID;
	int value;
	
	
	public SetVariableEvent( String intVarID, int value ) 
	{
		this.intVarID = intVarID;
		this.value = value;
	}
	
	public void init() {		
		finished = true;
		
		Game.variables.put(intVarID,  value);
	}
	
	public void update() {
	}
	
	
	
	public boolean isFinished() {
		return finished;
	}
}