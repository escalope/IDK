package ingenias.testing;

public interface AssertExpression {

	public boolean canEvaluate();
	public boolean evaluate();
	public String getFailureMessage();
	public String getName();
}
