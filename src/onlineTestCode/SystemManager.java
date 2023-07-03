package onlineTest;

import java.util.*;
import java.io.*;

/**
 * This class manages the creation and utilization of the proper objects needed
 * for the creation of an Online Test system. It will use of mix of TreeMaps for
 * efficient obtaining of information and ArrayLists for efficient iterating.
 * All the methods work together to ultimately create a system that adds 
 * students, exams, questions, answers, and grades appropriately. 
 * 
 * @author yanitgeb
 *
 */
public class SystemManager implements Manager, Serializable {

	private static final long serialVersionUID = 1L;

	private TreeMap<Integer, Exam> mapOfExams;
	private TreeMap<String, Student> mapOfStudents;
	private ArrayList<Student> listOfStudents;
	private ArrayList<Exam> listOfExams;
	private String[] possibleGrades;
	private double[] gradeCutoffs;

	/**
	 * This constructor will initialize the instance variables appropriately. 
	 * Several TreeMaps and ArrayLists will be used to store and access
	 * necessary information
	 */
	public SystemManager() {
		mapOfExams = new TreeMap<Integer, Exam>();
		listOfStudents = new ArrayList<Student>();
		mapOfStudents = new TreeMap<String, Student>();
		listOfExams = new ArrayList<Exam>();

		possibleGrades = null;
		gradeCutoffs = null;

	}

	/**
	 * This method adds an exam with an ID and title. It will always return
	 * true when the method finishes. 
	 * 
	 * @return true
	 */
	public boolean addExam(int examID, String title) {
		Exam newExam = new Exam(examID, title);

		mapOfExams.put(examID, newExam);

		listOfExams.add(newExam);

		return true;
	}
	
	/**
	 * This method will add a student to the system. Once the process is done,
	 * the method will always return true.
	 * @param name
	 * @return true
	 */
	public boolean addStudent(String name) {
		Student newPerson = new Student(name);

		mapOfStudents.put(name, newPerson);
		listOfStudents.add(newPerson);

		mapOfStudents.get(name).setTakenExams(mapOfExams);

		return true;
	}
	
	/**
	 * This method will help setup the possible letterGrade and cutoffs that 
	 * will be used to determine the students' grades
	 * 
	 * @param letterGrades
	 * @param cutoffs
	 */
	public void setLetterGradesCutoffs(String[] letterGrades,
			double[] cutoffs) {
		this.possibleGrades = letterGrades;
		this.gradeCutoffs = cutoffs;
	}


	/**
	 * This method will create a fill in the blank question and add the 
	 * appropriate answer. Once the Question object has been instantiated, it is
	 * added to the appropriate exam by accessing the TreeMap of exams
	 * @param examID
	 * @param questionNumber
	 * @param text
	 * @param points
	 * @param answer
	 */
	public void addFillInTheBlanksQuestion(int examID, int questionNumber,
			String text, double points, String[] answer) {
		
		Question fillInTheBlank = new Question(questionNumber, text, points);

		fillInTheBlank.addTextOrLetterAnswer(answer);

		mapOfExams.get(examID).addQuestion(questionNumber, fillInTheBlank);
	}

	/**
	 * This method will create a multiple choice question and add the 
	 * appropriate answer. Once the Question object has been instantiated, it is
	 * added to the appropriate exam by accessing the TreeMap of exams
	 * @param examID
	 * @param questionNumber
	 * @param text
	 * @param points
	 * @param answer
	 */
	public void addMultipleChoiceQuestion(int examID, int questionNumber,
			String text, double points, String[] answer) {
		
		MultipleChoiceQuestion multipleChoice = new MultipleChoiceQuestion(
				questionNumber, text, points);

		multipleChoice.addTextOrLetterAnswer(answer);

		mapOfExams.get(examID).addQuestion(questionNumber, multipleChoice);
	}
	
