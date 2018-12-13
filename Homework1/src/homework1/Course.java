/**
 * @author Munthasir Islam
 * Email: Munthasir.Islam@stonybrook.edu
 * ID: 111314942
 * TA: Reed Gantz
 */
package homework1;

//import java.util.InputMismatchException;

public class Course {
	private String courseName, department, instructor;
	private int code;
	private byte section;
	
	//Constructor
	/**
	 * Constructs and initializes the Course object.
	 * @param courseName
	 * @param department
	 * @param instructor
	 * @param code
	 * @param section
	 */
	public Course(String courseName, String department, String instructor, int code, byte section) {
		this.courseName = courseName;
		this.department = department;
		this.instructor = instructor;
		this.code = code;
		this.section = section;
	}
	
	//mutator
	/**
	 * Sets the course name.
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * Sets the department name.
	 * @param department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * Sets the instructor name.
	 * @param instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	/**
	 * Sets the course code.
	 * @param code
	 */
	public void setCode(int code) {
		int tryAgain = 1; // 1 means try again 0 means don't try anymore.
		do {
			try {
				this.code = code;
				if (code<0) {
					throw new Exception();
				}
				tryAgain = 0;
			} catch (Exception e) {
				System.out.println("Please only enter integers");
			}
		} while(tryAgain == 1);
		
	}
	/**
	 * Sets the Course section.
	 * @param section
	 */
	public void setSection(byte section) {
		this.section = section;
	}
	
	//Accessor
	/**
	 * This method returns the name of the course.
	 * @return courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * This method returns the name of the department.
	 * @return department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * This method returns the name of the instructor.
	 * @return instructor
	 */
	public String getInstructor() {
		return instructor;
	}
	/**
	 * This method returns the name of the course code.
	 * @return code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * This method returns the name of the course section.
	 * @return section
	 */
	public byte getSection() {
		return section;
	}
	
	//Clone
	/**
	 * This method creates a clone of a Course.
	 * 
	 * @return
	 * A clone of the Course
	 */
	public Object clone() {
		Course c = new Course(this.courseName, this.department, this.instructor, this.code, this.section);
		return c;
	}
	
	//Equals
	/**
	 * Returns true if two Course objects are equal else returns false.
	 * @param obj
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			return (this.courseName == ((Course)obj).getCourseName() && this.department == ((Course)obj).getDepartment()
					&& this.instructor == ((Course)obj).getInstructor() && this.code == ((Course)obj).getCode()
					&& this.section == ((Course)obj).getSection()); 
				//return true;
			
		}
		return false;
	}


}

