package channelpopularity.state;
/**
 *
 * @author Krupa Sawant
 * returns max length for Ultra Popular state
 */

public class UltraPopularState extends AbstractState{

    @Override
    public int getMaxLength() {
        return 40;
    }
    @Override
    public String toString() {
        return "Class:UltraPopularState, Data Members:[]";
    }
}
