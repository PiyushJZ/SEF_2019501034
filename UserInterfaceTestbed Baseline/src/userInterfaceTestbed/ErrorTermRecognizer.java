package userInterfaceTestbed;


public class ErrorTermRecognizer {
	/**
	 * <p> Title: FSM-translated ErrorTermRecognizer. </p>
	 * 
	 * <p> Description: A demonstration of the mechanical translation of Finite State Machine 
	 * diagram into an executable Java program using the Error Term Recognizer. The code detailed
	 * design is based on a while loop with a select list</p>
	 * 
	 * <p> Copyright: Lynn Robert Carter © 2018 </p>
	 * 
	 * @author Lynn Robert Carter
	 * 
	 * @version 0.00	2018-01-28	Initial baseline 
	 * 
	 */
	
	/**********************************************************************************************
	 * 
	 * Result attributes to be used for GUI applications where the returned string error message
	 * and pointer to the character of the error are not adequate for the required output.
	 * 
	 */
	
	public static String errorTermErrorMessage = "Error Term recognition has not been implements";
	public static String errorTermInput = "";			// The input being processed
	public static int errorTermIndexofError = -1;		// The index where the error was located
		
	/**********
	 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
	 * method.
	 * 
	 * @param input		The input string for the Finite State Machine
	 * @return			An output string that is empty if every things is okay or it will be
	 * 						a string with a help description of the error follow by two lines
	 * 						that shows the input line follow by a line with an up arrow at the
	 *						point where the error was found.
	 */
	public static String checkErrorTerm(String input) {
		// This is a sub for an Error Term recognizer
		return errorTermErrorMessage;
	}
	
}
