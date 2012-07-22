package ingenias.jade.comm;

public interface StateBehaviorChangesListener {
	public void stateTransitionExecuted(String fromState, String toState);
	public void protocolFinished();
	public void protocolStarted();
}
