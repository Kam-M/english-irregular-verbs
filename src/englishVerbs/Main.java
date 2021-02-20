package englishVerbs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	private static VerbsCollection verbsCollection;
	private static Quiz quiz;
	private static File file;
	private static Scanner scanner;
	private static boolean wasOverwrite;

	public static void main(String[] args) {

		Main main = new Main();
		file = new File("./irregular-verbs.txt");
		verbsCollection = new VerbsCollection(main.getVerbsFromExternalSource(file));
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
				verbsCollection.printVerbsCollectionByInfinitive();
				continue;
			} else if (userInput.equals("2")) {
				verbsCollection.printVerbsCollectionByTranslation();
				continue;
			} else if (userInput.equals("3")) {
				quiz.printOneRandomVerbToLearn();
				continue;
			} else if (userInput.equals("4")) {
				quiz.printFiveRandomVerbsToLearn();
				continue;
			} else if (userInput.equals("5")) {
				quiz.quizRandomFormOfVerb(scanner);
				continue;
			} else if (userInput.equals("6")) {
				wasOverwrite = verbsCollection.addVerb(scanner);
				continue;
			} else if (userInput.equals("7")) {
				wasOverwrite = verbsCollection.removeVerb(scanner);
				continue;
			} else if (userInput.equals("8")) {
				if (wasOverwrite) {
					main.changeFile(file, verbsCollection);
				}
				System.out.println("Thank you for learning! Bye!");
				break;
			} else {
				System.out.println("Invalid command...");
			}
		}
	}

	private List<String[]> getVerbEntriesFromFile(File file) throws IOException {
		List<String[]> linesOfVerbs = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			while (line != null) {
				String[] splitLines = line.split(",");
				linesOfVerbs.add(splitLines);
				line = reader.readLine();
			}
		}
		return linesOfVerbs;
	}

	private Set<Verb> getVerbsFromExternalSource(File file) {
		Set<Verb> verbsCollection = new TreeSet<>();

		try {
			List<String[]> arraysWithVerbs = getVerbEntriesFromFile(file);
			for (var verbPart : arraysWithVerbs) {
				Verb verb = new Verb(verbPart[0], verbPart[1], verbPart[2], verbPart[3]);
				verbsCollection.add(verb);
			}
		} catch (IOException e) {
			System.out.println("File not found");
		}
		return verbsCollection;
	}

	private void changeFile(File file, VerbsCollection verbsCollection) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (var verb : verbsCollection.getArrayOfVerbs()) {
				String verbToOneString = verb.verbToOneLineString(verb);
				writer.write(verbToOneString);
				writer.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
