package englishVerbs;

import java.io.IOException;
import java.util.Scanner;

public class CollectionManager {

	private VerbsCollection mainCollection;
	private VerbsCollection learntCollection;
	private FileHandler fileHandler;
	private boolean needToUpdateSourceFiles;

	public void startLearning(String filePathMainCollection, String filePathLearntVerbs) {
		loadVerbsFromSource(filePathMainCollection, filePathLearntVerbs);
		enterMainMenu();

		if (needToUpdateSourceFiles) {
			updateVerbsInSourceFiles(filePathMainCollection, filePathLearntVerbs);
		}
	}

	public void loadVerbsFromSource(String filePathMainCollection, String filePathLearntVerbs) {
		System.out.println("Loading verbs from source files...");

		fileHandler = new FileHandler();

		try {
			mainCollection = new VerbsCollection(fileHandler.getDataFromFile(filePathMainCollection));
			learntCollection = new VerbsCollection(fileHandler.getDataFromFile(filePathLearntVerbs));
		} catch (IOException e) {
			System.err.println("Cannot load verbs properly -> check source files. Shutting down...");
			return;
		}
	}

	public void updateVerbsInSourceFiles(String filePathMainCollection, String filePathLearntVerbs) {
		System.out.println("Updating source files...");
		fileHandler.writeDataToFile(filePathMainCollection, mainCollection);
		fileHandler.writeDataToFile(filePathLearntVerbs, learntCollection);
	}

	private void askForSortingType(Scanner scanner, VerbsCollection verbsCollection) {
		System.out.println("Sorted alphabetically:\n1.By infinitive.\n2.By translation.");
		String userInput = scanner.nextLine();
		switch (userInput) {
		case "1":
			verbsCollection.printVerbsByInfinitive();
			break;
		case "2":
			verbsCollection.printVerbsByTranslation();
			break;
		default:
			System.out.println("Incorrect input. Going back to MAIN MENU.");
		}
	}

	private void askToMarkVerbAsLearnt(Scanner scanner, Verb verb) {
		System.out.println(
				"Mark verb as learnt (move it to Learn List)?\n1. Yes, of course.\n2. No, I would like to leave it in Main List.");
		String userInput = scanner.nextLine();
		switch (userInput) {
		case "1":
			learntCollection.addVerbToCollection(verb);
			mainCollection.removeVerbFromCollection(verb.getInfinitive());
			break;
		case "2":
			System.out.println("Going back to MAIN MENU.");
			break;
		default:
			System.out.println("Incorrect input. Going back to MAIN MENU.");
		}
	}

	private int askForCollectionToPlay(Scanner scanner) {
		System.out.println("Choose a source for generating verbs to play Quiz:\n1. Main List.\n2. Learnt List.");
		String input = scanner.nextLine();
		switch (input) {
		case "1":
			System.out.println("Preparing MAIN LIST QUIZ...");
			return 0;
		case "2":
			System.out.println("Preparing LEARNT LIST QUIZ...");
			return 1;
		default:
			System.out.println("Invalid input. Going back to MAIN MENU...");
			return -1;
		}
	}

	public int startQuiz(int chosenList, Scanner scanner) {
		int score = 0;
		if (chosenList < 0) {
			System.out.println("Cannot run QUIZ. Going back to MAIN MENU...");
			return score;
		} else if (chosenList == 0) {
			score = new Quiz(mainCollection).typeCorrectFormOfVerb(scanner);
		} else if (chosenList == 1) {
			score = new Quiz(learntCollection).typeCorrectFormOfVerb(scanner);
		}
		return score;
	}

	public void enterMainMenu() {

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("****************************************************************\n"
					+ "\t\t\t\t MAIN MENU\n"
					+ "Please enter number from below and confirm pressing Enter:\n1. View all verbs from the Main List (unlearnt verbs).\n"
					+ "2. View all verbs from the Learnt List (verbs marked as learnt).\n"
					+ "3. Print a random verb to learn from Main List.\n"
					+ "4. Add a brand new verb to the Main List.\n"
					+ "5. Remove completely a specific verb from the Main List (operation cannot be undone!).\n"
					+ "6. Clear entire Learnt List -> move all verbs back to Main List\n" + "7. Enter QUIZ MENU.\n"
					+ "8. Quit app.");

			String userInput = scanner.nextLine();

			if (userInput.equals("1")) {
				askForSortingType(scanner, mainCollection);
				continue;
			} else if (userInput.equals("2")) {
				askForSortingType(scanner, learntCollection);
				continue;
			} else if (userInput.equals("3")) {
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				Verb randomVerb = mainCollection.getOneRandomVerbToLearn();
				System.out.println(randomVerb + "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				askToMarkVerbAsLearnt(scanner, randomVerb);
				continue;
			} else if (userInput.equals("4")) {
				needToUpdateSourceFiles = mainCollection.addNewVerb(scanner);
				continue;
			} else if (userInput.equals("5")) {
				needToUpdateSourceFiles = mainCollection.removeVerb(scanner);
				continue;
			} else if (userInput.equals("6")) {
				boolean merged = mainCollection.merge(learntCollection);
				boolean removed = learntCollection.removeAllVerbsFromCollection();
				if ( merged && removed ) {
					System.out.println("Succesfully moved verbs beetween lists. Learnt List is empty.");
				}
				else {
					System.out.println( "&&&&&&&&&&&&&&&&&&&");
				}
				continue;
			} else if (userInput.equals("7")) {
				int chosenList = askForCollectionToPlay(scanner);
				int score = startQuiz(chosenList, scanner);
				System.out.println("Shutting down quiz... Your today's final score: " + score);
				continue;
			} else if (userInput.equals("8")) {
				break;
			} else {
				System.out.println("Invalid command... Type a number and press ENTER");
			}
		}
	}

}
