/**
 * @author Munthasir Islam 
 * ID: 111314942
 * Recitation: 10
 * Recitation TA: Reed Gantz
 */
public class Router {
	public int capacity;					//static removed
	public int size;
	private Packet[] data = new Packet[capacity];
	private int front, rear;
	/**
	 * Constructs and initializes the Router object.
	 * @param capacity
	 */
	public Router(int capacity) {
		front = -1;
		rear = -1;
		this.capacity = capacity;
		size = 0;
		data = new Packet[capacity];
		
	}
	/**
	 * This method adds a new Packet to the end of the router buffer.
	 * @param p
	 * takes an argument Packet p which will be enqueued into the queue.
	 * @throws FullQueueException
	 * throws FullQueueException if user tries to enqueue a Packet object
	 * and the queue is full. 
	 */
	public void enqueue(Packet p) throws FullQueueException {
		if((rear+1)%capacity == front)throw new FullQueueException();
		if(front == -1) {
			front = 0;
			rear = 0;
		}
		else {
			rear = (rear+1)%capacity;
		}
		size++;
		data[rear] = p;
	}
	/**
	 * This method removes the first Packet in the router buffer.
	 * @return answer
	 * Returns a Packet object name answer which holds the front object that
	 * have been dequeued.
	 * @throws EmptyQueueException
	 * throws EmptyQueueException to handle exception if the queue is empty
	 * and user tried to dequeue.
	 */
	public Packet dequeue() throws EmptyQueueException{
		Packet answer;
		if (front == -1) throw new EmptyQueueException();
		answer = data[front];
		if (front == rear) {
			front = -1;
			rear = -1;
		}
		else {
			front = (front+1)%capacity;
		}
		size--;
		return answer;
	}
	/**
	 * This method lets user peek into the front object of the queue
	 * without actually taking that Packet object out of the queue. 
	 * @return temp
	 * returns a Packet object temp without taking that object
	 * out of the queue.
	 */
	public Packet peek() {
		Packet temp = data[front];
		return temp;
	}
	/**
	 * This method returns the size of the queue.
	 * @return size
	 * size is an integer that represents the size of the queue.
	 */
	public int size() {
		return size;
	}
	/**
	 * This method returns if the queue is empty or not.
	 * @return boolean
	 * returns a boolean true or false depending if a queue is empty or not.
	 */
	public boolean isEmpty() {
		if(capacity == 0 || size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This method is a toString method for the Router object.
	 * @return s
	 * returns a string s containing a String representation of 
	 * the router buffer in the following format:
	 * {[packet1], [packet2], ... , [packetN]}
	 */
	public String toString() {
		String s = "{";
		for (int i=front; i<=rear; i++) {
			if (i+1 == size) {
				s = s + data[i].toString();
			}
			else {
				s = s + data[i].toString() + ",";
			}
		}
		s = s + "}";
		return s;
	}
	/**
	 * This method returns loop through the list Intermediate 
	 * routers. Find the router with the most free buffer space 
	 * (contains least Packets), and return the index of the 
	 * router.
	 * @param routers
	 * Takes an array of routers as an argument.
	 * @return rNumber
	 * Returns an index of router with the most free 
	 * buffer space.
	 */
	public int sendPacketTo(Router[] routers){	//static removed
		int rNumber = 1;
		int answer = routers[1].size();
		for(int i = 2; i<routers.length; i++) {
			if(routers[i].size()< answer) {
				answer = routers[i].size();
				rNumber = i;
				break;
				
			}
		}
		return rNumber;
	}
}
