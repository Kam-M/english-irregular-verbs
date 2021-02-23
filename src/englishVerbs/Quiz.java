package englishVerbs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Quiz {

	private VerbsCollection verbsCollection;

	public Quiz(VerbsCollection verbsCollection) {
		this.verbsCollection = verbsCollection;
	}

	public int typeCorrectFormOfVerb(Scanner scanner) {

		int score = 0;
		int attemptCount = 0;

		while (true) {

			System.out.println("Wanna play?\n1. Yes.\n2. No, enough for me, go to MAIN MENU.");

			String input = scanner.nextLine();

			if (input.equals("2")) {
				break;
			}

			Verb randomVerb = verbsCollection.getRandomVerb(verbsCollection.getArrayOfVerbs());
			List<String> verbFormsList = new ArrayList<>(randomVerb.verbFormsIntoList());
			String translation = verbFormsList.remove(0);

			System.out.print(
					"\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ QUIZ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\nRandomly generated verb(translation) is: -- "
							+ translation + " -- \n" + "Please type the correct ");

			int randomIndex = new Random().nextInt(verbFormsList.size());
			String[] randomFormOfVerb = verbFormsList.get(randomIndex).split("/");
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
			}

			input = scanner.nextLine();
			if (isCorrectAnswer(input, randomFormOfVerb)) {
				System.out
						.println("\n***Correct!*** Your score is " + ++score + " in " + ++attemptCount + " attempts.");
			} else {
				System.out.println("\n...Incorrect... \n" + "Hint: " + randomVerb + "\n" + "Your score is " + score
						+ " in " + ++attemptCount + " attempts.");
			}
		}
		return score;
	}

	private boolean isCorrectAnswer(String input, String[] randomForm) {
		for (var f : randomForm) {
			if (f.equals(input))
				return true;
		}
		return false;
	}
}
