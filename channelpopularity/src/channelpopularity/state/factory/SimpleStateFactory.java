package channelpopularity.state.factory;

import channelpopularity.state.HighlyPopularState;
import channelpopularity.state.MildlyPopularState;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.UltraPopularState;
import channelpopularity.state.UnpopularState;

/**
 * implements the SimpleStateFactory interface overriding the creating method in order to map enums with every state class
 */
public class SimpleStateFactory implements SimpleStateFactoryI {

    @Override
    public StateI create(Enum event) {
        switch (StateName.valueOf(event.name())) {
            case ULTRA_POPULAR:
                return new UltraPopularState();
            case HIGHLY_POPULAR:
                return new HighlyPopularState();
            case MILDLY_POPULAR:
                return new MildlyPopularState();
            case UNPOPULAR:
                return new UnpopularState();
            default:
                return null;
        }
    }

}
