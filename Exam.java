package onlineTest;

import java.io.Serializable;
import java.util.*;


/**
 * This class will create an Exam object that contains a map of questions and 
 * an entire exam key. When the object is created, you will be able to update
 * and add questions. Even though an exam comes with a name, for the purposes of
 * my code, the name will not be necessary. However, the name can be used in the 
 * Interpreter class if necessary. In some cases, an examID will be more 
 * distinct than a name if there are some name repeats, so my code will utilize
 * the examID as more of a unique distinction since the numbers ideally should 
 * not repeat. 
 * 
 * @author yanitgeb
 *
 */
public class Exam implements Serializable {

	private static final long serialVersionUID = 1L;

	private int examID;
	private ArrayList<Question> listOfQuestions;
	private TreeMap<Integer, Question> mapOfQuestions;
	private String examName;

	/**
	 * Initializes an Exam object appropriately and the instance variables as 
	 * well. An array of questions will be initialized for iterative purposes, 
	 * while a TreeMap of questions will be used for easy access to random
	 * questions as necessary. Both will be used for better code efficiency. 
	 * 
	 * @param examID
	 * @param examName
	 */
	public Exam(int examID, String examName) {
		this.examID = examID;
		listOfQuestions = new ArrayList<Question>();
		mapOfQuestions = new TreeMap<Integer, Question>();
		this.examName = examName;
	}

	/**
	 * This method adds a question to an Exam object. The TreeMap will associate
	 * the question with a question number while the ArrayList will simply add
	 * the question in to be iterated through later. 
	 * @param questionNumber
	 * @param problem
	 */
	public void addQuestion(int questionNumber, Question problem) {
		listOfQuestions.add(problem);
		mapOfQuestions.put(questionNumber, problem);
	}

	/**
	 * This method returns an array of Questions to be used in a different class
	 * and to iterate through when using for loops. 
	 * @return
	 */
	public Question[] getListOfQuestions() {
		Question[] list = new Question[listOfQuestions.size()];

		for (int i = 0; i < listOfQuestions.size(); i++) {
			list[i] = listOfQuestions.get(i);
		}

		return list;
	}

	/**
	 * This method will return a TreeMap of Questions 
	 * @return
	 */
	public TreeMap<Integer, Question> getMapOfQuestions() {
		return mapOfQuestions;
	}
	
	/**
	 * This method will iterate through the whole list of Questions to get each
	 * distinct Question key and append it to become a larger key. This method
	 * will returns the key for a particular exam once all the Question keys
	 * have been added. 
	 * @return String representation of exam key
	 */
	public String getExamKey() {
		StringBuffer questionKeys = new StringBuffer("");

		for (int i = 0; i < listOfQuestions.size(); i++) {
			questionKeys.append(listOfQuestions.get(i).getQuestionKeyInfo());
		}

		return questionKeys.toString();

	}

	/**
	 * This method will return the total score worth of an exam by iterating
	 * through the list of questions to tally up the points
	 * 
	 * @return total score of exam
	 */
	public double getTotalScore() {
		double totalExamScore = 0;
		for (int i = 0; i < listOfQuestions.size(); i++) {
			totalExamScore += listOfQuestions.get(i).getPoints();
		}

		return totalExamScore;
	}

	/**
	 * This method will return the examID of a particular exam
	 * @return examID
	 */
	public int getExamID() {
		return examID;
	}

	/**
	 * This method will return the examName of a particular exam
	 * @return examName
	 */
	public String getExamName() {
		return examName;
	}

}
