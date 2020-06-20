package channelpopularity.driver;
import channelpopularity.context.Context2;
import channelpopularity.helper.Helper;
import channelpopularity.util.FileProcessor;
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
		System.out.println("Hello World! Lets get started with the assignment");
		FileProcessor fp= new FileProcessor(args[0]);
		String nextword;
		
		Helper helper=new Helper();
		while(true){
		nextword=fp.poll();
		if(nextword==null)
		break;
		helper.printinput(nextword);}
		Context2 context = new Context2();
		//context.addvideo();
	}
}
