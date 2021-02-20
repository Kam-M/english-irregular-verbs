package englishVerbs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	private static VerbsCollection verbsCollection;
	private static Quiz quiz;
	private static String filePath;
	private static Scanner scanner;
	private static boolean isUpdatedCollection;

	public static void main(String[] args) throws IOException {

		Main main = new Main();

		filePath = new File("").getAbsolutePath() + File.separator + "irregular-verbs.txt";
		verbsCollection = new VerbsCollection(main.getVerbsFromFile(filePath));
		quiz = new Quiz(verbsCollection);
		scanner = new Scanner(System.in);

		System.out.println("Welcome to ENGLISH-IRREGULAR-VERBS APP!\n"
				+ "This is not a complete list of all irregular english verbs.\nBut it is constantly being expanded...");

		while (true) {
			System.out.println("****************************************************************\n"
					+ "Please enter key:\n1. Print all verbs sorted alphabetically by infinitive.\n"
					+ "2. Print all verbs sorted alphabetically by translation.\n"
					+ "3. Print a random verb to learn.\n" + "4. Print five random verbs to learn.\n"
					+ "5. QUIZ (enter the correct form of the randomly given verb).\n"
					+ "6. Add a new verb to the collection.\n" + "7. Remove particular verb from the collection.\n"
					+ "8. Quit app.");

			String userInput = scanner.nextLine();

			if (userInput.equals("1")) {
				verbsCollection.printVerbsByInfinitive();
				continue;
			} else if (userInput.equals("2")) {
				verbsCollection.printVerbsByTranslation();
				continue;
			} else if (userInput.equals("3")) {
				quiz.printOneRandomVerbToLearn();
				continue;
			} else if (userInput.equals("4")) {
				quiz.printFiveRandomVerbsToLearn();
				continue;
			} else if (userInput.equals("5")) {
				quiz.typeCorrectFormOfVerb(scanner);
				continue;
			} else if (userInput.equals("6")) {
				isUpdatedCollection = verbsCollection.addVerb(scanner);
				continue;
			} else if (userInput.equals("7")) {
				isUpdatedCollection = verbsCollection.removeVerb(scanner);
				continue;
			} else if (userInput.equals("8")) {
				if (isUpdatedCollection) {
					main.updateSourceFile(filePath, verbsCollection);
				}
				System.out.println("Thank you for learning! Bye!");
				break;
			} else {
				System.out.println("Invalid command...");
			}
		}
	}

	private Set<Verb> getVerbsFromFile(String filePath) {
		Stream<String> streamOfVerbs;
		Set<Verb> verbs = null;
		try {
			streamOfVerbs = Files.lines(Paths.get(filePath));
			verbs = streamOfVerbs
					.map(stream -> stream.strip())
					.map(s -> s.split(","))
					.map(array -> new Verb(array[0], array[1], array[2], array[3]))
					.collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return verbs;
	}

	private void updateSourceFile(String filePath, VerbsCollection verbsCollection) {
		Set<Verb> verbsSortedByTranslation = verbsCollection.getVerbsSortedByTranslation();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
			for (var verb : verbsSortedByTranslation) {
				writer.write(verb.verbToOneSqueezedString() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
