package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for CS2420Student.
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
class CS2420StudentTest {

	private CS2420Student testFirstStudent, testSecondStudent;

	@BeforeEach
	void setUp() throws Exception {
		testFirstStudent = new CS2420Student("Cobi", "Toeun", 1010101, new EmailAddress("hi", "gmail.com"));
		testSecondStudent = new CS2420Student("Hongxuan", "Zhu", 0101010, new EmailAddress("hello", "gmail.com"));
	}

	// First student tests
	// -------------------------------------------------------------------------

	@Test
	public void testFirstStudentContactInfo() {
		EmailAddress checkEmail = testFirstStudent.getContactInfo();
		assertTrue(checkEmail.equals(new EmailAddress("hi", "gmail.com")));
	}
	
	@Test
	public void testFirstStudentContactInfoFalse() {
		EmailAddress checkEmail = testFirstStudent.getContactInfo();
		assertFalse(checkEmail.equals(new EmailAddress("hello", "gmail.com")));
	}
	
	@Test
	public void testFirstFinalScore0() {
		testFirstStudent.addScore(90, "assignment");
		testFirstStudent.addScore(60.7, "exam");
		testFirstStudent.addScore(85.2, "lab");
		testFirstStudent.addScore(51, "quiz");
		testFirstStudent.addScore(80.5, "quiz");
		assertEquals(0, testFirstStudent.computeFinalScore(), 0);
	}

	@Test
	public void testFirstFinalGradeNA() {
		testFirstStudent.addScore(90, "assignment");
		testFirstStudent.addScore(60.7, "exam");
		testFirstStudent.addScore(85.2, "lab");
		testFirstStudent.addScore(51, "quiz");
		testFirstStudent.addScore(80.5, "quiz");
		assertEquals("N/A", testFirstStudent.computeFinalGrade());
	}

	@Test
	public void testFirstStudentFinalScoreCMinus() {
		testFirstStudent.addScore(84, "assignment");
		testFirstStudent.addScore(72, "assignment");
		testFirstStudent.addScore(60, "exam");
		testFirstStudent.addScore(60, "exam");
		testFirstStudent.addScore(80, "lab");
		testFirstStudent.addScore(40.5, "quiz");
		testFirstStudent.addScore(78, "assignment");
		testFirstStudent.addScore(42, "lab");
		testFirstStudent.addScore(69.5, "quiz");
		assertEquals(71.95, testFirstStudent.computeFinalScore(), 0.001);
	}

	@Test
	public void testFirstStudentFinalGradeCMinus() {
		testFirstStudent.addScore(84, "assignment");
		testFirstStudent.addScore(72, "assignment");
		testFirstStudent.addScore(60, "exam");
		testFirstStudent.addScore(60, "exam");
		testFirstStudent.addScore(80, "lab");
		testFirstStudent.addScore(40.5, "quiz");
		testFirstStudent.addScore(78, "assignment");
		testFirstStudent.addScore(42, "lab");
		testFirstStudent.addScore(69.5, "quiz");
		assertEquals("C-", testFirstStudent.computeFinalGrade());
	}

	// Second student tests
	// -------------------------------------------------------------------------

	@Test
	public void testSecondStudentContactInfo() {
		EmailAddress checkEmail = testSecondStudent.getContactInfo();
		assertTrue(checkEmail.equals(new EmailAddress("hello", "gmail.com")));
	}
	
	@Test
	public void testSecondStudentContactInfoFalse() {
		EmailAddress checkEmail = testSecondStudent.getContactInfo();
		assertFalse(checkEmail.equals(new EmailAddress("hi", "gmail.com")));
	}
	
	@Test
	public void testSecondFinalScore0() {
		testSecondStudent.addScore(70, "assignment");
		testSecondStudent.addScore(93.2, "assignment");
		testSecondStudent.addScore(100, "assignment");
		testSecondStudent.addScore(81.1, "exam");
		testSecondStudent.addScore(100, "exam");
		testSecondStudent.addScore(84.6, "lab");
		testSecondStudent.addScore(95.2, "quiz");
		assertEquals(0, testSecondStudent.computeFinalScore(), 0);
	}

	@Test
	public void testSecondFinalGradeNA() {
		testSecondStudent.addScore(70, "assignment");
		testSecondStudent.addScore(93.2, "assignment");
		testSecondStudent.addScore(100, "assignment");
		testSecondStudent.addScore(81.1, "exam");
		testSecondStudent.addScore(100, "exam");
		testSecondStudent.addScore(84.6, "lab");
		testSecondStudent.addScore(95.2, "quiz");
		assertEquals("N/A", testSecondStudent.computeFinalGrade());
	}

	@Test
	public void testSecondStudentFinalScoreAMinus() {
		testFirstStudent.addScore(80, "assignment");
		testFirstStudent.addScore(95, "exam");
		testFirstStudent.addScore(92.5, "lab");
		testFirstStudent.addScore(98.6, "quiz");
		testFirstStudent.addScore(100, "assignment");
		testFirstStudent.addScore(94.2, "lab");
		testFirstStudent.addScore(81.5, "quiz");
		assertEquals(92.78, testFirstStudent.computeFinalScore(), 0.001);
	}

	@Test
	public void testSecondStudentFinalGradeAMinus() {
		testFirstStudent.addScore(80, "assignment");
		testFirstStudent.addScore(95, "exam");
		testFirstStudent.addScore(92.5, "lab");
		testFirstStudent.addScore(98.6, "quiz");
		testFirstStudent.addScore(100, "assignment");
		testFirstStudent.addScore(94.2, "lab");
		testFirstStudent.addScore(81.5, "quiz");
		assertEquals("A-", testFirstStudent.computeFinalGrade());
	}

	// Testing other cases
	// -------------------------------------------------------------------------

	@Test
	public void testFinalScoreE() {
		testFirstStudent.addScore(0, "assignment");
		testFirstStudent.addScore(40.5, "exam");
		testFirstStudent.addScore(53.5, "lab");
		testFirstStudent.addScore(72.5, "lab");
		testFirstStudent.addScore(53, "quiz");
		testFirstStudent.addScore(0, "quiz");
		testFirstStudent.addScore(84, "assignment");
		testFirstStudent.addScore(34, "lab");
		testFirstStudent.addScore(50, "quiz");
		assertEquals(44.6, testFirstStudent.computeFinalScore(), 0.001);
	}

	@Test
	public void testFinalGradeE() {
		testFirstStudent.addScore(0, "assignment");
		testFirstStudent.addScore(40.5, "exam");
		testFirstStudent.addScore(53.5, "lab");
		testFirstStudent.addScore(72.5, "lab");
		testFirstStudent.addScore(53, "quiz");
		testFirstStudent.addScore(0, "quiz");
		testFirstStudent.addScore(84, "assignment");
		testFirstStudent.addScore(34, "lab");
		testFirstStudent.addScore(50, "quiz");
		assertEquals("E", testFirstStudent.computeFinalGrade());
	}

}
