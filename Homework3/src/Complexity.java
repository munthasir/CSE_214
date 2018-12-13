
public class Complexity {
	private int nPower, logPower;
	/**
	 * Constructs and initializes the Complexity object.
	 */
	public Complexity() {
		nPower = 0;
		logPower = 0;
	}
	public Complexity(int nPower, int logPower) {
		this.nPower = nPower;
		this.logPower = logPower;
	}
	//accessor
	/**
	 * This method lets user access nPower.
	 * @return nPower
	 */
	public int getNpower() {
		return nPower;
	}
	/**
	 * This method lets user access logPower.
	 * @return logPower
	 */
	public int getLogPower() {
		return logPower;
	}
	//mutators
	/**
	 * This method sets the int variable nPower.
	 * @param nPower
	 */
	public void setNpower(int nPower) {
		this.nPower = nPower;
	}
	/**
	 * This method sets the int variable logPower.
	 * @param logPower
	 */
	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
	/**
	 * This method returns the human-readable Big-Oh notation.
	 * e.g. O(n^4 * log(n)^2).
	 * @return 
	 * returns a String
	 */
	public String toString() {
		if(logPower == 0 && nPower != 0) {
			return "O(n^" + nPower + ").";
		}
		else if(nPower == 0 && logPower != 0) {
			return "("+"log(n)^" + logPower + ").";
		}
		else if (logPower == 0 && nPower == 0) {
			return "O(1)";
		}
		else{
			return "O(n^" + nPower + " * " + "log(n)^" + logPower + ").";
		}
	}
}
