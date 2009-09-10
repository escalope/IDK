package ingenias.testing;

import java.util.Collection;
import java.util.Vector;

public abstract class EventHandler {

	private State[] nextStates;

	public EventHandler(State[] states) {
		nextStates=states;
	}

	abstract public boolean evaluate(Event event, State fromState);

	public void setNextStates(State[] nextStates) {
		this.nextStates = nextStates;
	}

	public State[] getNextStates() {
		return nextStates;
	}

}
