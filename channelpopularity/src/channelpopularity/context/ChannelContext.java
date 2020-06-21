package channelpopularity.context;

import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelContext implements ContextI {
	private StateI curState;
	private Map<StateName, StateI> availableStates;

	private List<VideoContext> videos;

	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
		// initialize states using factory instance and the provided state names.
		// initialize current state.
		videos = new ArrayList<>();
		availableStates = new HashMap<>();
		for (StateName stateName : stateNames) {
			availableStates.put(stateName, stateFactoryIn.create(stateName));
		}
		curState = availableStates.get(getCurrentState());
	}

	// Called by the States based on their logic of what the machine state should change to.
	public void setCurrentState(StateName nextState) {
		if (availableStates.containsKey(nextState)) { // for safety.
			curState = availableStates.get(nextState);
		}
	}

	private void updatePopularityState() {
		StateName nextState = getCurrentState();
		setCurrentState(nextState);
	}

	public StateName getCurrentState() {
		double popularity = getPopularity();
		if (popularity > 10000)
			return StateName.HIGHLY_POPULAR;

		else
			return StateName.UNPOPULAR;
	}

	public boolean addRequest(int length) {
		return curState.adRequest(length);
	}

	@Override
	public double getPopularity() {
		double popularity = 0.0;
		for (VideoContext video : videos)
			popularity += video.getPopularity();
		return popularity / videos.size();
	}

	public ChannelContext addVideo(String name) {
		VideoContext video = findVideo(name);
		if (video != null) {
			videos.add(new VideoContext(name));
			updatePopularityState();
		}
		return this;
	}

	public ChannelContext removeVideo(String name) {
		VideoContext video = findVideo(name);
		videos.remove(video);
		updatePopularityState();
		return this;
	}


	public ChannelContext updateVideoMetrics(String name, int views, int likes, int dislikes) {
		VideoContext video = findVideo(name);
		video.updateMetrics(likes, dislikes, views);
		updatePopularityState();
		return this;
	}

	private VideoContext findVideo(String name) {
		for (VideoContext video : videos) {
			if (video.getName().equals(name)) {
				return video;
			}
		}
		return null;
	}
}