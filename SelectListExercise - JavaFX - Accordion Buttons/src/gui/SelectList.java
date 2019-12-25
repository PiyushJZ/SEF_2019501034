package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * <p>
 * Title: SelectList using a ComboBox
 * </p>
 *
 * <p>
 * Description: The main screen for a Select List exercise
 * </p>
 *
 * <p>
 * Copyright: Copyright 2016
 * </p>
 *
 * @author Lynn Robert Carter
 * @version 1.00
 */

public class SelectList extends Application {

	// UI Section asking the user to select a Word from a Select List
	Group rootMain;
	Group rootPopUp;
	Scene sceneMain;
	Scene scenePopUp;
	
	static Stage stagePopUp;

	TitledPane t1, t2, t3, t4, t5; //panes for accordion
	ObservableList<Button> arrayA = FXCollections.observableArrayList();
	ObservableList<Button> arrayB = FXCollections.observableArrayList();
	ObservableList<Button> arrayC = FXCollections.observableArrayList();
	ObservableList<Button> arrayD = FXCollections.observableArrayList();
	ObservableList<Button> arrayE = FXCollections.observableArrayList();
	
	// UI Section asking the user to select a country from a JComboBox
	Label labelSelectAWord = new Label("Select a word:");
	static Button btnSelectAWord = new Button("Select a word");

	// UI Section showing the user the text of the currently selected item
	Label labelSelectedWord = new Label("Selected word:");
	TextField outputSelectedWord = new TextField("- no word selected -");

	// UI Section showing the user the index of the currently selected item
	Label labelSelectedWordIndex = new Label("Selected word list index:");
	TextField outputSelectedWordIndex = new TextField("0");

	// The quit button that terminates the execution of this application
	Button buttonQuit = new Button();

	private final double WINDOW_WIDTH = 610;  	

		Button btnWord000 = createButton ("- no word selected - ", "0");
		Button btnWord001 = createButton ("aback", "1");
		Button btnWord002 = createButton ("abaft", "2");
		Button btnWord003 = createButton ("abalone", "3");
		Button btnWord004 = createButton ("abandon", "4");
		Button btnWord005 = createButton ("abandoned", "5");
		Button btnWord006 = createButton ("abandonment", "6");
		Button btnWord007 = createButton ("abandonware", "7");
		Button btnWord008 = createButton ("abase", "8");
		Button btnWord009 = createButton ("abasement", "9");
		Button btnWord010 = createButton ("abash", "10");
		Button btnWord011 = createButton ("abate", "11");
		Button btnWord012 = createButton ("abating", "12");
		Button btnWord013 = createButton ("abatis", "13");
		Button btnWord014 = createButton ("abattoir", "14");
		Button btnWord015 = createButton ("abaxial", "15");
		Button btnWord016 = createButton ("abaya", "16");
		Button btnWord017 = createButton ("abbacies", "17");
		Button btnWord018 = createButton ("abbatial", "18");
		Button btnWord019 = createButton ("abbé", "19");
		Button btnWord020 = createButton ("abbess", "20");
		Button btnWord021 = createButton ("abbey", "21");
		Button btnWord022 = createButton ("abbott", "22");
		Button btnWord023 = createButton ("abbr.", "23");
		Button btnWord024 = createButton ("abreviated", "24");
		Button btnWord025 = createButton ("abbreviation", "25");
		Button btnWord026 = createButton ("abdabs", "26");
		Button btnWord027 = createButton ("abdicate", "27");
		Button btnWord028 = createButton ("abdicating", "28");
		Button btnWord029 = createButton ("abdication", "29");
		Button btnWord030 = createButton ("abdomen", "30");
		Button btnWord031 = createButton ("abdominal", "31");
		Button btnWord032 = createButton ("abdominoplasties", "32");
		Button btnWord033 = createButton ("abdominoplasty", "33");
		Button btnWord034 = createButton ("abdominous", "34");
		Button btnWord035 = createButton ("abduct", "35");
		Button btnWord036 = createButton ("abductee", "36");
		Button btnWord037 = createButton ("abduction", "37");
		Button btnWord038 = createButton ("abeam", "38");
		Button btnWord039 = createButton ("abecedarian", "39");
		Button btnWord040 = createButton ("abed", "40");
		Button btnWord041 = createButton ("abele", "41");
		Button btnWord042 = createButton ("abelian", "42");
		Button btnWord043 = createButton ("aberrant", "43");
		Button btnWord044 = createButton ("abelian", "44");
		Button btnWord045 = createButton ("aberrant", "45");
		Button btnWord046 = createButton ("aberration", "46");

