/**
 * @author Munthasir Islam
 * ID:111314942
 * Email: munthasir.islam@stonybrook.edu
 * Homework 7
 * CSE 214
 * R10 - Reed Gantz
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

public class WebPage {
	private String url;
	private int index;
	private int rank;
	private ArrayList<String> keywords = new ArrayList<>();
	private String links;
	public WebPage() {
		
	}
	/**
	 * This is a constructor for the WebPage object.
	 * @param url
	 * This String variable holds the url of a web page.
	 * @param index
	 * This integer variable holds the index of a web page.
	 * @param rank
	 * This integer variable holds the ranking of a web page.
	 * @param keywords
	 * This Collection type is a collection of keywords associated with a web page.
	 */
	public WebPage(String url, int index, int rank, ArrayList<String> keywords) {
		this.url = url;
		this.index = index;
		this.rank = rank;
		this.keywords = keywords;
	}
	/**
	 * This is a get method for URL
	 * @return url
	 */
	public String getURL() {
		return url;
	}
	/**
	 * This is a get method for Rank
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * This is a get method for Keywords
	 * @return keywords
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	/**
	 * This is a set method for rank
	 * @param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * This is a set method for link
	 * @param link
	 */
	public void setLink(String link) {
		this.links = link;
	}
	
	/**
	 * This method returns the string of data members in tabular form.
	 */
	public String toString() {
		String temp = "";
		for (String s : keywords) {
	        if (temp == "") {
	        	temp = temp + " " + s;
	        }else {
	        	temp = temp + ", " + s;
	        }
	    }
		String ret = String.format("%3s %3s %-19s %-1s %4s %4s %-20s %-1s %-42s", index, "|", url, "|", rank, "|", links, "|", temp);
		return ret;
	}
}
