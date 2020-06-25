package channelpopularity.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Integer;

import channelpopularity.context.ChannelContext;
import java.util.Arrays;

import channelpopularity.exceptions.VideoAlreadyExistsException;
import channelpopularity.exceptions.VideoNotFoundException;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.Results;

/**
 * Helper is a utility that performs the operations for adding, removing videos calculating metrics and comsiders ad_requests
 *
 * @author Krupa Sawant
 */

public class Helper {

	private static ChannelContext context;
	Results resultw;

	public Helper(Results rp){
		resultw =rp;
	}

	static {
		context = new ChannelContext(new SimpleStateFactory(), Arrays.asList(StateName.values()));
	}
	/**
	 * printOutput matches an enum for required operation and processes the request
	 * @exception VideoAlreadyExistsException if video exists, cannot add the same video
	 * @exception VideoNotFoundException if video not found, cannot remove or ad requests for it
	 * @exception SecurityException On not having necessary read permissions to the input file.
	 * @exception FileNotFoundException On input file not found.
	 * @exception IOException On any I/O errors while reading lines from input file.
	 */

	public void printOutput(String inputLine) throws IOException, VideoAlreadyExistsException, VideoNotFoundException {

		String[] inputs = inputLine.split("::");
		String[] operationParams = inputs[0].split("__");
		String operationString = operationParams[0];
		Operation operation = Operation.valueOf(operationString);


		switch (operation) {
			case ADD_VIDEO:
				addVideo(inputs[1]);
				break;
			case REMOVE_VIDEO:
				removeVideo(inputs[1]);
				break;
			case AD_REQUEST:
				String values[] = inputs[1].split("=");
				try{
				int length = Integer.parseInt(values[1]);
				adRequest(length);
				break;
				}catch(NumberFormatException ex) {
					throw new NumberFormatException("enter an integer value");


			}
			case METRICS:
				String[] params = inputs[1].substring(1, inputs[1].length() -1 ).split(",");
				updateMetrics(operationParams[1], params);
				break;

			default:
				throw new IllegalArgumentException("invalid input format or file is empty");

		}

	}
	/**
	 * calculates metrics for every video based on given likes, views and dislikes
	 */

	public void updateMetrics(String videoName, String[] params) throws IOException {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		try {
			int views = Integer.parseInt(params[0].split("=")[1]);
			int likes = Integer.parseInt(params[1].split("=")[1]);
			int dislikes = Integer.parseInt(params[2].split("=")[1]);
			StateName prevState = context.getCurrentState();
			context = context.updateVideoMetrics(videoName, views, likes, dislikes);
			//System.out.println(prevState + "_POPULARITY_SCORE_UPDATE::" + context.getPopularity());
			result.append(prevState);
			result.append( "__POPULARITY_SCORE_UPDATE::" );
			result.append(context.getPopularity());
			result.append(newLine);
			resultw.store(result);
		}catch(NumberFormatException | VideoNotFoundException ex) {
			throw new NumberFormatException("enter an integer value");
		}


	}
	/**
	 * decides whether the ad should be approved or rejected for every video based on given length
	 */

	public void adRequest(int length) throws IOException {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		StateName prevState = context.getCurrentState();
		String status = context.addRequest(length) ? "APPROVED" : "REJECTED";
		//System.out.println(prevState + "_AD_REQUEST::" + status);
		result.append(prevState);
		result.append( "__AD_REQUEST::" );
		result.append(status);
		result.append(newLine);
		resultw.store(result);
	}
	/**
	 * removes given video by it's name
	 */

	public void removeVideo(String videoName) throws IOException, VideoNotFoundException {

		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		StateName prevState = context.getCurrentState();
		context = context.removeVideo(videoName);
		//System.out.println(prevState + "_VIDEO_REMOVED::" + videoName);
		result.append(prevState);
		result.append( "__VIDEO_REMOVED::" );
		result.append(videoName);
		result.append(newLine);
		resultw.store(result);
	}
	/**
	 * adds given video by it's name
	 */

	public void addVideo(String videoName) throws IOException, VideoAlreadyExistsException {
		Results results=new Results();
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		StateName prevState = context.getCurrentState();
		context = context.addVideo(videoName);
		//System.out.println(prevState + "_ADD_VIDEO::" + videoName);
		result.append(prevState);
		result.append( "__VIDEO_ADDED::" );
		result.append(videoName);
		result.append(newLine);
		resultw.store(result);
	}

	@Override
	public String toString() {
		return "Helper{" +
				"resultw=" + resultw +
				'}';
	}
}