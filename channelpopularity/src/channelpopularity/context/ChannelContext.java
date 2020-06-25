package channelpopularity.context;

import channelpopularity.exceptions.VideoNotFoundException;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.exceptions.VideoAlreadyExistsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Channel Context is a utility to be used every time we need to update popularity score based on given action
 *
 *  @author Krupa Sawant
 */
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
	/**
	 * returns current state based on popularity
	 */

	public StateName getCurrentState() {
		return curState.getCurrentState(popularity);
	}

	/**
	 * updates state every time popularity score gets updated
	 */
	private void updateState() {
		calcPopularity();
		setCurrentState(getCurrentState());
	}
	/**
	 * returns true or false based on whether ad request approved or rejected
	 */
	public boolean addRequest(int length) {
		return curState.adRequest(length);
	}

	/**
	 * returns type Channel Context based on whether video added or not
	 */

	public ChannelContext addVideo(String name) throws VideoAlreadyExistsException {
		VideoContext video = findVideo(name);
		if (video == null) {
			videos.add(new VideoContext(name));
			updateState();
		}
		else throw new VideoAlreadyExistsException("cannot add video as it already exists");
		return this;
	}
	/**
	 * returns type channel context based on whetther video removed or not
	 */

	public ChannelContext removeVideo(String name) throws VideoNotFoundException {
		VideoContext video = findVideo(name);
		// raise VideoNotFoundException if  null
		if (video != null) {

			videos.remove(video);
			updateState();
		}
		else throw  new VideoNotFoundException("cannot remove video as it cannot be found");
		return this;
	}
	/**
	 * returns type channel context after calc popularity score of particular video
	 */

	public ChannelContext updateVideoMetrics(String name, int views, int likes, int dislikes) throws VideoNotFoundException {
		VideoContext video = findVideo(name);
		// raise VideoNotFoundException if  null
		if (video != null) {
			video.updateMetrics(views, likes, dislikes);
			updateState();
		}
		else throw  new VideoNotFoundException("cannot remove video as it cannot be found");
		return this;
	}
	/**
	 * returns type Video Context after finding video in video context
	 */

	private VideoContext findVideo(String name) {
		for (VideoContext video : videos) {
			if (video.getName().equals(name)) {
				return video;
			}
		}
		return null;
	}
	/**
	 * calculates popularity score
	 */

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

	@Override
	public String toString() {
		return "ChannelContext{" +
				"curState=" + curState +
				", availableStates=" + availableStates +
				", videos=" + videos +
				", popularity=" + popularity +
				'}';
	}
}