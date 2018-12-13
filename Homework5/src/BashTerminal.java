import java.util.Scanner;

public class BashTerminal {

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		String inp;
		DirectoryNode dn = new DirectoryNode("root", false, null);
		DirectoryTree dt = new DirectoryTree(dn);
		while (true) {
			System.out.print("[user@host]: $ ");
			Scanner input = new Scanner(System.in);
			inp = input.nextLine();
			if ("pwd".equals(inp)) {
				System.out.println(dt.presentWorkingDirectory());
			}
			else if ("ls".equals(inp)) {
				System.out.println(dt.listDirectory());
			}
			else if ("ls -R".equals(inp)) {
				dt.printDirectoryTree();
			}
			else if ("cd /".equals(inp)) {
				dt.resetCursor();
			}
			//Extra Credit
			else if("cd ..".equals(inp)) {
				dt.moveToParent();
			}
			else if (inp.contains("cd")) {
				String str;
				String[] arrOfStr = inp.split(" ");
				str = arrOfStr[1];
				try {
					dt.changeDirectory(str);
				} catch (NotADirectoryException e) {
					System.out.println("ERROR: Cannot change directory into a file.");
				}
			}
			else if (inp.contains("mkdir")) {
				String str;
				String[] arrOfStr = inp.split(" ");
				str = arrOfStr[1];
				try {
					dt.makeDirectory(str);
				} catch (FullDirectoryException e) {
					System.out.println("ERROR: Present directory is full.");
				} catch (NotADirectoryException e) {
					System.out.println("ERROR: Cannot change directory into a file.");
				} catch (IllegalArgumentException e) {
					System.out.println("ERROR: Invalid name!");
				}
				
			}
			else if (inp.contains("touch")) {
				String str;
				String[] arrOfStr = inp.split(" ");
				str = arrOfStr[1];
				try {
					dt.makeFile(str);
				} catch (FullDirectoryException e) {
					System.out.println("ERROR: Present directory is full.");
				} catch (IllegalArgumentException e) {
					System.out.println("ERROR: Invalid name!");
				}
			}
			//Extra Credit
			else if (inp.contains("find")) {
				String str;
				String[] arrOfStr = inp.split(" ");
				str = arrOfStr[1];
				dt.find(str);
			}
			else if ("exit".equals(inp)) {
				System.exit(0);
			}
			else {
				System.out.println("Invalid input!");
			}
		}
		
	}	

}
