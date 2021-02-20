package englishVerbs;

import java.util.HashSet;
import java.util.Set;

public class Quiz {

	private VerbsCollection verbsCollection;

	public Quiz(VerbsCollection verbsCollection) {
		this.verbsCollection = verbsCollection;
	}

	public void printFiveRandomVerbsToLearn() {
		Set<Verb> randomGeneratedVerbs = new HashSet<>();
		for (; randomGeneratedVerbs.size() <= 4;) {
			randomGeneratedVerbs.add(verbsCollection.getRandomVerb());
		}
		randomGeneratedVerbs.forEach(System.out::println);
	}
	
	public void quizOneVerbByTranslation() {
		Verb verb = verbsCollection.getRandomVerb();
		String translation = verb.getTranslation();
		System.out.println();
	}

}
