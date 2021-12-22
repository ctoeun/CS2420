package comprehensive;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class of the RandomPhraseGenerator
 * Generates random phrases by the .g file passed in
 * @author Zhu, Hongxuan and Cobi Toeun
 *
 */
public class RandomPhraseGenerator {

	private static CommandHashTable mainTable;
	private static int cycles;
	private static String fileName;
	private static StringBuilder resultString = new StringBuilder("");
	
	public static void main(String[] args) throws IOException {
		processCommand(args);
		FileReader fr = new FileReader(fileName);
		mainTable = fr.getTable();
		for (int i = 0; i < cycles; i++) {
			resultString.append(generateString());
			resultString.append("\n");
		}
		System.out.print(resultString.toString());
	}
	
	/**
	 * Process the input commands
	 * @param args [filename, cycles]
	 */
	private static void processCommand(String args[]) {
		if(args.length >= 1) {
			fileName = args[0];
		}
		if(args.length >= 2) {
			cycles = Integer.parseInt(args[1]);
		}
	}
	/**
	 * Driver method for generate the result string
	 * @return the result string
	 */
	private static StringBuilder generateString() {
		ArrayList<String[]> commandStack = mainTable.getCommand("start");
		StringBuilder returnValue = recursiveWriting(commandStack, new StringBuilder(""));
		return returnValue;
	}
	/**
	 * Recursive working on generate the result string
	 * the code logic is:<br/>
	 * <br/><br/>
	 * If current command is "string", go to next command.<br/>
	 * If current command is command, pause current loop, go to the command,
	 * 		pass the current result string to next recursive call.<br/>
	 * If current command arraylist is loop to the end, close the recursive call, 
	 * 		return the result string to the upper level method.
	 * 
	 * @param commandArray : The array holds commands, first command should in the first
	 * @param returnString : The variable that keeps the result during the recursive call
	 * @return the result string
	 */
	private static StringBuilder recursiveWriting(ArrayList<String[]> commandArray, StringBuilder returnValue) {
		for(int i = 0; i < commandArray.size(); i++) {
			String[] command = commandArray.get(i);
			if (command[1] == "s") {
				returnValue.append(command[0]);
			}else {
				ArrayList<String[]> nextArray = mainTable.getCommand(command[0]);
				returnValue = (recursiveWriting(nextArray, returnValue));
			}
		}
		return returnValue;
	}
}
