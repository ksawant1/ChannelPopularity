package channelpopularity.driver;
import channelpopularity.exceptions.InputFileEmptyException;
import channelpopularity.exceptions.VideoAlreadyExistsException;
import channelpopularity.exceptions.VideoNotFoundException;
import channelpopularity.helper.Helper;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Krupa Sawant
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		*/
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		//inputs a string line and passes it to helper class
        try{
		//System.out.println("Hello World! Lets get started with the assignment");
		FileProcessor fp= new FileProcessor(args[0]);
		File file = new File(args[0]);
		Results resultw= new Results();
		Helper helper= new Helper(resultw);
        boolean empty = file.exists() && file.length() == 0;
        if(empty==true)
	        	throw new InputFileEmptyException("file is empty");

		while (true) {
			String line = fp.poll();
			if (line == null){

				break;}
			if(line != null)
			helper.printOutput(line);}

			resultw.writeToFile();
			resultw.writeToStdout();

        }catch(FileNotFoundException ex){
	        throw new FileNotFoundException("file is missing");
        }catch(IOException ex){
	        throw new IOException("input not right");
        }catch(NullPointerException ex){
	        throw new NullPointerException("input not right");
        }catch(ArrayIndexOutOfBoundsException ex){
	        throw new ArrayIndexOutOfBoundsException("empty input file");
        } catch (VideoNotFoundException e) {
	        e.printStackTrace();
        } catch (VideoAlreadyExistsException e) {
	        e.printStackTrace();
        } catch (InputFileEmptyException e) {
	        e.printStackTrace();
        }

	}

}
