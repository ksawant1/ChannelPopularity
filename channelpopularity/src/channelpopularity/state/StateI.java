package channelpopularity.state;

public interface StateI {

    public boolean adRequest(int length);
    public int getMinLength();
    public int getMaxLength();
}
