package englishVerbs;

import java.util.TreeSet;

public class VerbsCollection {

	private TreeSet<Verb> verbsCollection;

	public VerbsCollection() {
		this.verbsCollection = new TreeSet<>();
	}

	public void addVerbToCollection(String translation, String infinitive, String pastTense, String pastParticiple) {
		Verb verb = new Verb(translation, infinitive, pastTense, pastParticiple);
		if (verbsCollection.add(verb)) {
			System.out.println("Succesfully added: " + verb.toString());
		} else {
			System.out.println("This verb is already in the collection (checked by translation).");
		}
	}

	public void printVerbsCollection() {
		verbsCollection.forEach(Verb::toString);
	}

}
