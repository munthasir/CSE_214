/**
 * @author Munthasir Islam
 * ID: 111314942
 * TA: Reed Gantz
 */
package homework2;

public class ProductLoad {
	private String productName;
	private double weight, value;
	private boolean productStatus;

	// Constructor
	/**
	 * Constructs and initializes the ProductLoad object.
	 */
	public ProductLoad() {
		this.productName = "";
		this.weight = 0;
		this.value = 0;
		this.productStatus = false;
	}

	// mutator
	/**
	 * This method sets product name
	 * 
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * This method sets the product weight in tons.
	 * 
	 * @param weight
	 */
	public void setWeight(double weight) throws IllegalNegativeValueException {
		if (weight < 0) {
			throw new IllegalNegativeValueException();
		}
		else {
			this.weight = weight;
		}
	}

	/**
	 * This method sets the product value in dollars.
	 * 
	 * @param value
	 */
	public void setValue(double value) throws IllegalNegativeValueException {
		if(value<0) {
			throw new IllegalNegativeValueException();
		}
		else{
			this.value = value;
		}
	}

	/**
	 * This method sets whether the product is dangerous or not.
	 * 
	 * @param productStatus
	 */
	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	// accessor
	/**
	 * This method returns the name of the product.
	 * 
	 * @return productName
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * This method returns the product weight in tons.
	 * 
	 * @return weight
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * This method returns the product value in dollars.
	 * 
	 * @return value
	 */
	public double getValue() {
		return this.value;
	}

	/**
	 * This method r eturns whether the product is dangerous or not.
	 * 
	 * @return productStatus
	 */
	public boolean getProductStatus() {
		return this.productStatus;
	}

}
