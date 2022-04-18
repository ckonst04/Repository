package hw5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is the main class, it reads from three files and saves the three texts in three strings
 * and then calls all the classes to find the persentage of success.Prints the output
 * in a text file.
 * 
 * @author Christos Konstantinou
 * @date 18/04/2022
 *
 */
public class mainClass {

	/**
	 * Method that helps with the reading from a file
	 * @param fileName
	 * @return text
	 */
	public static String readFileAsString(String fileName) {
	    String text = "";
	    try {
	      text = new String(Files.readAllBytes(Paths.get(fileName)));
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    return text;
	  }
	
	/**
	 * Main method, does everything that mentioned above.
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		String text1=readFileAsString("brown-train-sentences.txt");
		String text2=readFileAsString("pg2600.txt");
		String text3=readFileAsString("pg7178.txt");
		

		String s1 = "brown-train-sentences";
		String s2 = "pg2600";
		String s3 = "pg7178";

		SentenceLists SL = new SentenceLists(s1, s2, s3, text1, text2, text3);
		ArrayList<ArrayList<ArrayList<String>>> A = SL.get_sentence_lists_from_files();

		built_sematic_discriptors B = new built_sematic_discriptors(A);
		HashMap<String, HashMap<String, Integer>> HM = B.getHM();

		Scanner scan = new Scanner(new FileInputStream("Synonyms.txt"));
		ArrayList<String> syns=new ArrayList<>();
		while(scan.hasNextLine()) {
			syns.add(scan.nextLine());
		}
		
		run_similarity_test test=new run_similarity_test(syns,HM);
		
		PrintWriter writeFile=null;
		writeFile=new PrintWriter(new FileOutputStream("output.txt"));
		
		String SYN[]=new String[syns.size()];
		for(int i=0;i<SYN.length;i++)
			SYN[i]=syns.get(i);
		int num=1;
		for(int i=0;i<SYN.length;i++) {
			String line[]=SYN[i].split(" ");
			writeFile.println(num+". "+line[0]);
			char c='a';
			for(int j=2;j<line.length;j++) {
				writeFile.println("\t"+"("+c+") "+line[j]);
				c++;
			}
			writeFile.println("(answer: "+line[1]+")"+"\n");
			num++;
		}
		writeFile.println("\n"+"Persenatge= "+test.getPSG()+"%");
		
		writeFile.close();
		scan.close();
	}

}