	/**
	 * This method will create a true or false question and add the 
	 * appropriate answer. Once the Question object has been instantiated, it is
	 * added to the appropriate exam by accessing the TreeMap of exams
	 * @param examID
	 * @param questionNumber
	 * @param text
	 * @param points
	 * @param answer
	 */
	public void addTrueFalseQuestion(int examID, int questionNumber,
			String text, double points, boolean answer) {

		TrueOrFalseQuestion trueOrFalse = new TrueOrFalseQuestion(
				questionNumber, text, points);

		trueOrFalse.addTrueOrFalse(answer);

		mapOfExams.get(examID).addQuestion(questionNumber, trueOrFalse);
	}
	
	
	/**
	 * This method will allow a particular student to answer a fill in question 
	 * the blank question and also update their Map of exams and list of exams 
	 * instance variables within the class. 
	 * @param studentName
	 * @param examID
	 * @param questionNumber
	 * @param answer
	 */
	public void answerFillInTheBlanksQuestion(String studentName, int examID,
			int questionNumber, String[] answer) {

		if (!mapOfStudents.containsKey(studentName)) {
			System.out.println("Student Not Found!");
		} 
		else {
			mapOfStudents.get(studentName).answerQuestions(examID,
					questionNumber, answer);
			mapOfStudents.get(studentName).setTakenExams(mapOfExams);

			mapOfStudents.get(studentName).setListOfExams(listOfExams);
		}

	}

	/**
	 * This method will allow a particular student to answer a multiple choice 
	 * question and also update their Map of exams and list of exams 
	 * instance variables within the class. 
	 * @param studentName
	 * @param examID
	 * @param questionNumber
	 * @param answer
	 */
	public void answerMultipleChoiceQuestion(String studentName, int examID,
			int questionNumber, String[] answer) {

		if (!mapOfStudents.containsKey(studentName)) {
			System.out.println("Student Not Found!");
		}

		else {
			mapOfStudents.get(studentName).answerQuestions(examID,
					questionNumber, answer);
			mapOfStudents.get(studentName).setTakenExams(mapOfExams);

			mapOfStudents.get(studentName).setListOfExams(listOfExams);
		}

	}

	/**
	 * This method will allow a particular student to answer a true or false
	 * question and also update their Map of exams and list of exams 
	 * instance variables within the class. 
	 * @param studentName
	 * @param examID
	 * @param questionNumber
	 * @param answer
	 */
	public void answerTrueFalseQuestion(String studentName, int examId,
			int questionNumber, boolean answer) {

		if (!mapOfStudents.containsKey(studentName)) {
			System.out.println("Student Not Found!");
		} 
		
		else {

			mapOfStudents.get(studentName).answerQuestions(examId,
					questionNumber, answer);
			mapOfStudents.get(studentName).setTakenExams(mapOfExams);

			mapOfStudents.get(studentName).setListOfExams(listOfExams);
		}

	}

	/**
	 * This method will run through all the students and retrieve their exam
	 * scores on a particular exam to calculate the average score on an exam.
	 * 
	 * @param examId
	 * @return averageScore
	 */
	public double getAverageScore(int examId) {
		double scores = 0.0;
		int totalNumberOfExams = listOfStudents.size();

		for (Student student : listOfStudents) {
			scores += ((student.getScoreOnExam(examId)));

		}

		double averageScore = scores / totalNumberOfExams;

		return averageScore;

	}

	/**
	 * This method will iterate through a list of students sorted in 
	 * alphabetical order and will retrieve their number grade and their letter
	 * grade as well. It will return a string representation of each student's
	 * grades for the course
	 * 
	 * @return courseGrade of each student
	 */
	public String getCourseGrades() {
		StringBuffer courseGradeReport = new StringBuffer("");
		Collections.sort(listOfStudents);
		for (Student student : listOfStudents) {
			courseGradeReport.append(student.getName() + " ");
			courseGradeReport.append(student.getTotalNumericGrade() + " ");
			courseGradeReport.append(
					student.getGrade(possibleGrades, gradeCutoffs) + "\n");

		}

		return courseGradeReport.toString();

	}
	
	/**
	 * This method will retrieve the letter grade of a particular student by
	 * accessing the Map of students and the determined grade that the student
	 * has
	 * 
	 * @param studentName
	 * @return letterGrade
	 */
	public String getCourseLetterGrade(String studentName) {
		String letterGrade;

		if (!mapOfStudents.containsKey(studentName)) {
			System.out.println("Student Not Found!");
			return "Student Not Found";
		} 
		
		else {
			letterGrade = mapOfStudents.get(studentName)
					.getGrade(possibleGrades, gradeCutoffs);
			return letterGrade;
		}

	}

