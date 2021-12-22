package comprehensive;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * This CommandObject class, create objects that contains a boolean variable
 * which represents the type of data, and two string variable which holds the 
 * value of string or object.
 * @author Zhu, Hongxuan and Cobi Toeun
 */
public class CommandHashTable {
	private Hashtable<String, CommandObject> table = new Hashtable<String, CommandObject>();
	private CommandObject tempObject = null;
	
	/**
	 * Get the randomly selected command by the name given
	 * @param name : name of the command
	 * @return The choosed stack(now is arraylist)
	 */
	public ArrayList<String[]> getCommand(String name) {
		CommandObject commandBluePrint = this.table.get(name);
		ArrayList<String[]> randomStack = commandBluePrint.chooseStack();
		return randomStack;
	}
	
	/**
	 * Initialize the tempObject, prepare to receive information
	 */
	public void addNewCommand() {
		this.tempObject = new CommandObject();
	}
	
	/**
	 * Set the name of command into the stack
	 * @param name : the name of command
	 */
	public void setCommand(String name) {
		if (this.tempObject.getName() == null) {
			this.tempObject.setName(name);
		}else {
			this.tempObject.inStack(name, "o");
		}
	}
	
	/**
	 * Set the content of String into the stack
	 * @param name : the String content
	 */
	public void setString(String element) {
		this.tempObject.inStack(element, "s");
	}
	
	/**
	 * Close the input command, put the command into the outer Stack
	 * Check if null is designed to handle the header or first line cases
	 */
	public void finishCommand() {
		if (this.tempObject == null) {
			return;
		}
		if (!this.tempObject.commandEmpty()) {
			tempObject.closeStack();
		}
	}
	
	/**
	 * Close the entire command, put the entire stack into the outer arrayList
	 * Clear the tempObject
	 */
	public void closeCommands() {
		this.table.put(tempObject.getName(), tempObject);
		this.tempObject = null;
	}
	
	/**
	 * @return If the command is open to write
	 */
	public boolean isCommandOpen() {
		return this.tempObject != null;
	}

}
