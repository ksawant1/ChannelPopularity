package channelpopularity.state.factory;

import channelpopularity.state.StateI;

public interface SimpleStateFactoryI {
    StateI create(Enum event);
}
