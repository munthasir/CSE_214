/**
 * @author Munthasir Islam
 * ID:111314942
 * Email: munthasir.islam@stonybrook.edu
 * Homework 6
 * CSE 214
 * R10 - Reed Gantz
 */
import java.io.Serializable;
import java.text.DecimalFormat;

public class Auction implements Serializable{
	private int timeRemaining;
	private double currentBid;
	private String auctionID, sellerName, buyerName, itemInfo;
	
	/**
	 * This is a constructor method for Auction class.
	 * @param timeRemaining
	 * This variable holds the time value that is remaining till auction is closed.
	 * @param currentBid
	 * This variable holds the current bid value.
	 * @param auctionID
	 * This variable holds the auction id.
	 * @param sellerName
	 * This variable holds the seller name.
	 * @param buyerName
	 * This variable holds the buyer name.
	 * @param itemInfo
	 * This variable holds the information about the item.
	 */
	public Auction(int timeRemaining, double currentBid, String auctionID, String sellerName, String buyerName, String itemInfo) {
		this.timeRemaining = timeRemaining;
		this.currentBid = currentBid;
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
	}
	/**
	 * This method returns the time that is remaining.
	 * @return timeRemaining
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}
	/**
	 * This method returns the current bid price
	 * @return currentBid
	 */
	public double getCurrentBid() {
		return currentBid;
	}
	/**
	 * This method returns the auction id.
	 * @return auctionID
	 */
	public String getAuctionID() {
		return auctionID;
	}
	/**
	 * This method returns the seller name.
	 * @return sellerName
	 */
	public String getSellerName() {
		return sellerName;
	}
	/**
	 * This method returns the buyer name.
	 * @return buyerName
	 */
	public String getBuyerName() {
		return buyerName;
	}
	/**
	 * This method returns the item information.
	 * @return itemInfo
	 */
	public String getItemInfo() {
		return itemInfo;
	}
	/**
	 * This method is used to decrement the amount of time that is remaining.
	 * @param time
	 * time parameter will be subtracted from the remaining time
	 * and if time parameter is greater than remaining time then
	 * timeRemaining is set to 0.
	 */
	public void decrementTimeRemaining(int time) {
		if (time > timeRemaining) {
			timeRemaining = 0;
		}
		else {
			timeRemaining = timeRemaining - time; 
		}
	}
	/**
	 * This method makes a new bid on this auction.
	 * @param bidderName
	 * bidderName is the name of the bidder which will be
	 * updated if bidAmt is greater than the current bid.
	 * @param bidAmt
	 * bidAmt is the new bid by the user.
	 * @throws ClosedAuctionException
	 * This exception is thrown if user try to bid on an
	 * auction that is closed.
	 */
	public void newBid (String bidderName, double bidAmt) throws ClosedAuctionException{
		if (timeRemaining == 0) throw new ClosedAuctionException();
		else if(bidAmt > currentBid) {
			currentBid = bidAmt;
			buyerName = bidderName;
		}
	}
	/**
	 * This is a toString method for Auction class.
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		String temp = String.format("%-10s %-1s %-9s %-1s %-24s %-1s %-24s %-1s %-10s %-1s %-42s", auctionID, "|", "$"+formatter.format(currentBid), "|", sellerName, "|", buyerName, "|", timeRemaining + " hours", "|" , itemInfo);
		return temp;
	}
}
