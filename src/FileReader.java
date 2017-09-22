import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	
	public static PrintWriter outputFile(String fName) {
			File fileName = new File(fName);

			PrintWriter output = null;

			try {
				output = new PrintWriter(fileName);
			} catch (FileNotFoundException ex) {
				System.out.print("Cannot open " + fName);
				return null;

			}

			return output;
	}
	
	public static boolean braces(Scanner fname) {
		int numB = 0;
		
		
		
	
	}
		
	public static boolean compare(Scanner first, Scanner second) {
		int fLines = 0;
		while(first.hasNextLine()) {
			fLines++;
		}
		int sLines = 0;
		while(second.hasNextLine()) {
			sLines++;
		}
		if(fLines != sLines){return false;}
		
		while (first.hasNextLine() && second.hasNextLine()) {
			String line1 = first.nextLine();
			String line2 = second.nextLine();
			if(!(line1.equals(line2))) {return false;}
		}
		
		
		return true;
	}
	
	public static boolean storyTime(Scanner in) {
		
		
	return false;
	}
	
	public static void main(String[] args) {
		Scanner file1 = openWords(args[0]);
		Scanner file2 = openWords(args[1]);
		Scanner file3 = openWords(args[2]);
		PrintWriter out = outputFile("output.txt");
		
		if (braces(file1))
			out.println("Braces Balanced");
		else
			out.println("Braces Not Balanced");
		
		out.println();
		
		if (compare(file1, file2))
			out.println("Files Identical");
		else
			out.println("Files Not Identical");
		
		out.println();
		
		storyTime(file3);
		
		file1.close();
		file2.close();
		file3.close();
		
		out.close();
			
	}


	

}
