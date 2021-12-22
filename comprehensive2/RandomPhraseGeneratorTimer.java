package comprehensive2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import comprehensive.RandomPhraseGenerator;

/**
 * This class collects running times for methods of Random Phrase Generator.
 * 
 * @author Erin Parker, Hongxuan Zhu and Cobi Toeun
 * @version April 24, 2021
 */
public class RandomPhraseGeneratorTimer {

	public static void main(String[] args) throws IOException {
		int probSize = 200;
		int IncreaseRate = 10;

		for (int i = 10; i < probSize; i += IncreaseRate) {
			nonTerminals(i);
			productionsPerNonTerminal(i);
			nonTerminalsPerProduction(i);

			long stopTime, midpointTime, startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}
			long timesToLoop = 10000;

			// Collect running times.
			startTime = System.nanoTime();
			for (int j = 0; j < timesToLoop; j++) {
				String[] grammar = { "nonTerminal.g", "1" };
//				String[] grammar = {"nonTerminalsInProd.g","1"};
//				String[] grammar = {"numbe rOfProduction.g","1"};
//				String[] grammar = { "src/comprehensive/super_simple.g", "" + i};
				RandomPhraseGenerator.main(grammar);
			}
			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
			System.out.println(i + " " + averageTime / 100);

		}
	}

	/**
	 * This will make a file that is used for the amount of terminals in the file
	 * 
	 * @param size - int number of items to be added to the file.
	 */
	private static void nonTerminals(int size) {
		try {
			PrintWriter myFile = new PrintWriter((new FileWriter("nonTerminal.g")));

			for (int i = 1; i < size; i++) {
				if (i == 1) {
					myFile.println("{");
					myFile.println("<start>");
					myFile.println("<1>");
					myFile.println("}");
					myFile.println();
				}
				myFile.println("{");
				myFile.println("<" + i + ">");
				myFile.print(i);
				myFile.println("<" + (i + 1) + ">");
				myFile.println("}");
				myFile.println();
				if (i + 1 == size) {
					myFile.println("{");
					myFile.println("<" + (i + 1) + ">");
					myFile.println("0");
					myFile.println("}");
				}

			}
			myFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param size
	 */
	private static void productionsPerNonTerminal(int size) {

		try {
			PrintWriter myFile = new PrintWriter((new FileWriter("numberOfProduction.g")));

			for (int i = 1; i < size; i++) {
				if (i == 1) {
					myFile.println("{");
					myFile.println("<start>");
					myFile.println("<1>");
					myFile.println("}");
					myFile.println();
					myFile.println("{");
					myFile.println("<1>");
				}
				myFile.println(i);
			}
			myFile.println("}");
			myFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * this will add nonterminals in a product
	 * 
	 * @param size
	 */
	private static void nonTerminalsPerProduction(int size) {

		try {
			PrintWriter myFile = new PrintWriter((new FileWriter("nonTerminalsInProd.g")));
			Random rand = new Random();

			myFile.println("{");
			myFile.println("<start>");

			for (int i = 0; i < size; i++) {
				int index = rand.nextInt(3);
				myFile.print("<" + index + ">");
			}
			myFile.println();
			myFile.println("}");
			myFile.println();
			myFile.println("{");
			myFile.println("<" + 0 + ">");
			myFile.println("zing");
			myFile.println("}");
			myFile.println();
			myFile.println("{");
			myFile.println("<" + 1 + ">");
			myFile.println("ping");
			myFile.println("}");
			myFile.println();
			myFile.println("{");
			myFile.println("<" + 2 + ">");
			myFile.println("plane");
			myFile.println("}");
			myFile.println();
			myFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
