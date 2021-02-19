package englishVerbs;

public class Main {

	public static void main(String[] args) {
		
		VerbsCollection irregularVerbsCollection = new VerbsCollection();
		irregularVerbsCollection.addVerbToCollection("skakać", "spring", "sprang", "sprung");
		irregularVerbsCollection.addVerbToCollection("pisać", "write", "wrote", "written");
		irregularVerbsCollection.addVerbToCollection("budzić się", "wake", "woke", "woken");
		irregularVerbsCollection.addVerbToCollection("usiłować", "strive", "strove/strived", "striven/strived");
		irregularVerbsCollection.addVerbToCollection("zostać", "become", "became", "become");
		irregularVerbsCollection.addVerbToCollection("trzymać", "hold", "held", "held");
		irregularVerbsCollection.addVerbToCollection("dawać", "give", "gave", "given");

		
		System.out.println("-----------------------");
		
		irregularVerbsCollection.printVerbsCollectionByTranslation();
		System.out.println("----------------------");
		irregularVerbsCollection.printVerbsCollectionByInfinitive();



	}

}
