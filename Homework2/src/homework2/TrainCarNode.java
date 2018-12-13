/**
 * @author Munthasir Islam
 * ID: 111314942
 * TA: Reed Gantz
 */
package homework2;

public class TrainCarNode {
	private TrainCarNode prev, next;
	private TrainCar car;
	
	/**
	 * Constructs and initializes the TrainCarNode object.
	 */
	public TrainCarNode() {
		prev = null;
		next = null;
		car = null;
	}
	/**
	 * Constructs and initializes the TrainCarNode object.
	 * @param car
	 */
	public TrainCarNode(TrainCar car) {
		this.car = car;
		prev = null;
		next = null;
	}
	/**
	 * This methods takes a TrainCarNode object as an argument and sets it as reference to previous node.
	 * @param node
	 */
	public void setPrev(TrainCarNode node) {
		this.prev = node;
	}
	/**
	 * This methods takes a TrainCarNode object as an argument and sets it as reference to next node.
	 * @param node
	 */
	public void setNext(TrainCarNode node) {
		this.next = node;
	}
	/**
	 * This method sets a TrainCar object as reference containing the data.
	 * @param car
	 */
	public void setCar(TrainCar car) {
		this.car = car;
	}
	/**
	 * This methods returns a TrainCarNode object which is the reference to previous node.
	 * @return prev
	 */
	public TrainCarNode getPrev() {
		return this.prev;
	}
	/**
	 * This methods returns a TrainCarNode object which is the reference to next node.
	 * @return next
	 */
	public TrainCarNode getNext() {
		return this.next;
	}
	/**
	 * This method returns a TrainCar object as reference containing the data.
	 * @return car
	 */
	public TrainCar getCar() {
		return this.car;
	}
}
