import java.util.*;


public class Node {
	public int check = 0;
	public ArrayList<String> anagram_list = new ArrayList<String>();
	
	public Node(ArrayList<String> anagram_list, int check){
		this.anagram_list = anagram_list;
		this.check = check;
	}
}