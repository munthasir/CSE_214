/**
 * @author Munthasir Islam
 * Email: Munthasir.Islam@stonybrook.edu
 * ID: 111314942
 * TA: Reed Gantz
 */
package homework1;

import java.util.Scanner;

public class PlannerManager {
	public static Planner p = new Planner();
	public static Planner backUpP = new Planner();
	/**
	 * Main method creates a menu for users to choose from.
	 * @param args
	 */
	public static void main(String[] args) {
		String inp;

		System.out.println("(A) Add Course\r\n" + "(G) Get Course\r\n" + "(R) Remove Course\r\n"
				+ "(P) Print Courses in Planner\r\n" + "(F) Filter by Department Code\r\n" + "(L) Look For Course\r\n"
				+ "(S) Size\r\n" + "(B) Backup\r\n" + "(PB) Print Courses in Backup\r\n" + "(RB) Revert to Backup\r\n"
				+ "(Q) Quit");
		System.out.println("Enter a selection: ");
		Scanner input = new Scanner(System.in);
		inp = input.nextLine();
		menu(inp);
	}
	/**
	 * This method selects menu depending on user's input.
	 * @param inp
	 */
	public static void menu(String inp) {
		String decision;
		
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		switch (inp) {
		case "A":
			String cn, dep, instr;
			int cd, pos;
			byte sec;
			System.out.println("Enter course name: ");
			cn = input.nextLine(); 
			System.out.println("Enter department: ");
			dep = input.nextLine();
			System.out.println("Enter course code: ");
			cd = input.nextInt();
			System.out.println("Enter course section: ");
			sec = input.nextByte();
			System.out.println("Enter instructor: ");
			instr = input1.nextLine();

			System.out.println("Enter position: ");
			pos = input.nextInt();

			Course c = new Course(cn, dep, instr, cd, sec);
			try {
				p.addCourse(c, pos);
				System.out.println(dep + cd + "." + sec + " successfully added to planner.");

			} catch (FullPlannerExpception | IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
			main(null);
		case "G":
			Course c1;
			int num;
			p.printAllCourses();
			System.out.println("Which No. course information you want to display?: ");
			num = input.nextInt();
			c1 = p.getCourse(num);
			System.out.println(String.format("%s %5s %30s %5s %5s %5s", "No.", "Course Name", "Department", "Code",
					"Section", "Instructor"));
			System.out.println(String.format("%s","-------------------------------------------------------------------------------"));
			System.out.println(String.format("%s %5s %30s %5s %5s %5s", num , c1.getCourseName(), c1.getDepartment(),
					c1.getCode(), c1.getSection(), c1.getInstructor()));
			main(null);
		case "R":
			int no;
			p.printAllCourses();
			System.out.println("Which course No. do you want to remove?: ");
			no = input.nextInt();
			p.removeCourse(no);
			System.out.println(no + " course is successfully removed! New list is: ");
			p.printAllCourses();
			main(null);
		case "P":
			p.printAllCourses();
			main(null);
		case "F":
			System.out.println("Enter department code: ");
			dep = input.nextLine();
			Planner.filter(p, dep);
			main(null);
		case "L":
			Course c2;
			System.out.println("Enter course name: ");
			cn = input.nextLine();
			System.out.println("Enter department: ");
			dep = input.nextLine();
			System.out.println("Enter course code: ");
			cd = input.nextInt();
			System.out.println("Enter course section: ");
			sec = input.nextByte();
			System.out.println("Enter instructor: ");
			instr = input1.nextLine();
			c2 = new Course(cn, dep, instr, cd, sec);
			if (p.exists(c2)) {
				System.out.println(dep + cd + "." + sec + " exists!");
			} else {
				System.out.println(dep + cd + "." + sec + " doesnt exists!");
			}
			main(null);
		case "S":
			System.out.println("There are " + p.size() + " course in the list");
			main(null);
		case "B":
			backUpP = (Planner) p.clone();
			System.out.println("A backup copy of the current Planner have been created!");
			main(null);
		case "PB":
			backUpP.printAllCourses();
			main(null);
		case "RB":
			p = (Planner) backUpP.clone();
			System.out.println("The current Planner have been reverted to the backed up copy!");
			main(null);
		case "Q":
			System.out.print("*****Program ends!*****");
			System.exit(0);
		}
	}

}
