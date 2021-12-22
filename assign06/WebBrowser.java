package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * This class simulates a Web Browser
 * 
 * @param <E>
 * @param <E>
 * @authors Cobi Toeun and Hongxuan Zhu
 *
 */
public class WebBrowser extends LinkedListStack<URL> {

	private LinkedListStack<URL> preStack = new LinkedListStack<URL>();
	private LinkedListStack<URL> postStack = new LinkedListStack<URL>();

	/**
	 * 
	 */
	public WebBrowser() {
		
	}

	/**
	 * 
	 * @param history
	 */
	public WebBrowser(SinglyLinkedList<URL> history) {
		Object[] arr = history.toArray();

		for (int i = history.size() - 1; i >= 0; i--) {
			preStack.push((URL) arr[i]); // option 1
//			preStack.push(history.get(i)); // option 2
		}

	}

	/**
	 * 
	 * @param webpage
	 */
	public void visit(URL webpage) {
		postStack.clear();
		preStack.push(webpage);
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */
	public URL back() throws NoSuchElementException {
		URL preURL = preStack.pop();
		postStack.push(preURL);

		return preURL;

	}

	/**
	 * 
	 * @return
	 * @throws NoSuchElementException
	 */
	public URL forward() throws NoSuchElementException {
		URL postURL = postStack.pop();
		preStack.push(postURL);

		return postURL;

	}

	/**
	 * 
	 * @return
	 */
	public SinglyLinkedList<URL> history() {
		URL[] copyArray = new URL[preStack.size()];
		SinglyLinkedList<URL> history = new SinglyLinkedList<URL>();
		
		for (int i = preStack.size() - 1; i >= 0; i--) {
			copyArray[i] = preStack.pop();
		}
		
		for (int i = 0; i < copyArray.length; i++) {
			history.insertFirst(copyArray[i]);
			preStack.push(copyArray[i]);
		}
		
		return history;

	}
}
