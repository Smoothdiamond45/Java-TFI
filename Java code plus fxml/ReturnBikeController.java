package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReturnBikeController {
	//private Parent root;
	@FXML
	TextField damTextField;
	@FXML
	TextField mainTextField;
	public void submitmain(ActionEvent event) throws IOException {
        String maintenance = mainTextField.getText();
        Maintance.maintenanceMessage = maintenance;

        // Switch back to main scene
        Parent root = FXMLLoader.load(getClass().getResource("ReturnScene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    public void submitDam(ActionEvent event) throws IOException {
        String damage = damTextField.getText();
        Maintance.damageMessage = damage;

        // Switch back to main scene
        Parent root = FXMLLoader.load(getClass().getResource("ReturnScene.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
   
	@FXML
	private Button returnBike;
	@FXML
	private AnchorPane scenePane;
	@FXML
	Stage stage;
	public void returnBike() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Return bikes");
		alert.setHeaderText("Returning all bikes");
		alert.setContentText("Are you finished cycling around?");

		if(alert.showAndWait().get() == ButtonType.OK) {
		
	}}

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

