package channelpopularity.state;

/**
 * @author Krupa Sawant
 * state inteface for common methods
 */
public interface StateI {

    public StateName getCurrentState(double popularity);
    public boolean adRequest(int length);

    public int getMinLength();
    public int getMaxLength();
}
