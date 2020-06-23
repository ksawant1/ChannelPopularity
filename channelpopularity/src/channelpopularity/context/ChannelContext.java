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
	private double popularity;

	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
		// initialize states using factory instance and the provided state names.
		// initialize current state.
		videos = new ArrayList<>();
		availableStates = new HashMap<>();
		for (StateName stateName : stateNames) {
			availableStates.put(stateName, stateFactoryIn.create(stateName));
		}
		curState = availableStates.get(StateName.UNPOPULAR);
	}

	// Called by the States based on their logic of what the machine state should change to.
	public void setCurrentState(StateName nextState) {
		if (availableStates.containsKey(nextState)) { // for safety.
			curState = availableStates.get(nextState);
		}
	}

	public StateName getCurrentState() {
		return curState.getCurrentState(popularity);
	}

	private void updateState() {
		calcPopularity();
		setCurrentState(getCurrentState());
	}

	public boolean addRequest(int length) {
		return curState.adRequest(length);
	}

	public ChannelContext addVideo(String name) {
		VideoContext video = findVideo(name);
		if (video == null) {
			videos.add(new VideoContext(name));
			updateState();
		}
		// raise VideoAlreadyExistsException if not null
		return this;
	}

	public ChannelContext removeVideo(String name) {
		VideoContext video = findVideo(name);
		// raise VideoNotFoundException if  null
		videos.remove(video);
		updateState();
		return this;
	}


	public ChannelContext updateVideoMetrics(String name, int views, int likes, int dislikes) {
		VideoContext video = findVideo(name);
		// raise VideoNotFoundException if  null
		video.updateMetrics(views, likes, dislikes);
		updateState();
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

	@Override
	public void calcPopularity() {
		double sum = 0.0;
		for (VideoContext video : videos) {
			video.calcPopularity();
			sum += video.getPopularity();
		}
		popularity = sum / videos.size();
		//popularity = videos.stream().mapToDouble(video -> calcVideoPopularity(video)).average().orElse(0.0);
	}

	public double getPopularity() {
		return popularity;
	}
}