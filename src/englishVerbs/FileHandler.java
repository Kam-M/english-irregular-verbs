package englishVerbs;

import java.io.BufferedWriter;
import java.io.File;
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
import java.util.stream.Stream;

class FileHandler implements IDataHandler {

	private String filePathMainCollection = new File("").getAbsolutePath() + File.separator + "irregular-verbs.txt";
	private String filePathLearntVerbs = new File("").getAbsolutePath() + File.separator + "learnt-verbs.txt";

	private VerbsCollection mainCollection;
	private VerbsCollection learntCollection;

	public FileHandler(VerbsCollection mainCollection, VerbsCollection learntCollection) {
		this.mainCollection = mainCollection;
		this.learntCollection = learntCollection;
	}

	@Override
	public Set<Verb> readMainCollectionFromSource() {
		Set<Verb> verbsCollection = null;
		try (Stream<String> s = Files.lines(Paths.get(filePathMainCollection.toString()), StandardCharsets.UTF_8)) {
			verbsCollection = s.map(streamString -> streamString.strip()).map(oneLineString -> oneLineString.split(","))
					.map(arrayWithVerb -> new Verb(arrayWithVerb[0], arrayWithVerb[1], arrayWithVerb[2],
							arrayWithVerb[3]))
					.collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return verbsCollection;
	}

	@Override
	public Set<Verb> readLearntCollectionFromSource() {
		Set<Verb> verbsCollection = null;
		try (Stream<String> s = Files.lines(Paths.get(filePathLearntVerbs.toString()), StandardCharsets.UTF_8)) {
			verbsCollection = s.map(streamString -> streamString.strip()).map(oneLineString -> oneLineString.split(","))
					.map(arrayWithVerb -> new Verb(arrayWithVerb[0], arrayWithVerb[1], arrayWithVerb[2],
							arrayWithVerb[3]))
					.collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return verbsCollection;
	}

	@Override
	public void saveMainCollection() {
		try (Writer output = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePathMainCollection), Charset.forName("UTF8")))) {
			for (var verb : this.mainCollection.getVerbsSortedByTranslation()) {
				output.write(verb.verbToOneSqueezedString() + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveLearnCollection() {
		try (Writer output = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(filePathLearntVerbs), Charset.forName("UTF8")))) {
			for (var verb : learntCollection.getVerbsSortedByTranslation()) {
				output.write(verb.verbToOneSqueezedString() + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
