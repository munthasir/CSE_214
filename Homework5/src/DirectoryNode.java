
public class DirectoryNode {
	private String name;
	private boolean isFile = false;
	private DirectoryNode pd = null; 		//Parent directory
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	/**
	 * Constructs and initializes the DirectoryNode object.
	 */
	public DirectoryNode() {
		name = null;
		isFile = false;
		left = null;
		middle = null;
		right = null;
	}
	/**
	 * Constructs and initializes the DirectoryNode object.
	 * @param name
	 * First argument is the name of the directory node with 
	 * no spaces, tabs, or any other whitespace.
	 * @param isFile
	 * Second argument is if the node is a file or not
	 * set it as true if its a file. If it is not then set
	 * it as false.
	 */
	public DirectoryNode(String name, boolean isFile, DirectoryNode pd) {
		this.name = name;
		this.isFile = isFile;
		this.pd = pd;
		left = null;
		middle = null;
		right = null;
	}
	/**
	 * This method sets the name of the node.
	 * @param name
	 * A string argument called name with 
	 * no spaces, tabs, or any other whitespace.
	 */
	public void setName(String name) {
		this.name = name;
	}
	public void setLeft(DirectoryNode left) {
		this.left = left;
	}
	public void setMiddle(DirectoryNode middle) {
		this.middle = middle;
	}
	public void setRight(DirectoryNode right) {
		this.right = right;
	}
	/**
	 * This method lets users access name of the node.
	 * @return name
	 * A string called name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method lets users know if the directory
	 * is a file or not.
	 * @return
	 * Returns true if the directory is a file
	 */
	public boolean getFile() {
		return isFile;
	}
	public DirectoryNode getPD() {
		return pd; 
	}
	/**
	 * This method lets users access left Child
	 * of a directory node.
	 * @return left
	 * A DirectoryNode object name left.
	 */
	public DirectoryNode getLeft() {
		return left;
	}
	/**
	 * This method lets users access middle Child
	 * of a directory node.
	 * @return middle
	 * A DirectoryNode object name middle.
	 */
	public DirectoryNode getMiddle() {
		return middle;
	}
	/**
	 * This method lets users access right Child
	 * of a directory node.
	 * @return right
	 * A DirectoryNode object name right.
	 */
	public DirectoryNode getRight() {
		return right;
	}
	/**
	 * This method adds newChild to any of the open child positions of this node.
	 * @param newChild
	 * A newChild argument object of DirectoryNode class.
	 * @throws FullDirectoryException
	 * If all the children nodes are filled then this exception is thrown.
	 * @throws NotADirectoryException
	 * If the node is a file this is not a directory and exception is thrown.
	 */
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
		if (isFile) throw new NotADirectoryException();
		if (this.left == null) {
			this.left = newChild;
		}
		else if (middle == null) {
			middle = newChild;
		}
		else if (right == null) {
			right = newChild;
		}
		else throw new FullDirectoryException();
	}
	public String printChildren(DirectoryNode node) {
		if (node.left != null && node.middle != null && node.right != null) {
			return "\n-" + node.left.getName() + "\n-" + node.middle.getName()+ "\n-" + node.right.getName();
		}
		else if (node.left == null && node.middle != null && node.right != null) {
			return "\n-" + node.middle.getName()+ "\n-" + node.right.getName();
		}
		else if (node.left != null && node.middle == null && node.right != null) {
			return "\n-" + node.left.getName() + "\n-" + node.right.getName();
		}
		else if (node.left != null && node.middle != null && node.right == null) {
			return "\n-" + node.left.getName() + "\n-" + node.middle.getName();
		}
		else {
			return null;
		}
	}
	public String toString() {
		if(isFile) {
			return "- " + getName();
		}
		else {
			return "|- " + getName();
		}
	}
	
}
