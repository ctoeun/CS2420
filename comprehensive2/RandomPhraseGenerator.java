package comprehensive2;

/**
 * 
 * @author Cobi Toeun
 *
 */
public class RandomPhraseGenerator {

	private static String filePath;
	private static int phrases;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		filePath = args[0];
		phrases = Integer.parseInt(args[1]);
		ReadFile.readInput(filePath);
		for (int i = 0; i < phrases; i++) {
			System.out.println(GeneratePhrase.createPhrase());
		}
	}
}
