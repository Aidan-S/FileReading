import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author scannella
 *
 */
public class FileReader {

	/**
	 * @param args
	 */
	public static Scanner openWords(String fname) {
		
		File file = new File(fname);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.println("Part 1: Unable to Open File");
			return null;
		}
		

		return input;
		
	}
	
	public static boolean braces(Scanner in) {
		int numB = 0;
		while(in.hasNextLine()) {
			
		}
		
	
	}
	
	
	
	public static void main(String[] args) {
		
		
		Scanner file1 = openWords(args[0]);
		Scanner file2 = openWords(args[1]);
		Scanner file3 = openWords(args[2]);
		
		
		
		
		file1.close();
		file2.close();
		file3.close();
			
	}

}
