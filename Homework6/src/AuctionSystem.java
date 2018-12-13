/**
 * @author Munthasir Islam
 * ID:111314942
 * Email: munthasir.islam@stonybrook.edu
 * Homework 6
 * CSE 214
 * R10 - Reed Gantz
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class AuctionSystem implements Serializable{
	private static AuctionTable auctionTable;
	private static Auction temp;
	private static String username;

	public static void main(String[] args) {
		try {
			System.out.println("Starting...");
			FileInputStream file;
			file = new FileInputStream("auction.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);

			auctionTable = (AuctionTable) inStream.readObject();
			System.out.println("Loading previous Auction Table...");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("No previous auction table detected.\r\n" + 
					"Creating new table... ");
			//e.printStackTrace();
		}
		
		
		
		Scanner input = new Scanner(System.in);
		System.out.print("Please select a username: ");
		username = input.nextLine();
		menuList();
	}

	public static void menuList() {
		String inp;
		Scanner input = new Scanner(System.in);
		System.out.println("(D) - Import Data from URL\r\n" + "(A) - Create a New Auction\r\n"
				+ "(B) - Bid on an Item\r\n" + "(I) - Get Info on Auction\r\n" + "(P) - Print All Auctions\r\n"
				+ "(R) - Remove Expired Auctions\r\n" + "(T) - Let Time Pass\r\n" + "(Q) - Quit");
		System.out.print("Please select an option: ");
		inp = input.nextLine().toUpperCase();
		menu(inp);
	}

	public static void menu(String inp) {

		//String decision;
		Scanner input = new Scanner(System.in);
		switch (inp) {
		case "D":
			String URL;
			System.out.print("Please enter a URL: ");
			URL = input.nextLine();
			//AuctionTable temp1 = new AuctionTable();
			auctionTable = AuctionTable.buildFromURL(URL);
			System.out.println("Loading...\r\n" + "Auction data loaded successfully!");
			menuList();
		case "A":
			String id, info;
			int time;
			System.out.println("Creating new Auction as " + username);
			System.out.print("Please enter an Auction ID:");
			id = input.nextLine();
			System.out.print("Please enter an Auction time (hours): ");
			time = input.nextInt();
			input.nextLine();
			System.out.print("Please enter some Item Info: ");
			info = input.nextLine();
			temp = new Auction(time, 0, id, username, "", info);
			auctionTable.putAuction(id, temp);
			System.out.println("Auction " + id + " inserted into table.");
			menuList();
		case "B":
			float newBid;
			System.out.print("Please enter an Auction ID: ");
			id = input.nextLine();
			temp = auctionTable.getAuction(id);
			
			System.out.println("    Current Bid: $" + temp.getCurrentBid());
			System.out.println("What would you like to bid?: ");
			newBid = input.nextFloat();
			try {
				temp.newBid(username, newBid);
				if (temp.getCurrentBid() == newBid) {
					System.out.println("Bid accepted.");
				} else {
					System.out.println("Bid not accepted");
				}
			} catch (ClosedAuctionException e) {
				System.out.println("Auction " + id + "is CLOSED");
			}
			menuList();
		case "I":
			System.out.print("Please enter an Auction ID: ");
			id = input.nextLine();
			temp = auctionTable.getAuction(id);
			System.out.println("Auction " + id);
			System.out.println("\tSeller: " + temp.getSellerName());
			System.out.println("\tBuyer: " + temp.getBuyerName());
			System.out.println("\tTime: " + temp.getTimeRemaining() + "hours");
			System.out.println("\tinfo: " + temp.getItemInfo());
			menuList();
		case "P":
			auctionTable.printTable();
			menuList();
		case "R":
			System.out.println("Removing expired auctions...");
			auctionTable.removeExpiredAuctions();
			System.out.println("All expired auctions removed.");
			menuList();
		case "T":
			System.out.print("How many hours should pass: ");
			time = input.nextInt();
			auctionTable.letTimePass(time);
			System.out.println("Time passing...\r\n" + "Auction times updated.");
			menuList();
		case "Q":
			System.out.println("Writing Auction Table to file... ");
			FileOutputStream file;
			try {
				file = new FileOutputStream("auction.obj");
				ObjectOutputStream outStream = new ObjectOutputStream(file);
				outStream.writeObject(auctionTable);
				outStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Done!\n\nGoodbye.");
			System.exit(0);
		}
	}
}
