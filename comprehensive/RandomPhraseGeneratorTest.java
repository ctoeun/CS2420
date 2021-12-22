package comprehensive;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class RandomPhraseGeneratorTest {

	@Test
	void testSimple() throws IOException {
		RandomPhraseGenerator.main(new String[] { "src/comprehensive/super_simple.g", "15" });
	}

	@Test
	void testAssignment() throws IOException {
		 RandomPhraseGenerator.main(new String[] {"src/comprehensive/assignment_extension_request.g", "100"});
	}
	
	@Test
	void testMath() throws IOException {
		 RandomPhraseGenerator.main(new String[] {"src/comprehensive/mathematical_expression.g", "20"});
	}

	
	@Test
	void testPoetic() throws IOException {
		 RandomPhraseGenerator.main(new String[] {"src/comprehensive/poetic_sentence.g", "15"});
	}
	
	@Test
	void testGrammar() throws IOException {
		 RandomPhraseGenerator.main(new String[] {"src/comprehensive/grammar_file.g", "10"});
	}
	
}
