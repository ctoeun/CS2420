package comprehensive2;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Cobi Toeun
 *
 */
public class GenerateHashMap {

	private static HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();

	/**
	 * 
	 * @return
	 */
	private static HashMap<String, ArrayList<String>> getHash() {
		return hash;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static ArrayList<String> get(String string) {
		return getHash().get(string);
	}

	/**
	 * 
	 * @param terminalName
	 * @param arrayList
	 */
	public static void put(String terminalName, ArrayList<String> arrayList) {
		getHash().put(terminalName, arrayList);
	}

}
