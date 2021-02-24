package englishVerbs;

import java.util.Set;

interface IDataHandler{

	Set<Verb> readMainCollectionFromSource();
	Set<Verb> readLearntCollectionFromSource();
	void saveMainCollection();
	void saveLearnCollection();

}
