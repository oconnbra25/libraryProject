import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // Import the ArrayList utilities

public class CrosswalkCompare {
	public static void main(String[] args) {
		ArrayList<Integer> columnA = new ArrayList<Integer>(); //list for the first column, aka numbers
		ArrayList<String> columnV = new ArrayList<String>(); //list for the title of the book
		Scanner scan = new Scanner(System.in); //for keyboard inputs
		int searchInt = 0; //temp int for people to input a # to search for  
		
		try { //reading the giant text file full of books
			File myObj = new File("Export Items, Copy Based-3.txt");
			Scanner myReader = new Scanner(myObj); //for the file inputs
			myReader.nextLine(); //skips the first line
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String regex = "[\t]";
				String[] eachColumn = data.split(regex);
				columnA.add(Integer.valueOf(eachColumn[0]));
				columnV.add(eachColumn[21]);
			}
		//System.out.println(columnA);
		//System.out.println(columnV);
		myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred. File Export Items, Copy Based-3.txt not found.");
			e.printStackTrace();
		}
		
		ArrayList<Integer> barcode = new ArrayList<Integer>(); //list for the data
		
		try { //reading the student barcodes file
			File myObj2 = new File("StudentBarcodeIDS.csv");
			Scanner myReader2 = new Scanner(myObj2); //for the file inputs
			myReader2.nextLine(); //skips the first line
			while (myReader2.hasNextLine()) {
				String data2 = myReader2.nextLine();
				barcode.add(Integer.valueOf(data2)); //converts string to an Integer object
		}
		//System.out.println(data2);
		myReader2.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred. File StudentBarcodeIDS.csv not found.");
			e.printStackTrace();
		}
		
		//attempting to find similarities within the two lists
		
		int left = 0;
		int right = barcode.size() - 1;
		int mid = (left + right) / 2;
		
		int tempCounter = 0;
		
		boolean numFalse = false; //checks to see if the number doesn't exist
		
		for (Integer value : columnA) //goal is to search through the entire list of columnA
		{
			while (true)
			{
				if (numFalse)
				{
					break;
				}
				else if (barcode.get(mid) == columnA.get(value) || barcode.get(right) == columnA.get(value) || barcode.get(left) == columnA.get(value))
				{
					System.out.println(columnV.get(value) + ", " + columnA.get(value));
				}
				else
				{
					while (barcode.get(mid) != columnA.get(value) && barcode.get(right) != columnA.get(value) && barcode.get(left) != columnA.get(value)) //loop to run the search over and over
					{
						if (tempCounter > (Math.log(barcode.size())/Math.log(2))) //finds the most times that a set of data can be cut in half
						{
							numFalse = true;
							break;
						}
						else if (columnA.get(value) < barcode.get(mid))
						{
							right = mid;
							mid = (left + right) / 2;
							System.out.println("too low");
						}
						else if (columnA.get(value) > barcode.get(mid))
						{
							left = mid;
							mid = (left + right) / 2;
							System.out.println("too high");
						}
						
						tempCounter++; //keeps in track how many times it has iterated through
					}
				}
			}
		}
	}
}
