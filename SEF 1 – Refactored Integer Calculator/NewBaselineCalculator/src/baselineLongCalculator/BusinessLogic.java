package baselineLongCalculator;

public class BusinessLogic {
	public UserInterface ui;
	BusinessLogic(UserInterface ui) {
		this.ui = ui;
	}
	/*******************************************************************************************************
	 * This portion of the class is the business logic that defines the computation that takes place when
	 * the user modifies either of the two text fields of the user interface
	 */
	
	/**********
	 * This routine checks the first operand and returns it.  If the value is invalid, a value of zero 
	 * is returned, but more importantly, a red error message is displayed next to the text field.
	 * If the value is valid, it resets the error field and returns the actual value.
	 */
	public void convertOperand1() {
		ui.operand1Defined = false;									// Assume the first operand is
		ui.label_Result.setText("Result");								// not valid, reset the label,
		ui.text_Result.setText("");									// and clear the result
		// Fetch the string the user entered into the operand's text field
		String str = ui.text_Operand1.getText();						// Get the text from the input field
		if (str.length() == 0) {
			ui.label_errOperand1.setText("");							// reset the error message
			ui.text_Result.setText("");								// clear the result text field
			ui.label_Result.setText("Result");							// clear the result text field
			return;													// and return a zero value	
		}
		ui.operand1 = new CalculatorValue(str);						// There is a string so try to
		String errorString = ui.operand1.getErrorMessage();			// make and operand out of it
		if (errorString.length() > 0) {								// If there is an error the
			ui.label_errOperand1.setText(errorString);					// returned error string is used
			ui.operandError = true;
			return;
		}
		
		ui.text_Result.setText("");									// Clear the result field
		ui.label_errOperand1.setText("");								// Reset the error message
		ui.operand1Defined = true;										// The first operand is defined
		ui.operandError = false;										// Indicate there is no error
		}	

	/**********
	 * This routine checks the second operand and returns it.  If the value is invalid, a value of zero 
	 * is returned, but more importantly, a red error message is displayed next to the text field.
	 * If the value is valid, it resets the error field and returns the actual value.
	 */
	public void convertOperand2() {
		ui.operand2Defined = false;									// Assume the first operand is
		ui.label_Result.setText("Result");								// not valid, reset the label,
		ui.text_Result.setText("");									// and clear the result
		// Fetch the string the user entered into the operand's text field
		String str = ui.text_Operand2.getText();						// Get the text from the input field
		if (str.length() == 0) {
			ui.label_errOperand2.setText("");							// reset the error message
			ui.text_Result.setText("");								// clear the result text field
			ui.label_Result.setText("Result");							// clear the result text field
			return;													// and return a zero value	
		}
		ui.operand2 = new CalculatorValue(str);						// There is a string so try to
		String errorString = ui.operand2.getErrorMessage();			// make and operand out of it
		if (errorString.length() > 0) {								// If there is an error the
			ui.label_errOperand2.setText(errorString);					// returned error string is used
			ui.operandError = true;
			return;
		}
		
		ui.text_Result.setText("");									// Clear the result field
		ui.label_errOperand2.setText("");								// Reset the error message
		ui.operand2Defined = true;										// The first operand is defined
		ui.operandError = false;										// Indicate there is no error
		}	
	
	/**********
	 * This method is called when an operation button has been pressed.  Assess if there are issues 
	 * with either of the binary operands. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		if (!ui.operand1Defined) 										// Check operand 1 and set a
			ui.label_errOperand1.setText("Missing a valid value");		// missing value error
		if (!ui.operand2Defined) 										// Check operand 2 and set a
			ui.label_errOperand2.setText("Missing a valid value");		// missing value error
		if (ui.operandError)											// See if invalid input errors
			return true;											// return true if so
		if (!ui.operand1Defined || !ui.operand2Defined)					// See if any missing input values
			return true;											// return true is so
		return false;												// Else okay input, return false
	}

	/*******************************************************************************************************
	 * This portion of the class is the business logic that defines the computation that takes place when
	 *  the various calculator buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	public void addOperands(){
		if (binaryOperandIssues()) 									// If there is an operand error
			return;													// just return. Otherwise, reset
		ui.label_errOperand2.setText("");								// any second operand errors
		ui.result = new CalculatorValue(ui.operand1);						// Establish the left operand
		ui.result.add(ui.operand2);										// add the right operand to it
		String theAnswer = ui.result.toString();						// No possible errors, so get the 
		ui.text_Result.setText(theAnswer);								// result, place in it the output, and
		ui.label_Result.setText("Sum");								// specify the result is a sum.
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	public void subOperands(){
		ui.label_Result.setText("Subtraction not yet implemented!");	// Replace this line with the code
		ui.text_Result.setText("");									// required to do subtraction
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	public void mpyOperands(){
		ui.label_Result.setText("Multiplication not yet implemented!");// Replace this line with the code
		ui.text_Result.setText("");									// required to do multiplication
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	public void divOperands(){
		ui.label_Result.setText("Division not yet implemented!");		// Replace this line with the code
		ui.text_Result.setText("");									// required to do division
	}
}
