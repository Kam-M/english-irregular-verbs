package englishVerbs;

import java.util.Scanner;

public class QuizManager {

	private IQuizable quiz;
	private VerbsCollection mainCollection;
	private VerbsCollection learntCollection;
	
	public QuizManager(VerbsCollection main, VerbsCollection learnt) {
		this.mainCollection = main;
		this.learntCollection = learnt;
	}

	public int launchQuiz() {
		VerbsCollection chosenCollection = askForCollection(new Scanner(System.in));
		quiz = new RandomVerbQuiz(chosenCollection);
		int score = 0;
		if ((chosenCollection != null) && (chosenCollection.getVerbsCollection().size() > 1)) {
			score = quiz.startQuiz();
		}else {
			System.out.println("It is impossible to launch Quiz with this list (it must contain at least 2 or more verbs).");
		}
		return score;
	}
	
	private VerbsCollection askForCollection(Scanner scanner) {
		System.out.println("Select a source for Quiz:\n1. Main List.\n2. Learnt List.");
		String input = scanner.nextLine();
		switch (input) {
		case "1":
			System.out.println("Preparing MAIN LIST for QUIZ...");
			return this.mainCollection;
		case "2":
			System.out.println("Preparing LEARNT LIST for QUIZ...");
			return this.learntCollection;
		default:
			return null;
		}
	}

	
}
