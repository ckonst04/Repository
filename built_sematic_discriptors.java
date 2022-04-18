package hw5;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class takes a list(created from the SentenceLists class) and creates a hash map
 * that has as keys all the word from the list and as values other hash maps that represent
 * a unique vector of each word, it has all the words of the sentences that the key is in
 * as values and the number of each one as value.
 * 
 * @author Christos Konstantinou
 * @date 18/04/2022
 */
public class built_sematic_discriptors {

	private HashMap<String, HashMap<String, Integer>> HM;// outer hashmap
	private HashMap<String, Integer> innerHM;
	private ArrayList<ArrayList<ArrayList<String>>> sentences;

	/**
	 * Constructor of the class, creates new hash map
	 * @param sentences
	 */
	public built_sematic_discriptors(ArrayList<ArrayList<ArrayList<String>>> sentences) {
		this.sentences = sentences;
		HM = new HashMap<>();
	}


	/**
	 * This method creates a hash map that has as keys all the word from the list and as values other hash maps that represent
 * a unique vector of each word, it has all the words of the sentences that the key is in
 * as values and the number of each one as value
	 * @return
	 */
	public HashMap<String, HashMap<String, Integer>> getHM() {
		innerHM = new HashMap<>();
		for (ArrayList<ArrayList<String>> L1 : sentences) {
			for (ArrayList<String> L2 : L1) {
				for (String word : L2) {
					if (!HM.containsKey(word)) {
						innerHM = new HashMap<>();
						int value = 1;
						for (String s : L2) {
							if (!s.equals(word))
								innerHM.put(s, value);
						}
						HM.put(word, innerHM);
					} else {
						HashMap<String, Integer> hm = HM.get(word);
						for (String s : L2) {
							if (!s.equals(word)) {
								if (!hm.containsKey(s)) {
									hm.put(s, 1);
								} else {
									hm.put(s, hm.get(s) + 1);
								}
							}
						}
						HM.put(word, hm);
					}
				}
			}
		}
		return HM;
	}
}
