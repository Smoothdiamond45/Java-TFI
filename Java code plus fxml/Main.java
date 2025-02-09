package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage Stage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root, 600, 400);
	        String css = this.getClass().getResource("application.css").toExternalForm();
	        scene.getStylesheets().add(css);
	        Stage.setScene(scene);
	        Stage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

