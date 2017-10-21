import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Math.*;
import java.util.*;

public class Anagram {

	public Anagram(String filename) {
		String line = null;
		// int total_size = 262139;
		char[] letters;
		int collisions = 0;
		// Set<Integer> codes = new TreeSet<Integer>();
		// Set<String> words = new TreeSet<String>();
		double count = 0;
		ArrayList<String> file_words = new ArrayList<String>();
		int word_index = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) {
				String new_word = new String(line);
				file_words.add(word_index, new_word);
				word_index++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<Node> hash_table = new ArrayList<Node>(word_index);

		for(int i=0; i <= word_index; i++) {
			hash_table.add(i, null);
		}
		// System.out.println(word_index);
		// System.out.println("hash_table size: " + hash_table.size());
		// System.out.println(file_words.size());
		System.out.println(hash("hello"));

		for(int i = 0; i < word_index; i++) {
			letters = file_words.get(i).toCharArray();
			char[] c = letters.clone(); 
			count++;
			mergeSortLetters(c, 0, (letters.length-1));
			// // System.out.println("c: " + (new String(c)) + "      orig: " + (new String(letters)) + "         hashCode: " + c.hashCode());
			// words.add((new String(c)));
			// codes.add(c.hashCode());
			// hash_table[c.hashCode() % total_size] = (new String(c));
			int index = c.hashCode() % (word_index + 1);
			if(hash_table.get(index) != null && !hash_table.get(index).sorted_anagram.equals(new String(c))){
				// System.out.println(hash_table.get(index));
				// System.out.println("START");

				// System.out.println("old: " + hash_table.get(index).sorted_anagram);
				// System.out.println((new String(c)));

				// System.out.println(hash_table.get(index).sorted_anagram.equals(new String(c)));
				// System.out.println(hash_table.get(index).sorted_anagram.hashCode());
				// System.out.println(c.hashCode());
				// System.out.println((hash_table.get(index).sorted_anagram.hashCode() % (word_index +1)));
				// System.out.println(c.hashCode() % (word_index +1));
				// System.out.println("END");

				collisions++;
			}
			else if (hash_table.get(index) == null){ // no anagram stored there yet
				ArrayList<String> w = new ArrayList<String>();
				w.add((new String(letters)));
				hash_table.add(index, (new Node((new String(c)), w)));
			}
			else { //they match
				hash_table.get(index).anagram_list.add(new String(c));
			}
		}


		// System.out.println(codes.size());
		System.out.println("count: " + count);
		// System.out.println("words: " + words.size());
		// System.out.println(hash_table);
		System.out.println("collisions: " + collisions);

	}



	// Have to read in files
	// have to scan through files

	// Sort the letters in word alphabetically using merge sort
	private void mergeSortLetters(char[] letters, int start, int end) {
		if (start < end) {
			int middle = (start + end)/2;
			mergeSortLetters(letters, start, middle);
			mergeSortLetters(letters, middle + 1, end);
			mergeLetters(letters, start, middle, end);
		}
	}

	private void mergeLetters(char[] letters, int start, int middle, int end) {
		int n1 = middle - start; 
		int n2 = end - middle;

		char[] left = new char[n1+1];
		char[] right = new char[n2];

		for(int i = 0; i <= n1; i++) {
			left[i] = letters[start + i];
		}
		for(int i = 0; i < n2; i++) {
			right[i] = letters[middle + i + 1];
		}

		int i = 0;
		int j = 0;
		for(int index = start; index <= end; index++){
			if(!(i < left.length) || !(j < right.length)){ // one of the lists is finished
				if(!(i < left.length)) { // left list is finished
					letters[index] = right[j];
					j++;
				}
				else { // right list is finished
					letters[index] = left[i];
					i++;
				}
			} 
			else if(left[i] <= right[j]) { // front of left list <= front of right list
				letters[index] = left[i];
				i++;
			}
			else { //front of right list smaller
				letters[index] = right[j];
				j++;
			}
		}

	}
	
	// ascribe value to letters to get hash input value
	// map each character to a prime number, comb of them multiplied together is a prime factorization. Only 
	// anagrams will have the same prime factorization. 
	private int hash(String word) {
		int[] p_nums = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
		System.out.println(p_nums.length);
		return 0;
	}

	// Create a hash table
	// make good hash function map each character to a prime number, each word will have its own unique prime facotization

	// map hash input value > key to hash table (array)
	// Chain with linked lists? or quad probe? 

}