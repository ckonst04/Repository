package hw5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * This class takes a word, a list, and the hash map created and finds which word from the
 * list is the synonym of the given word.
 * 
 * @author Christos Konstantinou
 * @date 18/04/2022
 */
public class most_similar_word {

	private String word;
	private ArrayList<String> choices;
	private HashMap<String, HashMap<String, Integer>> HM;

	/**
	 * Constructor of the class, takes a word, a list and a hash map as parameters.
	 * @param word
	 * @param choices
	 * @param HM
	 */
	public most_similar_word(String word, ArrayList<String> choices, HashMap<String, HashMap<String, Integer>> HM) {
		this.word = word;
		this.choices = choices;
		this.HM = HM;
	}
	
	/**
	 * This method checks if all the words are in the files given.
	 * @return
	 */
	public boolean checkValid() {
		if(!HM.containsKey(word))
			return false;
		for(String s: choices)
			if(!HM.containsKey(s))
				return false;
		return true;
	}

	/**
	 * This method returns the vector we need, which is the value of the hash map.
	 * @param s
	 * @return vec
	 */
	private HashMap<String, Integer> getVec(String s) {
		HashMap<String, Integer> vec = HM.get(s);
		return vec;
	}

	/**
	 * This method compute and returns the sum of squares in sqrt of the vector given
	 * @param vec
	 * @return
	 */
	private double norm(HashMap<String, Integer> vec) {
		double sum_of_squares = 0.0;
		for (Integer x : vec.values())
			sum_of_squares += x * x;
		return Math.sqrt(sum_of_squares);
	}

	/**
	 * This method compute and returns a number between 0 and 1 that represents how 
	 * similar are the two words given.
	 * @param vec1
	 * @param vec2
	 * @return
	 */
	private double cosin_similarity(HashMap<String, Integer> vec1, HashMap<String, Integer> vec2) {
		double dot_product = 0.0;
		for (String x : vec1.keySet())
			if (vec2.containsKey(x))
				dot_product += vec1.get(x) * vec2.get(x);
		return dot_product / (norm(vec1) * norm(vec2));
	}

	/**
	 * This method creates the choices, the duals that are going to be computed
	 * @return
	 */
	private HashMap<String[], Double> create_choicesHM() {
		ArrayList<String[]> compinations = new ArrayList<>();
		for (String choice : choices) {
			String[] S = { word, choice };
			compinations.add(S);
		}

		HashMap<String[], Double> hm = new HashMap<>();
		for (String[] S : compinations) {
			hm.put(S, cosin_similarity(getVec(S[0]), getVec(S[1])));
		}

		return hm;
	}
	
	/**
	 * This method finds the synonym, returns the word from the list with the highest similarity 
	 * with the given word.
	 * @return
	 */
	public String find_the_Synonym() {
		HashMap<String[], Double> compinations =create_choicesHM();
		String[] synonym=null;
		double maxValue=0.0;
		for(Entry<String[],Double> entry: compinations.entrySet()) {
			if(entry.getValue()>maxValue) {
				maxValue=entry.getValue();
				synonym=entry.getKey();
			}
		}
		return synonym[1];
	}

}
