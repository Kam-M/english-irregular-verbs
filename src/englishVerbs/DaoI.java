package englishVerbs;

import java.util.Set;

interface DaoI{

	Set<Verb> readMainCollectionFromSource();
	Set<Verb> readLearntCollectionFromSource();
	void saveMainCollection();
	void saveLearnCollection();

}
