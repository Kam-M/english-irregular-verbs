package englishVerbs;

import java.util.Scanner;

public class CollectionManager {

	private VerbsCollection mainCollection;
	private VerbsCollection learntCollection;
	private Dao dataHandler;
	private boolean needToUpdateSourceFiles;

	public void learn() {
		
		loadVerbsFromSource();
		enterMainMenu();
		
		if (needToUpdateSourceFiles) {
			updateVerbsInSource();
		}
	}
	
	private void loadVerbsFromSource() {
		System.out.println("Loading verbs from source files...");
		dataHandler = new FileHandler(mainCollection, learntCollection);

		mainCollection = new VerbsCollection(dataHandler.readMainCollectionFromSource());
		learntCollection = new VerbsCollection(dataHandler.readLearntCollectionFromSource());
	}

	private void enterMainMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("**********************************************************************************\n"
					+ "\t\t\t\t MAIN MENU\n"
					+ "Choose number from below and confirm pressing Enter:\n1. View all verbs from the Main List (unlearnt verbs).\n"
					+ "2. View all verbs from the Learnt List (verbs marked as learnt).\n"
					+ "3. Print a random verb to learn from Main List.\n"
					+ "4. Add a brand new verb to the Main List.\n"
					+ "5. Remove completely a specific verb from the Main List (operation cannot be undone!).\n"
					+ "6. Clear entire Learnt List -> move all it's verbs back to Main List\n" + "7. Enter QUIZ MENU.\n"
					+ "8. Quit app.");

			String userInput = scanner.nextLine();

			switch (userInput) {
			case "1":
				displayCollection(mainCollection, askForSorting(scanner));
				continue;
			case "2":
				displayCollection(learntCollection, askForSorting(scanner));
				continue;
			case "3":
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				Verb randomVerb = mainCollection.getOneRandomVerb();
				System.out.println(randomVerb + "\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n");
				needToUpdateSourceFiles = askToMarkVerbAsLearnt(scanner, randomVerb);
				continue;
			case "4":
				needToUpdateSourceFiles = mainCollection.addBrandNewVerb(scanner);
				continue;
			case "5":
				needToUpdateSourceFiles = mainCollection.removeVerb(scanner);
				continue;
			case "6":
				mainCollection.merge(learntCollection);
				if (learntCollection.removeAllVerbs()) {
					System.out.println("Learnt List cleared!");
				}
				needToUpdateSourceFiles = true;
				continue;
			case "7":
				int score = new QuizManager(mainCollection, learntCollection).launchQuiz();
				System.out.println("Shutting down QUIZ...\nTOTAL SCORE: " + score + ".");
				continue;
			case "8":
				scanner.close();
				running = false;
				break;
			default:
				System.out.println("Invalid command... Type a number and press ENTER");
			}
		}
	}

	private boolean askForSorting(Scanner scanner) {
		while (true) {
			System.out.println("Choose sorting type:\n1.By infinitive.\n2.By translation.");
			switch (scanner.nextLine()) {
			case "1":
				return false;
			case "2":
				return true;
			default:
				System.out.println("Incorrect input.\n");
			}
		}
	}

	private void displayCollection(VerbsCollection verbsCollection, boolean displayByTranslation) {
		if (verbsCollection.getVerbsCollection().size() < 1) {
			System.out.println("List is empty!\n");
			return;
		} else if (displayByTranslation) {
			System.out.println("************************************************************************");
			verbsCollection.printVerbsByTranslation();
		} else {
			System.out.println("************************************************************************");
			verbsCollection.printVerbsByInfinitive();
		}
	}

	private boolean askToMarkVerbAsLearnt(Scanner scanner, Verb verb) {
		while (true) {
			System.out.println(
					"Mark verb as learnt (move it to Learn List)?\n1. Yes, of course.\n2. No, I would like to leave it in Main List.");
			switch (scanner.nextLine()) {
			case "1":
				if (learntCollection.addVerbToCollection(verb)
						&& mainCollection.removeVerbByGivenInf(verb.getInfinitive())) {
					System.out.println("Verb succesfully marked as learnt.\n");
					return true;
				}
			case "2":
				System.out.println("Going back to MAIN MENU.\n");
				return false;
			default:
				System.out.println("Incorrect input.\n");
			}
		}
	}

	private void updateVerbsInSource() {
		System.out.println("Updating source files...");
		dataHandler = new FileHandler(mainCollection, learntCollection);

		if (mainCollection.getVerbsCollection() != null) {
			dataHandler.saveMainCollection();
		}
		if (learntCollection.getVerbsCollection() != null) {
			dataHandler.saveLearnCollection();
		}
	}
}
