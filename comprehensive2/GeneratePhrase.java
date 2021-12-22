package comprehensive2;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Cobi Toeun
 *
 */
public class GeneratePhrase {

	private static Random randomNumber = new Random();

	/**
	 * 
	 * @return
	 */
	public static String createPhrase() {
		StringBuilder phrase = new StringBuilder();
		phrase.append(randomString(GenerateHashMap.get("<start>")));

		while (phrase.indexOf("<") != -1) {
			int start = phrase.indexOf("<");
			int end = phrase.indexOf(">");
			String word = (String) phrase.substring(start, end + 1);
			
			if (end > start)
				phrase.replace(start, end + 1, (String) randomString(GenerateHashMap.get(word)));
		}
		return phrase.toString();
	}

	/**
	 *
	 */
	public static Object randomString(ArrayList<String> temp) {
		return temp.get(randomNumber.nextInt(temp.size()));
	}

}
