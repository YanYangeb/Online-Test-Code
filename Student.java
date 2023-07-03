package onlineTest;

import java.util.*;
import java.io.Serializable;

/**
 * This class holds a lot of information and creates a Student object. A student
 * will have taken exams and the answers to those questions. This class also
 * implements the Comparable interface to allow for a list of students to be
 * sorted in alphabetical order. 
 * 
 * @author yanitgeb
 *
 */
public class Student implements Comparable<Student>, Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private TreeMap<Integer, Exam> takenExams;
	private TreeMap<String, Answer> questionAnswers;
	private TreeMap<Integer, TreeMap<String, Answer>> examAnswers;
	private Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
	private ArrayList<Exam> listOfExams;

	/**
	 * This constructor properly creates a Student object and initializes the
	 * instance variables appropriately
	 * 
	 * @param studentName
	 */
	public Student(String studentName) {
		this.name = studentName;
		takenExams = new TreeMap<Integer, Exam>();
		questionAnswers = new TreeMap<String, Answer>();
		examAnswers = new TreeMap<Integer, TreeMap<String, Answer>>();

		listOfExams = new ArrayList<Exam>();

	}

	/**
	 * This method is implemented for the purpose of the Comparable interface. 
	 * This will help the students to be organized in alphabetical order
	 * 
	 * @param s for Student
	 * @return a negative number, positive number, or zero
	 */
	public int compareTo(Student s) {
		return comparator.compare(this.name, s.name);
	}

	/**
	 * This will set the list of exams that a student has taken. The ArrayList
	 * will be important when iterating through the exams as necessary.
	 * 
	 * @param examsList
	 */
	public void setListOfExams(ArrayList<Exam> examsList) {
		listOfExams = examsList;
	}

	/**
	 * This method will help create a TreeMap of exams. The TreeMap will be used
	 * for easy access to exams just by using the examID. It will allow for 
	 * more efficient information grabbing. 
	 * 
	 * @param examList
	 */
	public void setTakenExams(TreeMap<Integer, Exam> examList) {
		this.takenExams = examList;
	}

	/**
	 * This will return a particular student's map of taken Exams. Although it
	 * won't be completely necessary because of the makeup of the public tests
	 * where every student takes the same exams, this method will still be 
	 * necessary in some cases where some students take different exams.  
	 * 
	 * @return takenExams
	 */
	public TreeMap<Integer, Exam> getTakenExams() {
		return takenExams;
	}

	/**
	 * This method will take all of the exams a student has taken and retrieve
	 * the scores on those exams by the student. It will add up each percentage
	 * on the exams and use it to total up the grade average. 
	 * 
	 * @return numeric grade percentage wise
	 */
	public double getTotalNumericGrade() {
		double getTotalEarnedPercentage = 0.0;

		for (int i = 0; i < listOfExams.size(); i++) {
			double newExamScore = getScoreOnExam(
					listOfExams.get(i).getExamID());
			
			double maxExamScore = listOfExams.get(i).getTotalScore();

			getTotalEarnedPercentage += (newExamScore / maxExamScore) * 100;

		}

		return (getTotalEarnedPercentage / listOfExams.size());

	}

	/**
	 * This method returns the name of the student
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method will allow a particular student to answer a true or false
	 * question. Once the student has created their own answer, it will be
	 * added to a Map of Question Answers and that map will be added to the 
	 * Map of exam Answers made by the student. This must reside in the Student
	 * class because each student has their own unique answers to be accessed.
	 * A TreeMap is used so that a student can override their own answers if 
	 * they change it. 
	 * 
	 * @param examID
	 * @param questionNumber
	 * @param answer
	 */
	public void answerQuestions(int examID, int questionNumber,
			boolean answer) {

		Answer studentAnswer = new Answer(examID, questionNumber, answer);

		String examIDWithQuestionNum = "" + examID + "." + questionNumber;

		questionAnswers.put(examIDWithQuestionNum, studentAnswer);

		examAnswers.put(examID, questionAnswers);

	}

	/**
	 * This method will allow a particular student to answer a normal question.
	 * Once the student has created their own answer, it will be
	 * added to a Map of Question Answers and that map will be added to the 
	 * Map of exam Answers made by the student. This must reside in the Student
	 * class because each student has their own unique answers to be accessed.
	 * A TreeMap is used so that a student can override their own answers if 
	 * they change it.  
	 * 
	 * @param examID
	 * @param questionNumber
	 * @param answer
	 */
	public void answerQuestions(int examID, int questionNumber,
			String[] answer) {

		Answer studentAnswer = new Answer(examID, questionNumber, answer);

		String examIDWithQuestionNum = "" + examID + "." + questionNumber;

		questionAnswers.put(examIDWithQuestionNum, studentAnswer);

		examAnswers.put(examID, questionAnswers);

	}

	/**
	 * This method allows us to obtain a particular exam score for a particular
	 * student since each student has their own distinct and unique exam scores.
	 * @param examID
	 * @return total exam score
	 */
	public double getScoreOnExam(int examID) {
		Question[] examQuestions = takenExams.get(examID).getListOfQuestions();
		double totalScore = 0;

		for (int i = 0; i < examQuestions.length; i++) {
			boolean correct = false;
			String questionNum = "" + examID + "."
					+ examQuestions[i].getQuestionNumber();
			Answer currentAnswer = examAnswers.get(examID).get(questionNum);

			correct = examQuestions[i].isCorrectAnswer(currentAnswer,
					comparator);

			totalScore += examQuestions[i].getEarnedPoints(correct);

		}

		return totalScore;
	}
	
	/**
	 * This method returns an exam report for a particular exam done by a 
	 * particular student. It must be implemented in the Student class because
	 * each student has a unique exam report.
	 * 
	 * @param examID
	 * @return
	 */
	public String getExamReport(int examID) {
		Question[] questions = takenExams.get(examID).getListOfQuestions();
		StringBuffer report = new StringBuffer("");

		double finalScore = 0;
		double totalExamScores = 0;
		for (int i = 0; i < questions.length; i++) {
			String questionNumWithID = "" + examID + "."
					+ questions[i].getQuestionNumber();
			int questionNum = questions[i].getQuestionNumber();
			Answer currentAnswer = examAnswers.get(examID)
					.get(questionNumWithID);
			boolean correct = questions[i].isCorrectAnswer(currentAnswer,
					comparator);
			double questionPoints = questions[i].getPoints();
			double earnedPoints = questions[i].getEarnedPoints(correct);
			report.append("Question #" + questionNum + " " + earnedPoints
					+ " points out of " + questionPoints);
			report.append("\n");

			finalScore += earnedPoints;
			totalExamScores += questionPoints;
		}

		report.append(
				"Final Score: " + finalScore + " out of " + totalExamScores);

		return report.toString();

	}

	/**
	 * This method helps set up and return the earned grade of a student. It is
	 * implemented here as each student has a distinct grade.
	 * 
	 * @param grades
	 * @param cutoffs
	 * @return grade in Letter format
	 */
	public String getGrade(String[] grades, double[] cutoffs) {
		int posOfCutoff = 0;
		for (int i = 0; i < cutoffs.length - 1; i++) {
			if (this.getTotalNumericGrade() < cutoffs[i]) {
				posOfCutoff = i + 1;
			}
		}

		return grades[posOfCutoff];
	}

}
