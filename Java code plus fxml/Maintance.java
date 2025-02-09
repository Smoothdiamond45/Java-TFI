package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class Maintance {
    // Static fields to store messages across scenes
    public static String damageMessage = "";
    public static String maintenanceMessage = "";

    @FXML
    Label label1;
    @FXML
    Label label6;

    // Method to update labels when Maintance scene loads
    public void initialize() {
        if (!damageMessage.isEmpty()) {
        	label1.setText("Damage: " + damageMessage);
        }
        if (!maintenanceMessage.isEmpty()) {
        	label6.setText("Maintenance: " + maintenanceMessage);
        }
    }

    // Method to clear issues and show alert
    @FXML
    public void fixIssues() {
        damageMessage = "";
        maintenanceMessage = "";
        label1.setText("");
        label6.setText("");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Issues Fixed");
        alert.setHeaderText(null);
        alert.setContentText("All reported issues have been resolved.");
        alert.showAndWait();
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