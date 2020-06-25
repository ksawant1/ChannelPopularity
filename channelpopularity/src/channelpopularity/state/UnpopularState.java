package channelpopularity.state;
/**
 *
 * @author Krupa Sawant
 * returns max length for UnPopular state
 */

public class UnpopularState extends AbstractState {

    @Override
    public int getMaxLength() {
        return 10;
    }
    @Override
    public String toString() {
        return "Class:UnpopularState, Data Members:[]";
    }
}
