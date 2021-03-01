package englishVerbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class RandomVerbQuiz implements IQuizable {

	private Scanner scanner;
	private VerbsCollection verbsCollection;
	private int score = 0;
	private int attemptCount = 0;

	public RandomVerbQuiz(VerbsCollection verbsCollection) {
		this.verbsCollection = verbsCollection;
	}

	public int startQuiz() {
		Set<Verb> verbsCopySet = new TreeSet<>(); // THINK ABOUT IT! 
		verbsCopySet.addAll(this.verbsCollection.getVerbsCollection());
		VerbsCollection verbsCopy = new VerbsCollection(verbsCopySet);
		
		scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nChoose and confirm pressing Enter:\n1. Start round. \n2. Back to MAIN MENU.");

			if (scanner.nextLine().equals("2")) {
				break;
			}
			if (verbsCopy.getVerbsCollection().size() < 1) {
				System.out.println("You already viewed all available verbs in the list!");
				break;
			}
			
			Verb drawnVerb = playWithRandomVerb(verbsCopy);
			verbsCopy.removeVerbByGivenInf(drawnVerb.getInfinitive());
		}
		return score;
	}

	private boolean isAnswerCorrect(String input, String[] verbForm) {
		for (var form : verbForm) {
			if (form.equals(input))
				return true;
		}
		return false;
	}

	private Verb playWithRandomVerb(VerbsCollection verbsCopy) {
		Verb randomVerb = verbsCopy.getOneRandomVerb();
		List<String> verbFormsList = new ArrayList<>(randomVerb.getVerbFormsIntoList());
		String translation = verbFormsList.remove(0);

		int randomIndex = new Random().nextInt(verbFormsList.size());
		String[] randomFormOfVerb = verbFormsList.get(randomIndex).split("/"); // splitting because some verbs has more
																				// than one meaning in specific form
		String userAnswer = askForSpecificFormOfVerb(translation, randomIndex);

		if (isAnswerCorrect(userAnswer, randomFormOfVerb)) {
			System.out.println("\n***Correct!*** Your score is " + ++score + " in " + ++attemptCount + " attempts.");
		} else {
			System.out.println("\n...Incorrect... \n" + "Hint: " + randomVerb + "\n" + "Your score is " + score + " in "
					+ ++attemptCount + " attempts.");
		}
		return randomVerb;
	}

	private String askForSpecificFormOfVerb(String translation, int randomIndex) {
		System.out.print(
				"\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ QUIZ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\nRandomly generated verb(translation) is: -- "
						+ translation + " -- \n" + "Please type the correct ");

		switch (randomIndex) {
		case 0:
			System.out.println("infinitive form of the verb:");
			break;
		case 1:
			System.out.println("Past Tense(2nd) form(one of the correct forms) of the verb:");
			break;
		case 2:
			System.out.println("Past Participle(3rd) form(one of the correct forms) of the verb:");
			break;
		default:
			System.out.println("Cannot reach specific form...");
			break;
		}
		return scanner.nextLine();
	}
}
