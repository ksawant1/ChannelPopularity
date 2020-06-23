package channelpopularity.state;

import static channelpopularity.state.StateName.ULTRA_POPULAR;
import static channelpopularity.state.StateName.HIGHLY_POPULAR;
import static channelpopularity.state.StateName.MILDLY_POPULAR;
import static channelpopularity.state.StateName.UNPOPULAR;

public abstract class AbstractState implements StateI {

	@Override
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
	public int getMinLength() {
		return 1;
	}

	@Override
	public boolean adRequest(int length) {
		return getMinLength() < length && length <= getMaxLength();
	}
}
