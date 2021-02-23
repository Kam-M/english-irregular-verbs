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
		return this.verbsCollection.addAll(verbsCollectionToMerge.getVerbsCollection());
	}

	public boolean addVerbToCollection(Verb verb) {
		if (verbsCollection.add(verb)) {
			System.out.println("Succesfully added: " + verb.toString());
			return true;
		} else {
			System.out.println("This verb is already in the collection (checked by infinitive).");
			return false;
		}
	}

	public boolean addNewVerb(Scanner scanner) {
		List<String> formsOfVerb = new ArrayList<>();

		String[] descriptionOfParticularForm = { "translation", "infinitive form", "Past Tense(2nd) form",
				"Past Participle(3rd) form" };

		for (int i = 0; i <= descriptionOfParticularForm.length - 1; i++) {
			System.out.println("Type " + descriptionOfParticularForm[i] + " for a new verb "
					+ "separating possible diffferent meanings by a forward slash sign \"/\" : ");
			String inputForNewVerb = scanner.nextLine().toLowerCase();
			if (!isInputForNewVerbValid(inputForNewVerb)) {
				System.out.println("Wrong input.");
				i--;
				continue;
			}
			formsOfVerb.add(inputForNewVerb);
		}

		return addVerbToCollection(
				new Verb(formsOfVerb.get(0), formsOfVerb.get(1), formsOfVerb.get(2), formsOfVerb.get(3)));
	}

	private boolean isInputForNewVerbValid(String input) {
		return input.matches("[\\p{L}/]+") && input.length() > 1;
	}

	boolean removeAllVerbsFromCollection() {
		this.verbsCollection.clear();
		return 0 == this.verbsCollection.size();
	}

	public boolean removeVerbFromCollection(String infinitive) {
		return verbsCollection.removeIf(v -> v.getInfinitive().equals(infinitive));
	}

	public boolean removeVerb(Scanner scanner) {
		System.out.println("Type infinitive of the verb you want to remove:");
		String infinitive = scanner.nextLine();
		if (removeVerbFromCollection(infinitive)) {
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

	public Verb getOneRandomVerbToLearn() {
		Verb[] arrayOfVerbs = getArrayOfVerbs();
		return getRandomVerb(arrayOfVerbs);
	}

	public void printVerbsByInfinitive() {
		verbsCollection.forEach(v -> System.out.println(v.toStringByInfinitive()));
	}

	public void printVerbsByTranslation() {
		getVerbsSortedByTranslation().forEach(v -> System.out.println(v.toString()));
	}

	public Verb[] getArrayOfVerbs() {
		Verb[] arrayOfVerbs = verbsCollection.toArray(new Verb[verbsCollection.size()]);
		return arrayOfVerbs;
	}

	public Verb getRandomVerb(Verb[] arrayOfVerbs) {
		int rnd = new Random().nextInt(arrayOfVerbs.length);
		return arrayOfVerbs[rnd];
	}

}
