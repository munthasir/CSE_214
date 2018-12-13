
public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor;
	private String printDirectory = null;
	/**
	 * Constructs and initializes the DirectoryTree method
	 */
	public DirectoryTree() {
		root = null;
		cursor = null;
	}
	/**
	 * Constructs and initializes the DirectoryTree method
	 * @param root
	 * Takes a DirectoryNode as an argument.
	 */
	public DirectoryTree(DirectoryNode root) {
		this.root = root;
		this.cursor = root;
		printDirectory = root.getName();
	}
	/**
	 * This method resets the cursor to root.
	 */
	public void resetCursor() {
		cursor = root;
	}
	/**
	 * This method changes the directory to the directory specified 
	 * or prints that the directory is not found.
	 * @param name
	 * Takes a string method as an argument specifying the directory name.
	 * @throws NotADirectoryException
	 * This exception is thrown if the directory that was passed is 
	 * actually a file.
	 */
	public void changeDirectory(String name) throws NotADirectoryException {
		if (cursor.getLeft() != null && cursor.getLeft().getName().equals(name) && cursor.getLeft().getFile())
			throw new NotADirectoryException();
		else if (cursor.getMiddle() != null && cursor.getMiddle().getName().equals(name)
				&& cursor.getMiddle().getFile())
			throw new NotADirectoryException();
		else if (cursor.getRight() != null && cursor.getRight().getName().equals(name) && cursor.getRight().getFile())
			throw new NotADirectoryException();
		else if (cursor.getLeft() != null && name.equals(cursor.getLeft().getName())) {
			cursor = cursor.getLeft();
		} else if (cursor.getMiddle() != null && name.equals(cursor.getMiddle().getName())) {
			cursor = cursor.getMiddle();
		} else if (cursor.getRight() != null && name.equals(cursor.getRight().getName())) {
			cursor = cursor.getRight();
		} else {
			System.out.println("ERROR: No such directory named " + name + ".");
		}
	}
	/**
	 * This method returns a string consisting of the present working directory.
	 * @return printDirectory
	 * Returns a string named printDirectory consisting of of the present
	 * working directory.
	 */
	public String presentWorkingDirectory() {
		printDirectory = cursor.getName();
		DirectoryNode temp = cursor;
		while (temp.getPD() != null) {
			temp = temp.getPD();
			printDirectory = temp.getName() + "/" + printDirectory;
		}
		return printDirectory;
	}
	/**
	 * This method returns a string String containing a space-separated list 
	 * of names of all the child directories or files of the cursor.
	 */
	public String listDirectory() {
		if (cursor.getLeft() == null && cursor.getMiddle() == null && cursor.getRight() == null) {
			return "";
		} else if (cursor.getLeft() == null && cursor.getMiddle() != null && cursor.getRight() != null) {
			return cursor.getMiddle().getName() + " " + cursor.getRight().getName();
		} else if (cursor.getLeft() != null && cursor.getMiddle() == null && cursor.getRight() != null) {
			return cursor.getLeft().getName() + " " + cursor.getRight().getName();
		} else if (cursor.getLeft() != null && cursor.getMiddle() != null && cursor.getRight() == null) {
			return cursor.getLeft().getName() + " " + cursor.getMiddle().getName();
		} else if (cursor.getLeft() == null && cursor.getMiddle() == null && cursor.getRight() != null) {
			return cursor.getRight().getName();
		} else if (cursor.getLeft() != null && cursor.getMiddle() == null && cursor.getRight() == null) {
			return cursor.getLeft().getName();
		} else if (cursor.getLeft() == null && cursor.getMiddle() != null && cursor.getRight() == null) {
			return cursor.getMiddle().getName();
		} else {
			return cursor.getLeft().getName() + " " + cursor.getMiddle().getName() + " " + cursor.getRight().getName();
		}
	}
	/**
	 * This method prints a formatted nested list of names of all 
	 * the nodes in the directory tree, starting from the cursor.
	 */
	public void printDirectoryTree() {
		helper(root, "");
	}
	/**
	 * This is a helper recursive method for the 
	 * printDirectoryTree method.
	 * @param node
	 * First argument is a DirectoryNode object.
	 * @param tabs
	 * Second argument is a String consisting of tabs.
	 */
	public void helper(DirectoryNode node, String tabs) {
		if (node != null) {
			System.out.println(tabs + node.toString());
			if (node.getLeft() != null) {
				helper(node.getLeft(), tabs + "\t");
			}
			if (node.getMiddle() != null) {
				helper(node.getMiddle(), tabs + "\t");
			}
			if (node.getRight() != null) {
				helper(node.getRight(), tabs + "\t");
			}
		}
	}
	/**
	 * This method creates a directory with the indicated name and adds it to the children of the cursor node.
	 * @param name
	 * Takes a String argument called name which will specify the directory name.
	 * @throws IllegalArgumentException
	 * This exception will be thrown if the 'name' argument is invalid.
	 * @throws FullDirectoryException
	 * This exception is thrown if all child references of this directory are occupied.
	 * @throws NotADirectoryException
	 * This exception is thrown if string that was passed is a file name.
	 */
	public void makeDirectory(String name)
			throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
		if (cursor.getLeft() != null && cursor.getMiddle() != null && cursor.getRight() != null)
			throw new FullDirectoryException();
		if (name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException();

		DirectoryNode temp = new DirectoryNode(name, false, cursor);
		cursor.addChild(temp);

	}
	/**
	 * This method creates a file with the indicated name and adds it to the children of the cursor node.
	 * @param name
	 * Takes a String argument called name which will specify the file name.
	 * @throws IllegalArgumentException
	 * This exception will be thrown if the 'name' argument is invalid.
	 * @throws FullDirectoryException
	 * This exception is thrown if string that was passed is a file name.
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException {
		if (cursor.getLeft() != null && cursor.getMiddle() != null && cursor.getRight() != null)
			throw new FullDirectoryException();
		if (name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException();
		DirectoryNode temp = new DirectoryNode(name, true, cursor);
		if (cursor.getLeft() == null) {
			cursor.setLeft(temp);
		} else if (cursor.getMiddle() == null) {
			cursor.setMiddle(temp);
		} else if (cursor.getRight() == null) {
			cursor.setRight(temp);
		}
	}
	/**
	 * This method moves the cursor to its parent.
	 */
	public void moveToParent() {
		if(cursor.getPD() != null) {
			cursor = cursor.getPD();
		}
	}
	/**
	 * This method finds the directory or file passed down to it.
	 * @param str
	 */
	public void find(String str) {
		System.out.print("root");
		helper_one(root, str);
		System.out.println();
	}
	/**
	 * This is a helper method for find method.
	 * @param node
	 * @param s
	 */
	public void helper_one(DirectoryNode node, String s) {
		if(node.getName().equals("root")) {
			if (node.getLeft() != null) {
				helper_one(node.getLeft(), s);
			}
		}
		else if (node != null) {
			System.out.print("/" + node.getName());
			if (node.getLeft() != null) {
				helper_one(node.getLeft(), s);
			}
			if (node.getMiddle() != null) {
				helper_one(node.getMiddle(), s);
			}
			if (node.getRight() != null) {
				helper_one(node.getRight(), s);
			}
		}
	}

}
