package comprehensive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import comprehensive.RandomPhraseGenerator;

/**
 * This class collects running times for the RandomPhraseGenerator class.
 * 
 * @author Erin Parker & Cobi Toeun & Hongxuan Zhu
 * @version April 24, 2021
 */
public class RandomPhraseGeneratorTimer {

	public static void main(String[] args) throws IOException {
		int probSize = 2100000;
		int IncreaseRate = 100000;

		for (int i = IncreaseRate; i < probSize; i += IncreaseRate) {
			// nonTerminals(i);
			productionsPerNonTerminal(i);
			// nonTerminalsPerProduction(i);

			long stopTime, midpointTime, startTime = System.nanoTime();

			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}
			long timesToLoop = 10000;

			// Collect running times.
			startTime = System.nanoTime();
			// for (int j = 0; j < timesToLoop; j++) {
			// String[] grammar = { "nonTerminals.g", "10" };
			//String[] grammar = { "productionsPerNonTerminal.g", "10" };
			// String[] grammar = { "nonTerminalsPerProduction.g", "10" };
			 String[] grammar = { "src/comprehensive/assignment_extension_request.g", "" + i};
			RandomPhraseGenerator.main(grammar);
			// }
			midpointTime = System.nanoTime();
			// for (long k = 0; k < timesToLoop; k++) {
			// }
			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / 10;
			System.out.println(i + " " + averageTime / 100);

		}
	}

	/**
	 * Increases the non-terminals in the file
	 * 
	 * @param size - number of items to be added
	 */
	private static void nonTerminals(int size) {
		try {
			PrintWriter nonTerm = new PrintWriter((new FileWriter("nonTerminals.g")));

			for (int i = 1; i < size; i++) {
				if (i == 1) {
					nonTerm.println("{");
					nonTerm.println("<start>");
					nonTerm.println("<1>");
					nonTerm.println("}");
					nonTerm.println();
				}
				nonTerm.println("{");
				nonTerm.println("<" + i + ">");
				nonTerm.print(i);
				nonTerm.println("<" + (i + 1) + ">");
				nonTerm.println("}");
				nonTerm.println();
				if (i + 1 == size) {
					nonTerm.println("{");
					nonTerm.println("<" + (i + 1) + ">");
					nonTerm.println("0");
					nonTerm.println("}");
				}
			}
			nonTerm.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Increases the production rules per non-terminal in the file
	 * 
	 * @param size - number of items to be added
	 */
	private static void productionsPerNonTerminal(int size) {
		try {
			PrintWriter prod = new PrintWriter((new FileWriter("productionsPerNonTerminal.g")));

			for (int i = 1; i < size; i++) {
				if (i == 1) {
					prod.println("{");
					prod.println("<start>");
					prod.println("<1>");
					prod.println("}");
					prod.println();
					prod.println("{");
					prod.println("<1>");
				}
				prod.println(i);
			}
			prod.println("}");
			prod.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Increases the non-terminals per production rules in the file
	 * 
	 * @param size - number of items to be added
	 */
	private static void nonTerminalsPerProduction(int size) {
		try {
			PrintWriter nonTermsPerProd = new PrintWriter((new FileWriter("nonTerminalsPerProduction.g")));
			Random rand = new Random();

			nonTermsPerProd.println("{");
			nonTermsPerProd.println("<start>");

			for (int i = 0; i < size; i++) {
				int index = rand.nextInt(3);
				nonTermsPerProd.print("<" + index + ">");
			}
			nonTermsPerProd.println();
			nonTermsPerProd.println("}");
			nonTermsPerProd.println();
			nonTermsPerProd.println("{");
			nonTermsPerProd.println("<" + 0 + ">");
			nonTermsPerProd.println("0");
			nonTermsPerProd.println("}");
			nonTermsPerProd.println();
			nonTermsPerProd.println("{");
			nonTermsPerProd.println("<" + 1 + ">");
			nonTermsPerProd.println("1");
			nonTermsPerProd.println("}");
			nonTermsPerProd.println();
			nonTermsPerProd.println("{");
			nonTermsPerProd.println("<" + 2 + ">");
			nonTermsPerProd.println("2");
			nonTermsPerProd.println("}");
			nonTermsPerProd.println();
			nonTermsPerProd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
