package onlineTest;

import java.util.*;
import java.io.Serializable;

/**
 * This class creates a parent class known as Question. A normal question is in 
 * the format of a fill in the blank question. It will allow for other classes
 * to obtain a particular Question object's distinct questionKey and correct 
 * answer along with the earned points of the question.
 * 
 * @author yanitgeb
 *
 */
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	private int questionNumber;
	private String text;
	private double points;
	private String[] normalAnswer;
	private double pointsDeducted = 0;

	/**
	 * Initializes the instance variables appropriately for a question. The exam
	 * ID of a question will not be particularly important as a Question object
	 * will be added in a Map of Exams associated with the particular examID
	 * 
	 * @param questionNumber
	 * @param text
	 * @param points
	 */
	public Question(int questionNumber, String text, double points) {
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;

	}

	/**
	 * This will allow a series of textual or letter answer to be added. This 
	 * will only be called to create a multiple choice question and fill in the
	 * blank question. 
	 * 
	 * @param answer
	 */
	public void addTextOrLetterAnswer(String[] answer) {
		normalAnswer = new String[answer.length];
		for (int i = 0; i < answer.length; i++) {
			normalAnswer[i] = answer[i];
		}
		Arrays.sort(normalAnswer);

	}
	
	/**
	 * This will return a string containing the information of A Question object
	 * key. It will help create a much larger exam key later on by helping
	 * to create a mini key for each Question object. It will present the text,
	 * number of points, and the correct answer. 
	 * @return The key of the current Question object
	 */
	public String getQuestionKeyInfo() {
		StringBuffer questionKey = new StringBuffer("");
		StringBuffer answer = new StringBuffer("");
		String correct = this.getCorrectAnswer();

		answer.append("[").append(correct).append("]");
		

		String questionText = "Question Text: " + text + "\n";
		String pointsValue = "Points: " + points + "\n";
		String correctAnswer = "Correct Answer: " + answer.toString() + "\n";

		questionKey.append(questionText).append(pointsValue)
				.append(correctAnswer);

		return questionKey.toString();

	}

	/**
	 * This method will return the String of the correct answer of a Question 
	 * object 
	 * 
	 * @return String representing the correct answer
	 */
	public String getCorrectAnswer() {
		StringBuffer answer = new StringBuffer("");

		for (int i = 0; i < normalAnswer.length; i++) {
			answer.append(normalAnswer[i]);
			if (i + 1 < normalAnswer.length) {
				answer.append(", ");
			}
		}

		String correct = answer.toString();

		return correct;

	}

	/**
	 * This method uses a comparator to help compare a student's answer with the
	 * correct answer of a Question Object. 
	 * 
	 * @param studentAnswer
	 * @param comparator
	 * @return true if the student answer passed in is correct and false if the 
	 * student answer is wrong
	 *
	 */
	public boolean isCorrectAnswer(Answer studentAnswer,
			Comparator<String> comparator) {

		String answer = studentAnswer.getStudentAnswer();
		String correctAnswer = this.getCorrectAnswer();
		String[] studentAnswers = studentAnswer.getArrayOfAnswers();

		if (comparator.compare(answer, correctAnswer) == 0) {
			return true;
		} 
		
		else if (normalAnswer.length >= 1) {
			pointsDeducted = points;
			for (int i = 0; i < studentAnswers.length; i++) {
				for (String entry : normalAnswer) {
					if (comparator.compare(entry, studentAnswers[i]) == 0) {
						pointsDeducted -= (points / normalAnswer.length);
					}
				}
			}
			return true;
		} 
		
		else {
			return false;
		}
	}

	/**
	 * This returns how much points this object with worth. This will be used
	 * to help total up how many points an exam has
	 * 
	 * @return point value
	 */
	public double getPoints() {
		return points;

	}

	/**
	 * This method returns the question number
	 * @return
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * This method will take the boolean parameter and tally up the earned 
	 * points of the Question as necessary.
	 * 
	 * @param correctAnswer
	 * @return earned point value
	 */
	public double getEarnedPoints(boolean correctAnswer) {
		if (correctAnswer) {
			return (points - pointsDeducted);
		} else {
			return 0;
		}
	}

}
