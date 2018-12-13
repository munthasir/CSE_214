/**
 * @author Munthasir Islam
 * ID:111314942
 * Email: munthasir.islam@stonybrook.edu
 * Homework 6
 * CSE 214
 * R10 - Reed Gantz
 */
import big.data.*;
import java.util.Hashtable;
import java.util.Set;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class AuctionTable implements Serializable {
	private static final long serialVersionUID = 1177312054109675970L;
	private static int infoLength = 41; // 1-42 or 0-41
	private HashMap<String, Auction> table = new HashMap<String, Auction>();
	/**
	 * This method uses the BigData library to construct an AuctionTable from a remote data source.
	 * @param URL
	 * @return
	 * @throws IllegalArgumentException
	 * Thrown if the URL does not represent a valid datasource (can't connect or invalid syntax).
	 */
	public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException {
		DataSource ds = DataSource.connect(URL).load();

		AuctionTable at = new AuctionTable();

		// Auction ID
		String[] id = ds.fetchStringArray("listing/auction_info/id_num");
		// Bid
		String[] highest_bid = ds.fetchStringArray("listing/bid_history/highest_bid_amount");
		// Seller
		String[] seller_name = ds.fetchStringArray("listing/seller_info/seller_name");
		// Buyer
		String[] bidder_name = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
		// Time
		String[] time_left = ds.fetchStringArray("listing/auction_info/time_left");
		// Item Info
		String[] cpu = ds.fetchStringArray("listing/item_info/cpu");
		String[] memory = ds.fetchStringArray("listing/item_info/memory");
		String[] hard_drive = ds.fetchStringArray("listing/item_info/hard_drive");
		String[] item_info = new String[cpu.length];
		// Making the item information with length of 42.
		for (int i = 0; i < cpu.length; i++) {
			item_info[i] = cpu[i] + memory[i] + hard_drive[i];
			if (!isNullOrWhiteSpace(item_info[i]) && item_info[i].length() > 41) {
				item_info[i] = item_info[i].substring(0, infoLength);
			}
		}
		// Converting time_left from string to integer amount of hour
		int[] time_remaining = new int[time_left.length];
		for (int i = 0; i < time_left.length; i++) {
			String[] track = time_left[i].split(" "); // Split the string to for ex. |2|days,|6|hours|+|

			if(time_left[i].contains("hours")) {
				String days = String.valueOf(track[0]);
				int day = Integer.parseInt(days);
				int dayToHour = day * 24;

				String hr = String.valueOf(track[2]);
				int hour = Integer.parseInt(hr);

				time_remaining[i] = dayToHour + hour;
			} else {
				String days = String.valueOf(track[0]);
				int day = Integer.parseInt(days);
				int dayToHour = day * 24;
				time_remaining[i] = dayToHour;
			}
		}
		// Making bids from string into float
		float[] top_bid = new float[highest_bid.length];
		for (int i = 0; i < highest_bid.length; i++) {
			highest_bid[i] = highest_bid[i].replace("$", "");
			highest_bid[i] = highest_bid[i].replace(",", "");
			float temp = 0;
			if (!isNullOrWhiteSpace(highest_bid[i])) {
				temp = Float.parseFloat(highest_bid[i]);
			}
			top_bid[i] = temp;
		}
		// Creating a list of auction and put them in hashmap.
		Auction auction;
		for (int i = 0; i < id.length; i++) {
			auction = new Auction(time_remaining[i], top_bid[i], id[i], seller_name[i], bidder_name[i], item_info[i]);
			at.putAuction(id[i], auction);
		}

		return at;
	}
	/**
	 * This method returns true if a string is null or just filled with white spaces.
	 * @param value
	 * @return boolean
	 */
	public static boolean isNullOrWhiteSpace(String value) {
		value = value.replace(" ", "");
	    return value == null || value.trim().isEmpty();
	}
	/**
	 * This method manually posts an auction, and add it into the table.
	 * @param auctionID
	 * @param auction
	 * @throws IllegalArgumentException
	 */
	public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException {
		table.put(auctionID, auction);
	}
	/**
	 * This method gets the information of an Auction that contains the given ID as key.
	 * @param auctionID
	 * @return
	 */
	public Auction getAuction(String auctionID) {
		
		Auction temp = table.get(auctionID);
		return temp;
	}
	/**
	 * Simulates the passing of time. Decrease the timeRemaining of all 
	 * Auction objects by the amount specified. The value cannot go below 0.
	 * @param numHours
	 * @throws IllegalArgumentException
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException {
		if (numHours < 0)
			throw new IllegalArgumentException();
		Set<String> keys = table.keySet();
		for (String i : keys) {
			table.get(i).decrementTimeRemaining(numHours);
		}
	}
	/**
	 * This method iterates over all Auction objects in the table and removes 
	 * them if they are expired (timeRemaining == 0).
	 */
	public void removeExpiredAuctions() {
		Set<String> keys = table.keySet();
		String[] removeKey = new String[keys.size()];
		int loopVar = 0;
		for (String i : keys) {
			if (table.get(i).getTimeRemaining() == 0) {
				removeKey[loopVar] = i;
				loopVar++;
			}
		}
		for (int i = 0; i < removeKey.length; i++) {
			table.remove(removeKey[i]);
		}
	}
	/**
	 * This method prints the AuctionTable in tabular form.
	 */
	public void printTable() {
		String s1 = String.format("%-10s %-5s %-5s %-10s %-15s %-10s %-15s %-4s %-7s %-3s %-42s", "Auction ID", "|",
				"Bid", "|", "Seller", "|", "Buyer", "|", "Time", "|", "item Info");
		System.out.println(s1);
		String equals = "";
		for (int i = 0; i < 134; i++) {
			equals = equals + "=";
		}
		System.out.println(equals);
		Set<String> keys = table.keySet();
		for (String i : keys) {
			System.out.println(table.get(i).toString());
		}
	}
}
