import java.util.LinkedList;

public class BucketTable {
	public LinkedList<Elements>[] hashTable;
	
	public BucketTable(){
		hashTable =new LinkedList[26];
	}
	
	public void insert(Elements a) {
		int key = (int) (a.getElementName().charAt(0)) - 65;
		
		if (hashTable[key] == null) {
			hashTable[key] = new LinkedList<Elements>();
		}

		hashTable[key].add(a);
	}
	
	public double search(String b) {
		//searches through the hashtable to find the molarmass of a given element
		int key = (int) (b.charAt(0)) - 65;
		
		for (int i = 0; i<hashTable[key].size(); i++) {
			if (hashTable[key].get(i).getElementName().equals(b)); {
				return hashTable[key].get(i).getMolarMass();
			}
		}
		return 0;
	}

}
