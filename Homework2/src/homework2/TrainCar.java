/**
 * @author Munthasir Islam
 * ID: 111314942
 * TA: Reed Gantz
 */
package homework2;

public class TrainCar {
	private double carLenth, carWeight;
	private ProductLoad load;
	
	//Constructor
	/**
	 * Constructs and initializes the TrainCar object.
	 * @param carLength
	 * @param carWeight
	 */
	public TrainCar(double carLength, double carWeight){
		this.carLenth = carLength;
		this.carWeight = carWeight;
		
	}
	
	//Accessor
	/**
	 * This method returns the length of a car.
	 * @return carLength
	 */
	public double getCarLength() {
		return this.carLenth;
	}
	/**
	 * This method returns the weight of a car.
	 * @return carWeight
	 */
	public double getCarWeight() {
		return this.carWeight;
	}
	/**
	 * This method returns the ProductLoad object load.
	 * @return load
	 */
	public ProductLoad getProductLoad() {
		return this.load;
	}
	//Mutators
	/**
	 * This method sets the load object of load class.
	 * @param load
	 */
	public void setProductLoad(ProductLoad load) {
		this.load = load;
		
	}
	/**
	 * This method returns true if Train car is empty else returns false.
	 * @return boolean
	 */
	public boolean isEmpty() {
		if(this.load.getWeight() == 0) {
			return true;
		}
		return false;
	}
	
}
