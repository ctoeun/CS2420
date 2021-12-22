package comprehensive;

import java.util.ArrayList;
import java.util.Random;

/**
 * This CommandObject class, create objects that contains a boolean variable
 * which represents the type of data, and two string variable which holds the 
 * value of string or object.
 * @author Zhu, Hongxuan and Cobi Toeun
 */
public class CommandObject {
	private Random rdm = new Random();
	private ArrayList<ArrayList<String[]>> commands = new ArrayList<ArrayList<String[]>>();
	private ArrayList<String[]> commandStack = new ArrayList<String[]>();
	private String commandName = null;
	
	/**
	 * Set name of the command
	 * @param name : The name
	 */
	public void setName(String name){
		this.commandName = name;
	}
	
	/**
	 * Get name of the command
	 * @return The name
	 */
	public String getName() {
		return this.commandName;
	}
	
	/**
	 * Put a new element into the Stack (note: the stack has been replaced by 
	 * arraylist for higher performance, however, it's still easier to understand 
	 * in the logic of stack, that's the reason I leave the stack name here.)
	 * @param element
	 * @param isString
	 */
	public void inStack(String element, String type) {
		String[] temp = new String[2];
		temp[0] = element;
		temp[1] = type;
		this.commandStack.add(temp);
	}
	
	/**
	 * Close and put the commandStack object into the CommandArrayList
	 */
	public void closeStack() {
		this.commands.add(commandStack);
		this.commandStack = new ArrayList<String[]>();
	}
	
	/**
	 * Randomly select from the CommandArrayList
	 * @return The randomly selected ArrayList
	 */
	public ArrayList<String[]> chooseStack() {
		int index = rdm.nextInt(this.commands.size());
		ArrayList<String[]> choicedStack = this.commands.get(index);
		return choicedStack;
	}
	
	/**
	 * @return True if the commandStack is empty, False if not
	 */
	public boolean commandEmpty() {
		return this.commandStack.isEmpty();
	}

}
