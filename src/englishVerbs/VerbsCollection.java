package englishVerbs;

import java.text.Collator;
import java.util.Comparator;
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

	private boolean addVerbToCollection(Verb verb) {
		if (verbsCollection.add(verb)) {
			System.out.println("Succesfully added: " + verb.toString());
			return true;
		} else {
			System.out.println("This verb is already in the collection (checked by infinitive).");
			return false;
		}
	}

	public boolean addVerb(Scanner scanner) {
		System.out.println("Type translation for a new verb separating possible diffferent meanings by a forward slash sign \"/\" : ");
		String translation = scanner.nextLine();
		System.out.println("Type infinitive: ");
		String infinitive = scanner.nextLine();
		System.out.println("Type Past Tense form: ");
		String pastTense = scanner.nextLine();
		System.out.println("Type Past Participle Form: ");
		String pastParticiple = scanner.nextLine();

		return addVerbToCollection(new Verb(translation, infinitive, pastTense, pastParticiple));
	}
	
	private boolean removeVerbFromCollection(String infinitive) {
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
