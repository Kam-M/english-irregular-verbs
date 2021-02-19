package englishVerbs;

public class Main {

	public static void main(String[] args) {
		
		VerbsCollection irregularVerbsCollection = new VerbsCollection();
		irregularVerbsCollection.addVerbToCollection("skakać", "spring", "sprang", "sprung");
		irregularVerbsCollection.addVerbToCollection("pisać", "write", "wrote", "written");
		irregularVerbsCollection.addVerbToCollection("budzić się", "wake", "woke", "woken");
		
		irregularVerbsCollection.printVerbsCollection();



	}

}
