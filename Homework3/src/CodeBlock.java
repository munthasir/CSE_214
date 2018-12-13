
public class CodeBlock {
	public static final String[] BLOCK_TYPES = {" def "," for ", " while ", " if ", " else ", " elif "};
	public static final int DEF = 0 , FOR = 1, WHILE = 2, IF = 3, ELIF = 4, ELSE = 5;
	private String name, loopVariable;
	private Complexity blockComplexity, highestSubComplexity; 
	/**
	 * Constructs and initializes the CodeBlock object.
	 */
	public CodeBlock() {
		name = "";
		blockComplexity = new Complexity();
		highestSubComplexity = new Complexity();
		loopVariable = "";
	}
	//accessor
	/**
	 * This method lets user access a String name.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method lets user access a String loopVariable.
	 * @return loopVariable
	 */
	public String getLoopVariable() {
		return loopVariable; 
	}
	/**
	 * This method lets user access the object blockComplexity.
	 * @return blockComplexity
	 */
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}
	/**
	 * This method lets user access the object highestSubComplexity.
	 * @return highestSubComplexity
	 */
	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}
	//mutator
	/**
	 * This method sets the String variable name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This method sets the String variable loopVariable.
	 * @param loopVariable
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
	/**
	 * This method sets the Complexity object blockComplexity.
	 * @param blockComplexity
	 */
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}
	/**
	 * This method sets the Complexity object highestSubComplexity.
	 * @param highestSubComplexity
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}
	
}
