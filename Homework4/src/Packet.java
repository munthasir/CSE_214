/**
 * @author Munthasir Islam 
 * ID: 111314942
 * Recitation: 10
 * Recitation TA: Reed Gantz
 */

public class Packet {
	private static int packetCount = 0;
	private int id , packetSize, timeArrive, timeToDest;
	//Constructors
	/**
	 * Constructs and initializes the Packet object.
	 */
	public Packet() {
		packetCount++;
		packetSize = 0;
		timeArrive = 0;
	}
	/**
	 * Constructs and initializes the Packet object.
	 */
	public Packet(int packetSize, int timeArrive) {
		packetCount++;
		id = packetCount;
		this.packetSize = packetSize;
		this.timeArrive = timeArrive;
		timeToDest = packetSize/100;
	}
	//Accessors
	/**
	 * This method lets user access the unique identifier for the Packet object.
	 * @return id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * This method lets user access the Packet object size.
	 * @return packetSize
	 */
	public int getPacketSize() {
		return this.packetSize;
	}
	/**
	 * This method lets user access the time a Packet object have arrived.
	 * @return timeArrive
	 */
	public int getTimeArrive() {
		return this.timeArrive;
	}
	/**
	 * This method lets user access the time required for a Packet object to reach its destination.
	 * @return
	 */
	public int getTimeToDest() {
		return this.timeToDest;
	}
	//Mutator
	/**
	 * This method sets the size of a Packet object.
	 * @param packetSize
	 */
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	/**
	 * This method sets the arrival time of a Packet object.
	 * @param timeArrive
	 */
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}
	/**
	 * This method sets the time left for a Packet object to reach destination router.
	 * @param timeToDest
	 */
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}
	//toString
	/**
	 * This method is a toString method for the Packet object.
	 */
	public String toString() {
		return "[" + id + ", " + timeArrive + ", " + timeToDest + "]";
	}
	
}
