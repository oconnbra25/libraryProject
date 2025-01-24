import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // Import the ArrayList utilities

public class Crosswalk {
	public static void main(String[] args) {
		ArrayList<Integer> barcode = new ArrayList<Integer>(); //list for the data
		Scanner scan = new Scanner(System.in); //for keyboard inputs
		int searchInt = 0; //temp int for people to input a # to search for  
		try {
			File myObj = new File("StudentBarcodeIDS.csv");
			Scanner myReader = new Scanner(myObj); //for the file inputs
			myReader.nextLine(); //skips the first line
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				barcode.add(Integer.valueOf(data)); //converts string to an Integer object
				System.out.println(data);
		}
		myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
			
		//Entering an input
		
		System.out.print("\nEnter a number to search for:");	//searches for the input int
		boolean validInput = false; //makes sure that the user enters a number
		while (!validInput)
		{
			if (scan.hasNextInt())
			{
				searchInt = scan.nextInt();
				validInput = true;
			}
			else
			{
				System.out.print("\nEnter a valid #: ");
				scan.next();
			}
		}
		
		//trying a binary sort
		
		int left = 0;
		int right = barcode.size() - 1;
		int mid = (left + right) / 2;
		
		int tempCounter = 0;
		
		boolean numFalse = false; //checks to see if the number doesn't exist
		
		while (true)
		{
			if (numFalse)
			{
				break;
			}
			else if (barcode.get(mid) == searchInt || barcode.get(right) == searchInt || barcode.get(left) == searchInt)
			{
				System.out.println("\nYour number exists!");
				break;
			}
			else
			{
				
				while (barcode.get(mid) != searchInt && barcode.get(right) != searchInt && barcode.get(left) != searchInt) //loop to run the search over and over
				{
					if (tempCounter > (Math.log(barcode.size())/Math.log(2))) //finds the most times that a set of data can be cut in half
					{
						System.out.println("\nYour number does not exist");
						numFalse = true;
						break;
					}
					else if (searchInt < barcode.get(mid))
					{
						right = mid;
						mid = (left + right) / 2;
						System.out.println("too low");
					}
					else if (searchInt > barcode.get(mid))
					{
						left = mid;
						mid = (left + right) / 2;
						System.out.println("too high");
					}
					
					System.out.print(tempCounter);
					tempCounter++; //keeps in track how many times it has iterated through
				}
			}
		}
		
		
		//graveyard:
		
		/*ArrayList<Integer> barcodeTwo = new ArrayList<Integer>(); //binary sort attempt one
		barcodeTwo = barcode; //setting barcodeTwo list to be the same as barcode so we don't mess it up
		
		while (barcodeTwo.size() >= 1) //checking and running through the parameters because at the end it will check if searchInt is equal to the last thing in the barcode
		{
			searchInt = scan.nextInt();
			if (searchInt < barcodeTwo.get(barcodeTwo.size()/2))
			{
				barcodeTwo.removeRange(barcodeTwo.size()/2, barcodeTwo.size() - 1); //remove from one element beyond
			}
			else if (searchInt > barcodeTwo.get(barcodeTwo.size()/2))
			{
				barcodeTwo.removeRange(0, barcodeTwo.size()/2);
			}
			else
			{
				System.out.println("The number exists");
			}
		}*/
		
		/*System.out.print("Enter a number to search for:");	//searches for the input int
		searchInt = scan.nextInt();
		for (Integer i : barcode) {
			if (barcode.get(i) == searchInt) {
				System.out.println("\ntrue");
			}
		} */
		
	} //close main
} //close class
