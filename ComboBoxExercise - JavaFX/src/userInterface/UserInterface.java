package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface test bed to develop and test ComboBox code.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2019-02-13 The JavaFX-based GUI for the implementation of a test bed
 * 
 */
public class UserInterface {
	
	// Array of Countries from the Internet
	String [] arrayCountries = {"- no country selected - ", 
			"Abkhazia", "Afghanistan", "Albania", "Algeria", "Andorra", 
			"Angola", "Antigua and Barbuda", "Argentina", "Armenia", "Australia", 
			"Austria", "Azerbaijan", "Bahamas, The", "Bahrain", "Bangladesh", 
			"Barbados", "Belarus", "Belgium", "Belize", "Benin", 
			"Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", 
			"Brunei", "Bulgaria", "Burkina Faso", "Burma", "Burundi", 
			"Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Republic", 
			"Chad", "Chile", "China", "China (Taiwan), Republic of ", "Colombia", 
			"Comoros", "Congo, Democratic Republic of the", "Congo, Republic of the",  
			"Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
			"Denmark","Djibouti", "Dominica", "Dominican Republic", "East Timor", 
			"Ecuador","Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
			"Estonia", "Ethiopia", "Fiji", "France", "Gabon",
			"Gambia, The", "Georgia", "Germany", "Ghana", "Greece",
			"Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
			"Haiti", "Honduras", "Hungary", "Iceland", "India",
			"Indonesia", "Iran", "Iraq", "Ireland", "Israel",
			"Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan",
			"Kazakhstan", "Kenya", "Kiribati", "Korea, North", "Korea, South",
			"Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia",
			"Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein",
			"Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi",
			"Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", 
			"Mauritius", "Mexico", "Micronesia, Federated States of", "Moldova",
			"Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique",
			"Myanmar (Burma)", "Nagorno-Karabakh ", "Namibia", "Nauru", "Nepal",
			"Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Palau", 
			"Niue", "Northern Cyprus ", "Norway", "Oman", "Pakistan", "Palestine", 
			"Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", 
			"Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Sahrawi Arab Democratic Republic",
			"Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", 
			"Samoa", "San Marino", "São Tomé and Príncipe",
			"Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone",
			"Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
			"Somaliland", "South Ossetia", "South Sudan", "Spain", "Sri Lanka",
			"Sudan", "Sudan, South", "Suriname", "Swaziland", "Sweden",
			"Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand",
			"Timor-Leste", "Togo", "Tonga", "Transnistria", "Trinidad and Tobago",
			"Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda",
			"Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay",
			"Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam",
			"Yemen", "Zambia", "Zimbabwe"};
	
	// The following are the various User Interface (UI) elements on the MainScreen

	// UI Section asking the user to select a country from a ComboBox
	Label labelComboBoxExperiment = new Label("ComboBox Experiment");

	// UI Section asking the user to select a country from a ComboBox
	Label labelSelectACountry = new Label("Select a country:");
	