	/**
	 * This method will retrieve the numeric grade of a particular student by
	 * accessing the Map of students and the calculated grade that the student
	 * has
	 * 
	 * @param studentName
	 * @return numeric grade
	 */
	public double getCourseNumericGrade(String studentName) {
		
		if (!mapOfStudents.containsKey(studentName)) {
			System.out.println("Student Not Found!");
			return 0.0;
		} 
		
		else {
			return mapOfStudents.get(studentName).getTotalNumericGrade();
		}
	}

	/**
	 * This method will retrieve the exam score of a particular student if both
	 * exist. 
	 * 
	 * @param studentName
	 * @param examId
	 * @return 0.0 if neither student or exam exists. Otherwise, returning the 
	 * determined score that the student had on the exam
	 */
	public double getExamScore(String studentName, int examId) {
		double examScore = 0;

		if (!mapOfStudents.containsKey(studentName)
				|| !mapOfExams.containsKey(examId)) {
			return 0.0;
		} 
		
		else {
			examScore = mapOfStudents.get(studentName).getScoreOnExam(examId);
		}

		return examScore;
	}

	/**
	 * This method will retrieve the grade report of a particular student and
	 * exam 
	 * 
	 * @param studentName
	 * @param examId
	 * @return ExamReport
	 */
	public String getGradingReport(String studentName, int examId) {
		
		if (!mapOfStudents.containsKey(studentName)
				|| !mapOfExams.containsKey(examId)) {
			return "Student or Exam does not exist";
		} 
		
		else {
			return mapOfStudents.get(studentName).getExamReport(examId);
		}

	}

	/**
	 * This method will return the key of a particular exam
	 * 
	 * @param examId
	 * @return examKey
	 */
	public String getKey(int examId) {
		if (!(mapOfExams.containsKey(examId))) {
			return "Exam not found";
		} 
		
		else {
			return mapOfExams.get(examId).getExamKey();
		}
	}

	/**
	 * This method will iterate through the list of students and find the 
	 * largest value out of those students and return that value
	 * @param examId
	 * @return maxScore
	 */
	public double getMaxScore(int examId) {
		if (!(mapOfExams.containsKey(examId))) {
			return 0.0;
		}

		double greatestScore = 0.0;

		for (Student student : listOfStudents) {
			double currentScore = 0.0;
			if (student != null) {
				currentScore = student.getScoreOnExam(examId);
			}

			if (currentScore >= greatestScore) {
				greatestScore = currentScore;
			}
		}

		return greatestScore;
	}

	/**
	 * This method will iterate through the list of students and find the 
	 * smallest value out of those students and return that value
	 * @param examId
	 * @return minScore
	 */
	public double getMinScore(int examId) {
		if (!(mapOfExams.containsKey(examId))) {
			return 0.0;
		}

		double lowestScore = listOfStudents.get(0).getScoreOnExam(examId);

		for (Student student : listOfStudents) {
			double currentScore;
			currentScore = student.getScoreOnExam(examId);

			if (currentScore <= lowestScore) {
				lowestScore = currentScore;
			}
		}

		return lowestScore;
	}

	/**
	 * This method will return a Manager object that was saved into the file
	 * name that was passed in as a String
	 * 
	 * @param fileName
	 * @return Manager
	 */
	public Manager restoreManager(String fileName) {
		try {
			FileInputStream readFile = new FileInputStream(fileName);
			ObjectInputStream readObjectFile = new ObjectInputStream(readFile);

			Manager restored = (Manager) readObjectFile.readObject();

			readObjectFile.close();

			return restored;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method will save the Manager object passed in to the fileName so 
	 * that it can be retrieved and accessed later
	 * 
	 * @param manager
	 * @param fileName
	 */
	public void saveManager(Manager manager, String fileName) {
		File newFile = new File(fileName);
		try {
			ObjectOutputStream fileOutput = new ObjectOutputStream(
					new FileOutputStream(newFile));
			fileOutput.writeObject(manager);
			fileOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
