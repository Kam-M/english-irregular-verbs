package englishVerbs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Quiz {

	private VerbsCollection verbsCollection;
	private int attemptCount;
	private int score;

	public Quiz(VerbsCollection verbsCollection) {
		this.verbsCollection = verbsCollection;
	}

	public void printOneRandomVerbToLearn() {
		Verb[] arrayOfVerbs = verbsCollection.getArrayOfVerbs();
		System.out.println(verbsCollection.getRandomVerb(arrayOfVerbs));
	}

	public void printFiveRandomVerbsToLearn() {
		Verb[] arrayOfVerbs = verbsCollection.getArrayOfVerbs();
		Set<Verb> randomGeneratedVerbsToLearn = new HashSet<>();
		for (; randomGeneratedVerbsToLearn.size() <= 4;) {
			randomGeneratedVerbsToLearn.add(verbsCollection.getRandomVerb(arrayOfVerbs));
		}
		randomGeneratedVerbsToLearn.forEach(System.out::println);
	}

	public void typeCorrectFormOfVerb(Scanner scanner) {
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

		String input = scanner.nextLine();
		if (isCorrectAnswer(input, randomFormOfVerb)) {
			System.out.println("\n***Correct!*** Your score is " + ++score + " in " + ++attemptCount + " attempts.");
		} else {
			System.out.println("\n...Incorrect... \n" + "Hint: " + randomVerb + "\n" + "Your score is " + score
					+ " in " + ++attemptCount + " attempts.");
		}
	}

	private boolean isCorrectAnswer(String input, String[] randomForm) {
		for (var f : randomForm) {
			if (f.equals(input))
				return true;
		}
		return false;
	}
}
