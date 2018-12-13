/**
 * @author Munthasir Islam 
 * ID: 111314942
 * Recitation: 10
 * Recitation TA: Reed Gantz
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Simulator {
	public static final int MAX_PACKETS = 3;
	public static Router dispatcher = new Router(MAX_PACKETS);
	public static int totalServiceTime, totalPacketsArrived, packetsDropped;
	public static double averageServiceTime;
	public static double arrivalProb;
	public static int numIntRouters, maxBufferSize, minPacketSize, maxPacketSize, bandwidth, duration;
	
	
	public static void main(String[] args) {
		String c;
		boolean dec = true;
		Scanner input = new Scanner(System.in);
		
		while(dec) {
			System.out.println("Starting simulator...");
			System.out.print("Enter the number of Intermediate routers: ");
			int a = input.nextInt();
			System.out.print("\nEnter the arrival probability of a packet: ");
			double b = input.nextDouble();
			input.nextLine();
			System.out.print("\nEnter the maximum buffer size of a router: ");
			int cc = input.nextInt();
			System.out.print("\nEnter the minimum size of a packet: ");
			int d = input.nextInt();
			System.out.print("\nEnter the maximum size of a packet: ");
			int ee  = input.nextInt();
			System.out.print("\nEnter the bandwidth size: ");
			int f = input.nextInt();
			System.out.print("\nEnter the simulation duration: ");
			int aa = input.nextInt();
			simulate(a,b,cc,d,ee,f,aa);
			System.out.print("\nSimulation ending...");
			System.out.print("\nTotal service time: " + totalServiceTime);
			System.out.print("\nTotal packets served: " + totalPacketsArrived);
			averageServiceTime = totalServiceTime/totalPacketsArrived;
			System.out.print("\nAverage service time per packet: " + averageServiceTime);
			System.out.print("\nTotal packets dropped: " + packetsDropped);
			
			boolean dec1 = true;
			while(dec1 == true) {
				System.out.print("\n\nDo you want to try another simulation? (y/n): ");
				c = input.nextLine();
				if("y".equals(c) || "n".equals(c)) {
					if("n".equals(c)) {
						System.out.print("\nProgram terminating successfully...");
						dec = false;
					}
					dec1 = false;
				}
				else {
					System.out.print("\nPlease enter y or n without any spaces!");
				}
			}
		}
		
	}
	/**
	 * This is the simulator method that calculate and return the average time 
	 * each packet spends within the network.
	 * *** A few notes ***
	 * A list is used as the priority queue if more packet arrives at the destination
	 * router than the bandwidth. When a packet is enqueued into a router that router
	 * number is stored in the list if its required later on. If the amount of packets
	 * that that are ready to be sent to destination router is less than the bandwidth 
	 * then those routers numbers are removed from the list and also dequeued. If it 
	 * is more then from the priority queue which is a list for us program will find the
	 * first router number that is ready to be sent to destination and then that number 
	 * is removed from the list and dequeued.
	 */
	public static double simulate(int a, double b, int cc, int d, int ee, int f, int aa) {
		numIntRouters = a;
		arrivalProb = b;
		maxBufferSize = cc; 
		minPacketSize = d; 
		maxPacketSize = ee; 
		bandwidth = f; 
		duration = aa;
		List<Integer> destinationRouterQueue = new ArrayList<Integer>(); 
		int track = 0;
		
		Router[] routers = new Router[numIntRouters+1];
		for(int k=1; k<routers.length; k++) {
			routers[k] = new Router(maxBufferSize);
		}
		
		for (int i=1; i<=duration; i++) {			//Time 
			System.out.println("Time: " + i);
			for (int j=0; j<MAX_PACKETS; j++) {		//Dispatcher router
				if (Math.random() <arrivalProb) {
					int packetSize = randInt(minPacketSize, maxPacketSize);
					Packet p = new Packet(packetSize ,i);
					p.setTimeArrive(i);
					System.out.println("Packet " + p.getId() + " arrives at dispatcher with size " + packetSize);
					try {
						dispatcher.enqueue(p);
						
							int routerNumber = dispatcher.sendPacketTo(routers);
							track = routerNumber;
							routers[routerNumber].enqueue(p);			//Routers enqueue
							destinationRouterQueue.add(routerNumber);	//Priority queue list
							try {
								dispatcher.dequeue();					//Remove form dispatcher 
							} catch (EmptyQueueException e) {
								System.out.println("You tried to dequeue empty dispatcher queue!");
							}
							System.out.println("Packet " + p.getId() + " sent to Router " + routerNumber);
							
						
						
					} catch (FullQueueException e) {
						System.out.println("Dispatcher is full!");
					}
					
				}
			}
			int count = 0;
			for(int j=1; j<routers.length; j++) {
				if(routers[j].size() == 0) {
					continue;
				}
				if(routers[j].peek().getTimeToDest() == 0) {
					count++;
				}
			}
			if (count <= bandwidth) {
				for (int j=1; j<routers.length; j++) {
					if(routers[j].size() == 0) {
						continue;
					}
					if(routers[j].peek().getTimeToDest() == 0) {
						for(int k=0; k<destinationRouterQueue.size(); k++) {
							if(j == (int)destinationRouterQueue.get(k)) {
								destinationRouterQueue.remove(k);
								break;
							}
						}
						try {
							int time = i-routers[j].peek().getTimeArrive();
							totalServiceTime = totalServiceTime + time;
							System.out.println("Packet "+routers[j].peek().getId()+" has successfully reached its destination: +"+ time);
							routers[j].dequeue();
							totalPacketsArrived++;
							
						} catch (EmptyQueueException e) {
							System.out.println("You tried to dequeue empty intermediate router queue!");
						}
					}
				}
			}
			if (count > bandwidth) {
				for(int j=1; j<=bandwidth; j++) {
					for(int k=0; k<destinationRouterQueue.size(); k++) {
						int l = (int)destinationRouterQueue.get(k);
						if(routers[l].peek().getTimeToDest() == 0) {
							destinationRouterQueue.remove(k);
							try {
								int time  = i-routers[j].peek().getTimeArrive();
								totalServiceTime = totalServiceTime + time;
								System.out.println("Packet "+routers[j].peek().getId()+" has successfully reached its destination: +"+ time);
								routers[j].dequeue();
								totalPacketsArrived++;
								
							} catch (EmptyQueueException e) {
								System.out.println("You tried to dequeue empty intermediate router queue!");
							}
							break;
						}
					}
					
				}
			}
			
			for (int j=1; j<routers.length; j++) {
				if(routers[j].size() == 0) {
					continue;
				}
				System.out.println("R"+ j + ": " + routers[j].toString());
			}
			for (int j=1; j<routers.length; j++) {
				if(routers[j].size() == 0) {
					continue;
				}
				if (routers[j].peek().getTimeToDest() > 0) {
					routers[j].peek().setTimeToDest(routers[j].peek().getTimeToDest()-1);
				}		
			}
		}
		
		return totalServiceTime;
	}
	public static int randInt(int minVal, int maxVal) {
		if (minVal > maxVal) throw new IllegalArgumentException("minVal is greater than maxVal!");
		Random r = new Random();
		return r.nextInt((maxVal - minVal) + 1) + minVal;
	}
	

}
