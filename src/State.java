/**
 * @author Mmm
 *
 */
public abstract class State implements java.io.Serializable {
	public abstract void doAction(Machine machine, Event event);
}
