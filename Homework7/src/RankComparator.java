/**
 * @author Munthasir Islam
 * ID:111314942
 * Email: munthasir.islam@stonybrook.edu
 * Homework 7
 * CSE 214
 * R10 - Reed Gantz
 */
import java.util.Comparator;

public class RankComparator implements Comparator {

	@Override
	public int compare(Object obj1, Object obj2) {
		WebPage w1 = (WebPage) obj1;
		WebPage w2 = (WebPage) obj2;
		return (w2.getRank() + "").compareTo(w1.getRank() + "");
	}

}
