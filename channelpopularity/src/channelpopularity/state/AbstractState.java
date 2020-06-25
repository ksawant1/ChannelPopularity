package channelpopularity.state;

import static channelpopularity.state.StateName.ULTRA_POPULAR;
import static channelpopularity.state.StateName.HIGHLY_POPULAR;
import static channelpopularity.state.StateName.MILDLY_POPULAR;
import static channelpopularity.state.StateName.UNPOPULAR;

/**
 * defines all the common methods for Channel Context
 * @author Krupa Sawant
 */
public abstract class AbstractState implements StateI {

	@Override
	/**
	 * returns current state based on popularity range
	 */
	public StateName getCurrentState(double popularity) {

		if (popularity > 100000)
			return ULTRA_POPULAR;
		if (popularity > 10000)
			return HIGHLY_POPULAR;
		if (popularity > 1000)
			return MILDLY_POPULAR;
		else
			return UNPOPULAR;
	}

	@Override
	/**
	 * minimum length for adrequest will always be 1 for every channel state
	 */
	public int getMinLength() {
		return 1;
	}

	@Override
	public boolean adRequest(int length) {
		return getMinLength() < length && length <= getMaxLength();
	}




}
