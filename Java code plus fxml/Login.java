package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login {
	public void start(Stage Stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
			Scene scene = new Scene(root,600,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			String css = this.getClass().getResource("application.css").toExternalForm();
			scene.getStylesheets().add(css);
			Stage.setScene(scene);
			Stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}