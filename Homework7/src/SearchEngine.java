/**
 * @author Munthasir Islam
 * ID:111314942
 * Email: munthasir.islam@stonybrook.edu
 * Homework 7
 * CSE 214
 * R10 - Reed Gantz
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class SearchEngine {
	public static WebGraph wg;
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	
	
	public static void main(String[] args) {
		System.out.println("Loading WebGraph data...");
		
		try {
			wg = WebGraph.buildFromFiles("pages.txt", "links.txt");
			System.out.println("Success!");
		} 
		catch (IOException e) {
			System.out.println("Unable to load WebGraph. Please try again");
		}
		menuList();
	}
	public static void menuList() {
		String inp;
		Scanner input = new Scanner(System.in);
		System.out.println("Menu:\r\n" +
				"    (AP) - Add a new page to the graph.\r\n" + 
				"    (RP) - Remove a page from the graph.\r\n" + 
				"    (AL) - Add a link between pages in the graph.\r\n" + 
				"    (RL) - Remove a link between pages in the graph.\r\n" + 
				"    (P)  - Print the graph.\r\n" + 
				"    (S)  - Search for pages with a keyword.\r\n" + 
				"    (Q)  - Quit.");
		System.out.print("Please select an option: ");
		inp = input.nextLine().toUpperCase();
		menu(inp);
	}
	public static void menu(String inp) {
		Scanner input = new Scanner(System.in);
		
		String url;
		ArrayList<String> keywords = new ArrayList<>();
		
		switch (inp) {
		case "AP":
			System.out.print("Enter a URL: ");
			url = input.nextLine();
			String kw;
			System.out.print("Enter keywords (space-separated): ");
			kw = input.nextLine();
			int spaceCount = 0;
			for (char c : kw.toCharArray()) {
			    if (c == ' ') {
			         spaceCount++;
			    }
			}
			if (spaceCount>0) {
				String temp[] = new String[spaceCount];
				for (int i = 0; i < spaceCount; i++) {
					temp = kw.split(" ");
				}
				for (int i = 0; i <= spaceCount; i++) {
					keywords.add(temp[i]);
				}
			} else {
				keywords.add(kw);
			}
			//Add them to the webpage
			try {
				wg.addPage(url, keywords);
				System.out.println("\n" + url + " successfully added to the WebGraph!");
			} catch(IllegalArgumentException e) {
				System.out.println("\nError: " + url + " already exists in  the WebGraph. Could not add new WebPage.");
			}
			menuList();
		case "RP":
			System.out.print("Enter a URL: ");
			url = input.nextLine();
			wg.removePage(url);
			System.out.println("\n" + url + " has been removed from the graph!\n");
			menuList();
		case "AL":
			String source, destination;
			System.out.print("Enter a source URL: ");
			source = input.nextLine();
			System.out.print("Enter a destination URL: ");
			destination = input.nextLine();
			try{
				wg.addLink(source, destination);
				wg.updateLinks();
				System.out.println("\nLink successfully added from " + source + " to " + destination + "!\n");
			} catch(IllegalArgumentException e) {
				System.out.println("Links couldn't be established between "+ source + " and " + destination);
			}
			menuList();
		case "RL":
			System.out.print("Enter a source URL: ");
			source = input.nextLine();
			System.out.print("Enter a destination URL: ");
			destination = input.nextLine();
			wg.removeLink(source, destination);
			System.out.println("\nLink removed from " + source + " to " + destination + "!");
			menuList();
		case "P":
			wg.printTable();
			menuList();
		case "S":
			System.out.print("Search keyword: ");
			String keyword = input.nextLine();
			wg.sortByRank();
			ArrayList<WebPage> hasKeyword = (ArrayList<WebPage>) wg.relatedKeyord(keyword);
			if(hasKeyword.isEmpty())
				System.out.println("No pages found containing keyword: \"" + keyword + "\"");
			else{
				System.out.format("%3s %6s %24s", "Rank", "Page Rank", "URL");
				System.out.println("---------------------------------------------");
				
				int rank;
				int list = 1;
				WebPage currentPage = new WebPage();
				Iterator<WebPage> iterator = hasKeyword.iterator();
				while(((Iterator<String>) hasKeyword).hasNext())
				{
					currentPage = iterator.next();
					url = currentPage.getURL();
					rank = currentPage.getRank();
					System.out.format("%1$-4s|%2$-7s|%3$-20s\\n", list++, rank, url);
				}
			}
			menuList();
		case "Q":
			System.out.println("\nGoodbye.");
			System.exit(0);
		}
		
	}


}
