package channelpopularity.state;
/**
 *
 * @author Krupa Sawant
 * returns max length for Mildly Popular state
 */
public class MildlyPopularState extends AbstractState{

    @Override
    public int getMaxLength() {
        return 20;
    }

    public String toString() {
        return "Class:MildlyPopularState, Data Members:[]";
    }

}
