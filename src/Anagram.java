import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Math.*;
import java.util.*;

public class Anagram {

	public Anagram(String filename) {
		char[] letters;
		double count = 0;
		ArrayList<String> file_words = new ArrayList<String>();

		read_file(file_words, filename);
		int file_length = file_words.size();
		int table_size = file_words.size();

		// do not want table size to be power of two or even to reduce collisions
		while (table_size % 2 == 0) { 
			table_size++;
		}
		// Uses Node support class to pair check value and arraylist of anagrams
		Node[] hash_table = new Node[table_size];

		// iterate over all the words in the arraylist and store anagram in appropriate location
		for(int i = 0; i < file_length; i++) {
			// get the word and the sorted character
			String word = file_words.get(i);

			// calculate the hash and check vals
			int[] hash = hash(word, table_size);
			int index = hash[0];
			int check = hash[1];


			// there is nothing stored at the index, create new anagram entry
			if(hash_table[index] == null){ 
				ArrayList<String> w = new ArrayList<String>();
				w.add(word);
				hash_table[index] = (new Node(w, check));
			}
			// anagram class exists at index, but it does not match (i.e. there is a collision)
			else if (hash_table[index].check != check){
				// linearly probe until empty location in array or the anagram class is found
				while(hash_table[index] != null && hash_table[index].check != check){ 
					index = (index + 1) % hash_table.length;
				} 
				// check if the anagram list was not found (null stored at location that previous loop halted on)
				// if this is the case, create a new Node entry for anagram at index
				if(hash_table[index] == null){ 
					ArrayList<String> w = new ArrayList<String>();
					w.add(word);
					hash_table[index] = (new Node(w, check));
				}
				// anagram class exists at index, update the anagram_list and append the word
				else { 
					hash_table[index].anagram_list.add(word);
				}
			}
			// anagram class exists and there was not a collision, add the word to the anagram_list
			else {
				count++;
				hash_table[index].anagram_list.add(word);
			}
		}

		// int num_classes = 0;
		String write = "";
		if(filename.contains("dict1")){
			write = "anagram1";
		}
		else{
			write = "anagram2";
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(write));
			// System.out.println(hash_table[29].anagram_list.toString());
			for(int j=0; j<table_size; j++){
				if(hash_table[j] != null){
					String to_file = hash_table[j].anagram_list.toString();
					// System.out.println(to_file);
					writer.write(to_file);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("FAILURE");
		}
	}

	// Take in all the words from file to determine length of hash_table
	// Store the words in file_words for processing
	private void read_file(ArrayList<String> words, String filename){
		int word_index = 0;
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) {
				String new_word = new String(line);
				words.add(word_index, new_word);
				word_index++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	// map each character to a prime number, combination of them multiplied together is a prime factorization.  
	// Only anagrams will have the same prime factorization, but issue of collisions from modding from table size. 
	// returns a tuple with hash and check values
	private int[] hash(String word, int table_size) {
		int[] p_nums = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
		int hash = 1; // index for hash_table 
		int check = 1; // check num for collisions for anagram (same anagram class should have same check value) 
		for(int i = 0; i < word.length(); i++) { // get each character and build up the factorization
			int charac = word.charAt(i) - 97; 
			hash = hash * p_nums[charac] % table_size;
			check = check * p_nums[charac];
		}
		int[] hashed = {hash, check};
		return hashed;
	}
}