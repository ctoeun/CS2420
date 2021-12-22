package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for CS2420Class.
 * 
 * @author Erin Parker, Cobi Toeun, and Hongxuan Zhu
 * @version January 27, 2021
 */
public class CS2420ClassTester {

	private CS2420Class emptyClass, verySmallClass, smallClass, largeClass;

	@BeforeEach
	void setUp() throws Exception {
		emptyClass = new CS2420Class();

		verySmallClass = new CS2420Class();
		verySmallClass.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		verySmallClass.addStudent(new CS2420Student("Drew", "Hall", 2323232, new EmailAddress("howdy", "gmail.com")));
		verySmallClass
				.addStudent(new CS2420Student("Riley", "Nguyen", 4545454, new EmailAddress("hello", "gmail.com")));

		smallClass = new CS2420Class();
		smallClass.addAll("src/assign02/a_small_2420_class.txt");

		// Generating a large class of CS2420 students
		largeClass = new CS2420Class();
		EmailAddress[] sharedEmails = new EmailAddress[5];
		sharedEmails[0] = new EmailAddress("hi", "gmail.com");
		sharedEmails[1] = new EmailAddress("howdy", "gmail.com");
		sharedEmails[2] = new EmailAddress("hello", "gmail.com");
		sharedEmails[3] = new EmailAddress("hello", "gmasdil.com");
		sharedEmails[4] = new EmailAddress("helslo", "gmasdil.com");
		for (int i = 0; i < 500; i++) {
			String first = (char) ('A' + i % 26) + "" + (char) ('b' + i % 26);
			String last = (char) ('C' + i % 26) + "" + (char) ('d' + i % 26);
			int uNID = 1000000 + i;
			CS2420Student student = new CS2420Student(first, last, uNID, sharedEmails[i % 5]);
			largeClass.addStudent(student);
			student.addScore(80, "assignment");
			student.addScore(75, "exam");
			student.addScore(90, "lab");
			student.addScore(80, "lab");
			student.addScore(80, "quiz");
			student.addScore(70, "quiz");
		}
	}

	// Empty CS 2420 class tests
	// --------------------------------------------------------------------------

	@Test
	public void testEmptyLookupUNID() {
		assertNull(emptyClass.lookup(1234567));
	}

	@Test
	public void testEmptyLookupContactInfo() {
		ArrayList<CS2420Student> students = emptyClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(0, students.size());
	}

	@Test
	public void testEmptyAddScore() {
		// ensure no exceptions thrown
		emptyClass.addScore(1234567, 100, "assignment");
	}

	@Test
	public void testEmptyClassAverage() {
		assertEquals(0, emptyClass.computeClassAverage(), 0);
	}

	@Test
	public void testEmptyContactList() {
		ArrayList<EmailAddress> contactList = emptyClass.getContactList();
		assertEquals(0, contactList.size());
	}

	// Very small CS 2420 class tests
	// --------------------------------------------------------------------

	@Test
	public void testVerySmallLookupUNID() {
		UofUStudent expected = new UofUStudent("Drew", "Hall", 2323232);
		CS2420Student actual = verySmallClass.lookup(2323232);
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupContactInfo() {
		UofUStudent expectedStudent = new UofUStudent("Riley", "Nguyen", 4545454);
		ArrayList<CS2420Student> actualStudents = verySmallClass.lookup(new EmailAddress("hello", "gmail.com"));
		assertEquals(1, actualStudents.size());
		assertEquals(expectedStudent, actualStudents.get(0));
	}

	@Test
	public void testVerySmallAddDuplicateStudent() {
		boolean actual = verySmallClass
				.addStudent(new CS2420Student("Jane", "Doe", 1010101, new EmailAddress("hi", "gmail.com")));
		assertFalse(actual);
	}

	@Test
	public void testVerySmallAddNewStudent() {
		boolean actual = verySmallClass
				.addStudent(new CS2420Student("Jane", "Doe", 1010100, new EmailAddress("hi", "gmail.com")));
		assertTrue(actual);
	}

	@Test
	public void testVerySmallStudentFinalScore0() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		student.addScore(89.2, "quiz");
		assertEquals(0, student.computeFinalScore(), 0);
	}

	@Test
	public void testVerySmallStudentFinalGradeNA() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(100, "lab");
		student.addScore(89.2, "quiz");
		assertEquals("N/A", student.computeFinalGrade());
	}

	@Test
	public void testVerySmallStudentFinalScore() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals(86.795, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallStudentFinalGrade() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		assertEquals("B", student.computeFinalGrade());
	}

	@Test
	public void testVerySmallStudentComputeScoreTwice() {
		CS2420Student student = verySmallClass.lookup(2323232);
		student.addScore(86.5, "assignment");
		student.addScore(75, "exam");
		student.addScore(90, "lab");
		student.addScore(89.2, "quiz");
		student.addScore(99, "assignment");
		student.addScore(80, "lab");
		student.addScore(77.7, "quiz");
		student.computeFinalScore(); // do not remove dropped scores, since may change
		student.addScore(70, "lab");
		student.addScore(67.7, "quiz");
		assertEquals(85.72, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testVerySmallUpdateName() {
		verySmallClass.lookup(1010101).updateName("John", "Doe");
		ArrayList<CS2420Student> students = verySmallClass.lookup(new EmailAddress("hi", "gmail.com"));
		assertEquals("John", students.get(0).getFirstName());
		assertEquals("Doe", students.get(0).getLastName());
	}

	// Small and large CS 2420 class tests
	// -------------------------------------------------------------------------

	@Test
	public void testSmallLookupContactInfo() {
		UofUStudent expectedStudent1 = new UofUStudent("Kennedy", "Miller", 888888);
		UofUStudent expectedStudent2 = new UofUStudent("Taylor", "Miller", 999999);

		ArrayList<CS2420Student> actualStudents = smallClass.lookup(new EmailAddress("we_love_puppies", "geemail.com"));
		assertEquals(2, actualStudents.size());
		assertTrue(actualStudents.contains(expectedStudent1));
		assertTrue(actualStudents.contains(expectedStudent2));
	}

	@Test
	public void testSmallGetContactList() {
		ArrayList<EmailAddress> actual = smallClass.getContactList();
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallStudentFinalScore() {
		CS2420Student student = smallClass.lookup(333333);
		assertEquals(95.540, student.computeFinalScore(), 0.001);
	}

	@Test
	public void testSmallComputeClassAverage() {
		assertEquals(78.006, smallClass.computeClassAverage(), 0.001);
	}

	@Test
	public void testLargeComputeClassAverage() {
		assertEquals(79.5, largeClass.computeClassAverage(), 0.001);
	}

	@Test
	public void testLargeStudentFinalScore() {
		CS2420Student student = largeClass.lookup(1000110);
		assertEquals(79.5, student.computeFinalScore(), 0.001);
	}
}