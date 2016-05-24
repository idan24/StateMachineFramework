package framework;
/**
 *
 *
 */
public abstract class State implements java.io.Serializable {
	public abstract State doAction(Event event);
}
