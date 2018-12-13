/**
 * @author Munthasir Islam
 * Email: Munthasir.Islam@stonybrook.edu
 * ID: 111314942
 * TA: Reed Gantz
 */
package homework1;

public class Planner {
	private int maxCourses = 50;
	public Course[] list;
	
	//Constructor
	/**
	 * Constructs and initializes the Planner object.
	 */
	public Planner() {
		list = new Course[maxCourses];
	}
	/**
	 * This method returns how many Course object is in the array.
	 * @return s
	 */
	public int size() {
		int s = 0; // Counting the size of the array.
		for (int i = 1; i < list.length; i++) {
			if (list[i] == null) {
				continue;
			} else {
				s++;
			}
		}
		return s;
	}
	/**
	 * This method adds a new course in the list with given position in the list.
	 * @param newCourse
	 * @param position
	 * @throws FullPlannerExpception
	 * @throws IllegalArgumentException
	 */
	public void addCourse(Course newCourse, int position)throws FullPlannerExpception, IllegalArgumentException {

		if (this.size() == 50) {
			throw new FullPlannerExpception("Planner is full!");
		} else {
			list[position] = newCourse;
		}

		if (position < 1 || position > 50) {
			throw new IllegalArgumentException("Position is not within the valid range!");
		} else {
			list[position] = newCourse;
		}
	}
	/**
	 * This method just adds a course at the earliest empty position in the list.
	 * @param newCourse
	 * @throws FullPlannerExpception
	 */
	public void addCourse(Course newCourse)throws FullPlannerExpception {
		int emptySpot = 0;
		for (int i = 1; i < list.length; i++) {
			if (list[i] == null) {
				emptySpot = i;
				break;
			} else {
				continue;
			}
		}
		if (size() == 50) {
			throw new FullPlannerExpception("Planner is full!");
		} else {
			list[emptySpot] = newCourse;
		}

	}
	/**
	 * This method removes a course from the list from a given position.
	 * @param position
	 * @throws IllegalArgumentException
	 */
	public void removeCourse(int position)throws IllegalArgumentException{
		if (position < 1 || position > 50) {
			throw new IllegalArgumentException("Position is not within the valid range!");
		} else {
			for(int i = 1; i < list.length; i++){
	            if(i == position){
	                // shifting elements
	                for(int j = i; j < list.length - 1; j++){
	                    list[j] = list[j+1];
	                }
	            }
	            if (i == list.length-1) {
	            	list[i] = null;
	            }
	        }
		}
	}
	/**
	 * This method returns a Course object with a given position.
	 * @param position
	 * @return list[position]
	 * @throws IllegalArgumentException
	 */
	public Course getCourse(int position)throws IllegalArgumentException {
		if (position < 1 || position > 50) {
			throw new IllegalArgumentException("Position is not within the valid range!");
		}
		return this.list[position];
	}
	/**
	 * This method prints a list of Course object using department as a filter.
	 * @param planner
	 * @param department
	 */
	public static void filter(Planner planner, String department) {
		System.out.println(String.format("%s %-25s %-10s %10s %10s %-20s", "No.", "Course Name", "Department", "Code", "Section", "Instructor"));
		System.out.println(String.format("%s","-------------------------------------------------------------------------------"));
		for(int i=1; i<=planner.size(); i++) {
			if (planner.list[i].getDepartment().equals(department)) {
				System.out.println(String.format("%s %-25s %-10s %10s %10s %-20s", i , planner.list[i].getCourseName() , 
						planner.list[i].getDepartment() , planner.list[i].getCode() , planner.list[i].getSection(), 
						planner.list[i].getInstructor()));
			}
		}
	} 
	/**
	 * This method returns true or false if a Course object exists in the Planner list.
	 * @param course
	 * @return true or false
	 */
	public boolean exists(Course course) {
		for (int i = 1; i<=size(); i++) {
			if(list[i].getCourseName().equals(course.getCourseName()) && list[i].getDepartment().equals(course.getDepartment()) &&
					list[i].getCode() == course.getCode() && list[i].getSection() == course.getSection() &&
					list[i].getInstructor().equals(course.getInstructor())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This method creates a clone of a Planner.
	 * 
	 * @return
	 * A clone of the Planner
	 */
	public Object clone() {	
		Planner p1 = new Planner();
		for (int i = 1; i<=size(); i++) {
			try {
				p1.addCourse(this.list[i]);
			} catch (FullPlannerExpception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		p1.maxCourses = this.maxCourses;
		return p1;
	}
	/**
	 * This method prints the entire list of Courses and all its information in a table.
	 */
	public void printAllCourses() {
		System.out.println("Planner:");
		System.out.println(String.format("%s %5s %30s %5s %5s %5s", "No.", "Course Name", "Department", "Code", "Section", "Instructor"));
		System.out.println(String.format("%s","-------------------------------------------------------------------------------"));
		for(int i=1; i<list.length; i++) {
			if (this.list[i] == null) {
				continue;
			}
			else {
				System.out.println(String.format("%s %5s %30s %5s %5s %5s", i , this.list[i].getCourseName() , 
						this.list[i].getDepartment() , this.list[i].getCode() , this.list[i].getSection() , 
						this.list[i].getInstructor()));
			}
		}
	}
	
	/**
	 * This method returns a string of information on list of Planners.
	 * 
	 * @return
	 * A string with list of Planner in the list.
	 */
	public String toString() {
		String ret = null;
		for(int i=1; i<list.length; i++) {
			ret = list[i] + " ";
		}
		return ret;
	}
}