		Button btnWord047 = createButton ("babirusa", "47");
		Button btnWord048 = createButton ("babushka", "48");
		Button btnWord049 = createButton ("babe", "49");
		Button btnWord050 = createButton ("babelicious", "50");
		Button btnWord051 = createButton ("babesiasis", "51");
		Button btnWord052 = createButton ("babesiosis", "52");
		Button btnWord053 = createButton ("babies", "53");
		Button btnWord054 = createButton ("baboon", "54");
		Button btnWord055 = createButton ("babble", "55");
		Button btnWord056 = createButton ("babbler", "56");
		Button btnWord057 = createButton ("babbling", "57");
		Button btnWord058 = createButton ("babel", "58");
		Button btnWord059 = createButton ("babiche", "59");
		Button btnWord060 = createButton ("babied", "60");
		Button btnWord061 = createButton ("babouche", "61");
		Button btnWord062 = createButton ("babul", "62");
		Button btnWord063 = createButton ("baby", "63");
		Button btnWord064 = createButton ("babyboomer", "64");
		Button btnWord065 = createButton ("babyboopmers", "65");
		Button btnWord066 = createButton ("babycino", "66");
		Button btnWord067 = createButton ("babycorn", "67");
		Button btnWord068 = createButton ("babycorns", "68");
		Button btnWord069 = createButton ("babydaddies", "69");
		Button btnWord070 = createButton ("babydaddy", "70");
		Button btnWord071 = createButton ("babydoll", "71");
		Button btnWord072 = createButton ("babyface", "72");
		Button btnWord073 = createButton ("babyfaced", "73");
		Button btnWord074 = createButton ("babyfaces", "74");
		Button btnWord075 = createButton ("babyfather", "75");
		Button btnWord076 = createButton ("babyfathers", "76");
		Button btnWord077 = createButton ("babyish", "77");
		Button btnWord078 = createButton ("babymoon", "78");
		Button btnWord079 = createButton ("babymother", "79");
		Button btnWord080 = createButton ("babysat", "80");
		Button btnWord081 = createButton ("babysit", "81");
		Button btnWord082 = createButton ("babysitter", "82");
		Button btnWord083 = createButton ("babytalk", "83");
		Button btnWord084 = createButton ("babywalker", "84");
		Button btnWord085 = createButton ("babywearing", "85");
		Button btnWord086 = createButton ("babywipes", "86");
		Button btnWord087 = createButton ("bacalao", "87");
		Button btnWord088 = createButton ("baccarat", "88");
		Button btnWord089 = createButton ("bacchanalian", "89");
		Button btnWord090 = createButton ("bachelor", "90");
		Button btnWord091 = createButton ("bachelorette", "91");

		Button btnWord092 = createButton ("cab", "92");
		Button btnWord093 = createButton ("cabana", "93");
		Button btnWord094 = createButton ("cabbie", "94");
		Button btnWord095 = createButton ("cabildo", "95");
		Button btnWord096 = createButton ("cabin", "96");

		Button btnWord097 = createButton ("dab", "97");
		Button btnWord098 = createButton ("dabber", "98");
		Button btnWord099 = createButton ("dabberlocks", "99");
		Button btnWord100 = createButton ("dabble", "100");
		Button btnWord101 = createButton ("dabbling", "101");

		Button btnWord102 = createButton ("each", "102");
		Button btnWord103 = createButton ("eager", "103");
		Button btnWord104 = createButton ("eagerly", "104");
		Button btnWord105 = createButton ("eagle", "105");
		Button btnWord106 = createButton ("eagleeyed", "106");

		/**********
		 * Create a new button, set up its word, and set up the value 
		 * 
		 * @param buttonText	The text that defines the word
		 * @param txtIndex		The text that defines the word's index
		 * @return
		 */
		private Button createButton (String buttonText, String txtIndex) {
			Button button = new Button(buttonText);
			button.setOnAction(eve->{btnSelectAWord.setText(button.getText());
			outputSelectedWord.setText(button.getText()); 
			outputSelectedWordIndex.setText(txtIndex); stagePopUp.close();});
			return button;
		}

	/*****
	 * MainScreen constructor - Establish the MainScreen as a window in the middle
	 * of the computer screen, complete with all of the various UI elements, and
	 * make it visible to the user as an interactive application window.
	 */

