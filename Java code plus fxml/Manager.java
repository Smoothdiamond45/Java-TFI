package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Manager {

	public static List<String> accountRevenues = new ArrayList<>();
    public static List<String> rentRevenues = new ArrayList<>();
    public static List<String> rentStations = new ArrayList<>();
	  
	    @FXML
	    Label labelRev1;
	    @FXML
	    Label labelRev2;
	    @FXML
	    Label labelAcc1;
	    @FXML
	    Label labelAcc2;
	    @FXML
	    Label labelStation1;
	    @FXML
	    Label labelStation2;
	// Method to update labels when Maintance scene loads
	    public void initialize() {
	        // Clear previous displays
	        clearLabels();
	        
	        // Display first entry if exists
	        if (!accountRevenues.isEmpty()) {
	            labelRev1.setText("Account Revenue 1: " + accountRevenues.get(0));
	        }
	        if (!rentRevenues.isEmpty()) {
	            labelAcc1.setText("Rental Revenue 1: €" + rentRevenues.get(0));
	        }
	        if (!rentStations.isEmpty()) {
	            labelStation1.setText("Rental Station 1: " + rentStations.get(0));
	        }
	        
	        // Display second entry if exists
	        if (accountRevenues.size() > 1) {
	            labelRev2.setText("Account Revenue 2: " + accountRevenues.get(1));
	        }
	        if (rentRevenues.size() > 1) {
	            labelAcc2.setText("Rental Revenue 2: €" + rentRevenues.get(1));
	        }
	        if (rentStations.size() > 1) {
	            labelStation2.setText("Rental Station 2: " + rentStations.get(1));
	        }
	    }
	    
	    private void clearLabels() {
	        labelRev1.setText("Account Revenue 1: ");
	        labelAcc1.setText("Rental Revenue 1: ");
	        labelStation1.setText("Rental Station 1: ");
	        labelRev2.setText("Account Revenue 2: ");
	        labelAcc2.setText("Rental Revenue 2: ");
	        labelStation2.setText("Rental Station 2: ");
	    }

	    // Optional method to add new entry
	    public static void addEntry(String accountRev, String rentRev, String rentStation) {
	        // If we already have 2 entries, remove the first one
	        if (accountRevenues.size() >= 2) {
	            accountRevenues.remove(0);
	            rentRevenues.remove(0);
	            rentStations.remove(0);
	        }
	        
	        // Add new entry
	        accountRevenues.add(accountRev);
	        rentRevenues.add(rentRev);
	        rentStations.add(rentStation);
	    }
	
	private Stage stage1;
	private Scene scene;

	public void switchtoMain(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
		
	}
}
