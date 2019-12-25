package baselineLongCalculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The user interface for this JavaFX application to 
 * establish the user interface associated with changing the text 
 * fields and pressing the buttons. </p>
 * 
 * <p> Copyright: Lynn Robert Carter Â© 2019 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @author Piyush Jain
 * 
 * @version 4.00	2019-12-03 Baseline JavaFX implementation of a long integer calculator
 * 
 */

public class UserInterface {
	
	// The root of the user interface and the width of the user interface window from the mainline
	private Pane theRoot;
	private static double WINDOW_WIDTH;
	
	// These are the major application values not associated with the user interface
	private boolean operandError = false;
	private boolean operand1Defined = false;
	private boolean operand2Defined = false;
	private BusinessLogic logicalTasks;

	// Constants used to parameterize the graphical user interface.  We do not use a layout for
	// this application. Rather we manually control the location of each graphical element.
	private double BUTTON_SPACE;		// These constants are defined based on the window width
	private double BUTTON_WIDTH;		// at the time this object is instantiated
	private double BUTTON_OFFSET;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("Floating Point Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	private Label label_errOperand1MeasuredValue = new Label("");
	private TextFlow errMeasuredValue1;
    private Text errMVPart1 = new Text();
    private Text errMVPart2 = new Text();

	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_errOperand2MeasuredValue = new Label("");
	private TextFlow errMeasuredValue2;
    private Text errMVPart3 = new Text();
    private Text errMVPart4 = new Text();

	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();
	private Label label_errResult = new Label("");

	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_Sqrt = new Button("\u221A");
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	
	public UserInterface (Pane root, double ww) {
		// Save the window root for the setup of the user interface
		theRoot = root;
		
		// Define the formatting constants using the window width from the mainline
		WINDOW_WIDTH = ww;
		BUTTON_SPACE = WINDOW_WIDTH / 6;		// There are five gaps
		BUTTON_WIDTH = WINDOW_WIDTH / 12;		// The width of a button from experimentation
		BUTTON_OFFSET = BUTTON_WIDTH / 2;		// The button offset is half the button width
		
		// Set up the User Interface
		initializeTheUserInterface(theRoot);
		
		logicalTasks = new BusinessLogic();
	}
	
	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	private void initializeTheUserInterface(Pane theRoot) {
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {convertOperand1(); });
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1MeasuredValue(); });
		// Move focus to the second operand when done
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 45);
		label_errOperand1.setTextFill(Color.RED);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 145);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 170, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> { convertOperand2(); });
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2MeasuredValue(); });
		// Move the focus to the result when done
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 145);
		label_errOperand2.setTextFill(Color.RED);
		
		// Label the result just above it, left aligned
		setupLabelUI(label_Result, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 240);
		
		// Establish the result output field.  It is not editable, so the text can be selected
		// and copied, but it cannot be altered by the user.
		setupTextUI(text_Result, "Arial", 18, WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 270, false);

		setupLabelUI(label_errResult, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 270);
		label_errResult.setTextFill(Color.RED);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * BUTTON_SPACE-BUTTON_OFFSET, 315);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * BUTTON_SPACE-BUTTON_OFFSET, 315);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * BUTTON_SPACE-BUTTON_OFFSET, 315);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * BUTTON_SPACE-BUTTON_OFFSET, 315);
		button_Div.setOnAction((event) -> { divOperands(); });

		// Establish the SQRT "√" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * BUTTON_SPACE-BUTTON_OFFSET, 315);
		button_Sqrt.setOnAction((event) -> { sqrtOperands(); });

		// Establish an error message for the first operand Measured Value, left aligned
		label_errOperand1MeasuredValue.setTextFill(Color.RED);
		label_errOperand1MeasuredValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1MeasuredValue, "Arial", 18,
						WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 125);

		// Establish an error message for the second operand Measured Value, left aligned
		label_errOperand2MeasuredValue.setTextFill(Color.RED);
		label_errOperand2MeasuredValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2MeasuredValue, "Arial", 18,
						WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 225);

		errMeasuredValue1 = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue1.setMinWidth(WINDOW_WIDTH-10);
		errMeasuredValue1.setLayoutX(22);
		errMeasuredValue1.setLayoutY(100);

		// Error Message for the Measured Value for operand 1
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));

		errMeasuredValue2 = new TextFlow(errMVPart3, errMVPart4);
		errMeasuredValue2.setMinWidth(WINDOW_WIDTH-10); 
		errMeasuredValue2.setLayoutX(20);  
		errMeasuredValue2.setLayoutY(200);

		// Error Message for the Measured Value for operand 1
		errMVPart3.setFill(Color.BLACK);
	    errMVPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart4.setFill(Color.RED);
	    errMVPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1,
				label_errOperand1MeasuredValue, label_errOperand2MeasuredValue, errMeasuredValue1, errMeasuredValue2,
				label_errOperand1, label_Operand2, text_Operand2, label_errOperand2, label_Result, 
				text_Result, button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}

	private void setOperand1MeasuredValue() {
		label_errOperand1MeasuredValue.setText("");
		errMVPart1.setText("");
		errMVPart2.setText("");
		performGo1();
		convertOperand1();
	}

	private void setOperand2MeasuredValue() {
		label_errOperand2MeasuredValue.setText("");
		errMVPart3.setText("");
		errMVPart4.setText("");
		performGo2();
		convertOperand2();
	}

	private void performGo1() {
		String text = "";
		if (text_Operand1.getText().length() == 0)
			return;
		if (text_Operand1.getText().charAt(0) == '+' || text_Operand1.getText().charAt(0) == '-') {
			text = text_Operand1.getText().substring(1);
		}
		else {
			text = text_Operand1.getText();
		}
		String errMessage = MeasuredValueRecognizer.checkMeasuredValue(text);
		if (errMessage != "") {
			System.out.println(errMessage);
			label_errOperand1MeasuredValue.setText(MeasuredValueRecognizer.measuredValueErrorMessage);
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1)
				return;
			String input = MeasuredValueRecognizer.measuredValueInput;
			errMVPart1.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			errMVPart2.setText("\u21EB");
		}
	}

	private void performGo2() {
		String text2 = "";
		if (text_Operand2.getText().length() == 0) return;
		if (text_Operand2.getText().charAt(0) == '+' || text_Operand2.getText().charAt(0) == '-')  {
			text2 = text_Operand2.getText().substring(1);
		}
		else {
			text2 = text_Operand2.getText();
		}
		String errMessage = MeasuredValueRecognizer.checkMeasuredValue(text2);
		if (errMessage != "") {
			System.out.println(errMessage);
			label_errOperand2MeasuredValue.setText(MeasuredValueRecognizer.measuredValueErrorMessage);
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return;
			String input = MeasuredValueRecognizer.measuredValueInput;
			errMVPart3.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			errMVPart4.setText("\u21EB");
		}
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
	private void convertOperand1() {
		operand1Defined = false;									// Assume the first operand is
		label_Result.setText("Result");								// not valid, reset the label,
		text_Result.setText("");									// and clear the result
		// Fetch the string the user entered into the operand's text field
		String str = text_Operand1.getText();						// Get the text from the input field
		if (str.length() == 0) {
			label_errOperand1.setText("");							// reset the error message
			text_Result.setText("");								// clear the result text field
			label_Result.setText("Result");							// clear the result text field
			return;													// and return a zero value	
		}
		logicalTasks.convertOperand1Logic(str);
		// operand1 = new CalculatorValue(str);						// There is a string so try to
		String errorString = logicalTasks.getOperand1().errorMessage;			// make and operand out of it
		if (errorString.length() > 0) {								// If there is an error the
			label_errOperand1.setText(errorString);					// returned error string is used
			operandError = true;
			return;
		}
		
		text_Result.setText("");									// Clear the result field
		label_errOperand1.setText("");								// Reset the error message
		operand1Defined = true;										// The first operand is defined
		operandError = false;										// Indicate there is no error
		}	

	/**********
	 * This routine checks the second operand and returns it.  If the value is invalid, a value of zero 
	 * is returned, but more importantly, a red error message is displayed next to the text field.
	 * If the value is valid, it resets the error field and returns the actual value.
	 */
	private void convertOperand2() {
		operand2Defined = false;									// Assume the first operand is
		label_Result.setText("Result");								// not valid, reset the label,
		text_Result.setText("");									// and clear the result
		// Fetch the string the user entered into the operand's text field
		String str = text_Operand2.getText();						// Get the text from the input field
		if (str.length() == 0) {
			label_errOperand2.setText("");							// reset the error message
			text_Result.setText("");								// clear the result text field
			label_Result.setText("Result");							// clear the result text field
			return;													// and return a zero value	
		}
		logicalTasks.convertOperand2Logic(str);
		// operand2 = new CalculatorValue(str);						// There is a string so try to
		String errorString = logicalTasks.getOperand2().errorMessage;			// make and operand out of it
		if (errorString.length() > 0) {								// If there is an error the
			label_errOperand2.setText(errorString);					// returned error string is used
			operandError = true;
			return;
		}
		
		text_Result.setText("");									// Clear the result field
		label_errOperand2.setText("");								// Reset the error message
		operand2Defined = true;										// The first operand is defined
		operandError = false;										// Indicate there is no error
		}	
	
	/**********
	 * This method is called when an operation button has been pressed.  Assess if there are issues 
	 * with either of the binary operands. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		if (!operand1Defined) 										// Check operand 1 and set a
			label_errOperand1.setText("Missing a valid value");		// missing value error
		if (!operand2Defined) 										// Check operand 2 and set a
			label_errOperand2.setText("Missing a valid value");		// missing value error
		if (operandError)											// See if invalid input errors
			return true;											// return true if so
		if (!operand1Defined || !operand2Defined)					// See if any missing input values
			return true;											// return true is so
		return false;												// Else okay input, return false
	}

	private boolean singleOperandIssues() {
		if (!operand1Defined) {
			label_errOperand1.setText("Missing a valid value");
		}

		if (operandError) {
			return true;
		}

		if (!operand1Defined) {
			return true;
		}
		return false;
	}

	/*******************************************************************************************************
	 * This portion of the class is the business logic that defines the computation that takes place when
	 *  the various calculator buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		if (binaryOperandIssues())
			return;						
		label_errOperand2.setText("");
		label_errResult.setText("");
		// result = new CalculatorValue(operand1);
		// result.add(operand2);		
		String theAnswer = logicalTasks.addOperandsLogic();
		if("Infinity".equals(theAnswer)) {
			label_errResult.setText("Operation out of range for doubles");
			return;
		}
		text_Result.setText(theAnswer);
		label_Result.setText("Sum");				
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
		if (binaryOperandIssues())
			return;						
		label_errOperand2.setText("");
		label_errResult.setText("");
		String theAnswer = logicalTasks.subOperandsLogic();
		if("Infinity".equals(theAnswer)) {
			label_errResult.setText("Operation out of range for doubles");
			return;
		}
		text_Result.setText(theAnswer);
		label_Result.setText("Difference");
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		if (binaryOperandIssues())
			return;						
		label_errOperand2.setText("");
		label_errResult.setText("");		
		String theAnswer = logicalTasks.mpyOperandsLogic();
		if("Infinity".equals(theAnswer)) {
			label_errResult.setText("Operation out of range for doubles");
			return;
		}
		text_Result.setText(theAnswer);
		label_Result.setText("Product");
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		if (binaryOperandIssues())
			return;
		label_errOperand2.setText("");
		label_errResult.setText("");
		String theAnswer = logicalTasks.divOperandsLogic();
		if("zeroDivision".equals(theAnswer)) {
			label_errResult.setText("Cannot be divided by zero");
			label_Result.setText("Zero Division");
			return;
		}
		else if("Infinity".equals(theAnswer)) {
			label_errResult.setText("Operation out of range for doubles");
			return;
		}
		text_Result.setText(theAnswer);
		label_Result.setText("Quotient");
	}

	private void sqrtOperands() {
		if (singleOperandIssues())
			return;
		label_errOperand2.setText("");		
		label_errResult.setText("");
		String theAnswer = logicalTasks.sqrtOperandsLogic();
		if ("Negitive".equals(theAnswer)) {
			label_errResult.setText("Square root for negative values not allowed.");
			return;
		}
		else if("Infinity".equals(theAnswer)) {
			label_errResult.setText("Operation out of range for doubles");
			return;
		}
		text_Result.setText(theAnswer);
		label_Result.setText("Square Root");
	}
}
