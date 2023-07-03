package onlineTest;

import java.io.Serializable;
/**
 * This class creates an Answer object that consists of information of the exam
 * it is associated with, the question number, and the student answer. It is 
 * called only by the Student class every time a student wants to answer a 
 * question. 
 * 
 * @author yanitgeb
 *
 */
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;
	private int examID;
	private int questionNumber;
	private boolean trueOrFalseQuestion;
	private String trueOrFalse;
	private String[] normalAnswer = {""};

	/**
	 * This constructor instantiates the instance variable appropriately and the
	 * student answer for a true or false question with the boolean parameter 
	 * answer
	 * 
	 * @param examID
	 * @param questionNumber
	 * @param answer
	 */
	public Answer(int examID, int questionNumber, boolean answer) {
		this.examID = examID;
		this.questionNumber = questionNumber;

		trueOrFalse = "" + answer;

		trueOrFalseQuestion = true;
	}

	/**
	 * This constructor instantiates the instance variable appropriately and the
	 * student answer for a true or false question with the String array 
	 * parameter answer
	 * 
	 * @param examID
	 * @param questionNumber
	 * @param answer
	 */
	public Answer(int examID, int questionNumber, String[] answer) {
		this.examID = examID;
		this.questionNumber = questionNumber;

		normalAnswer = new String[answer.length];
		for (int i = 0; i < answer.length; i++) {
			normalAnswer[i] = answer[i];
		}

	}

	/**
	 * This method will return the String of the Student Answer to be compared
	 * for correctness in the Question class to add points. If the question is 
	 * not a true or false question, it will run through the array of the
	 * student answer and return an appended answer split with commas.
	 * It will return the string representation of the boolean value 
	 * passed in, if it was a true or false answer.
	 *  
	 * @return String representation of student's answer
	 */
	public String getStudentAnswer() {
		StringBuffer answer = new StringBuffer("");

		if (trueOrFalseQuestion == false) {
			for (int i = 0; i < normalAnswer.length; i++) {
				answer.append(normalAnswer[i]);
				if (i + 1 < normalAnswer.length) {
					answer.append(", ");
				}
			}
		}

		String correct = ((trueOrFalseQuestion) ? trueOrFalse
				: answer.toString());

		return correct;
	}

	/**
	 * This method returns the array that is passed in so that. This method 
	 * will used for FillInTheBlank Answer comparisons for accuracy points
	 * since the answer won't be properly compared from getStudentAnswer() 
	 * method
	 * 
	 * @return Array of answers passed in by the constructor
	 */
	public String[] getArrayOfAnswers() {
		return normalAnswer;
	}

	/**
	 * This method will return the examID associated with the Answer object
	 * @return examID
	 */
	public int getExamID() {
		return examID;
	}

	/**
	 * This method will return the questionNumber associated with the Answer
	 * object
	 * 
	 * @return questionNumber
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}

}
