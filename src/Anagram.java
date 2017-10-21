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

		int table_size = word_index;
		while (table_size % 2 == 0) { // do not want table size to be power of two or even to reduce collisions
			table_size++;
		}
		Node[] hash_table = new Node[table_size];

		// iterate over all the words in the arraylist
		for(int i = 0; i < word_index; i++) {
			// get the word and the sorted characters (for collision check)
			String word = file_words.get(i);

			int[] hash = hash(word, word_index);
			int index = hash[0];
			int check = hash[1];
			if(hash_table[index] == null){ // there is nothing stored at the index
				ArrayList<String> w = new ArrayList<String>();
				w.add(word);
				// System.out.println(index + "        ==       " + hash((new String(lets)), word_index));
				hash_table[index] = (new Node(w, check));
			}
			else if (hash_table[index].check != check){ // anagram class exists at index, but it does not match
				// ArrayList<String> words = hash_table[index].anagram_list;
				// for(int x = 0; x < words.size(); x++){
				// 	System.out.print(words.get(x) + " ");
				// }
				
				collisions++;
				while(hash_table[index] != null && hash_table[index].check != check){
					index = (index + 1) % hash_table.length;
				} 
				if(hash_table[index] == null){ // anagram not present
					ArrayList<String> w = new ArrayList<String>();
					w.add(word);
					hash_table[index] = (new Node(w, check));
				}
				else { // anagram class exists at index
					hash_table[index].anagram_list.add(word);
				}


			}
			else { // anagram class exists and is not a collision
				count++;
				hash_table[index].anagram_list.add(word);
			}
		}
		System.out.println("count: " + count);
		System.out.println(collisions);





		for(int j = 0; j < table_size; j++){
			if(hash_table[j] != null && hash_table[j].anagram_list.size() >=5){
				System.out.println(hash_table[j].anagram_list);
			}
		}
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
}