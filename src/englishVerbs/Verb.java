package englishVerbs;

import java.util.ArrayList;
import java.util.List;

public class Verb implements Comparable<Verb> {

	private String translation;
	private String infinitive;
	private String pastTense;
	private String pastParticiple;

	public Verb(String translation, String infinitive, String pastTense, String pastParticiple) {
		this.infinitive = infinitive;
		this.translation = translation;
		this.pastTense = pastTense;
		this.pastParticiple = pastParticiple;
	}

	public String getInfinitive() {
		return infinitive;
	}

	public String getTranslation() {
		return translation;
	}

	@Override
	public String toString() {
		return infinitive + " -- " + pastTense + " -- " + pastParticiple + " || " + translation;
	}

	public String toStringByTranslation() {
		return translation + " || " + infinitive + " -- " + pastTense + " -- " + pastParticiple;

	}

	@Override
	public int compareTo(Verb o) {
		return this.infinitive.compareTo(o.getInfinitive());
	}

	List<String> getVerbFormsIntoList() {
		List<String> verbToList = new ArrayList<>();
		verbToList.add(this.translation);
		verbToList.add(this.infinitive);
		verbToList.add(this.pastTense);
		verbToList.add(this.pastParticiple);
		return verbToList;
	}

	String getVerbFormsAsOneSqueezedString() {
		StringBuilder verbAsString = new StringBuilder();
		getVerbFormsIntoList().forEach(s -> verbAsString.append(s + ","));
		return verbAsString.toString();
	}

}
