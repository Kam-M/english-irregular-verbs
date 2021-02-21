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

public class Main {

	private static VerbsCollection verbsCollection;
	private static Quiz quiz;
	private static String filePath;
	private static Scanner scanner;
	private static boolean isUpdatedCollection;

	public static void main(String[] args) {

		Main main = new Main();

		System.out.println("Welcome to ENGLISH-IRREGULAR-VERBS APP!\n"
				+ "This is not a complete list of all irregular english verbs.\nBut it is constantly being expanded :)\n\n"
				+ "Loading verbs from file...");

		filePath = new File("").getAbsolutePath() + File.separator + "irregular-verbs.txt";
		
		try {
			verbsCollection = new VerbsCollection(main.getVerbsFromFile(filePath));
		} catch (IOException e) {
			System.err.println("Cannot load file properly -> check file \"." + File.separator + "irregular-verbs.txt\"\n"
					+ "Shutting down...");
			System.exit(0);
		}
		
		quiz = new Quiz(verbsCollection);
		scanner = new Scanner(System.in);

		while (true) {
			System.out.println("****************************************************************\n"
					+ "\t\t\t\t MENU\n"
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
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				quiz.printOneRandomVerbToLearn();
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				continue;
			} else if (userInput.equals("4")) {
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				quiz.printFiveRandomVerbsToLearn();
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
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
					main.updateSourceFileWithSortedVerbs(filePath, verbsCollection);
				}
				System.out.println("Thank you for learning! Bye!");
				break;
			} else {
				System.out.println("Invalid command...");
			}
		}
	}

	private Set<Verb> getVerbsFromFile(String filePath) throws IOException{
		return Files.lines(Paths.get(filePath))
				.map(streamOfStrings -> streamOfStrings.strip())
				.map(oneLineString -> oneLineString.split(","))
				.map(arrayWithVerb -> new Verb(arrayWithVerb[0], arrayWithVerb[1], arrayWithVerb[2], arrayWithVerb[3]))
				.collect(Collectors.toCollection(TreeSet::new));
	}

	private void updateSourceFileWithSortedVerbs(String filePath, VerbsCollection verbsCollection) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
			for (var verb : verbsCollection.getVerbsSortedByTranslation()) {
				writer.write(verb.verbToOneSqueezedString() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
