package cmdLineInterpreter;

import java.util.*;

import onlineTest.SystemManager;

/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter {
	
	private static SystemManager newManager = new SystemManager();

	public static void main(String[] args) {
		
		// using the console
		// add a student
		// add an exam
		// add a true false question
		//answer a true false question
		// get exam score for a student
		
		Scanner newInput = new Scanner (System.in);
		Scanner newNumInput = new Scanner (System.in);
		
		//SystemManager newManager = new SystemManager();
		
		String menuTitle = new String("");
		
		menuTitle = "==========Welcome to Command Line Interpreter==========\n";
		
		System.out.println(menuTitle);
		
		String instruction = new String("");
		instruction ="To use the following menu, choose the appropriate number";
		
		instruction += " and type your input accordingly\n";
		
		instruction += "\nTo end use of menu, simply type \"End\"\n";
		
		instruction += "To choose a new menu option, type \"New\"\n";
		
		System.out.println(instruction);
		
		String choices = new String("");
		
		choices += "==========Choose The Following Menu Options==========\n\n";
		
		choices += "1) Add a Student -> [First Name] [Last Name]\n";
		choices += "2) Add an Exam -> [Exam ID] [Exam Name]\n";
		choices += "3) Add a True or False Question ->";
		choices += " [Exam ID] [Question Number] [Points] [Answer] [Text]\n";
		choices += "4) Answer a True or False Question ->";
		choices += " [Student Name] [Exam ID] [Question Number] [Answer]\n";
		choices += "5) Get Exam Score For a Student ->";
		choices += " [Student Name] [Exam ID]\n";
		
		System.out.println(choices);
		
		System.out.println("Number: ");
		int chosenNumber = newNumInput.nextInt();
		getMenuOption(chosenNumber, newManager);
		
		
		String endOrContinue = new String ("");
		
		while ((!(endOrContinue.equals("End")) || !(endOrContinue.equals("end"))) 
														&& newInput.hasNext()) {
			endOrContinue = newInput.nextLine(); 
			
			switch (endOrContinue) {
				
			case "End" :
			case "end" :
				try {
					newInput.close();
					newNumInput.close();
				} catch (Exception e) {
					System.out.println("End of menu");
				}
				System.out.println("Menu selection has ended");
				endOrContinue = "End";
				break;
			case "New" :
			case "new" :
				System.out.println("Number: ");
				chosenNumber = newNumInput.nextInt();
				getMenuOption(chosenNumber, newManager);
				break;
			default :
			}
		}
		
		
		
		
		
		
		
		
//		Scanner newInput = new Scanner (System.in);
//		SystemManager newManager = new SystemManager();
//		
//		System.out.println("Online Test Menu: ");
//		System.out.println("\n" + "Add a Student: ");
//		
//		String newStudent = newInput.nextLine();
//		
//		if (newStudent != null) {
//			newManager.addStudent(newStudent);
//		}
//		
//		System.out.println("\n" + "Add an Exam: ");
//		System.out.println("Add Exam Number: ");
//		
//		Scanner newNumInput = new Scanner (System.in);
//		
//		int newExamNum = newNumInput.nextInt();
//		
//		System.out.println("Add Exam Name: ");
//		
//		String newExamName = newInput.nextLine();
//		
//		if (newExamNum > 0 && newExamName != null) {
//			newManager.addExam(newExamNum, newExamName);
//		}
//		
//		System.out.println("Number of Questions: ");
//		
//		int numOfQuestions = newNumInput.nextInt();
//		
//		double totalExamScore = 0.0;
//		
//		for (int i = 0; i < numOfQuestions; i++) {
//			int questionNum = i+1;
//		
//			System.out.println("Add True or False Question: ");
//			
//			String newQuestion = newInput.nextLine();
//			
//			System.out.println("Correct Answer: ");
//			
//			//String correctAnswer = newInput.nextLine();
//			
//			//boolean trueOrFalse = (correctAnswer.contains("true")? true : false);
//			Scanner newBooleanInput = new Scanner (System.in);
//			
//			boolean trueOrFalse = newBooleanInput.nextBoolean();
//			
//			System.out.println("Number of Points: ");
//			
//			Scanner newDoubleInput = new Scanner (System.in);
//			
//			double numberOfPoints = newDoubleInput.nextDouble();
//			
//			totalExamScore += numberOfPoints;
//			
//			if (newQuestion != null) {
//				newManager.addTrueFalseQuestion(newExamNum, questionNum, 
//									newQuestion, numberOfPoints, trueOrFalse);
//			}
//		}
//		
//		for (int i = 0; i < 10; i++) {
//			System.out.println("///////////");
//		}
//		
//		
//		System.out.println("Student answer sheet: ");
//		
//		
//		
//		for (int i = 0; i < numOfQuestions; i++) {
//			int questionNum = i+1;
//			System.out.println("Question " + questionNum + ": ");
//			Scanner newBooleanInput = new Scanner (System.in);
//			
//			boolean studentAnswer = newBooleanInput.nextBoolean();
//			
//			newManager.answerTrueFalseQuestion(newStudent, newExamNum,
//													questionNum, studentAnswer);
//			
//		}
//		
//		
//		System.out.println("To Submit Exam, type Yes: ");
//		
//		String submitExam = newInput.nextLine();
//		
//		
//		
//		if (submitExam.equals("yes") || submitExam.equals("Yes")) {
//			double examScore = newManager.getExamScore(newStudent, newExamNum);
//			System.out.println(newStudent + "'s Exam Score for Exam #" + newExamNum
//								+ " is: " + examScore + " out of " + totalExamScore);
//		}
//		
//		
		
		
		
		

	}
	
	private static void getMenuOption(int chosenNumber, 
													SystemManager newManager) {
		
		switch (chosenNumber) {
		
		case 1 :
			Scanner studentScanner = new Scanner (System.in);
			System.out.println("Add a Student: ");
			String studentName = new String ("");
			
			try {
				studentName = studentScanner.nextLine();
			} catch (Exception e) {
				System.out.println("Please type according to menu format");
			}
			
			newManager.addStudent(studentName);
			System.out.println("\n===End or New?===");
			//studentScanner.close();
			
			break;
		case 2 : 
			Scanner examScanner1 = new Scanner (System.in);
			//Scanner examScanner2 = new Scanner (System.in);
			
			System.out.println("Add an Exam: ");
			
			int examID = 0;
			String examName = new String ("");
			
			try {
				examID = examScanner1.nextInt();
				examName = examScanner1.nextLine();
			} catch (Exception e) {
				System.out.println("Please type according to menu format");
			}
			
			newManager.addExam(examID, examName);
//			examScanner1.close();
//			examScanner2.close();
			
			System.out.println("\n===End or New?===");
			
			break;
		case 3 : 
//			Scanner numScan = new Scanner (System.in);
//			Scanner textScan = new Scanner (System.in);
//			Scanner pointScan = new Scanner (System.in);
//			Scanner answerScan = new Scanner (System.in);
			
			Scanner scanner2 = new Scanner (System.in);
			
			System.out.println("Add a True or False Question: ");
			
			int examIDNum = 0;
			int questionNum = 0;
			String text = new String ("");
			double points = 0.0;
			boolean answer = false;
			
			try {
				examIDNum = scanner2.nextInt();
				questionNum = scanner2.nextInt();
				try {
					points = scanner2.nextDouble();
				} catch (Exception e) {
				//	e.printStackTrace();
					System.out.println("Please type point value as a double");
					System.out.print(" such as 0.0 or 10.0");
				}
				
				try {
					answer = scanner2.nextBoolean();
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.print("Please type either true or false");
				}
				
				text = scanner2.nextLine();
				
			} catch (Exception e) {
				System.out.println("Please type according to menu format");
				//e.printStackTrace();
			}
			
			newManager.addTrueFalseQuestion(examIDNum, questionNum, text, 
																points, answer);
			
//			numScan.close();
//			textScan.close(); 
//			pointScan.close();
//			answerScan.close();
			
			System.out.println("\n===End or New?===");
					
			break;
		case 4 : 
			// [Student Name] [Exam ID] [Question Number] [Answer]
			
			//Scanner scanner3 = new Scanner (System.in);
		//	Scanner scanner3 = new Scanner (System.in);
			Scanner scanner3 = new Scanner (System.in);
			
			System.out.println("Answer a True or False Question: ");
			
			String student = new String ("");
			int examNum = 0;
			int questionNumber = 0;
			boolean studentAnswer = false;
			
			try {
				student = scanner3.next() + " " + scanner3.next();
				//student = scanner3.next();
				examNum = scanner3.nextInt();
				questionNumber = scanner3.nextInt();
				
				
				try {
					studentAnswer = scanner3.nextBoolean();
				} catch (Exception e) {
					//e.printStackTrace();
					System.out.print("Please type either true or false");
				}
				
			} catch (Exception e) {
				System.out.println("Please type according to menu format");
				//e.printStackTrace();
			}
			
			newManager.answerTrueFalseQuestion(student, examNum, 
												questionNumber, studentAnswer);
			
//			newScanner.close();
//			newNumScanner.close();
//			boolScanner.close();
			
			System.out.println("\n===End or New?===");
			
			break;
		case 5 : 
			//[Student Name] [Exam ID] 
			
			Scanner scanner4 = new Scanner (System.in);
			
			System.out.println("Get Exam Score For a Student: \n");
			
			String studentName2 = new String ("");
			int finalExamNum = 0;
			
			try {
				studentName2 = scanner4.next() + " " + scanner4.next();
				//studentName2 = scanner4.next();
				finalExamNum = scanner4.nextInt();
				
			} catch (Exception e) {
				System.out.println("Please type according to menu format");
				//e.printStackTrace();
			}
			
			double examScore = 0.0;
			examScore = newManager.getExamScore(studentName2, finalExamNum);
			
			System.out.println(studentName2 + "'s Exam Score for Exam #" +
						finalExamNum + " is " + examScore);
			
		//	System.out.println(newStudent + "'s Exam Score for Exam #" + newExamNum
//					+ " is: " + examScore + " out of " + totalExamScore);
			
			System.out.println("\n===End or New?===");
			
			//finalScan.close();
			
			break;
		}
		
	}
}
