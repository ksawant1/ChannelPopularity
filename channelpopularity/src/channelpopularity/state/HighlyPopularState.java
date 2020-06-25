package channelpopularity.state;

public class HighlyPopularState extends AbstractState {
    /**
     *
     * @author Krupa Sawant
     * returns max length for Highly Popular state
     */
    @Override
    public int getMaxLength() {
        return 30;
    }

    @Override
    public String toString() {
        return "Class:HighlyPopularState, Data Members:[]";
    }
}
