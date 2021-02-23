package englishVerbs;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		System.out.println("Welcome to ENGLISH-IRREGULAR-VERBS APP!\n"
				+ "This is not a complete list of all irregular english verbs.\nBut it is constantly being expanded :)\n\n");

		String filePathMainCollection = new File("").getAbsolutePath() + File.separator + "irregular-verbs.txt";
		String filePathLearntVerbs = new File("").getAbsolutePath() + File.separator + "learnt-verbs.txt";

		new CollectionManager().startLearning(filePathMainCollection, filePathLearntVerbs);
	}
}
