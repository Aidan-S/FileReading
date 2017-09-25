import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	public static Scanner openWords(String fname, int fnum) {
		
		File file = new File(fname);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.print("Part " + fnum + ": Unable to Open File");
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
		int braces = 0;
		while(fname.hasNextLine()){
			String line = fname.nextLine();
			for(int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if(c == '{') {
					braces++;
				}
				if(c == '}') {
					braces--;
				}	
					
			}
			
		}
		
		if(braces == 0) {
			return true;
		}else {
			return false;
		}
	
	}
		
	
	public static boolean compare(Scanner first, Scanner second) {
		while (first.hasNextLine() && second.hasNextLine()) {
			String line1 = first.nextLine();
			String line2 = second.nextLine();
			System.out.println("loop");

			if(!(line1.equals(line2))) {return false;}
			if((first.hasNextLine() && !second.hasNextLine()) || (!first.hasNextLine() && second.hasNextLine())) {return false;}	
		}
		return true;
	}
	
	
	public static void storyTime(Scanner story, ArrayList<String> words) {
		int i = 0;
		while (story.hasNextLine()&&i < words.size()) {
			String line = story.nextLine();

			while (line.indexOf('<') > -1 && line.indexOf('>') > -1) {
				line = line.substring(0, line.indexOf('<')) + words.get(i) + line.substring(line.indexOf('>') + 1);
				i++;
			}
			System.out.println(line);
		}

	}
	
	
	public static ArrayList<String> storyWordsSupp(Scanner in) {
		ArrayList<String> words = new ArrayList<String>();
		while (in.hasNextLine()) {
			words.add(in.nextLine());
		}
		return words;
	}
	
	
	public static ArrayList<String> userInput(Scanner file) {
		ArrayList<String> words = new ArrayList<String>();
		Scanner keyboard = new Scanner(System.in);
		
		while (file.hasNextLine()) {
			String nextline = file.nextLine();

			while (nextline.indexOf('<') > -1 && nextline.indexOf('>') > -1) {

				String word = nextline.substring(nextline.indexOf('<') + 1, nextline.indexOf('>'));
				System.out.println("Insert a " + word);
				String input = keyboard.nextLine();
				words.add(input);
				nextline = nextline.substring(nextline.indexOf('>') + 1);
			}
		}
		file.close();
		keyboard.close();
		return words;
	}
	
	
	public static void main(String[] args) {
		Scanner file1 = openWords(args[0], 1);
		Scanner file2 = openWords(args[1], 2);
		Scanner file3 = openWords(args[2], 3);
		PrintWriter out = outputFile("output.txt");
		
		if (braces(file1)) {
			out.println("Braces Balanced");
		}else{
			out.println("Braces Not Balanced");
		}
		
		out.println();
		
		if (compare(file1, file2)) {
			out.println("Files Identical");
		}else{
			out.println("Files Not Identical");
		}
		
		out.println();
		
		
		if (args.length < 4) {
			ArrayList<String> words = userInput(file3);
			file3 = openWords(args[2], 3);
			storyTime(file3, words);
		}else{
			Scanner file4 = openWords(args[3], 4);
			ArrayList<String> words = storyWordsSupp(file4);
			storyTime(file3, words);
		}
		
		
		file1.close();
		file2.close();
		file3.close();
		
		
		out.close();
		System.out.println("Done");
			
	}


	

}
