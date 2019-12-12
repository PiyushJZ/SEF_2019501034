package baselineLongCalculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <p> Title: BaselineCalculator Class. </p>
 * 
 * <p> Description: The user interface and business logic for this JavaFX application to 
 * establish the user interface and the business logic associated with changing the text 
 * fields and pressing the buttons. </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 4.00	2019-12-03 Baseline JavaFX implementation of a long integer calculator 
 * 
 */

public class UserInterface {
	
	// The root of the user interface and the width of the user interface window from the mainline
	private Pane theRoot;
	private static double WINDOW_WIDTH;
	
	// These are the major application values not associated with the user interface
	public CalculatorValue operand1;
	public CalculatorValue operand2;
	public CalculatorValue result;
	public boolean operandError = false;
	public boolean operand1Defined = false;
	public boolean operand2Defined = false;
	public BusinessLogic logicalTasks;

	// Constants used to parameterize the graphical user interface.  We do not use a layout for
	// this application. Rather we manually control the location of each graphical element.
	public double BUTTON_SPACE;		// These constants are defined based on the window width
	public double BUTTON_WIDTH;		// at the time this object is instantiated
	public double BUTTON_OFFSET;

	// These are the application values required by the user interface
	public Label label_IntegerCalculator = new Label("Integer Calculator");
	public Label label_Operand1 = new Label("First operand");
	public TextField text_Operand1 = new TextField();
	public Label label_Operand2 = new Label("Second operand");
	public TextField text_Operand2 = new TextField();
	public Label label_Result = new Label("Result");
	public TextField text_Result = new TextField();
	public Button button_Add = new Button("+");
	public Button button_Sub = new Button("-");
	public Button button_Mpy = new Button("×");				// The multiply symbol: \u00D7
	public Button button_Div = new Button("÷");				// The divide symbol: \u00F7
	public Label label_errOperand1 = new Label("");
	public Label label_errOperand2 = new Label("");
	
	public UserInterface (Pane root, double ww) {
		// Save the window root for the setup of the user interface
		theRoot = root;
		
		// Define the formatting constants using the window width from the mainline
		WINDOW_WIDTH = ww;
		BUTTON_SPACE = WINDOW_WIDTH / 5;		// There are five gaps
		BUTTON_WIDTH = WINDOW_WIDTH / 10;		// The width of a button from experimentation
		BUTTON_OFFSET = BUTTON_WIDTH / 2;		// The button offset is half the button width
		
		// Set up the User Interface
		initializeTheUserInterface(theRoot);
		
		logicalTasks = new BusinessLogic(this);
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
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {logicalTasks.convertOperand1(); });
		// Move focus to the second operand when done
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 45);
		label_errOperand1.setTextFill(Color.RED);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 135);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 160, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> { logicalTasks.convertOperand2(); });
		// Move the focus to the result when done
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 135);
		label_errOperand2.setTextFill(Color.RED);
		
		// Label the result just above it, left aligned
		setupLabelUI(label_Result, "Arial", 18, WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 220);
		
		// Establish the result output field.  It is not editable, so the text can be selected
		// and copied, but it cannot be altered by the user.
		setupTextUI(text_Result, "Arial", 18, WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 250, false);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * BUTTON_SPACE-BUTTON_OFFSET, 300);
		button_Add.setOnAction((event) -> { logicalTasks.addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * BUTTON_SPACE-BUTTON_OFFSET, 300);
		button_Sub.setOnAction((event) -> { logicalTasks.subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * BUTTON_SPACE-BUTTON_OFFSET, 300);
		button_Mpy.setOnAction((event) -> { logicalTasks.mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * BUTTON_SPACE-BUTTON_OFFSET, 300);
		button_Div.setOnAction((event) -> { logicalTasks.divOperands(); });
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, 
				label_errOperand1, label_Operand2, text_Operand2, label_errOperand2, label_Result, 
				text_Result, button_Add, button_Sub, button_Mpy, button_Div);

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
}
