package englishVerbs;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class FileHandler {

	Set<Verb> getDataFromFile(String filePath) throws IOException {
		return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8).map(streamString -> streamString.strip())
				.map(oneLineString -> oneLineString.split(","))
				.map(arrayWithVerb -> new Verb(arrayWithVerb[0], arrayWithVerb[1], arrayWithVerb[2], arrayWithVerb[3]))
				.collect(Collectors.toCollection(TreeSet::new));
	}

	boolean writeDataToFile(String filePath, VerbsCollection verbsCollection) {
		try (Writer output = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName("UTF8")))) {
			for (var verb : verbsCollection.getVerbsSortedByTranslation()) {
				output.write(verb.verbToOneSqueezedString() + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
