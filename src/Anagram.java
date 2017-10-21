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
		char[] letters;
		int collisions = 0;
		double count = 0;
		ArrayList<String> file_words = new ArrayList<String>();
		int word_index = 0;

		// Take in all the words from file to determine length of hash_table
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



		Node[] hash_table = new Node[word_index];

		for(int i=0; i < word_index; i++) {
			hash_table[i] = null;
		}

		// iterate over all the words in the arraylist
		for(int i = 0; i < word_index; i++) {
			// get the word and the sorted characters (for collision check)
			String word = file_words.get(i);
			char[] lets = word.toCharArray();
			mergeSortLetters(lets, 0, (lets.length-1));

			int[] hash = hash((new String(lets)), word_index);
			int index = hash[0];
			int check = hash[1];
			if(hash_table[index] == null){ // there is nothing stored at the index
				ArrayList<String> w = new ArrayList<String>();
				w.add(word);
				// System.out.println(index + "        ==       " + hash((new String(lets)), word_index));
				hash_table[index] = (new Node(w, check));
			}
			else if (hash_table[index].check != check){ // anagram class exists at index, but it does not match
				// System.out.println(hash_table[index].sorted_anagram + "          " + (new String(lets)));
				// System.out.println(index);
				ArrayList<String> words = hash_table[index].anagram_list;
				for(int x = 0; x < words.size(); x++){
					System.out.print(words.get(x) + " ");
				}
				// System.out.println();
				// System.out.println(hash(hash_table[index].sorted_anagram, word_index)[0] + "   ==     " + hash((new String(lets)), word_index)[0]);
				collisions++;
			}
			else { // anagram class exists and is not a collision
				count++;
				hash_table[index].anagram_list.add(word);
			}
		}
		System.out.println("count: " + count);
		System.out.println(collisions);
	}

	// ascribe value to letters to get hash input value
	// map each character to a prime number, comb of them multiplied together is a prime factorization. Only 
	// anagrams will have the same prime factorization. 
	private int[] hash(String word, int table_size) {
		int[] p_nums = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
		int hash = 1; // index for table 
		int real = 1; // check num for collisions for words (same word should have same real int) 
		for(int i = 0; i < word.length(); i++) { // get each character and build up the factorization
			int charac = word.charAt(i) - 97; 
			hash = hash * p_nums[charac] % table_size;
			real = real * p_nums[charac];
		}
		int[] hashed = {hash, real};
		return hashed;
	}

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

	// Create a hash table
	// make good hash function map each character to a prime number, each word will have its own unique prime facotization

	// map hash input value > key to hash table (array)
	// Chain with linked lists? or quad probe? 

}