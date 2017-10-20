import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Anagram {

	public Anagram(String filename) {
		String line = null;
		// int total_size = 262139;
		char[] letters;
		int collisions = 0;
		ArrayList<Node> hash_table = new ArrayList<Node>();
		// Set<Integer> codes = new TreeSet<Integer>();
		// Set<String> words = new TreeSet<String>();
		double count = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) {
				letters = line.toCharArray();
				char[] c = letters.clone(); 
				count++;
				mergeSortLetters(c, 0, (letters.length-1));
				// // System.out.println("c: " + (new String(c)) + "      orig: " + (new String(letters)) + "         hashCode: " + c.hashCode());
				// words.add((new String(c)));
				// codes.add(c.hashCode());
				// hash_table[c.hashCode() % total_size] = (new String(c));
				int index = c.hashCode();
				if(hash_table.get(index).sorted_anagram != (new String(c)) && hash_table.get(index) != null){
					collisions++;
				}
				else if (hash_table.get(index) != null){ // no anagram stored there yet
					ArrayList<String> w = new ArrayList<String>();
					w.add((new String(letters)));
					hash_table.add(index, (new Node((new String(c)), w)));
				}
				else { //they match
					hash_table.get(index).anagram_list.add(new String(c));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// System.out.println(codes.size());
		System.out.println("count: " + count);
		// System.out.println("words: " + words.size());
		System.out.println(hash_table);
		System.out.println(collisions);

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



	// Create a hash table
	// make good hash function
	// map hash input value > key to hash table (array)
	// Chain with linked lists? or quad probe? 

}