	private void initializeTheUserInterface() {
		// The following are the various User Interface (UI) elements on the MainScreen
		setupLabelUI(labelSelectAWord, "Arial", 18, 320, Pos.BASELINE_LEFT, 90, 80);

		setupButtonUI(btnSelectAWord, "Arial", 14, 70, Pos.BASELINE_LEFT, 200, 100);
		btnSelectAWord.setOnAction(e->{stagePopUp.showAndWait();});
		btnSelectAWord.setMinWidth(320);		

		setupLabelUI(labelSelectedWord, "Arial", 14, 320, Pos.BASELINE_LEFT, 190, 160);

		setupTextUI(outputSelectedWord, "Dialog", 14, 320, Pos.BASELINE_LEFT, 200, 180, false);

		setupLabelUI(labelSelectedWordIndex, "Arial", 14, 320, Pos.BASELINE_LEFT, 190, 220);

		setupTextUI(outputSelectedWordIndex, "Dialog", 14, 320, Pos.BASELINE_LEFT, 200, 240, false);

		setupButtonUI(buttonQuit, "Dialog", 14, 70, Pos.BASELINE_LEFT, 500, 430);
		buttonQuit.setText("  Quit  ");
		buttonQuit.setOnAction((event) -> doQuit());
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


	/**
	 * Graphical User Interface (GUI) initialization. This sets the font and
	 * the location for each graphical element in the window, links in the
	 * action listeners, and adds all of them into the JFrame for display.
	 *
	 */
	public void start(Stage stageMain) {
		initializeTheUserInterface();				// Define all the GUI elements

		// The following establish the arrays of words, grouped by the first letter
		arrayA = FXCollections.observableArrayList(
				btnWord000, btnWord001, btnWord002, btnWord003, btnWord004,
				btnWord005, btnWord006, btnWord007, btnWord008, btnWord009,
				btnWord010, btnWord011, btnWord012, btnWord013, btnWord014,
				btnWord015, btnWord016, btnWord017, btnWord018, btnWord019,
				btnWord020, btnWord021, btnWord022, btnWord023, btnWord024,
				btnWord025, btnWord026, btnWord027, btnWord028, btnWord029,
				btnWord030, btnWord031, btnWord032, btnWord033, btnWord034,
				btnWord035, btnWord036, btnWord037, btnWord038, btnWord039,
				btnWord040, btnWord041, btnWord042, btnWord043, btnWord044, 
				btnWord045, btnWord046);

		arrayB = FXCollections.observableArrayList(
				btnWord047, btnWord048, btnWord049, 
				btnWord050, btnWord051, btnWord052, btnWord053, btnWord054, 
				btnWord055, btnWord056, btnWord057, btnWord058, btnWord059,
				btnWord060, btnWord061, btnWord062, btnWord063, btnWord064, 
				btnWord065, btnWord066, btnWord067, btnWord068, btnWord069, 
				btnWord070, btnWord071, btnWord072, btnWord073, btnWord074, 
				btnWord075, btnWord076, btnWord077, btnWord078, btnWord079, 
				btnWord080, btnWord081, btnWord082, btnWord083, btnWord084, 
				btnWord085, btnWord086, btnWord087, btnWord088, btnWord089, 
				btnWord090, btnWord091); 

		arrayC = FXCollections.observableArrayList(
				btnWord092, btnWord093, btnWord094, btnWord095, btnWord096);

		arrayD = FXCollections.observableArrayList(
				btnWord097, btnWord098, btnWord099, btnWord100, btnWord101);

		arrayE = FXCollections.observableArrayList(
				btnWord102, btnWord103, btnWord104, btnWord105, btnWord106); 

		rootMain = new Group();			// Create a group to hold the window UI elements
		rootPopUp = new Group();

		// This objects establish the five accordion panes
		t1 = new TitledPane("a", new ListView<>(arrayA));
		t2 = new TitledPane("b", new ListView<>(arrayB));
		t3 = new TitledPane("c", new ListView<>(arrayC));
		t4 = new TitledPane("d", new ListView<>(arrayD));
		t5 = new TitledPane("e", new ListView<>(arrayE));

		// This method calls establishes the accordion panes and the width and height
		Accordion accordion = new Accordion();
		accordion.getPanes().addAll(t1,t2,t3,t4,t5);
		accordion.setMinWidth(300);
		accordion.setMaxHeight(400);

		rootMain.getChildren().addAll(labelSelectAWord, btnSelectAWord, labelSelectedWord, 
				outputSelectedWord, labelSelectedWordIndex, outputSelectedWordIndex, 
				buttonQuit);
		rootPopUp.getChildren().addAll(accordion);

		sceneMain = new Scene(rootMain, WINDOW_WIDTH, 500);		// Create the scene with
		scenePopUp = new Scene(rootPopUp, 300, 400);

		stagePopUp = new Stage();
		stagePopUp.setScene(scenePopUp);
		stagePopUp.setTitle("Select a Word");

		stageMain.setTitle("Lynn Robert Carter");				// Label the stage (a window)
		stageMain.setScene(sceneMain);							// Set the scene on the stage
		stageMain.show();										// Show the stage to the user
	}

	/**
	 * Code to handle the "Quit" button.
	 * @param e ActionEvent
	 */
	private void doQuit() {
		System.exit(0);
	}

	/**********
	 * This mainline launches the JavaFX application.
	 * 
	 * @param args	The args are ignored
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
