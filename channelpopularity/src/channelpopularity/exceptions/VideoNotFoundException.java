package channelpopularity.exceptions;
/**
 * exception for video if not found in video context
 * @author Krupa Sawant
 */

public class VideoNotFoundException extends Throwable {
	public VideoNotFoundException(String s){
		super(s);
	}
}
