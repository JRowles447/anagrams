import java.util.*;


public class Node {
	public String sorted_anagram = ""; 
	public ArrayList<String> anagram_list = new ArrayList<String>();
	
	public Node(String sorted_anagram, ArrayList<String> anagram_list){
		this.sorted_anagram = sorted_anagram;
		this.anagram_list = anagram_list;
	}
}