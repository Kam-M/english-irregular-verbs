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

	public void setInfinitive(String infinitive) {
		this.infinitive = infinitive;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getPastTense() {
		return pastTense;
	}

	public void setPastTense(String pastTense) {
		this.pastTense = pastTense;
	}

	public String getPastParticiple() {
		return pastParticiple;
	}

	public void setPastParticiple(String pastParticiple) {
		this.pastParticiple = pastParticiple;
	}

	@Override
	public String toString() {
		return translation + " || " + infinitive + " -- " + pastTense + " -- " + pastParticiple;
	}

	public String toStringByInfinitive() {
		return infinitive + " -- " + pastTense + " -- " + pastParticiple + " || " + translation;
	}

	@Override
	public int compareTo(Verb o) {
		return this.infinitive.compareTo(o.getInfinitive());
	}

	List<String> verbFormsIntoList() {
		List<String> verbToList = new ArrayList<>();
		verbToList.add(this.translation);
		verbToList.add(this.infinitive);
		verbToList.add(this.pastTense);
		verbToList.add(this.pastParticiple);
		return verbToList;
	}

	String verbToOneSqueezedString() {
		StringBuilder verbAsString = new StringBuilder();
		verbFormsIntoList().forEach(s -> verbAsString.append(s + ","));
		return verbAsString.toString();
	}

}
