/**
 * @author Munthasir Islam
 * ID: 111314942
 * TA: Reed Gantz
 */
/*
 * IMPORTANT NOTE: Case L doesn't work because for some reason product load always
 * 				   stays null even if I initialize it. I wanted to come to TA hour
 * 				   but it was raining really badly and I had no umbrella so I was stuck.
 * 				   I am sure it is just a small stupid mistake somewhere and I hope
 * 				   I will still receive partial credit for rest of my code.
 * 				   Thanks a lot!
 */
package homework2;

import java.util.Scanner;

public class TrainManager {
	
	public static TrainLinkedList t = new TrainLinkedList();
	public static TrainCar newCar;
	public static ProductLoad newLoad;
	
	public static void main(String[] args) {
		menuList();
	}
	
	public static void menuList() {
		String inp;

		System.out.println("(F) Cursor Forward \r\n" + 
				"(B) Cursor Backward \r\n" + 
				"(I) Insert Car After Cursor \r\n" + 
				"(R) Remove Car At Cursor \r\n" + 
				"(L) Set Product Load \r\n" + 
				"(S) Search For Product \r\n" + 
				"(T) Display Train \r\n" + 
				"(M) Display Manifest \r\n" + 
				"(D) Remove Dangerous Cars \r\n" + 
				"(Q) Quit");
		System.out.println("Enter a selection: ");
		Scanner input = new Scanner(System.in);
		inp = input.nextLine().toUpperCase();
		menu(inp);
	}
	
	public static void menu(String inp) {
		String decision;
		Scanner input = new Scanner(System.in);
		switch (inp) {
		case "F":
			if(t.cursorNextNull()) {
				System.out.println("No next car, cannot move cursor forward.");
			}
			else {
				t.cursorForward();
				System.out.println("Cursor have been moved forward.");
			}
			menuList();
		case "B":
			if(t.cursorPrevNull()) {
				System.out.println("This is the first car of the train, cannot move cursor backward.");
			}
			else {
				t.cursorBackward();
				System.out.println("Cursor have been moved backward.");
			}
			menuList();
		case "I":
			double weight, length;
			while(true) {
				System.out.println("Enter car length in meters: ");
				length = input.nextDouble();
				System.out.println("Enter car weight in tons: ");
				weight = input.nextDouble();
				newCar = new TrainCar(length, weight);
				try{
					t.insertAfterCursor(newCar);
					break;
				}catch(IllegalArgumentException e) {
					System.out.println("The car you entered is null!");
				}
			}
			System.out.println("New Train Car " + length+ " meters " + weight + " tons inserted into train");
			menuList();
		case "R":
			t.RemoveCursor();
			System.out.println("Train car is removed at current position of the cursor.");
			menuList();
		case "L":
			String name, dec;
			double value;
			newLoad = new ProductLoad();
			System.out.print("\nEnter product name: ");
			name = input.nextLine();
			while(true) {
				System.out.print("\nEnter product weight in tons: ");
				weight = input.nextDouble();
				try{
					newLoad.setWeight(weight);
					break;
				}catch(IllegalNegativeValueException e) {
					System.out.println("Negative weight entered!");
				}
			}
			while(true){
				System.out.print("\nEnter product value in dollars: ");
				value = input.nextDouble();
				input.nextLine();
				try{
					newLoad.setValue(value);
					break;
				}catch(IllegalNegativeValueException e) {
					System.out.println("Negative value entered!");
				}
			}
			System.out.print("\nEnter is product dangerous? (y/n): ");
			dec = input.nextLine();

			newLoad.setProductName(name);

			if("y".equals(dec)) {

				newLoad.setProductStatus(true);
			}
			else {

				newLoad.setProductStatus(false);
			}
			newCar.setProductLoad(newLoad);
			
			t.setCursorData(newCar);
			System.out.println("\n"+weight+" tons of "+name+" added to the current car.");
			menuList();
		case "S":
			System.out.print("Enter the name of the product you want to search for: ");
			name = input.nextLine();
			t.findProduct(name);
			menuList();
		case "T":
			System.out.println(t.toString());
			menuList();
		case "M":
			t.printManifest();
			menuList();
		case "D":
			t.removeDangerousCars();
			System.out.println("All the dangerous car's are removed from the train!");
			menuList();
		case "Q":
			System.out.print("*****Program ends!*****");
			System.exit(0);
		}
	}

}
