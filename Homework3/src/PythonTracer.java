import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class PythonTracer {
	public static final int SPACE_COUNT = 4;

	public static Complexity traceFile(String filename) {
		Stack<CodeBlock> s = new Stack();
		
		int count = 0, indents;
		CodeBlock oldTop, oldTopComplexity = new CodeBlock();
		CodeBlock k = new CodeBlock();
		
		
		try {
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader inStream = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(inStream);
			String line = null;
			while((line = reader.readLine()) != null) {
				if(!line.isEmpty() && line.charAt(0) != '#') {
					for(int i=0; i<line.length(); i++) {
						if(line.charAt(i) == ' ') {
							count++;
						}
					}
					indents = count/SPACE_COUNT;
					while(indents<s.size()) {
						if(indents == 0) {
							fis.close();
							return s.pop().getBlockComplexity();
						}
						else {
							oldTop = s.pop();
							oldTopComplexity.setBlockComplexity(oldTop.getBlockComplexity());
							Complexity c = new Complexity();
							
							if (oldTopComplexity.getBlockComplexity().getLogPower()>s.peek().getBlockComplexity().getLogPower() 
									&& oldTopComplexity.getBlockComplexity().getNpower()> s.peek().getBlockComplexity().getNpower()) {
								CodeBlock temp = s.pop();
								temp.setHighestSubComplexity(oldTopComplexity.getBlockComplexity());
								s.push(temp);
							}
						}
					}
					String keyword = null;
					for(int i=0; i<k.BLOCK_TYPES.length; i++) {
						if(line.contains(k.BLOCK_TYPES[i])) {
							keyword = k.BLOCK_TYPES[i];
						}
					}
					if (line.contains(keyword)) {
						if(" for ".equals(keyword)) {

							CodeBlock temp = new CodeBlock();
							Complexity temp1 = new Complexity();
							if(line.contains("N")) {
								temp1.setNpower(1);
								temp.setBlockComplexity(temp1);
								s.push(temp);
							}
							else if(line.contains("Log_N")) {
								temp1.setNpower(1);
								temp1.setLogPower(1);
								temp.setBlockComplexity(temp1);
								s.push(temp);
							}
						}
						else if(" while ".equals(keyword)){
							CodeBlock temp = new CodeBlock();
							Complexity c = new Complexity();
							c.setNpower(1);
							temp.setBlockComplexity(c);
							String[] s1 = line.split(" ");
							temp.setLoopVariable(s1[1]);
							s.push(temp);
						}
						else {
							CodeBlock cb = new CodeBlock();
							Complexity c = new Complexity();
							cb.setBlockComplexity(c);
							s.push(cb);
						}
					}
					else if("while".equals(s.peek().getName())) {
						if(line.contains(s.peek().getLoopVariable())) {
							
							if(line.contains("*") || line.contains("/")) {
								String str = s.peek().getLoopVariable();
								s.pop();
								CodeBlock temp = new CodeBlock();
								Complexity c = new Complexity();
								c.setLogPower(1);
								c.setNpower(1);
								temp.setBlockComplexity(c);
								temp.setLoopVariable(str);
								s.push(temp);
							}
						}
					}
				}
				else {
					continue;
				}
			}
		while(s.size()>1) {
			oldTop = s.pop();
			oldTopComplexity.setBlockComplexity(oldTop.getBlockComplexity());
			if (oldTopComplexity.getBlockComplexity().getLogPower()>s.peek().getBlockComplexity().getLogPower() 
					&& oldTopComplexity.getBlockComplexity().getNpower()> s.peek().getBlockComplexity().getNpower()) {
				CodeBlock temp = s.pop();
				temp.setHighestSubComplexity(oldTopComplexity.getBlockComplexity());
				s.push(temp);
			}
		}
		//return s.pop().getBlockComplexity();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return s.pop().getBlockComplexity();
	}

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a file name (or 'quit' to quit): ");
		String str = input.nextLine();
		Complexity c = traceFile(str);
		System.out.println("\nOverall complexity of " + str + ":" + c.toString());
		
	}
}
