package onlineTest;

import java.util.*;

/**
 * This class extends Question and represents a true or false question. It will
 * override several of the Question class methods to properly accumulate points
 * and question key info. It is a bit difference since it only has two 
 * correct answers, true or false.
 * 
 * @author yanitgeb
 *
 */
public class TrueOrFalseQuestion extends Question {

	private static final long serialVersionUID = 1L;
	private String trueOrFalse;
	private String text;
	private double points;

	/**
	 * This will initialize a true or false question and the instance variables
	 * appropriately by calling the super constructor.
	 * 
	 * @param questionNumber
	 * @param text
	 * @param points
	 */
	public TrueOrFalseQuestion(int questionNumber, String text, double points) {
		super(questionNumber, text, points);

		this.text = text;
		this.points = points;
	}

	/**
	 * This method will update the true or false String variable to store the 
	 * correct answer in the Question. 
	 * @param answer
	 */
	public void addTrueOrFalse(boolean answer) {
		trueOrFalse = answer ? "True" : "False";
	}

	/**
	 * This method overrides the method in the parent class, Question. Since 
	 * there is no array involved with true or false, this method helps create
	 * the polymorphic behavior of the method. For this question key, we only 
	 * need the string representation of either "true" or "false". We don't want
	 * the answer of the true or false question to be enclosed with [] as it is
	 * done in the parent class method, which is why this is necessary.
	 * 
	 * @returns String representation of True or false question key
	 */
	@Override
	public String getQuestionKeyInfo() {
		StringBuffer questionKey = new StringBuffer("");

		String questionText = "Question Text: " + text + "\n";
		String pointsValue = "Points: " + points + "\n";
		String correctAnswer = "Correct Answer: " + trueOrFalse + "\n";

		questionKey.append(questionText).append(pointsValue);
		questionKey.append(correctAnswer);

		return questionKey.toString();

	}

	/**
	 * This method overrides the method in the parent class, Question. Since 
	 * there is no array to iterate through as this method behaves in the 
	 * parent class, we will override to just return the string representation
	 * of either "true" or "false".
	 * 
	 *  @returns String representation of True or false question key
	 */
	@Override
	public String getCorrectAnswer() {

		String correct = trueOrFalse;

		return correct;
	}

	/**
	 * This method overrides the method in the parent class to properly compare
	 * a student answer for a true or false question since there is no array
	 * to iterate through. It will simply compare whether the student answered
	 * true or false and the correct answer
	 * 
	 * @param studentAnswer
	 * @param comparator
	 */
	@Override
	public boolean isCorrectAnswer(Answer studentAnswer,
			Comparator<String> comparator) {

		String answer = studentAnswer.getStudentAnswer();
		String correctAnswer = this.getCorrectAnswer();

		if (comparator.compare(answer, correctAnswer) == 0) {
			return true;
		} else {
			return false;
		}
	}

}
