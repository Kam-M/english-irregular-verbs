package englishVerbs;

import java.util.Set;

interface Dao{

	Set<Verb> readMainCollectionFromSource();
	Set<Verb> readLearntCollectionFromSource();
	void saveMainCollection();
	void saveLearnCollection();

}
