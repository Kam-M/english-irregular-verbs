package englishVerbs;

import java.util.Scanner;

public class QuizManager {

	private Quiz quiz;

	public int startQuiz(VerbsCollection verbsCollection) {
		quiz = new Quiz(verbsCollection);
		int score = 0;
		if ((verbsCollection != null) && (isCollectionFilledUp(verbsCollection))) {
			score = quiz.typeCorrectFormOfVerb(new Scanner(System.in));
		}else {
			System.out.println("It is impossible to launch Quiz with this list (it must contain at least 2 or more verbs).");
		}
		return score;
	}

	private boolean isCollectionFilledUp(VerbsCollection verbsCollection) {
		return verbsCollection.getVerbsCollection().size() > 1;
	}
}
