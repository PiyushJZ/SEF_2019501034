package baselineLongCalculator;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*******
* <p> Title: The Mainline Class. </p>
* 
* <p> Description: A JavaFX application: This controller class starts the JavaFX application,
* establishes the user interface stage, creates the user interface / business logic object,
* sets the JavaFX Scene and Stage, and makes this all visible to the user of the user can
* use the application.</p>
* 
* <p> Copyright: Lynn Robert Carter © 2019-12-03 </p>
* 
* @author Lynn Robert Carter
* 
* @version 4.00		2019-12-03 Baseline
* 
*/
public class Mainline extends Application {
	
	/**********************************************************************************************

	Class Attributes
	
	**********************************************************************************************/
	
	// Attributes used to establish the display and control panel within the window provided to us
	private static double WINDOW_HEIGHT;
	private static double WINDOW_WIDTH;
	
	/**********************************************************************************************

	Class Methods
	
	**********************************************************************************************/
	
	/**********
	 * This start method is called as soon as the JavaFX application is ready to set to have the 
	 * use interface established.
	 * 
	 * @param The primaryStage parameter is the link to the system's mechanism for managing the user
	 * interface.  This method sets a title into window establish for this application and is
	 * passed into the UserInterface class constructor to enable it to add additional GUI 
	 * elements to that window.
	 */
	public void start(Stage primaryStage) {
		
		// Determine the actual visual bounds for this display
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// Set the Stage boundaries so the window is centered but does not totally fill the screen
		WINDOW_WIDTH = primaryScreenBounds.getWidth() - primaryScreenBounds.getMinX() - 100;
		WINDOW_HEIGHT = primaryScreenBounds.getHeight() - primaryScreenBounds.getMinY() - 100;
		
		// We do not need a window larger than 600 pixels wide or 400 pixels tall
		if (WINDOW_WIDTH > 600) WINDOW_WIDTH = 600;
		if (WINDOW_HEIGHT > 400) WINDOW_HEIGHT = 400;

		// Set up the author's name as the title on the application's window
		primaryStage.setTitle("Lynn Robert Carter's");

		// Establish the root of the user interface
		Pane theRoot = new Pane();

		// Establish the user interface / business logic and sets up all of the major UI widgets
		new UserInterface(theRoot, WINDOW_WIDTH);

		// Place the scene on the stage
        primaryStage.setScene(new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT));		
        
        // And make the stage visible which allows the user to start interacting with it
        primaryStage.show();
    }
	
	
	/*******************************************************************************************************
	 * This is the method that launches the JavaFX application
	 * 
	 * @param args are the program parameters and they are not used by this program.
	 * 
	 */
    public static void main(String[] args) {
        Application.launch(args);
    }
}