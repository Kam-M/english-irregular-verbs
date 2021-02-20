package englishVerbs;

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
		System.out.println("Type translation for new verb: ");
		String translation = scanner.nextLine();
		System.out.println("Type infinitive: ");
		String infinitive = scanner.nextLine();
		System.out.println("Type Past Tense form: ");
		String pastTense = scanner.nextLine();
		System.out.println("Type Past Participle Form: ");
		String pastParticiple = scanner.nextLine();
		
		return addVerbToCollection(new Verb(translation, infinitive, pastTense, pastParticiple));
	}
	
	public boolean removeVerb(Scanner scanner) {
		System.out.println("Type infinitive of the verb you want to remove:");
		String infinitive = scanner.nextLine();
		boolean wasRemoved = verbsCollection.removeIf( v -> v.getInfinitive().equals(infinitive));
		if(wasRemoved) {
			System.out.println("Succesfully removed verb from collection.");
			return true;
		}else {
			System.out.println("Collection does not contain this verb (checked by infinitive).");
			return false;
		}
	}
	
	public void printVerbsCollectionByInfinitive() {
		verbsCollection.forEach( v -> System.out.println(v.toStringByInfinitive()));
	}

	public void printVerbsCollectionByTranslation() {
		Set<Verb> verbsCollectionByTranslation = new TreeSet<>(
				(v1, v2) -> v1.getTranslation().compareTo(v2.getTranslation()));
		verbsCollectionByTranslation.addAll(verbsCollection);
		verbsCollectionByTranslation.forEach( v -> System.out.println(v.toString()));
	}
	
	public Verb[] getArrayOfVerbs(){
		Verb[] arrayOfVerbs = verbsCollection.toArray(new Verb[verbsCollection.size()]);
		return arrayOfVerbs;
	}
	
	public Verb getRandomVerb(Verb[] arrayOfVerbs) {
		int rnd = new Random().nextInt(arrayOfVerbs.length);
	    return arrayOfVerbs[rnd];
	}
	
	

}
