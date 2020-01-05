package baselineLongCalculator;

import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: An entity class component of a JavaFX demonstration application that represents
 * the calculator value and hides its implementation from the rest of the system, making it easier
 * for us to do experiments with alternate representations.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2016 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @author Piyush Jain
 *
 * @version 4.00	2019-12-03 Baseline JavaFX implementation of a long integer calculator 
 * 
 */
public class CalculatorValue {

	// These are the major values that define a calculator value
	double measuredValue;
	double errorTerm;
	int unitCode;
	String errorMessage;

	/*****
	 * These are the constructors for the class
	 * 
	 * This is the default constructor
	 */
	public CalculatorValue() {
		measuredValue = 0;
		errorTerm = 0;
		unitCode = 0;
		errorMessage = "";
	}

	/*****
	 * This constructor creates a calculator value based on a long integer
	 */
	public CalculatorValue(double v) {
		measuredValue = v;
		errorTerm = 0;
		unitCode = 0;
		errorMessage = "";
	}

	/*****
	 * This constructor creates a calculator value based on two long values and an int coded unit code
	 */
	public CalculatorValue(double v, double et, int uc) {
		measuredValue = v;
		errorTerm = et;
		unitCode = uc;
		errorMessage = "";
	}

	
	/*****
	 * This constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorTerm = v.errorTerm;
		unitCode = v.unitCode;
		errorMessage = v.errorMessage;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a serious chance of errors, and so the routine returns
	 * the value with the error message value set to empty or the string of an error
	 * message.
	 */
	public CalculatorValue(String s) {
		measuredValue = 0;
		if (s.length() == 0) {									// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												// 
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;											// Start at character position zero
		boolean negative = false;								// Assume the value is not negative
		if (s.charAt(start) == '+')								// See if the first character is '+'
			 start++;											// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-') {						// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}
		
		// See if the user-entered string can be converted into Long value
		Scanner tempScanner = new Scanner(s.substring(start));	// Create scanner for the digits
		if (!tempScanner.hasNextDouble()) {						// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		// Double float value.  If not, 
			tempScanner.close();								// close the scanner and
			measuredValue = 0;									// return a zero value.
			return;												
		}
		
		// Convert the user-entered string to a integer value and see if something else is following it
		measuredValue = tempScanner.nextDouble();					// Convert the value and check to see
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 			// following the value.  If so,
			tempScanner.close();								// close the scanner and
			measuredValue = 0;									// return a zero value.
			return;													
		}
		
		// Getting here means that the input is valid
		tempScanner.close();									// Close the scanner
		errorTerm = 0;											// Clear the error term
		unitCode = 0;											// clear the unit code
		errorMessage = "";										// Clear the error message
		if (negative)											// Return the proper value based
			measuredValue = -measuredValue;						// on the state of the flag that
	}

	/************************************************************************************
	 * This constructor creates a calculator value from two strings.
	 * The first string is teh measured value, and the second string is the
	 * error term of the calculator value. We check for errors in both the
	 * measured value and the error terms.
	 * @param mv the measured value of the calculator value.
	 * @param et the error term of the calculator value.
	 */
	public CalculatorValue(String mv, String et) {
		measuredValue = 0;
		if (mv.length() == 0 || et.length() == 0) {
			errorMessage = "***Error*** Input is empty";
			return;
		}

		// If the first character is a plus sign, ignore it.
		int start = 0;											// Start at character position zero
		boolean negative = false;								// Assume the value is not negative
		if (mv.charAt(start) == '+')								// See if the first character is '+'
			 start++;											// If so, skip it and ignore it

		// If the first character is a minus sign, skip over it, but remember it
		else if (mv.charAt(start) == '-') {						// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}

		// See if the user-entered string can be converted into Long value
		Scanner mvScanner = new Scanner(mv.substring(start));	// Create scanner for the digits
		if (!mvScanner.hasNextDouble()) {						// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		// Double float value.  If not, 
			mvScanner.close();								// close the scanner and
			measuredValue = 0;									// return a zero value.
			return;												
		}
		
		// Convert the user-entered string to a integer value and see if something else is following it
		measuredValue = mvScanner.nextDouble();					// Convert the value and check to see
		if (mvScanner.hasNext()) {							// that there is nothing else is
			errorMessage = "***Error*** Excess data"; 			// following the value.  If so,
			mvScanner.close();								// close the scanner and
			measuredValue = 0;									// return a zero value.
			return;												
		}

		// Getting here means that the input is valid
		mvScanner.close();									// Close the scanner
		if (negative) {											// Return the proper value based
			measuredValue = -measuredValue;						// on the state of the flag that
		}

		// Setting the error term.
		if (et.length() == 0) {
			errorMessage = "***Error*** Input is empty";
			return;
		}

		Scanner etScanner = new Scanner(et);
		if (!etScanner.hasNextDouble()) {
			errorMessage = "***Error*** Invalid value";
			etScanner.close();
			errorTerm = 0;
			return;
		}

		errorTerm = etScanner.nextDouble();
		if (etScanner.hasNextDouble()) {
			errorMessage = "***Error*** Excess data";
			etScanner.close();
			errorTerm = 0;
			return;
		}

		etScanner.close();
		unitCode = 0;											// clear the unit code
		errorMessage = "";										// Clear the error message
	}

	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String toString() {
		return measuredValue + "," + errorTerm;
	}

	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(double v){
		measuredValue = v;
		errorMessage = "";
	}
	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorTerm = v.errorTerm;
		errorMessage = v.errorMessage;
		unitCode = v.unitCode;
	}
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values. These routines assume that the
	 * caller has verified that things are okay for the operation to take place.
	 * 
	 * Since this is addition, we don't recognize any errors.
	 */
	public void addMeasuredValue(CalculatorValue v) {
		measuredValue += v.measuredValue;
		errorMessage = "";
	}

	public void subMeasuredValue(CalculatorValue v) {
		measuredValue -= v.measuredValue;
		errorMessage = "";
	}

	public void mpyMeasuredValue(CalculatorValue v) {
		measuredValue *= v.measuredValue;
		errorMessage = "";
	}

	public void divMeasuredValue(CalculatorValue v) {
		measuredValue /= v.measuredValue;
		errorMessage = "";
	}

	public void sqrt() {
		double sqroot = Math.pow(measuredValue, 0.5);
		errorTerm = (0.5 * (errorTerm / measuredValue)) * sqroot;
		measuredValue = sqroot;
		errorMessage = "";
	}

	public void addErrorTerm(CalculatorValue v) {
		errorTerm += v.errorTerm;
		errorMessage = "";
		
	}

	/**
	 * Performs the error term calculation for the multiplication operation.
	 * @param v the multiplicand.
	 */
	public void mpyErrorTerm(CalculatorValue v) {
		errorTerm /= measuredValue;
		errorTerm += (v.errorTerm/v.measuredValue);
		errorTerm *= (this.measuredValue * v.measuredValue);
	}

	/**
	 * Performs the error term calculation for the division operation.
	 * @param v the divisor.
	 */
	public void divErrorTerm(CalculatorValue v) {
		errorTerm /= measuredValue;
		errorTerm += (v.errorTerm/v.measuredValue);
		errorTerm *= (this.measuredValue / v.measuredValue);
	}
}
