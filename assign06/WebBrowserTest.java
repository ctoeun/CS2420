package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

/**
 * 
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
class WebBrowserTest {

	@Test
	void testWebBrowser() throws MalformedURLException {
		WebBrowser browser = new WebBrowser();
		browser.visit(new URL("https://a"));
		assertTrue(browser.back().toString().equals("https://a"));
		assertTrue(browser.forward().toString().equals("https://a"));
		
		SinglyLinkedList<URL> history = browser.history();
		assertTrue(history.getFirst().toString().equals("https://a"));
		
		WebBrowser newBrowser = new WebBrowser(history);
		assertTrue(newBrowser.back().toString().equals("https://a"));
	}
	
	
}
