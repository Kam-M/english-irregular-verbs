package englishVerbs;

public class Verb implements Comparable<Verb>{

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
		return translation + "(inf:" + infinitive + ") -- " + pastTense + " - " + pastParticiple;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infinitive == null) ? 0 : infinitive.hashCode());
		result = prime * result + ((pastParticiple == null) ? 0 : pastParticiple.hashCode());
		result = prime * result + ((pastTense == null) ? 0 : pastTense.hashCode());
		result = prime * result + ((translation == null) ? 0 : translation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Verb other = (Verb) obj;
		if (infinitive == null) {
			if (other.infinitive != null)
				return false;
		} else if (!infinitive.equals(other.infinitive))
			return false;
		if (pastParticiple == null) {
			if (other.pastParticiple != null)
				return false;
		} else if (!pastParticiple.equals(other.pastParticiple))
			return false;
		if (pastTense == null) {
			if (other.pastTense != null)
				return false;
		} else if (!pastTense.equals(other.pastTense))
			return false;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}

	@Override
	public int compareTo(Verb o) {
		return this.translation.compareTo(o.getTranslation());
	}
	
	
}
