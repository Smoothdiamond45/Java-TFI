package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

	public class Controller implements Initializable{
	
	@FXML
	private ChoiceBox<String> myChoiceBox;
	 
	private String[] county = {"Cork", "Dublin", "Kerry", "Antrim", "Armagh", "Carlow", "Cavan", "Clare", "Donegal", "Galway", "Kildare", "KilKenny", "Laois", "Leitrim","Limerick", "Mayo", "Other"};
	 
	@FXML
	private TextField loginTextField;
	@FXML
	private Button loginButton;
	@FXML
	private Button logoutButton;
	@FXML
	private AnchorPane scenePane;
	private static String CUSTOMER_CODE = "";
	private static String MAINTENANCE_CODE = "1111";
	private static String MANAGER_CODE = "2222";
	private double deposit = 150;
	private static double topup = 0;
	double bikecost = 0.0;
	
	@FXML
	public Label dateTimeLabel;
	@FXML
	public Label dateTimencLabel;
	@FXML
	private TextField nameFField;
	@FXML
	private TextField nameSField;
	@FXML
	private TextField phoneNumField;
	@FXML
	private TextField dobField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField cardNumberField;
	@FXML
 	private TextField expiryField;
	@FXML
	private TextField cardNameField;
	@FXML
	private TextField cvvField;
	@FXML
	private TextField pinField;
	@FXML
	private Label myLabel;
	@FXML
	private Label subLabel;
	@FXML
	private RadioButton maleRButton, femaleRbutton, BadAssATron;
	@FXML
	private Button switchtoRent;
	@FXML
	private RadioButton threedayRadio;
	@FXML
	private RadioButton annualdayButton;
	@FXML
	private ToggleGroup optionGroup;
	@FXML
	private Button switchtoReturn;
	@FXML
	private TextField topUpField;

	@FXML
private void checkPayment() {
		 cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
	            String digitsOnly = newValue.replaceAll("[^\\d]", "");
	            StringBuilder formatted = new StringBuilder();
	            for (int i = 0; i < digitsOnly.length(); i++) {
	                if (i > 0 && i % 4 == 0) {
	                    formatted.append("/");
	                }
	                formatted.append(digitsOnly.charAt(i));
	            }
	            cardNumberField.setText(formatted.toString());
	            cardNumberField.positionCaret(formatted.length());
	        });
	}
	
	@FXML
	private void checkdob() {
		 dobField.textProperty().addListener((observable, oldValue, newValue) -> {
	            String digitsOnly = newValue.replaceAll("[^\\d]", "");
	            if (digitsOnly.length() > 8) digitsOnly = digitsOnly.substring(0, 8);
	            StringBuilder formatted = new StringBuilder(digitsOnly);
	            if (digitsOnly.length() > 2) formatted.insert(2, "/");
	            if (digitsOnly.length() > 4) formatted.insert(5, "/");
	            dobField.setText(formatted.toString());
	            dobField.positionCaret(formatted.length());
	        });
		
	}
	
	@FXML
	private void expirycheck() {
		 expiryField.textProperty().addListener((observable, oldValue, newValue) -> {
	            String digitsOnly = newValue.replaceAll("[^\\d]", "");
	            if (digitsOnly.length() > 4) digitsOnly = digitsOnly.substring(0, 4);
	            StringBuilder formatted = new StringBuilder(digitsOnly);
	            if (digitsOnly.length() > 2) formatted.insert(2, "/");
	            expiryField.setText(formatted.toString());
	            expiryField.positionCaret(formatted.length());
	        });
		 
	}
	
	@FXML
 	private void verifyPin() {
	    String enteredPin = loginTextField.getText();

	    // Validate PIN length and format
	    if (enteredPin.length() != 4) {
	        showAlert("Invalid PIN", "Please enter a 4-digit PIN");
	        return;
	    }

	    // Check if PIN matches
	    if (enteredPin.equals(CUSTOMER_CODE)) {
	        try {
	            // Load the new scene
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            
	            // Get the current stage
	            Stage stage = (Stage) loginTextField.getScene().getWindow();
	            stage.setScene(scene);
	        } catch (IOException e) {
	            e.printStackTrace();
	            showAlert("Error", "Could not load the next scene");
	        }
	    } else if (enteredPin.equals(MAINTENANCE_CODE)) {
	    	  try {
		            // Load the new scene
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Maintance.fxml"));
		            Parent root = loader.load();
		            Scene scene = new Scene(root);
		            
		            // Get the current stage
		            Stage stage = (Stage) loginTextField.getScene().getWindow();
		            stage.setScene(scene);
		        } catch (IOException e) {
		            e.printStackTrace();
		            showAlert("Error", "Could not load the next scene");
		        }
	        showAlert("Success", "Maintenance Login Successful");
	    } else if (enteredPin.equals(MANAGER_CODE)) {
	    	  try {
		            // Load the new scene
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager.fxml"));
		            Parent root = loader.load();
		            Scene scene = new Scene(root);
		            
		            // Get the current stage
		            Stage stage = (Stage) loginTextField.getScene().getWindow();
		            stage.setScene(scene);
		        } catch (IOException e) {
		            e.printStackTrace();
		            showAlert("Error", "Could not load the next scene");
		        }
	        showAlert("Success", "Manager Login Successful");
	    } else {
	        showAlert("Error", "Incorrect PIN");
	        loginTextField.clear();
	    }
	}

	private void showAlert(String title, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	
	@FXML
	public void pass(ActionEvent event) {
	    // Optional: Additional logging or processing
	    if (threedayRadio.isSelected()) {
	       
	    }
	    if (annualdayButton.isSelected()) {
	       
	    }
	}
	
	Stage stage;
	public void logout(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Your about to logout!");
		alert.setContentText("Are you sure you want to exit?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
		stage = (Stage) scenePane.getScene().getWindow();
		stage.close();
	}}
	
	public void gender(ActionEvent event) {

	    ToggleGroup group = new ToggleGroup();
	    if(maleRButton.isSelected()) {
	    	myLabel.setText(maleRButton.getText());
			maleRButton.setUserData("male");
			maleRButton.setSelected(true);
		}
		
		 if(femaleRbutton.isSelected()) {
			 myLabel.setText(femaleRbutton.getText());
			 femaleRbutton.setUserData("Female");
		        femaleRbutton.setSelected(true);
		}
		 /*else if(BadAssATron.isSelected()) {
				myLabel.setText(BadAssATron.getText());
				BadAssATron.setUserData("BadAssATron");
				BadAssATron.setSelected(true);
			}*/
      

		 maleRButton.setToggleGroup(group);
		 femaleRbutton.setToggleGroup(group);
		// BadAssATron.setToggleGroup(group);
      
        group.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
            if (group.getSelectedToggle() != null) {
    
           }
        });	
	}
	
	public void submit(ActionEvent event) {
		
		if (bikeTypeGroup == null || bikeTypeGroup.getSelectedToggle() == null) {
            showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select a bike pass type");
            return;	
		}
	        if (nameFField.getText().isEmpty() || nameSField.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill in your name correctly. Only letters are allowed.");
	            return;
	        }

	     // Enhanced DOB validation
	        String dobText = dobField.getText();
	        if (!isValidDateOfBirth(dobText)) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", 
	                "Please enter a valid date of birth. You must be at least 14 years old.");
	            return;
	        }

	        if (phoneNumField.getText().isEmpty() || emailField.getText().isEmpty() || !emailField.getText().contains("@")) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Please provide a valid phone number and email address. Email must include '@'.");
	            return;
	        }

	        String phoneNumber = phoneNumField.getText();

	        // Validate if the phone number is numeric
	        if (!phoneNumber.matches("\\d+")) { // Checks if the input contains only digits
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Phone number must contain only numbers.");
	            return;
	        }

	        // Optionally, check phone number length (e.g., must be 10 digits)
	        if (phoneNumber.length() != 10) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Phone number must be exactly 10 digits long.");
	            return;
	        }
	        if (cardNameField.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill in your name correctly. Only letters are allowed.");
	            return;
	        }

	        if (cardNumberField.getText().isEmpty() || cvvField.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Please provide a valid credit card number and or cvv.");
	            return;
	        }

	        String creditCard = cardNumberField.getText();
	        String creditCVV = cvvField.getText();

	     // Validate if the phone number contains only digits or '/'
	        if (!creditCard.matches("[\\d/]+")) { // Allows digits and '/' characters
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Credit Card number must contain only numbers or '/'.");
	            return;
	        }
	        // Optionally, check phone number length (e.g., must be 10 digits)
	        if (creditCard.length() != 19) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Credit Card must be 16 digits long.");
	            return;
	        }
	        
	        if (!creditCVV.matches("\\d+")) { // Checks if the input contains only digits
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Cvv number must contain only numbers.");
	            return;
	        }

	        // Optionally, check phone number length (e.g., must be 10 digits)
	        if (creditCVV.length() != 3) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Cvv number must be 3 digits long.");
	            return;
	        }
	     	CUSTOMER_CODE = pinField.getText();

	        // Validate if the phone number is numeric
	        if (!CUSTOMER_CODE.matches("\\d+")) { // Checks if the input contains only digits
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Pin number must contain only numbers.");
	            return;
	        }

	        // Optionally, check phone number length (e.g., must be 10 digits)
	        if (CUSTOMER_CODE.length() != 4) {
	            showAlert(Alert.AlertType.ERROR, "Input Error", "Pin number must be 4 digits long.");
	            return;}
	            
	        double topupAmount = 0.0;
	        if (topUpField != null && !topUpField.getText().trim().isEmpty()) {
	            try {
	                topupAmount = Double.parseDouble(topUpField.getText().trim());
	                
	                // Additional validation if needed
	                if (topupAmount < 0) {
	                    showAlert(Alert.AlertType.ERROR, "Input Error", "Top-up amount cannot be negative");
	                    return;
	                }
	                
	                // Set the static topup variable
	                RentBikeController.topup = String.valueOf(topupAmount);
	            } catch (NumberFormatException e) {
	                showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid top-up amount");
	                return;
	            }
	        }


	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Payment Confirmation");
	        alert.setHeaderText(null);
	        alert.setContentText("When renting a bike a deposit of €150 will be placed on hold\n\n"
	            + String.format("Top-up Amount: €%.2f\n", topupAmount)
	            + String.format("Deposit Amount: €%.2f\n", deposit) 
	            + "Cork City Bikes Terms and Conditions:\n"
	            + "1. Users must be 14 years or older.\n"
	            + "2. Bicycles must be returned within the rental period.\n"
	            + "3. Any damage to bicycles will incur repair costs.\n"
	            + "4. Users are responsible for their own safety while using the bikes.\n"
	            + "5. Cork City Bikes is not liable for personal injuries.\n\n"
	            + "By renting a bike, you agree to abide by these terms.\n");

	        alert.showAndWait(); // Add this line to actually display the alert
	}
	       
	@FXML
	private ToggleGroup bikeTypeGroup;  // This should match the fx:id in FXML if you use one

	@FXML
	private int calculateBikeCost() {
	    
	    if (bikeTypeGroup == null) {
	        showAlert(Alert.AlertType.WARNING, "Error", "Bike type group initialization failed");
	        return 0;
	    }

	    Toggle selectedToggle = bikeTypeGroup.getSelectedToggle();
	    if (selectedToggle == null) {
	        showAlert(Alert.AlertType.WARNING, "Selection Error", "Please select a pass type");
	        return 0;
	    }

	    Object userData = selectedToggle.getUserData();
	    if (userData instanceof Number) {
	        return ((Number) userData).intValue();
	    }

	    showAlert(Alert.AlertType.WARNING, "Error", "Invalid pass type selection");
	    return 0;
	}
	@FXML
	public double calculateTotalCost() {
	    int bikeCost = calculateBikeCost();
	    return deposit + topup + bikeCost;
	   
	}
	
	public void showAlert(Alert.AlertType alertType, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null); // No header text
	        alert.setContentText(message);
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
	
	public void switchtoNewCustomer(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
	}
	
	public void switchtoLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
	}
	
	public void switchtoRent(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RentScene.fxml"));
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
		
	}
	
	public void switchtoReturn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ReturnScene.fxml"));
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
		
	}
	private boolean isValidDateOfBirth(String dob) {
	    // Check if the date is in the correct format (DD/MM/YYYY)
	    if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
	        return false;
	    }

	    try {
	        // Parse the date
	        String[] parts = dob.split("/");
	        int day = Integer.parseInt(parts[0]);
	        int month = Integer.parseInt(parts[1]);
	        int year = Integer.parseInt(parts[2]);

	        // Validate month range
	        if (month < 1 || month > 12) {
	            return false;
	        }

	        // Validate day range based on month
	        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	        if (day < 1 || day > daysInMonth[month - 1]) {
	            return false;
	        }

	        // Age validation (e.g., must be at least 14 years old)
	        LocalDate birthDate = LocalDate.of(year, month, day);
	        LocalDate currentDate = LocalDate.now();
	        Period age = Period.between(birthDate, currentDate);

	        return age.getYears() >= 14;

	    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
	        return false;
	    }
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bikeTypeGroup = new ToggleGroup();
	    
	    if (threedayRadio != null) {
	        threedayRadio.setToggleGroup(bikeTypeGroup);
	        threedayRadio.setUserData(3);
	    }
	    
	    if (annualdayButton != null) {
	        annualdayButton.setToggleGroup(bikeTypeGroup);
	        annualdayButton.setUserData(10);
	    }
		if (myChoiceBox != null) {
	        myChoiceBox.getItems().addAll(county);
	    }
		
		 
        if (dateTimeLabel == null) {
            return;
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            
            String formattedTime = currentTime.format(formatter);
           
            
            dateTimeLabel.setText(formattedTime);
            
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
	}}
