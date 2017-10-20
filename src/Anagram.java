import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Anagram {

	public Anagram(String filename) {
		String line = null;
		char[] letters;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) != null) {
				letters = line.toCharArray();
				char[] c = mergeSortLetters(letters, 0, (letters.length-1));
				System.out.println(c);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



	// Have to read in files
	// have to scan through files

	// Sort the letters in word alphabetically using merge sort
	private char[] mergeSortLetters(char[] letters, int start, int end){
		if (start < end) {
			int middle = (start + end)/2;
			mergeSortLetters(letters, start, middle);
			mergeSortLetters(letters, middle + 1, end);
			mergeLetters(letters, start, middle, end);
		}
		return letters;
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