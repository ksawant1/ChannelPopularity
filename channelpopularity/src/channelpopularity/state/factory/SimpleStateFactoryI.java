package channelpopularity.state.factory;

import channelpopularity.state.StateI;

/**
 * create method defined in interface
 */
public interface SimpleStateFactoryI {
    StateI create(Enum event);
}
