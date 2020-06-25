package channelpopularity.exceptions;

/**
 * exception for video if exists in video context
 * @author Krupa Sawant
 */
public class VideoAlreadyExistsException extends Throwable {
	public VideoAlreadyExistsException(String s){
		super(s);
	}
}
