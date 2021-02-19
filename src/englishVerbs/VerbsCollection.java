package englishVerbs;

import java.util.Random;
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

	public void addVerbToCollection(String translation, String infinitive, String pastTense, String pastParticiple) {
		Verb verb = new Verb(translation, infinitive, pastTense, pastParticiple);
		if (verbsCollection.add(verb)) {
			System.out.println("Succesfully added: " + verb.toString());
		} else {
			System.out.println("This verb is already in the collection (checked by translation).");
		}
	}

	public void printVerbsCollectionByTranslation() {
		verbsCollection.forEach(System.out::println);
	}

	public void printVerbsCollectionByInfinitive() {
		Set<Verb> verbsCollectionByInfinitive = new TreeSet<>(
				(v1, v2) -> v1.getInfinitive().compareTo(v2.getInfinitive()));
		verbsCollectionByInfinitive.addAll(verbsCollection);
		verbsCollectionByInfinitive.forEach( v -> System.out.println(v.toStringByInfinitive()));
	}
	
	public Verb getRandomVerbByTranslation() {
		Verb[] arrayOfVerbs = (Verb[]) verbsCollection.toArray();
		int rnd = new Random().nextInt(arrayOfVerbs.length);
	    return arrayOfVerbs[rnd];
	}

}
