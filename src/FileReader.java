import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author scannella
 *
 */
public class FileReader {

	/**
	 * 
	 * @param fname is the name of the file that will be opened
	 * @param fnum is what number step the file is for, used in error statement
	 * @param out printwriter for outputting error
	 * @return
	 */
	public static Scanner openWords(String fname, int fnum, PrintWriter out) {
		//make a scanner with the given file but if there's an issue return null instead
		File file = new File(fname);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException ex) {
			out.println("Part " + fnum + ": Unable to Open File");
			return null;
		}
		return input;	
	}
	
	/**
	 * 
	 * @param fName name
	 * @return the printwriter i will be printing to
	 */
	public static PrintWriter outputFile(String fName) {
		//make a printwritter with the given file but if there's an issue return null instead	
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
	
	/**
	 * 
	 * @param fname name of the file to be used
	 * @return whether or not the braces are balances
	 */
	public static boolean braces(Scanner fname) {
		//count braces; char for going 1 char at a time
		int braces = 0;
		char c;
		while(fname.hasNextLine()){
			String line = fname.nextLine();
			for(int i = 0; i < line.length(); i++) {
				c = line.charAt(i);
				if(c == '{') {
					braces++;
				}
				if(c == '}') {
					braces--;
				}	
				//if braces is negative that means that there is a closed bracket without an open, therefore unbalanced
				if(braces < 0) {return false;}
			}	
		}
		//balanced if braces is 0
		if(braces == 0) {
			return true;
		}else {
			return false;
		}
	}
		
	/**
	 * 
	 * @param first, first file to be compared
	 * @param second, second file to be compared
	 * @return
	 */
	public static boolean compare(Scanner first, Scanner second) {
		//go line by line comparing each line as a string
		while (first.hasNextLine() && second.hasNextLine()) {
			String line1 = first.nextLine();
			String line2 = second.nextLine();
			//if 1 has more lines than the other then not the same
			if(!(line1.equals(line2))) {return false;}
			if((first.hasNextLine() && !second.hasNextLine()) || (!first.hasNextLine() && second.hasNextLine())) {return false;}	
		}
		return true;
	}
	
	/**
	 * 
	 * @param story file containing the story with <>
	 * @param out printwriter to print to
	 * @param words array list of words to use
	 */
	public static void storyTime(Scanner story, PrintWriter out, ArrayList<String> words) {
		int i = 0;
		while (story.hasNextLine() && i < words.size()) {
			String line = story.nextLine();
			//take line as a string and then substring in words from array list
			while (line.indexOf('<') >= 0 && line.indexOf('>') >= 0) {
				line = line.substring(0, line.indexOf('<')) + words.get(i) + line.substring(line.indexOf('>') + 1);
				i++;
			}
			//print out line by line
			out.println(line);
		}
	}
	
	/**
	 * 
	 * @param in scanner with words that will be used
	 * @return array list of the words
	 */
	public static ArrayList<String> storyWordsSupp(Scanner fname) {
		//take it 1 line/word/statement at a time and add into array list
		ArrayList<String> words = new ArrayList<String>();
		while (fname.hasNextLine()) {
			words.add(fname.nextLine());
		}
		return words;
	}
	
	/**
	 * 
	 * @param file with <> where to find out what words are needed
	 * @return arraylist with words to use
	 */
	public static ArrayList<String> userInput(Scanner fname) {
		ArrayList<String> words = new ArrayList<String>();
		Scanner keyboard = new Scanner(System.in);
		
		while (fname.hasNextLine()) {
			String nextline = fname.nextLine();
			//substring out words inbetween <> and then ask for them
			while (nextline.indexOf('<') > -1 && nextline.indexOf('>') > -1) {
				String word = nextline.substring(nextline.indexOf('<') + 1, nextline.indexOf('>'));
				System.out.println("Insert a " + word);
				String input = keyboard.nextLine();
				words.add(input);
				nextline = nextline.substring(nextline.indexOf('>') + 1);
			}
		}
		//close scanners and return
		keyboard.close();
		return words;
	}
	
	/**
	 * 
	 * @param args Main runner Method
	 */
	public static void main(String[] args) {
		//get ready to read and write
		PrintWriter out = outputFile("output.txt");
		Scanner file1 = openWords(args[0], 1, out);
		//Part 1 check braces
		if (braces(file1)) {
			out.println("Braces Balanced");
		}else{
			out.println("Braces Not Balanced");
		}
		out.println();
		//Part 2 compare files
		Scanner file2 = openWords(args[1], 2, out);
		if (compare(file1, file2)) {
			out.println("Files Identical");
		}else{
			out.println("Files Not Identical");
		}
		out.println();
		//Part 3 Story time
		Scanner file3 = openWords(args[2], 3, out);
		if (args.length < 4) {
			ArrayList<String> words = userInput(file3);
			file3 = openWords(args[2], 3, out);
			storyTime(file3, out, words);
		}else{
			//if 4+ files then Step 4 read in words from file for story
			Scanner file4 = openWords(args[3], 3, out);
			ArrayList<String> words = storyWordsSupp(file4);
			storyTime(file3, out, words);
			file4.close();
		}
		
		//close all of my scanners and writer
		if(file1 != null) {file1.close();}
		if(file2 != null) {file2.close();}
		if(file3 != null) {file3.close();}
		out.close();			
	}
}
