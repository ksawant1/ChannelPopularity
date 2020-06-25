package channelpopularity.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	ArrayList<StringBuilder> words= new ArrayList<StringBuilder>();
	ArrayList<Double> metrics= new ArrayList<Double>();
	double metric_value;
	public Results(){
	}

	/**
	 * writetoFile() prints the array list of words and the metrics calculated into the output.txt file
	 */

	public void writeToFile() throws IOException{
		try{
			FileWriter fileWriter = new FileWriter("output.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);

			for (int i=0;i<words.size();i++){
				printWriter.print(words.get(i));

			}



			printWriter.close();
		}catch(IOException ex){
			System.out.println("file not found");
		}
	}
	/**
	 * writeToStdout prints the word array list onto the console along with the metrics.
	 *
	 */
	public void writeToStdout() throws ArrayIndexOutOfBoundsException{
		try{
			for (int i=0;i<words.size();i++){

					System.out.print(words.get(i));

			}


		}catch(ArrayIndexOutOfBoundsException ex){
			throw new ArrayIndexOutOfBoundsException("empty input file");
		}
	}
	/**
	 * stores the rotated words and metrics in arraylist
	 *
	 */
	public void store(StringBuilder word){
		words.add(word);

	}


}
	