	ComboBox <String> comboboxSelectCountry = new ComboBox <String>();
	ActionListener selectCountryActionListner = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			if (!boolUpdatingComboBox) selectCountryItem();
		}
	};
	
	// UI label showing the user the text of the currently selected item
	Label labelSelectedCountry = new Label("Selected country:");
	TextField outputSelectedCountry = new TextField("- no country selected -");
	
	// UI label showing the user the index of the currently selected item
	Label labelSelectedCountryIndex = new Label("Selected country list index:");
	TextField outputSelectedCountryIndex = new TextField("0");
	
	// Flag used to ignore ComboBox events when ComboBox objects are being updated
	boolean boolUpdatingComboBox = false;
	
	// The quit button that terminates the execution of this application
    Button buttonQuit = new Button();
	
	// The select list state variable keeps track of how many previously selected
	// items are at the top of the enhanced select list
	int selectListState = 0;
	
	// This string hold the first item string for doing a reset;
	String stringDefaultItem = "";
	
	// The following are the three most recent selections
	String stringSelectItem1 = "";
	int intFoundIndex1 = 0;
	String stringSelectItem2 = "";
	int intFoundIndex2 = 0;
	String stringSelectItem3 = "";
	int intFoundIndex3 = 0;
	
	// UI ready for the user flag
	boolean UserInterfaceIsReady = false;


	public UserInterface(Pane theRoot) {
        
        // Load up the ComboBox will all of the items for the select list
        loadComboBoxData(arrayCountries);
        setupLabelUI(labelComboBoxExperiment, "Dialog", 24, 800, Pos.CENTER, 0, 20);
        setupLabelUI(labelSelectACountry, "Dialog", 14, 210, Pos.BASELINE_LEFT, 70, 80);
        setupComboBoxUI(comboboxSelectCountry, "Dialog", 18, 220, 100, 100); 
        comboboxSelectCountry.getSelectionModel().selectedItemProperty()
        	.addListener((ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
        		selectCountryItem();
        	});

        setupLabelUI(labelSelectedCountry, "Dialog", 14, 210, Pos.BASELINE_LEFT, 170, 160);
        setupTextUI(outputSelectedCountry, "Dialog", 14, 320, Pos.BASELINE_LEFT,200, 180, false);
        
        setupLabelUI(labelSelectedCountryIndex, "Dialog", 14, 210, Pos.BASELINE_LEFT, 170, 220);
        setupTextUI(outputSelectedCountryIndex, "Dialog", 14, 320, Pos.BASELINE_LEFT, 200, 240, false);
        
        setupButtonUI(buttonQuit, "Dialog", 18, 100, Pos.CENTER, 630, 310);
		buttonQuit.setText("  Quit  ");
		buttonQuit.setOnAction((event) -> { doQuit(); });
		
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(labelComboBoxExperiment, labelSelectACountry, 
				comboboxSelectCountry, labelSelectedCountry, outputSelectedCountry, 
				labelSelectedCountryIndex, outputSelectedCountryIndex, buttonQuit);

		UserInterfaceIsReady = true;
		
        // At this point, the program waits for the user to do something and
        // the code reacts to that user action.
	}

	/**********
	 * Private local method to initialize the standard fields for a label
	 * 
	 * @param l		The Label object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the label
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}

	/**********
	 * Private local method to initialize the standard fields for a text field
	 * 
	 * @param t		The TextField object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the text field
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 * @param e		true if the field should be editable, else false
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
	 * Private local method to initialize the standard fields for a ComboBox
	 * 
	 * @param c		The ComboBox object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the ComboBox
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupComboBoxUI(ComboBox <String> c, String ff, double f, double w, double x, double y){
		c.setStyle("-fx-font: " + f + " " + ff + ";");
		c.setMinWidth(w);
		c.setLayoutX(x);
		c.setLayoutY(y);
	}

    /**
     * Code to handle the "Quit" button.
     * 
     * @param e ActionEvent
     */
    private void doQuit() {
        System.exit(0);
    }
    
    /**
     * Code to handle the "SelectCountry" ComboBox selection event.  When
     * the user selects an item, this code is performed.  (This method is
     * not called when the boolBuildingComboBox variable is true.)
     * 
     * @param e		The actual action event object - it is ignored in this method
     */
    private void selectCountryItem() {
    	// Do not perform this code if it is being performed! (Do not allow recursion)
    	if (boolUpdatingComboBox) return;
   	
    	// There are fours cases depending on how many previously selected list items
    	// have been selected
		
		boolUpdatingComboBox=true;
		String theSelectedItem = (String) comboboxSelectCountry.getValue();
		int theSelectedIndex = comboboxSelectCountry.getSelectionModel().getSelectedIndex();
    	switch (selectListState) {
    	case 1:
    		switch (theSelectedIndex) {
    		case 0:
    			outputSelectedCountry.setText(stringSelectItem1);
    			outputSelectedCountryIndex.setText(intFoundIndex1 + "");
    			boolUpdatingComboBox=true;
    			comboboxSelectCountry.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    		case 2:
    			outputSelectedCountry.setText(stringDefaultItem);
    			outputSelectedCountryIndex.setText("0");
    			selectListState = 0;
    			stringSelectItem1 = "";
    			intFoundIndex1 = 0;
    			loadComboBoxData(arrayCountries);
    			break;
    		default:
    			outputSelectedCountry.setText(theSelectedItem);
    			outputSelectedCountryIndex.setText((theSelectedIndex - 2) + "");
    			stringSelectItem2 = stringSelectItem1;
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex2 = intFoundIndex1;
    			intFoundIndex1 = theSelectedIndex - 2;
    			selectListState = 2;
    			loadComboBoxData(arrayCountries);
    			break;
    		}
    		break;
    		
    	case 2:
    		switch (theSelectedIndex) {
    		case 0:
    			outputSelectedCountry.setText(stringSelectItem1);
    			outputSelectedCountryIndex.setText(intFoundIndex1 + "");
    			boolUpdatingComboBox=true;
    			comboboxSelectCountry.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    			outputSelectedCountry.setText(stringSelectItem2);
    			outputSelectedCountryIndex.setText(intFoundIndex2 + "");
    			String stringTemp = stringSelectItem1;
    			stringSelectItem1 = stringSelectItem2;
    			stringSelectItem2 = stringTemp;
    			int intTemp = intFoundIndex1;
    			intFoundIndex1 = intFoundIndex2;
    			intFoundIndex2 = intTemp;
    			loadComboBoxData(arrayCountries);
    			break;
    		case 2:
    		case 3:
    			outputSelectedCountry.setText(stringDefaultItem);
    			outputSelectedCountryIndex.setText("0");
    			selectListState = 0;
    			stringSelectItem1 = "";
    			stringSelectItem2 = "";
    			intFoundIndex1 = 0;
    			intFoundIndex2 = 0;
    			loadComboBoxData(arrayCountries);
    			break;
    		default:
    			outputSelectedCountry.setText(theSelectedItem);
    			outputSelectedCountryIndex.setText((theSelectedIndex - 3) + "");
    			stringSelectItem3 = stringSelectItem2;
    			stringSelectItem2 = stringSelectItem1;
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex3 = intFoundIndex2;
    			intFoundIndex2 = intFoundIndex1;
    			intFoundIndex1 = theSelectedIndex - 3;
    			selectListState = 3;
    			loadComboBoxData(arrayCountries);
    			break;
    		}
    		break;
    		
    	case 3:
    		switch (theSelectedIndex) {
    		case 0:
    			outputSelectedCountry.setText(stringSelectItem1);
    			outputSelectedCountryIndex.setText(intFoundIndex1 + "");
    			boolUpdatingComboBox=true;
    			comboboxSelectCountry.getSelectionModel().select(0);
    			boolUpdatingComboBox=false;
    			break;
    		case 1:
    			outputSelectedCountry.setText(stringSelectItem2);
    			outputSelectedCountryIndex.setText(intFoundIndex2 + "");
    			String stringTemp = stringSelectItem1;
    			stringSelectItem1 = stringSelectItem2;
    			stringSelectItem2 = stringTemp;
    			int intTemp = intFoundIndex1;
    			intFoundIndex1 = intFoundIndex2;
    			intFoundIndex2 = intTemp;
    			loadComboBoxData(arrayCountries);
    			break;
    		case 2:
    			outputSelectedCountry.setText(stringSelectItem3);
    			outputSelectedCountryIndex.setText(intFoundIndex3 + "");
    			stringTemp = stringSelectItem1;
    			stringSelectItem1 = stringSelectItem3;
    			stringSelectItem3 = stringSelectItem2;
    			stringSelectItem2 = stringTemp;
    			intTemp = intFoundIndex1;
    			intFoundIndex1 = intFoundIndex3;
    			intFoundIndex3 = intFoundIndex2;
    			intFoundIndex2 = intTemp;
    			loadComboBoxData(arrayCountries);
    			break;
    		case 3:
    		case 4:
    			outputSelectedCountry.setText(stringDefaultItem);
    			outputSelectedCountryIndex.setText("0");
    			selectListState = 0;
    			stringSelectItem1 = "";
    			stringSelectItem2 = "";
    			stringSelectItem3 = "";
    			intFoundIndex1 = 0;
    			intFoundIndex2 = 0;
    			intFoundIndex3 = 0;
    			loadComboBoxData(arrayCountries);
    			break;
    		default:
    			outputSelectedCountry.setText(theSelectedItem);
    			outputSelectedCountryIndex.setText((theSelectedIndex - 4) + "");
    			stringSelectItem3 = stringSelectItem2;
    			stringSelectItem2 = stringSelectItem1;
    			stringSelectItem1 = theSelectedItem;
    			intFoundIndex3 = intFoundIndex2;
    			intFoundIndex2 = intFoundIndex1;
    			intFoundIndex1 = theSelectedIndex - 4;
    			selectListState = 3;
    			loadComboBoxData(arrayCountries);
    			break;
    		}
    		break;
    		
    	default:
    		outputSelectedCountry.setText(theSelectedItem);
     		outputSelectedCountryIndex.setText(theSelectedIndex + "");
     		stringSelectItem1 = theSelectedItem;
     		intFoundIndex1 = theSelectedIndex;
			selectListState = 1;
			loadComboBoxData(arrayCountries);
    		break;
    	}
		boolUpdatingComboBox=false;
    }
    
	/**
	 * The action item selection lists in the ComboBoxes are dynamic. They can
	 * be changed by the program.
	 * 
	 * The buildingComboBox flag is used to signal the rest of this screen that
	 * a ComboBox is in the process of being updated. Changes to the ComboBox
	 * whether brought about by the user or by code, results in change events. 
	 * We do not want change events that come from defining any ComboBox select
	 * list via code to generate update events so we can treat all of those as
	 * coming from the user.
	 * 
	 * This routine assumes that the order of the action items in the array is
	 * precisely the correct order for display in the ComboBox. Sorting must be
	 * done elsewhere.
	 * 
	 * @param names
	 *            String[] - This is the array of names for the select list
	 * 
	 */
	private void loadComboBoxData(String[] names) {

		// Define a new list of countries.
		List<String> list = new ArrayList<String>();
				
		// Load up the select list with each of the items from the array
		if (names != null) { // If names is null, there are no action items
			
			// Based on the state of the select list, display the right number
			// of previously selected items on the top of the whole list
			switch (selectListState) {
			case 1:
				list.add(stringSelectItem1);
				list.add("----------");
				break;
			case 2:
				list.add(stringSelectItem1);
				list.add(stringSelectItem2);
				list.add("----------");
				break;
			case 3:
				list.add(stringSelectItem1);
				list.add(stringSelectItem2);
				list.add(stringSelectItem3);
				list.add("----------");
				break;
			default:
				break;
			}
			stringDefaultItem = names[0];
			for (int i = 0; i < names.length; i++) {
				list.add(names[i]);
			}
		}
		
		comboboxSelectCountry.setItems(FXCollections.observableArrayList(list));
		comboboxSelectCountry.getSelectionModel().select(0);
		
        if (UserInterfaceIsReady) {
            // Force the skin to scroll to the same place as the selection.  I have no idea why
        	// one would think that we should have to do this!  The following web page helped me
        	// to get this to work: https://bugs.openjdk.java.net/browse/JDK-8091560
            ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>)comboboxSelectCountry.getSkin();
            ((ListView<?>)skin.getPopupContent()).scrollTo(0);
        }
	}
}