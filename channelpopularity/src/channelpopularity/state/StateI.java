package channelpopularity.state;

public interface StateI {

    public StateName getCurrentState(double popularity);
    public boolean adRequest(int length);

    public int getMinLength();
    public int getMaxLength();
}
