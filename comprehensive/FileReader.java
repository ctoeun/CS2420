package comprehensive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * This class reads file and generates the CommandHashTable object
 * @author Zhu, Hongxuan and Cobi Toeun
 */
public class FileReader {
	private String fileName = null;
	private CommandHashTable elementsTable = new CommandHashTable();
	private Stack<Character> commandStack = new Stack<Character>();
	private boolean methodOpen = false;
	
	/**
	 * Get the file name and drives all following methods
	 * @param name The .g file name
	 * @throws IOException
	 */
	public FileReader(String name) throws IOException {
		this.fileName = name;
		this.analyseFile();
	}

	/**
	 * Read the content in the file
	 * @return A string represents the content in the file
	 * @throws IOException
	 */
	private String getFileContent() throws IOException{
	    String content = new String(Files.readAllBytes(Paths.get(fileName)));
	    return content;
	}
	
	/**
	 * Analyse the string which contains the entire file content
	 * The code logic is:<br/><br/>
	 * 
	 * Read one character from string in each loop<br/>
	 * If the character is:<br/><br/>
	 * '{': Start the method, push '{' into the stack. Other keyword will not be considered before '{' is in the stack. <br/>
	 * '<': Outstack all characters in the stack until meet the '{' keyword, push itself into stack.<br/>
	 * '>': Outstack all characters in the stack until meet the '<' keyword, then outstack one more time to remove the '<'<br/>
	 * '/n': Outstack all characters in the stack until meet the '{' keyword.<br/>
	 * '}': Close the method, before see the next '{', all keyword will not be considered.<br/>
	 * '(all_other_characters)': push the character into the stack.<br/>
	 * 
	 * @throws IOException
	 */
	private void analyseFile() throws IOException {
		String allContent = this.getFileContent();
		for (int i = 0; i < allContent.length(); i++) {
			char curChar = allContent.charAt(i);
			if (curChar == '{') {
				this.commandStack.clear();
				elementsTable.addNewCommand();
				this.commandStack.push(curChar);
				this.methodOpen = true;
			}else if(this.methodOpen) {
				if(curChar == '<') {
					String element = this.gatherString('{');
					this.commandStack.push('{');
					if (!element.equals("")) {
						elementsTable.setString(element);
					}
					this.commandStack.push(curChar);
				}else if(curChar == '>') {
					String name = this.gatherString('<');
					elementsTable.setCommand(name);
				}else if(curChar == '\n') {
					String element = this.gatherString('{');
					this.commandStack.push('{');
					if (this.elementsTable.isCommandOpen() && !element.equals("")) {
						elementsTable.setString(element);
					}
					elementsTable.finishCommand();
				}else if(curChar == '}') {
					elementsTable.closeCommands();
					this.methodOpen = false;
				}else {
					this.commandStack.push(curChar);
				}
			}
		}
	}
	/**
	 * Helper method, pop characters out from stack until meet the terminal
	 * @param terminal : The stop point of pop out operation
	 * @return A string contains all values between the stack-top and the terminal
	 */
	private String gatherString(char terminal) {
		String element = "";
		Stack<Character> tempStack = new Stack<Character>();
		while (!this.commandStack.isEmpty()) {
			if(this.commandStack.peek() != terminal) {
				tempStack.push(this.commandStack.pop());
			}else{
				this.commandStack.pop();
				break;
			}
		}
		while(!tempStack.isEmpty()) {
			element += tempStack.pop();
		}
		return element;
	}
	
	/**
	 * Get the generated table 
	 * @return A table that contains all commands object.
	 */
	public CommandHashTable getTable() {
		return this.elementsTable;
	}
}
