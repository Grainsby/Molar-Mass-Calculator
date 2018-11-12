import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class driver {
	public static BucketTable table = new BucketTable();

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Reading in element values...");
		Scanner filereader = new Scanner(new File("Molar Mass Values.txt"));
		while (filereader.hasNextLine()) {
			String temp = filereader.nextLine();
			String[] values = temp.split("	");
			Elements insert = new Elements(values[0],Double.parseDouble(values[1]));
			table.insert(insert);
		}
		System.out.println("Pleaase enter your compound in this format: C205");
		Scanner scan = new Scanner(System.in);
		String compound = scan.nextLine();
		double molarMass = parseCompound(compound);
		System.out.println("the compounds molar mass is: " + molarMass);
		scan.close();
	}
	
	public static double parseCompound(String compound) {
		ArrayList<String> elementName = new ArrayList<String>();
		ArrayList<Integer> elementMult = new ArrayList<Integer>();
		int start = 0;
		for (int i = 0; i<compound.length(); i++) {
			if (Character.isUpperCase(compound.charAt(i))) {
				start = i;
				boolean done = false;
				while (i<compound.length() && !done) {
					i++;
					if (i == compound.length()) {
						elementName.add(compound.substring(start,i));
						elementMult.add(1);
						break;
					}
					else if (Character.isUpperCase(compound.charAt(i))) {
						done = true;
						elementName.add(compound.substring(start,i));
						elementMult.add(1);
					}
				}
				i--;
			}
		}
		
		for (int i = 0; i<elementName.size(); i++) {
			for (int j = 0; j<elementName.get(i).length(); j++) {
				if (elementName.get(i).charAt(j) == ')') {
					break;
				}
				if (Character.isDigit(elementName.get(i).charAt(j))) {
					start = j;
					while (j<elementName.get(i).length() && Character.isDigit(elementName.get(i).charAt(j))) {
						j++;
					}
					System.out.println(elementName.get(i).substring(start,j));
					elementMult.set(i,Integer.parseInt(elementName.get(i).substring(start,j)));
					if (j < elementName.get(i).length()) {
						elementName.set(i, elementName.get(i).substring(0,start) + elementName.get(i).substring(j));
					}
					else {
						elementName.set(i, elementName.get(i).substring(0,start));
					}
					break;
				}
			}
		}
		
		
		for (int i = 0; i<elementName.size(); i++) {
			if (elementName.get(i).contains(")")) {
				int multiplier = 1;
				for (int l = 0; l<elementName.get(i).length(); l++) {//find the multiplier
					if (Character.isDigit(elementName.get(i).charAt(l))) {
						start = l;
						while (l<elementName.get(i).length() && Character.isDigit(elementName.get(i).charAt(l))) {
							l++;
						}
						multiplier = Integer.parseInt(elementName.get(i).substring(start, l));
						elementName.set(i, elementName.get(i).substring(0,start-1));
						break;
					}
				}
				for (int j = i;j>=0; j--) {//apply multiplier and find the end of this series of paren
					if (elementName.get(j).contains("(")) {
						elementName.set(j, elementName.get(j).substring(0,elementName.get(j).length()-1));
						break;
					}
					else {
						elementMult.set(j, elementMult.get(j)*multiplier);
					}
				}
			}
		}
		double finalSum = 0;
		for (int i = 0; i<elementName.size(); i++) {
			//System.out.println(i + " " +elementName.get(i));
			//System.out.println(i + " " +elementMult.get(i));
			finalSum += (table.search(elementName.get(i)) * elementMult.get(i));
		}
		
		return finalSum;

	}

}
