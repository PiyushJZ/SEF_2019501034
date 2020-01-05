package baselineLongCalculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

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
	// Attributes related to operand 1.
	private Label label_IntegerCalculator = new Label("Floating Point Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1MeasuredValue = new TextField();
	private TextField text_Operand1ErrorTerm = new TextField("");
	private Label label_Operand1MeasuredValue = new Label("Measured Value");
	private Label label_Operand1ErrorTerm = new Label("Error Term");
	private Label label_errOperand1MeasuredValue = new Label("");
	private TextFlow errMeasuredValue1;
    private Text errMVPart1 = new Text();
    private Text errMVPart2 = new Text();
    private CheckBox errorCheck1 = new CheckBox("Constant");
    private Label label_errOperand1ErrorTerm = new Label();
    private TextFlow errErrorTerm1;
    private Text errETPart1 = new Text();
    private Text errETPart2 = new Text();
    private Label label_errOperand1 = new Label("");

    // Attributes related to operand 2.
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2MeasuredValue = new TextField();
	private TextField text_Operand2ErrorTerm = new TextField("");
	private Label label_Operand2MeasuredValue = new Label("Measured Value");
	private Label label_Operand2ErrorTerm = new Label("Error Term");
	private Label label_errOperand2MeasuredValue = new Label("");
	private TextFlow errMeasuredValue2;
    private Text errMVPart3 = new Text();
    private Text errMVPart4 = new Text();
    private CheckBox errorCheck2 = new CheckBox("Constant");
    private Label label_errOperand2ErrorTerm = new Label();
    private TextFlow errErrorTerm2;
    private Text errETPart3 = new Text();
    private Text errETPart4 = new Text();
    private Label label_errOperand2 = new Label("");

    // Attributes related to the result.
	private Label label_Result = new Label("Result");
	private Label label_ResultMV = new Label("Measured Value");
	private Label label_ResultET = new Label("Error Term");
	private TextField text_ResultMV = new TextField();
	private TextField text_ResultET = new TextField();
	private Label label_errResult = new Label("");

	// Attributes related to buttons.
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_Sqrt = new Button("\u221A");

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

		setupCheckUI(errorCheck1, 10, 30);
		errorCheck1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean old_value, Boolean new_value) {
				text_Operand1ErrorTerm.setEditable(new_value ? true : false);
			}
		});

		errorCheck1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean old_value, Boolean new_value) {
				errorCheck1.setText(new_value ? "Measured Value" : "Constant");
			}
		});

		errorCheck1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean old_value, Boolean new_value) {
				text_Operand1ErrorTerm.setText(new_value ? "" : "");
			}
		});

		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 50);

		setupLabelUI(label_Operand1MeasuredValue, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 80);

		setupLabelUI(label_Operand1ErrorTerm, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, WINDOW_WIDTH-500, 70);

		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1MeasuredValue, "Arial", 18, WINDOW_WIDTH-550, Pos.BASELINE_LEFT, 10, 110, true);
		text_Operand1MeasuredValue.textProperty().addListener((observable, oldValue, newValue) -> {convertOperand1(); });
		text_Operand1MeasuredValue.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1MeasuredValue(); });
		// Move focus to Error term when done or second operand if no error term present.
		if (errorCheck1.isSelected()){
			text_Operand1MeasuredValue.setOnAction((event) -> { text_Operand1ErrorTerm.requestFocus(); });
		} else {
			text_Operand1MeasuredValue.setOnAction((event) -> { text_Operand2MeasuredValue.requestFocus(); });
		}

		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, WINDOW_WIDTH-550, Pos.BASELINE_LEFT, WINDOW_WIDTH-500, 110, false);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {convertOperand1(); });
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1ErrorTerm(); });
		// Move focus to the second operand when done
		text_Operand1ErrorTerm.setOnAction((event) -> { text_Operand2MeasuredValue.requestFocus(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, WINDOW_WIDTH - 10, Pos.BASELINE_LEFT, WINDOW_WIDTH-250, 50);
		label_errOperand1.setTextFill(Color.RED);

		label_errOperand1ErrorTerm.setTextFill(Color.RED);
		label_errOperand1ErrorTerm.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1ErrorTerm, "Arial", 18,
						WINDOW_WIDTH-10, Pos.BASELINE_LEFT,
						WINDOW_WIDTH - 500, 170);

		errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));

	    errErrorTerm1 = new TextFlow(errETPart1, errETPart2);
		errErrorTerm1.setMinWidth(WINDOW_WIDTH-10);
		errErrorTerm1.setLayoutX(WINDOW_WIDTH - 500);
		errErrorTerm1.setLayoutY(140);

		setupCheckUI(errorCheck2, 10, 200);
		errorCheck2.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean old_value, Boolean new_value) {
				text_Operand2ErrorTerm.setEditable(new_value ? true : false);
			}
		});

		errorCheck2.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean old_value, Boolean new_value) {
				errorCheck2.setText(new_value ? "Measured Value" : "Constant");
			}
		});

		errorCheck2.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean old_value, Boolean new_value) {
				text_Operand2ErrorTerm.setText(new_value ? "" : "");
			}
		});
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 220);

		setupLabelUI(label_Operand2MeasuredValue, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 250);

		setupLabelUI(label_Operand2ErrorTerm, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, WINDOW_WIDTH-500, 250);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2MeasuredValue, "Arial", 18, WINDOW_WIDTH-550, Pos.BASELINE_LEFT, 10, 280, true);
		text_Operand2MeasuredValue.textProperty().addListener((observable, oldValue, newValue) -> { convertOperand2(); });
		text_Operand2MeasuredValue.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2MeasuredValue(); });
		// Set focus on error term text box when done or result if no error term.
		if (errorCheck2.isSelected()) {
			text_Operand2MeasuredValue.setOnAction((event) -> { text_Operand2ErrorTerm.requestFocus(); });
		} else {
			text_Operand2MeasuredValue.setOnAction((event) -> { text_ResultMV.requestFocus(); });
		}

		setupTextUI(text_Operand2ErrorTerm, "Arial", 18, WINDOW_WIDTH-550, Pos.BASELINE_LEFT, WINDOW_WIDTH-500, 280, false);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> { convertOperand2(); });
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2ErrorTerm(); });
		// Move the focus to the result when done
		text_Operand2ErrorTerm.setOnAction((event) -> { text_ResultMV.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, WINDOW_WIDTH-250, 220);
		label_errOperand2.setTextFill(Color.RED);

		label_errOperand2ErrorTerm.setTextFill(Color.RED);
		label_errOperand2ErrorTerm.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2ErrorTerm, "Arial", 18,
						WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 
						WINDOW_WIDTH - 500, 345);

		errETPart3.setFill(Color.BLACK);
	    errETPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart4.setFill(Color.RED);
	    errETPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));

	    errErrorTerm2 = new TextFlow(errETPart3, errETPart4);
		errErrorTerm2.setMinWidth(WINDOW_WIDTH-10); 
		errErrorTerm2.setLayoutX(WINDOW_WIDTH - 500);  
		errErrorTerm2.setLayoutY(310);
		
		// Label the result just above it, left aligned
		setupLabelUI(label_Result, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 370);

		setupLabelUI(label_ResultMV, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 400);

		setupLabelUI(label_ResultET, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, WINDOW_WIDTH-500, 400);
		
		// Establish the result output field.  It is not editable, so the text can be selected
		// and copied, but it cannot be altered by the user.
		setupTextUI(text_ResultMV, "Arial", 18, WINDOW_WIDTH-550, Pos.BASELINE_LEFT, 10, 430, false);

		setupTextUI(text_ResultET, "Arial", 18, WINDOW_WIDTH-550, Pos.BASELINE_LEFT, WINDOW_WIDTH-500, 430, false);

		setupLabelUI(label_errResult, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 310);
		label_errResult.setTextFill(Color.RED);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.CENTER, 1 * BUTTON_SPACE-BUTTON_OFFSET, 480);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.CENTER, 2 * BUTTON_SPACE-BUTTON_OFFSET, 480);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.CENTER, 3 * BUTTON_SPACE-BUTTON_OFFSET, 480);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.CENTER, 4 * BUTTON_SPACE-BUTTON_OFFSET, 480);
		button_Div.setOnAction((event) -> { divOperands(); });

		// Establish the SQRT "√" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.CENTER, 5 * BUTTON_SPACE-BUTTON_OFFSET, 480);
		button_Sqrt.setOnAction((event) -> { sqrtOperands(); });

		// Establish an error message for the first operand Measured Value, left aligned
		label_errOperand1MeasuredValue.setTextFill(Color.RED);
		label_errOperand1MeasuredValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1MeasuredValue, "Arial", 18,
						WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 170);

		// Establish an error message for the second operand Measured Value, left aligned
		label_errOperand2MeasuredValue.setTextFill(Color.RED);
		label_errOperand2MeasuredValue.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2MeasuredValue, "Arial", 18,
						WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 345);

		errMeasuredValue1 = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue1.setMinWidth(WINDOW_WIDTH-10);
		errMeasuredValue1.setLayoutX(22);
		errMeasuredValue1.setLayoutY(140);

		// Error Message for the Measured Value for operand 1
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));

		errMeasuredValue2 = new TextFlow(errMVPart3, errMVPart4);
		errMeasuredValue2.setMinWidth(WINDOW_WIDTH-10); 
		errMeasuredValue2.setLayoutX(20);  
		errMeasuredValue2.setLayoutY(310);

		// Error Message for the Measured Value for operand 1
		errMVPart3.setFill(Color.BLACK);
	    errMVPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart4.setFill(Color.RED);
	    errMVPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1MeasuredValue, text_Operand1ErrorTerm,
				label_Operand1MeasuredValue, label_Operand1ErrorTerm, label_Operand2MeasuredValue, label_Operand2ErrorTerm,
				label_ResultET, text_ResultET, errorCheck1, errorCheck2, label_errOperand1ErrorTerm,
				label_errOperand2ErrorTerm, errErrorTerm1, errErrorTerm2, text_Operand2ErrorTerm, label_ResultMV,
				label_errOperand1MeasuredValue, label_errOperand2MeasuredValue, errMeasuredValue1, errMeasuredValue2,
				label_errOperand1, label_Operand2, text_Operand2MeasuredValue, label_errOperand2, label_Result,
				text_ResultMV, button_Add, button_Sub, button_Mpy, button_Div, button_Sqrt);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y) {
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e) {
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
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y) {
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}

	private void setupCheckUI(CheckBox cb, double x, double y) {
		cb.setLayoutX(x);
		cb.setLayoutY(y);
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

	private void setOperand1ErrorTerm() {
		label_errOperand1ErrorTerm.setText("");
		errETPart1.setText("");
		errETPart2.setText("");
		performGo1();
		convertOperand1();
	}

	private void setOperand2ErrorTerm() {
		label_errOperand2ErrorTerm.setText("");
		errETPart3.setText("");
		errETPart4.setText("");
		performGo2();
		convertOperand2();
	}

	private void performGo1() {
		String text = "";
		if (text_Operand1MeasuredValue.getText().length() == 0)
			return;
		if (text_Operand1MeasuredValue.getText().charAt(0) == '+' || text_Operand1MeasuredValue.getText().charAt(0) == '-') {
			text = text_Operand1MeasuredValue.getText().substring(1);
		}
		else {
			text = text_Operand1MeasuredValue.getText();
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
		} else {
			errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand1ErrorTerm.getText());
			if (errMessage != "") {
				System.out.println(errMessage);
				label_errOperand1ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
				String input = ErrorTermRecognizer.errorTermInput;
				if (ErrorTermRecognizer.errorTermIndexofError <= -1)
					return;
				errETPart1.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
				errETPart2.setText("\u21EB");
			}
		}
	}

	private void performGo2() {
		String text = "";
		if (text_Operand2MeasuredValue.getText().length() == 0) return;
		if (text_Operand2MeasuredValue.getText().charAt(0) == '+' || text_Operand2MeasuredValue.getText().charAt(0) == '-')  {
			text = text_Operand2MeasuredValue.getText().substring(1);
		}
		else {
			text = text_Operand2MeasuredValue.getText();
		}
		String errMessage = MeasuredValueRecognizer.checkMeasuredValue(text);
		if (errMessage != "") {
			System.out.println(errMessage);
			label_errOperand2MeasuredValue.setText(MeasuredValueRecognizer.measuredValueErrorMessage);
			if (MeasuredValueRecognizer.measuredValueIndexofError <= -1) return;
			String input = MeasuredValueRecognizer.measuredValueInput;
			errMVPart3.setText(input.substring(0, MeasuredValueRecognizer.measuredValueIndexofError));
			errMVPart4.setText("\u21EB");
		} else {
			errMessage = ErrorTermRecognizer.checkErrorTerm(text_Operand2ErrorTerm.getText());
			if (errMessage != "") {
				System.out.println(errMessage);
				label_errOperand2ErrorTerm.setText(ErrorTermRecognizer.errorTermErrorMessage);
				String input = ErrorTermRecognizer.errorTermInput;
				if (ErrorTermRecognizer.errorTermIndexofError <= -1) return;
				errETPart3.setText(input.substring(0, ErrorTermRecognizer.errorTermIndexofError));
				errETPart4.setText("\u21EB");
			}
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
		text_ResultMV.setText("");									// and clear the result
		// Fetch the string the user entered into the operand's text field
		String mv = text_Operand1MeasuredValue.getText();						// Get the text from the input field
		String et = text_Operand1ErrorTerm.getText();
		if (mv.length() == 0) {
			label_errOperand1.setText("");							// reset the error message
			text_ResultMV.setText("");								// clear the result text field
			label_Result.setText("Result");							// clear the result text field
			return;													// and return a zero value	
		}
		if (!errorCheck1.isSelected()) {
			logicalTasks.convertOperand1Logic(mv);
		} else {
			if (et.length() == 0) {
				label_errOperand1ErrorTerm.setText("");							// reset the error message
				text_ResultMV.setText("");								// clear the result text field
				text_ResultET.setText("");
				label_Result.setText("Result");							// clear the result text field
				return;
			}
			logicalTasks.convertOperand1Logic(mv, et);
		}
		String errorString = logicalTasks.getOperand1().getErrorMessage();
		if (errorString.length() > 0) {								// If there is an error the
			label_errOperand1.setText(errorString);					// returned error string is used
			operandError = true;
			return;
		}

		text_ResultMV.setText("");									// Clear the result field
		text_ResultET.setText("");
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
		text_ResultMV.setText("");									// and clear the result
		// Fetch the string the user entered into the operand's text field
		String mv = text_Operand2MeasuredValue.getText();						// Get the text from the input field
		String et = text_Operand1ErrorTerm.getText();
		if (mv.length() == 0) {
			label_errOperand2.setText("");							// reset the error message
			text_ResultMV.setText("");								// clear the result text field
			label_Result.setText("Result");							// clear the result text field
			return;													// and return a zero value
		}
		if (!errorCheck2.isSelected()) {
			logicalTasks.convertOperand2Logic(mv);
		} else {
			if (et.length() == 0) {
				label_errOperand2ErrorTerm.setText("");							// reset the error message
				text_ResultMV.setText("");								// clear the result text field
				text_ResultET.setText("");
				label_Result.setText("Result");							// clear the result text field
				return;
			}
			logicalTasks.convertOperand2Logic(mv, et);
		}
		// operand2 = new CalculatorValue(str);						// There is a string so try to
		String errorString = logicalTasks.getOperand2().errorMessage;			// make and operand out of it
		if (errorString.length() > 0) {								// If there is an error the
			label_errOperand2.setText(errorString);					// returned error string is used
			operandError = true;
			return;
		}
		
		text_ResultMV.setText("");									// Clear the result field
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

	/*******************************************************************************************************
	 * This is the add routine.
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
		String[] answerTerms = theAnswer.split(",");
		text_ResultMV.setText(answerTerms[0]);
		if (errorCheck1.isSelected() || errorCheck2.isSelected()) {
			text_ResultET.setText(answerTerms[1]);
		}
		label_Result.setText("Sum");
	}

	/*******************************************************************************************************
	 * This is the subtract routine.
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
		String[] answerTerms = theAnswer.split(",");
		text_ResultMV.setText(answerTerms[0]);
		if (errorCheck1.isSelected() || errorCheck2.isSelected()) {
			text_ResultET.setText(answerTerms[1]);
		}
		label_Result.setText("Difference");
	}

	/*******************************************************************************************************
	 * This is the multiply routine.
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
		String[] answerTerms = theAnswer.split(",");
		text_ResultMV.setText(answerTerms[0]);
		if (errorCheck1.isSelected() || errorCheck2.isSelected()) {
			text_ResultET.setText(answerTerms[1]);
		}
		label_Result.setText("Product");
	}

	/*******************************************************************************************************
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
		String[] answerTerms = theAnswer.split(",");
		text_ResultMV.setText(answerTerms[0]);
		if (errorCheck1.isSelected() || errorCheck2.isSelected()) {
			text_ResultET.setText(answerTerms[1]);
		}
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
		String[] answerTerms = theAnswer.split(",");
		text_ResultMV.setText(answerTerms[0]);
		if (errorCheck1.isSelected() || errorCheck2.isSelected()) {
			text_ResultET.setText(answerTerms[1]);
		}
		label_Result.setText("Square Root");
	}
}
