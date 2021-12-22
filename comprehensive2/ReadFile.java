package comprehensive2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Cobi Toeun
 *
 */
public class ReadFile {
	
	/**
	 * 
	 * @param inputFile
	 */
	public static void readInput(String inputFile) {
		BufferedReader file = null;
		
		try {
			file = new BufferedReader(new FileReader(inputFile));
			while (file.ready()) {
				String line = file.readLine();
				if (line.compareTo("{") == 0)
					inputIntoHash(file);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param input
	 * @throws IOException 
	 */
	public static void inputIntoHash(BufferedReader file) {
		try {
			String terminal = file.readLine();
			GenerateHashMap.put(terminal, new ArrayList<String>());
			ArrayList<String> term = GenerateHashMap.get(terminal);

			while (file.ready()) {
				String line = file.readLine();
				if (line.compareTo("}") == 0) {
					return;
				}
				term.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
