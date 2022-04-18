package hw5;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class takes the hash map and a list of words, and finds in each line the answer
 * compares it with the given answer and computes the persentage of success.
 * 
 * @author Christos Konstantinou
 * @date 18/04/2022
 *
 */
public class run_similarity_test {

	private ArrayList<String> synonyms;
	private String word;
	private ArrayList<String> list;
	private most_similar_word sim;
	private HashMap<String, HashMap<String, Integer>> HM;
	private ArrayList<String> givenAns;

	/**
	 * Constructor of the class, creates new list with the given answers
	 * @param synonyms
	 * @param HM
	 */
	public run_similarity_test(ArrayList<String> synonyms, HashMap<String, HashMap<String, Integer>> HM) {
		this.synonyms = synonyms;
		this.HM = HM;
		givenAns = new ArrayList<>();
	}

	/**
	 * This method finds the synonym of the line
	 * @param line
	 * @return
	 */
	private String find_the_answer(String line) {
		list = new ArrayList<>();
		String[] lineArr = line.split(" ");
		word = lineArr[0];
		givenAns.add(lineArr[1]);// save the given answers for later
		for (int i = 2; i < lineArr.length; i++)
			list.add(lineArr[i]);
		sim = new most_similar_word(word, list, HM);
		if (sim.checkValid())
			return sim.find_the_Synonym();
		else
			return "Not recognised...";
	}

	/**
	 * This method creates a list of the answers the program found
	 * @return
	 */
	private ArrayList<String> answerList() {
		ArrayList<String> ansList = new ArrayList<>();
		for (String line : synonyms) {
			ansList.add(find_the_answer(line));
		}
		return ansList;
	}

	/**
	 * This method compares the answers given and the answers found and returns the
	 * persentage of success.
	 * @return
	 */
	public double getPSG() {
		ArrayList<String> correctAns = answerList();
		int correct = 0;
		for (int i = 0; i < correctAns.size(); i++)
			if (correctAns.get(i).equals(givenAns.get(i)))
				correct++;
		return (double) (correct * 100) / correctAns.size();
	}

}
