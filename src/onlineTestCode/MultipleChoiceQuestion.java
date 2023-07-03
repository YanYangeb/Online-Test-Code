package onlineTest;

import java.util.Comparator;

/**
 * This class represents a Multiple Choice Question object that extends the 
 * Question class. A multiple choice question will be set up similarly to a 
 * fill in the blank question. The only difference between the multiple choice
 * question and fill in the blank is how the questions will be graded. 
 * 
 * @author yanitgeb
 *
 */
public class MultipleChoiceQuestion extends Question {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * This constructor calls the super constructor and initializes the 
	 * instance variables in the parent class appropriately.
	 * 
	 * @param questionNumber
	 * @param text
	 * @param points
	 */
	public MultipleChoiceQuestion(int questionNumber, String text,
			double points) {
		super(questionNumber, text, points);
		
	}

	/**
	 * This method overrides the parent class method because I do not want it 
	 * to compare each answer in the string array and deduct points accordingly.
	 * For a multiple choice question, it will be all or nothing for points.
	 * There is no partial credit offered as a fill in the blank question would
	 * offer. 
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
