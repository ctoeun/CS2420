package assign02;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class adds and calculates the final score for CS2420 Students.
 * 
 * @author Cobi Toeun and Hongxuan Zhu
 *
 */
public class CS2420Student extends UofUStudent {

	private EmailAddress contactInfo;
	private ArrayList<Double> assignmentScore = new ArrayList<Double>(), examScore = new ArrayList<Double>(),
			labScore = new ArrayList<Double>(), quizScore = new ArrayList<Double>();

	/**
	 * Creates constructor for CS2420Student and extends from UofUStudent class
	 * 
	 * @param firstName
	 * @param lastName
	 * @param uNID
	 * @param contactInfo
	 */
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
		super(firstName, lastName, uNID);

		this.contactInfo = contactInfo;
	}

	/**
	 * Gathers and returns the student's email address information
	 * 
	 * @return student's email address information
	 */
	public EmailAddress getContactInfo() {
		return this.contactInfo;
	}

	/**
	 * Adds one score to student in a specific category "assignment", "exam", "lab",
	 * and "quiz"
	 * 
	 * @param score    the score to be added to specific category
	 * @param category the category that score adds to - "assignment", "exam",
	 *                 "lab", and "quiz"
	 */
	public void addScore(double score, String category) {
		switch (category) {
		case "assignment": {
			this.assignmentScore.add(score);
			break;
		}
		case "exam": {
			this.examScore.add(score);
			break;
		}
		case "lab": {
			this.labScore.add(score);
			break;
		}
		case "quiz": {
			this.quizScore.add(score);
			break;
		}
		default: {
			break;
		}
		}
	}

	/**
	 * Computes the final score using the CS2420 weighting system.
	 * 
	 * If the student has at least one assignment and exam score and at least two
	 * lab and quiz scores, the method will run and the lowest lab and quiz score
	 * will be dropped. Else the method will just return 0.0
	 * 
	 * @return the final grade after calculations
	 */
	public double computeFinalScore() {
		double finalGrade = 0.0;

		// Check if student meets scoring requirements
		if (this.checkIfValid()) {

			// Sorting the lab and quiz scores from least to greatest
			Collections.sort(this.labScore);
			Collections.sort(this.quizScore);

			// Adding lab and quiz scores to temporary array list
			ArrayList<Double> tempLabScore = this.replaceArrayList(labScore);
			ArrayList<Double> tempQuizScore = this.replaceArrayList(quizScore);

			// Dropping the lowest lab and quiz score
			tempLabScore.remove(0);
			tempQuizScore.remove(0);

			double totalAssignScore = calculatingFromScoreList(this.assignmentScore);
			double totalExamScore = calculatingFromScoreList(this.examScore);
			double totalLabScore = calculatingFromScoreList(tempLabScore);
			double totalQuizScore = calculatingFromScoreList(tempQuizScore);

			// Calculate final score using weighted system
			finalGrade = totalAssignScore * 0.5 + totalExamScore * 0.3 + totalLabScore * 0.1 + totalQuizScore * 0.1;
		}
		return finalGrade;
	}

	/**
	 * Computes the student's final letter grade
	 * 
	 * @return the letter grade based on what the student's final score is. Return
	 *         "N/A" if score is not available
	 */
	public String computeFinalGrade() {
		double score = this.computeFinalScore();

		// Check if student meets scoring requirements
		if (this.checkIfValid()) {
			if (score > 60) {
				if (score < 63)
					return "D-";
				else if (score < 67)
					return "D";
				else if (score < 70)
					return "D+";
				else if (score < 73)
					return "C-";
				else if (score < 77)
					return "C";
				else if (score < 80)
					return "C+";
				else if (score < 83)
					return "B-";
				else if (score < 87)
					return "B";
				else if (score < 90)
					return "B+";
				else if (score < 93)
					return "A-";
				else if (score < 100)
					return "A";
			} else
				return "E";
		}
		return "N/A";
	}

	/**
	 * Calculate the student's average grade in a category
	 *
	 * @param scoreList - array list from a specific category
	 * @return student's total score divided by the amount of scores
	 */
	private double calculatingFromScoreList(ArrayList<Double> scoreList) {
		// Total of scores
		double sumScore = 0.0;

		// Average of scores
		double finalResult = 0.0;

		for (double score : scoreList) {
			sumScore += score;
		}
		finalResult = sumScore / scoreList.size();
		return finalResult;
	}

	/**
	 * Check if the student has at least one assignment and exam score and at least
	 * two lab and quiz scores
	 * 
	 * @return true if they meet the requirements, false otherwise
	 */
	private boolean checkIfValid() {
		if (this.assignmentScore.size() >= 1) {
			if (this.examScore.size() >= 1) {
				if ((this.labScore.size()) >= 2) {
					if ((this.quizScore.size()) >= 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Creates a temporary array list for given scores
	 * 
	 * @param inputArray - array list to be transferred to temporary
	 * @return new temporary array list
	 */
	private ArrayList<Double> replaceArrayList(ArrayList<Double> inputArray) {
		ArrayList<Double> resultArray = new ArrayList<Double>();
		for (double score : inputArray) {
			resultArray.add(score);
		}
		return resultArray;
	}
}
