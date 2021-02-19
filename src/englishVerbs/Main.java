package englishVerbs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {

		File file = new File("./irregular-verbs.txt");

		VerbsCollection verbs = new VerbsCollection(getVerbsFromExternalSource(file));
		verbs.printVerbsCollectionByTranslation();
		System.out.println("-------------------------");
		verbs.printVerbsCollectionByInfinitive();
		System.out.println("**************************");
		System.out.println(verbs.getRandomVerbByTranslation());

	}

	private static List<String[]> getVerbEntriesFromFile(File file) throws IOException {
		List<String[]> linesOfVerbs = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				String[] splitLines = line.split(",");
				linesOfVerbs.add(splitLines);
				line = br.readLine();
			}
		}
		return linesOfVerbs;
	}

	public static Set<Verb> getVerbsFromExternalSource(File file) {
		Set<Verb> verbsCollection = new TreeSet<>();
		
		try {
			List<String[]> arraysWithVerbs = getVerbEntriesFromFile(file);
			for (var verbPart : arraysWithVerbs) {
				Verb verb = new Verb(verbPart[0], verbPart[1], verbPart[2], verbPart[3]);
				verbsCollection.add(verb);
			}
		} catch (IOException e) {
			System.out.println("File not found");
		}
		return verbsCollection;
	}
	
	public static void guessFormsOfAVerb() {
		
	}

}
