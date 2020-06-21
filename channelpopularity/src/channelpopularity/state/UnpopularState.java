package channelpopularity.state;

public class UnpopularState extends AbstractState {

    @Override
    public int getMaxLength() {
        return 10;
    }

}
