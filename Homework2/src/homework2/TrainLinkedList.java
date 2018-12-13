/**
 * @author Munthasir Islam
 * ID: 111314942
 * TA: Reed Gantz
 */
package homework2;

public class TrainLinkedList {
	private TrainCarNode head, tail, cursor;

	/**
	 * Constructs and initializes the TrainLinkedList object.
	 */
	public TrainLinkedList() {
		head = null;
		tail = null;
		cursor = null;
	}

	/**
	 * This method returns a reference to the TrainCar at the node currently
	 * referenced by the cursor.
	 * 
	 * @return A TrainCar object at cursor.
	 */
	public TrainCar getCursorData() {
		return this.cursor.getCar();
	}

	/**
	 * This method places car in the node currently referenced by the cursor.
	 * 
	 * @param car
	 * A TrainCar object car
	 */
	public void setCursorData(TrainCar car) {
		cursor.setCar(car);
	}

	/**
	 * This method moves the cursor to point at the next TrainCarNode.
	 */
	public void cursorForward() {
		cursor = cursor.getNext();
	}

	/**
	 * This method moves the cursor to point at the previous TrainCarNode.
	 */
	public void cursorBackward() {
		cursor = cursor.getPrev();
	}

	/**
	 * This method returns false if the next TrainCarNode is empty.
	 * 
	 * @return A boolean variable
	 */
	public boolean cursorNextNull() {
		if (cursor.getNext() == null) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns false if the previous TrainCarNode is empty.
	 * 
	 * @return A boolean variable
	 */
	public boolean cursorPrevNull() {
		if (cursor.getPrev() == null) {
			return true;
		}
		return false;
	}

	/**
	 * This method inserts a car into the train after the cursor position.
	 * 
	 * @param newCar
	 * A TrainCar object named newCar.
	 * 
	 * @throws IllegalArgumentException
	 * Throws exception if the @param that is passed is null.
	 */
	public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException {
		if (newCar == null) {
			throw new IllegalArgumentException();
		}
		TrainCarNode newNode = new TrainCarNode(newCar);
		if (cursor == null) {
			head = newNode;
			tail = newNode;
			cursor = newNode;
		} else {
			// newNode.setNext(cursor.getNext());
			cursor.setNext(newNode);
			TrainCarNode temp = cursor;
			cursor = newNode;
			cursor.setPrev(temp);
			if (cursor.getNext() == null) {
				tail = cursor;
			}
		}
	}

	/**
	 * This Method removes the TrainCarNode referenced by the cursor and returns the
	 * TrainCar contained within the node.
	 * 
	 * @return TrainCar object at cursor.
	 */
	public TrainCar RemoveCursor() {
		TrainCar tc = new TrainCar(0, 0);
		if (head.getPrev() == null && head.getNext() == null) {
			tc = cursor.getCar();
			head = null;
			cursor = null;
			tail = null;
			return tc;

		} else if (cursor.getPrev() == null) {
			head = cursor.getNext();
			cursor.setNext(null);
			cursor = head;
			// cursor.setPrev(null);
			return cursor.getCar();
		} else if (cursor.getNext() == null) {
			tail = cursor.getPrev();
			cursor.setPrev(null);
			cursor = tail;
			cursor.setNext(null);
			return cursor.getCar();
		} else {
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
			tc = cursor.getCar();
			cursor = cursor.getNext();
			return tc;
		}
	}

	/**
	 * This method determines the number of TrainCar objects currently on the train.
	 * 
	 * @return a variable named answer containing integer data type.
	 */
	public double size() {
		TrainCarNode nodePtr = head;
		int answer = 0;
		while (nodePtr != null) {
			answer++;
			nodePtr = nodePtr.getNext();
		}
		return answer;
	}

	/**
	 * This method returns the total length of the train in meters.
	 * 
	 * @return a variable named answer containing a double data type.
	 */
	public double getLength() {
		TrainCarNode nodePtr = head;
		double answer = 0;
		while (nodePtr != null) {
			answer = answer + (nodePtr.getCar().getCarLength());
			nodePtr = nodePtr.getNext();
		}
		return answer;
	}

	/**
	 * This method returns the total value of product carried by the train.
	 * 
	 * @return A variable named answer containing a double data type.
	 */
	public double getValue() {
		TrainCarNode nodePtr = head;
		double answer = 0;
		while (nodePtr != null) {
			answer = answer + (nodePtr.getCar().getProductLoad().getValue());
			nodePtr = nodePtr.getNext();
		}
		return answer;
	}

	/**
	 * This method returns the total weight in tons of the train. Note that the
	 * weight of the train is the sum of the weights of each empty TrainCar, plus
	 * the weight of the ProductLoad carried by that car.
	 * 
	 * @return A variable named answer containing a double data type.
	 */
	public double getWeight() {
		TrainCarNode nodePtr = head;
		double answer = 0;
		while (nodePtr != null) {
			answer = answer + (nodePtr.getCar().getCarWeight()) + (nodePtr.getCar().getProductLoad().getWeight());
			nodePtr = nodePtr.getNext();
		}
		return answer;
	}

	/**
	 * This method returns whether or not there is a dangerous product on one of the
	 * TrainCar objects on the train.
	 * 
	 * @return A boolean variable
	 */
	public boolean isDangerous() {
		TrainCarNode nodePtr = head;
		while (nodePtr != null) {
			if (nodePtr.getCar().getProductLoad().getProductStatus() == true) {
				return true;
			}
			nodePtr = nodePtr.getNext();
		}
		return false;
	}

	/**
	 * This method Searches the list for all ProductLoad objects with the indicated
	 * name and sums together their weight and value (Also keeps track of whether
	 * the product is dangerous or not), then prints a single ProductLoad record to
	 * the console.
	 * 
	 * @param name
	 *            A string named name to search the product with same name list.
	 */
	public void findProduct(String name) {
		TrainCarNode nodePtr = head;
		double weight = 0;
		double value = 0;
		boolean dangerous = false;
		while (nodePtr != null) {
			if (nodePtr.getCar().getProductLoad().getProductName().equals(name)) {
				weight = weight + (nodePtr.getCar().getCarWeight()) + (nodePtr.getCar().getProductLoad().getWeight());
				value = value + (nodePtr.getCar().getProductLoad().getValue());
				if (nodePtr.getCar().getProductLoad().getProductStatus() == true) {
					dangerous = true;
				}
			}
			nodePtr = nodePtr.getNext();
		}
		if (dangerous == true) {
			System.out.println("The Product: " + name + "'s weight is: " + weight + " and value is: " + value
					+ " and the product is DANGEROUS!");
		} else {
			System.out.println("The Product: " + name + "'s weight is: " + weight + " and value is: " + value
					+ " and the product is not dangerous!");
		}
	}

	/**
	 * This method Prints a neatly formatted table of the car number, car length,
	 * car weight, load name, load weight, load value, and load dangerousness for
	 * all of the car on the train.
	 */
	public void printManifest() {
		System.out.format("%4s %31s", "CAR:", "LOAD: \n");
		System.out.format("%5s %10s %10s %s %-10s %10s %10s %10s", "Num", "Length (m)", "Weight (t)", "|", "Name",
				"Weight (t)", "Value ($)", "Dangerous");
		System.out.println("\n============================+============================================");
		int i = 0;
		String dan = "NO";
		TrainCarNode nodePtr = head;
		while (nodePtr != null) {
			i++;
			if (nodePtr.getCar().getProductLoad() != null) {
				if (nodePtr.getCar().getProductLoad().getProductStatus()) {
					dan = "YES";
				}
				System.out.format("%5s %10s %10s %s %-10s %10s %10s %10s", i, nodePtr.getCar().getCarLength(),
						nodePtr.getCar().getCarWeight(), "|", nodePtr.getCar().getProductLoad().getProductName(),
						nodePtr.getCar().getProductLoad().getWeight(), nodePtr.getCar().getProductLoad().getValue(),
						dan);
				System.out.println("");
			} else {
				System.out.format("%5s %10s %10s %s %-10s %10s %10s %10s", i, nodePtr.getCar().getCarLength(),
						nodePtr.getCar().getCarWeight(), "|", "Empty", "0.0", "0.00", dan);
				System.out.println("");
			}

			nodePtr = nodePtr.getNext();
		}
	}

	/**
	 * This method removes all dangerous cars from the train, maintaining the order
	 * of the cars in the train.
	 */
	public void removeDangerousCars() {
		cursor = head;
		while (cursor != null) {
			if (isDangerous()) {
				RemoveCursor();
			} else {
				cursorForward();
			}
		}
	}

	/**
	 * This method returns a neatly formatted String representation of the train.
	 */
	public String toString() {
		TrainCarNode nodePtr = head;
		double length = 0;
		double weight = 0;
		double value = 0;
		int size = 0;
		boolean dangerous = false;
		while (nodePtr != null) {
			length = length + (nodePtr.getCar().getCarLength());
			weight = weight + (nodePtr.getCar().getCarWeight()) + (nodePtr.getCar().getProductLoad().getWeight());
			value = value + (nodePtr.getCar().getProductLoad().getValue());
			if (nodePtr.getCar().getProductLoad().getProductStatus() == true) {
				dangerous = true;
			}
			size++;
			nodePtr = nodePtr.getNext();
		}
		
		if (dangerous) {
			return ("Train: " + size + " cars, " + length + " meters, "
					+ weight + " tons, $" + value + " value, dangerous!");
		} else {
			return ("Train: " + size + " cars, " + length + " meters, "
					+ weight + " tons, $" + value + " value, not dangerous!");
		}
	}
}
