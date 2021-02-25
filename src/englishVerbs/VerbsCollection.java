package englishVerbs;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class VerbsCollection {

	private Set<Verb> verbsCollection;

	public VerbsCollection() {
		this.verbsCollection = new TreeSet<>();
	}

	public VerbsCollection(Set<Verb> verbsCollection) {
		this.verbsCollection = verbsCollection;
	}

	public Set<Verb> getVerbsCollection() {
		return verbsCollection;
	}

	public void setVerbsCollection(Set<Verb> verbsCollection) {
		this.verbsCollection = verbsCollection;
	}

	public boolean merge(VerbsCollection verbsCollectionToMerge) {
		if (verbsCollection.addAll(verbsCollectionToMerge.getVerbsCollection())) {
			return true;
		}
		return false;
	}

	public boolean addVerbToCollection(Verb verb) {
		if (verbsCollection.add(verb)) {
			System.out.println("Verb succesfully added.");
			return true;
		} else {
			System.out.println("This verb is already in the collection (checked by infinitive).");
			return false;
		}
	}

	public boolean addBrandNewVerb(Scanner scanner) {
		List<String> formsOfVerb = new ArrayList<>();

		String[] verbFormsDescription = { "translation", "infinitive form", "Past Tense(2nd) form",
				"Past Participle(3rd) form" };

		for (int i = 0; i <= verbFormsDescription.length - 1; i++) {
			System.out.println("Type " + verbFormsDescription[i] + " for a new verb "
					+ "separating possible diffferent meanings by a forward slash sign \"/\" : ");
			String inputWithForm = scanner.nextLine().toLowerCase();
			if (!isInputForNewVerbValid(inputWithForm)) {
				System.out.println("Wrong input.");
				i--;
				continue;
			}
			formsOfVerb.add(inputWithForm);
		}

		return addVerbToCollection(
				new Verb(formsOfVerb.get(0), formsOfVerb.get(1), formsOfVerb.get(2), formsOfVerb.get(3)));
	}

	private boolean isInputForNewVerbValid(String input) {
		return input.matches("[\\p{L}/]+") && input.length() > 1;
	}

	boolean removeAllVerbs() {
		this.verbsCollection.clear();
		return verbsCollection.isEmpty();
	}

	boolean removeVerbByGivenInf(String infinitive) {
		return verbsCollection.removeIf(v -> v.getInfinitive().equals(infinitive));
	}

	boolean removeVerb(Scanner scanner) {
		System.out.println("Type infinitive of the verb you want to remove:");
		String infinitive = scanner.nextLine();
		if (removeVerbByGivenInf(infinitive)) {
			System.out.println("Succesfully removed verb from collection.");
			return true;
		} else {
			System.out.println("Collection does not contain this verb (checked by infinitive).");
			return false;
		}
	}

	public Set<Verb> getVerbsSortedByTranslation() {
		Set<Verb> verbsSortedByTranslation = new TreeSet<Verb>(new Comparator<Verb>() {
			private Collator collator = Collator.getInstance(Locale.getDefault());

			@Override
			public int compare(Verb v1, Verb v2) {
				return collator.compare(v1.getTranslation(), v2.getTranslation());
			}
		});
		verbsSortedByTranslation.addAll(verbsCollection);
		return verbsSortedByTranslation;
	}

	Verb getOneRandomVerb() {
		Verb[] arrayOfVerbs = getArrayOfVerbs();
		int rnd = new Random().nextInt(arrayOfVerbs.length);
		return arrayOfVerbs[rnd];
	}

	public Verb[] getArrayOfVerbs() {
		Verb[] arrayOfVerbs = verbsCollection.toArray(new Verb[verbsCollection.size()]);
		return arrayOfVerbs;
	}
	
	public void printVerbsByInfinitive() {
		verbsCollection.forEach(v -> System.out.println(v.toString()));
	}

	public void printVerbsByTranslation() {
		getVerbsSortedByTranslation().forEach(v -> System.out.println(v.toStringByTranslation()));
	}
}
