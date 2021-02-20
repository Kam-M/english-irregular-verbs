package englishVerbs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Quiz {

	private VerbsCollection verbsCollection;
	private int quizCount;
	private int score;

	public Quiz(VerbsCollection verbsCollection) {
		this.verbsCollection = verbsCollection;
	}

	public void printOneRandomVerbToLearn() {
		Verb[] arrayOfVerbs = verbsCollection.getArrayOfVerbs();
		System.out.println(verbsCollection.getRandomVerb(arrayOfVerbs));
	}

	public void printFiveRandomVerbsToLearn() {
		Set<Verb> randomGeneratedVerbs = new HashSet<>();
		Verb[] arrayOfVerbs = verbsCollection.getArrayOfVerbs();
		for (; randomGeneratedVerbs.size() <= 4;) {
			randomGeneratedVerbs.add(verbsCollection.getRandomVerb(arrayOfVerbs));
		}
		randomGeneratedVerbs.forEach(System.out::println);
	}

	public void quizRandomFormOfVerb(Scanner scanner) {
		Verb verb = verbsCollection.getRandomVerb(verbsCollection.getArrayOfVerbs());
		List<String> verbFormsIntoList = new ArrayList<>(verb.verbFormsIntoList(verb));

		String translation = verbFormsIntoList.remove(0);
		System.out.print("\t&&&&&&&&&&&&& QUIZ &&&&&&&&&&&&&\n\tRandomly generated verb(translation) is: -- "
				+ translation + " -- \n\t" + "Please type the correct ");

		int randomIndex = new Random().nextInt(verbFormsIntoList.size());
		String[] randomForm = verbFormsIntoList.get(randomIndex).split("/");

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

		if (isCorrectAnswer(input, randomForm)) {
			System.out.println("\t***Correct!*** Your score is " + ++score + " in " + ++quizCount + " attempts.");
		} else {
			System.out.println("\t...Incorrect... \n\t" + "Hint: " + verb + "\n\t" + "Your score is " + score + " in "
					+ ++quizCount + " attempts.");
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
