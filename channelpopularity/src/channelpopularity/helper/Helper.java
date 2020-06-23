package channelpopularity.helper;

import java.lang.Integer;

import channelpopularity.context.ChannelContext;

import java.util.Arrays;

import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;



public class Helper {

	private static ChannelContext context;

	static {
		context = new ChannelContext(new SimpleStateFactory(), Arrays.asList(StateName.values()));
	}

	public static void printOutput(String inputLine) {

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
				int length = Integer.parseInt(values[1]);
				adRequest(length);
				break;
			case METRICS:
				String[] params = inputs[1].substring(1, inputs[1].length() -1 ).split(",");
				updateMetrics(operationParams[1], params);
		}

	}

	private static void updateMetrics(String videoName, String[] params) {

		int views = Integer.parseInt(params[0].split("=")[1]);
		int likes = Integer.parseInt(params[1].split("=")[1]);
		int dislikes = Integer.parseInt(params[2].split("=")[1]);

		StateName prevState = context.getCurrentState();
		context = context.updateVideoMetrics(videoName, views, likes, dislikes);
		System.out.println(prevState + "_POPULARITY_SCORE_UPDATE::" + context.getPopularity());
	}

	private static void adRequest(int length) {
		StateName prevState = context.getCurrentState();
		String status = context.addRequest(length) ? "APPROVED" : "REJECTED";
		System.out.println(prevState + "_AD_REQUEST::" + status);
	}

	private static void removeVideo(String videoName) {
		StateName prevState = context.getCurrentState();
		context = context.removeVideo(videoName);
		System.out.println(prevState + "_VIDEO_REMOVED::" + videoName);
	}

	private static void addVideo(String videoName) {
		StateName prevState = context.getCurrentState();
		context = context.addVideo(videoName);
		System.out.println(prevState + "_ADD_VIDEO::" + videoName);
	}
}