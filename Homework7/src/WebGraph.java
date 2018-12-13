/**
 * @author Munthasir Islam
 * ID:111314942
 * Email: munthasir.islam@stonybrook.edu
 * Homework 7
 * CSE 214
 * R10 - Reed Gantz
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Comparator;
import java.util.Collections;
import java.util.stream.Collectors;

public class WebGraph {
	public static final int MAX_PAGES = 40;
	private static ArrayList<WebPage> pages = new ArrayList<>();
	private static int[][] edges = new int [MAX_PAGES][MAX_PAGES];
	private static int index = 0;
	
	
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) 
			throws IllegalArgumentException, FileNotFoundException, IOException{
		FileInputStream pagesIn;
		WebGraph wg = new WebGraph();
		try {
			//Webpage collections
			pagesIn = new FileInputStream(pagesFile);
			InputStreamReader pagesInStream = new InputStreamReader(pagesIn);
			BufferedReader pagesReader = new BufferedReader(pagesInStream);
			
			String currentLine[] = pagesReader.readLine().split("\\s+");
			String url;
			
			
			while(!currentLine.equals(null)){
				ArrayList<String> keywords = new ArrayList<String>();
				url = currentLine[0];
				for(int i = 1; i < currentLine.length; i++)
					keywords.add(currentLine[i]);
				
				wg.addPage(url, keywords);
				index++;
				try {
					currentLine = pagesReader.readLine().split("\\s+");
				} catch (Exception e){
					break;//break if there are no more lines
				}
			}
			
			
			FileInputStream linksIn = new FileInputStream(linksFile);
			InputStreamReader linksInStream = new InputStreamReader(linksIn);
			BufferedReader linksReader = new BufferedReader(linksInStream);
			
			String line = linksReader.readLine();
			String source;
			String destination;
			while(line != (null))
			{
				source = line.substring(0, line.indexOf(" "));
				destination = line.substring(line.indexOf(" ") + 1, line.length());
				
				wg.addLink(source, destination);
				
				try {
					line = linksReader.readLine();
				} catch (Exception e) {
					break;
				}
			}
			wg.updatePageRanks();
			wg.updateLinks();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return wg;
	}
	/**
	 * This method adds a webpage into WebGraph
	 * @param url
	 * @param keywords
	 * @throws IllegalArgumentException
	 */
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
		WebPage temp = new WebPage(url,index,0,keywords);
		for(int i = 0; i < pages.size(); i++){
			if(pages.get(i).getURL().equals(url))
				throw new IllegalArgumentException("Url already exists in page");
		}
		pages.add(temp);
		
	}
	/**
	 * This method adds links between two webpage
	 * @param source
	 * @param destination
	 * @throws IllegalArgumentException
	 */
	public void addLink(String source, String destination) throws IllegalArgumentException{
		int sourceIndex = 0;
		int destinationIndex = 0;
		int i=0, j=0, k=0;
		while(!pages.isEmpty()) {
			for(WebPage temp : pages) {
				if (temp.getURL().contains(source)) {
					sourceIndex = i;
					j = 1;
				}
				if (temp.getURL().contains(destination)) {
					destinationIndex = i;
					k = 1;
				}
				i++;
			}
			if(j == 1 && k == 1)
				break;
		}
		if (edges[sourceIndex][destinationIndex] == 1) 
				throw new IllegalArgumentException("Link already exist between "+ source + " and " + destination);
		edges[sourceIndex][destinationIndex] = 1;
		
		//updatePageRanks();
	}
	/**
	 * This method removes webpage based on given url
	 * @param url
	 */
	public void removePage(String url) {
		int sourceIndex = 0;
		for(WebPage temp : pages) {
			if (temp.getURL().equals(url)) {
				//remove object
				pages.remove(temp);
				//remove link
				for(int i=0; i<edges.length-1; i++) {
					for (int j=i; j<edges.length-1; j++) {
						if(i >= sourceIndex)
							edges[i][j] = edges [i+1][j];
						if(j >= sourceIndex)
							edges[j][i] = edges [i][j+1];
					}
				}
				break;
			}
			sourceIndex++;
		}

	}
	/**
	 * This method removes links between two web page
	 * @param source
	 * @param destination
	 */
	public void removeLink(String source, String destination) {
		int sourceIndex = -1;
		int destinationIndex = -1;
		int i=0;
		while(!pages.isEmpty()) {
			for(WebPage temp : pages) {
				if (temp.getURL().contains(source)) {
					sourceIndex = i;
				}
				if (temp.getURL().contains(destination)) {
					destinationIndex = i;
				}
				i++;
			}
			break;
		}
		if (sourceIndex == -1) {
			System.out.println(source + " couldn't be found");
		}
		else if (destinationIndex == -1) {
			System.out.println(destination + " couldn't be found");
		}
		else{
			edges[sourceIndex][destinationIndex] = 0;
		}
	}
	/**
	 * This method updates page rankings
	 */
	public void updatePageRanks() {
		int rank[] = new int [index];
		while(!pages.isEmpty()) {
			//Count how many 1 in each column and store it in an array
			for(int i=0; i<index; i++) {
				for(int j=0; j<index; j++) {
					 rank[j] = rank[j] + edges[i][j];
				}
			}
			break;
		}
		//Assign the ranks from the rank array to the objects.
		for(int i=0; i<pages.size(); i++) {
			pages.get(i).setRank(rank[i]);
		}
		
	}
	/**
	 * This method updates the links
	 */
	public void updateLinks() {
		
		while(!pages.isEmpty()) {
			for(int i=0; i<index; i++) {
				String str[] = new String[index];
				int k = 0;
				for(int j=0; j<index; j++) {
					if (edges[i][j] == 1) {
						str[k++] = Integer.toString(j); 
					}
				}
				String str1 = "";
				for(int l=0; l<str.length; l++) {
					if (str[l] == null) {
						break;
					}
					str1 = str1 + str[l] + ", ";
				}
				pages.get(i).setLink(str1);
			}
			break;
		}
		
	}
	/**
	 * This method finds if a keyword exist in the page for searching purposes
	 * @param keyword
	 * @return
	 */
	public Collection<WebPage> relatedKeyord(String keyword){
		Collection<WebPage> wPage = new ArrayList<WebPage>();
		
		WebPage find;
		String temp;
		
		for(int i = 0; i < pages.size(); i++)
		{
			find = pages.get(i);
			temp = find.getKeywords().stream().map(x -> String.valueOf(x)).collect(Collectors.joining(", "));;
			if(temp.contains(keyword))
			{
				wPage.add(find);
			}
		}
		
		return wPage;
	}
	/**
	 * This is basically to string method for WebGraph object.
	 */
	public void printTable() {
		String temp = String.format("%3s %6s %24s %6s %25s", "Index", "URL", "PageRank", "Links", "Keywords");
		String temp1 = "-----------------------------------------------------------------------------------------------------";
		System.out.println(temp);
		System.out.println(temp1);
		while(!pages.isEmpty()) {
			for(WebPage temp2 : pages) {
				System.out.println(temp2.toString());
			}
			break;
		}
	}
	/**
	 * This method sorts by WebPage ranking
	 */
	public void sortByRank()
	{
		Collections.sort((ArrayList<WebPage>) pages, new RankComparator());
	}
}
