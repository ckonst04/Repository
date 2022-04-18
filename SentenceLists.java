package hw5;

import java.util.ArrayList;

/**
 * This class takes as parameters three strings that represent the file names and three
 * strings that contains the three texts from the files and creates a list that contains
 * lists that represent sentences.
 * 
 * @author Christos Konstantinou
 * @date 18/04/2022
 *
 */
public class SentenceLists {

	private ArrayList<String> filenames;
	private ArrayList<String> textList;
	
	/**
	 * Constructor of the class,takes the names and the texts of the files as parameters
	 * and creates tow lists
	 * @param file1
	 * @param file2
	 * @param file3
	 * @param text1
	 * @param text2
	 * @param text3
	 */
	public SentenceLists(String file1, String file2, String file3, String text1,String text2,String text3) {
		filenames=new ArrayList<>();
		filenames.add(file1);
		filenames.add(file2);
		filenames.add(file3);
		textList=new ArrayList<>();
		textList.add(text1);
		textList.add(text2);
		textList.add(text3);
	}
	
	/**
	 * This method takes as a parameter a string(sentence) and returns the string only
	 * with lowcase letters
	 * @param s
	 * @return newS
	 */
	private String sentenceConv(String s) {
		String newS = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'A' && c <= 'Z')
				c += 32;//ascii
			newS += c;
		}
		return newS;
	}
	
	/**
	 * This method takes a string(sentence) as a parameter and returns a list ,one word at
	 * every position.
	 * @param s
	 * @return list
	 */
	private ArrayList<String> makeList(String s) {
		ArrayList<String> list = new ArrayList<>();
		String str = "";
		str = sentenceConv(s);
		str += ' ';// to count the last word, we suppose that the last char is a letter
		String word = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
				word += str.charAt(i);
			else {
				if (word.length() > 0)//to avoid null positions
					list.add(word);
				word = "";
			}
		}
		return list;
	}
	
	/**
	 * This method takes a string(text from a file) as a parameter and uses the makeList
	 * meTHod to convert every sentence of this text to an ArrayList.
	 * @param text
	 * @return list
	 */
	private ArrayList<ArrayList<String>> getSentenceLists(String text){
		ArrayList<ArrayList<String>> list=new ArrayList<>();
		String s="";
		for(int i=0;i<text.length();i++) {
			char c=text.charAt(i);
			if(c!='.'&&c!='!'&&c!='?') {
				s+=c;
			}
			else if (s.length() > 0) {
				ArrayList<String> sentenceList=makeList(s);
				list.add(sentenceList);
				s="";			}
		}
		return list;
	}
	
	/**
	 * This method takes all the texts from the textlist and uses the methods above to
	 * create a list that contains lists that represents sentences.
	 * @return filesList
	 */
	public ArrayList<ArrayList<ArrayList<String>>> get_sentence_lists_from_files() {
		ArrayList<ArrayList<ArrayList<String>>> filesList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			filesList.add(getSentenceLists(textList.get(i)));
		}
		return filesList;
	}
	
	

	
}
