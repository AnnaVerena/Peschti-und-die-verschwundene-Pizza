package event;

import game.Game;
import util.Pair;

public class CasesEvent extends GameEvent {
		
	boolean finished;
	String intVarID;
	GameEvent defaultEvent;
	Pair<Integer, GameEvent>[] cases;
	
	@SafeVarargs
	public CasesEvent( String intVarID, GameEvent defaultEvent, Pair<Integer, GameEvent>... cases ) 
	{
		this.intVarID = intVarID;
		this.defaultEvent = defaultEvent;
		this.cases = cases;
	}
	
	public void init() {		
		finished = true;
		
		if( !Game.variables.containsKey(intVarID) ) {
			Game.startEvent(defaultEvent);
			return;
		}
		
		int var = Game.variables.get(intVarID);
		
		for(Pair<Integer,GameEvent> p : cases) {
			if(p._1 == var) {
				Game.startEvent(p._2);
				return;
			}
		}
		
		Game.startEvent(defaultEvent);
	}


	public boolean isFinished() {
		return finished;
	}
}