package application;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RentBikeController {
    // Static list to maintain bike stations across scenes
    private static List<BikeStation> stations = new ArrayList<>();
    public static String topup = "";
    public static String rentStation = "";
    
    // Bike Station class
    class BikeStation {
        private String name;
        private int totalCapacity;
        private int availableBikes;

        public BikeStation(String name, int totalCapacity) {
            this.name = name;
            this.totalCapacity = totalCapacity;
            this.availableBikes = totalCapacity;
        }

        // Getters
        public String getName() { return name; }
        public int getAvailableBikes() { return availableBikes; }
        public int getTotalCapacity() { return totalCapacity; }
    }

    // Rental duration and pricing
    private static final Map<String, Double> RENTAL_RATES = new LinkedHashMap<>();
    
    @FXML
    private ChoiceBox<String> stationChoiceBox;

    @FXML
    private ChoiceBox<String> rentalDurationChoiceBox;

    @FXML
    private Label availableBikesLabel;

    @FXML
    private TextField bikeRentQuantityField;

    @FXML
    private Label totalCostLabel;

    @FXML
    public void initialize() {
        // Initialize stations if not already done
        if (stations.isEmpty()) {
            stations.add(new BikeStation("Ucc", 10));
            stations.add(new BikeStation("Morrisons island", 5));
            stations.add(new BikeStation("Kent Station", 8));
            stations.add(new BikeStation("Grand Parade", 7));
        }

        // Populate station choice box
        stationChoiceBox.getItems().clear();
        for (BikeStation station : stations) {
            stationChoiceBox.getItems().add(station.getName());
        }

        // Initialize rental rates
        RENTAL_RATES.put("1 Hour - €0.50", 0.5);
        RENTAL_RATES.put("2 Hours - €1.50", 1.5);
        RENTAL_RATES.put("3 Hours - €3.50", 3.50);
        RENTAL_RATES.put("4 Hours - €6.50", 6.50);

        // Populate rental duration choice box
        rentalDurationChoiceBox.getItems().addAll(RENTAL_RATES.keySet());

        // Station selection listener
        stationChoiceBox.setOnAction(event -> updateAvailableBikesDisplay());

        // Bike quantity listener to update total cost
        bikeRentQuantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateTotalCost();
        });

        // Rental duration listener to update total cost
        rentalDurationChoiceBox.setOnAction(event -> {
            updateTotalCost();
        });
    }

    // Update available bikes display
    private void updateAvailableBikesDisplay() {
        String selectedStation = stationChoiceBox.getValue();
        BikeStation station = findStationByName(selectedStation);
        
        if (station != null) {
            availableBikesLabel.setText("Available Bikes: " + station.getAvailableBikes());
        }
    }

    private void updateTotalCost() {
        try {
            // Validate inputs
            int bikeQuantity = 0;
            if (!bikeRentQuantityField.getText().isEmpty()) {
                bikeQuantity = Integer.parseInt(bikeRentQuantityField.getText());
                if (bikeQuantity < 0) {
                    totalCostLabel.setText("Quantity must be non-negative");
                    return;
                }
            }

            String selectedDuration = rentalDurationChoiceBox.getValue();
            String selectedStation = stationChoiceBox.getValue();

            if (selectedStation == null || selectedDuration == null) {
                totalCostLabel.setText("Select station and duration");
                return;
            }

            BikeStation station = findStationByName(selectedStation);
            if (station == null || bikeQuantity > station.getAvailableBikes()) {
                totalCostLabel.setText("Invalid Bike Quantity");
                return;
            }

            // Safely handle topup amount
            double topupAmount = 0.0;
            try {
                topupAmount = topup != null && !topup.isEmpty() 
                    ? Double.parseDouble(topup) 
                    : 0.0;
            } catch (NumberFormatException e) {
                totalCostLabel.setText("Invalid Topup Amount");
                return;
            }

            double hourlyRate = RENTAL_RATES.get(selectedDuration);
            double totalCost = bikeQuantity * hourlyRate;

            // Calculate remaining amount 
            double remainingCost = totalCost - topupAmount;

            // Prepare the display message
            String costMessage;
            if (remainingCost > 0) {
                costMessage = String.format(
                    "Total Cost: €%.2f\n" +
                    "Top-up Applied: €%.2f\n" +
                    "Remaining to be Charged: €%.2f", 
                    totalCost, topupAmount, remainingCost
                );
            } else {
                costMessage = String.format(
                    "Total Cost: €%.2f\n" +
                    "Top-up Applied: €%.2f\n" +
                    "Excess Top-up: €%.2f (will be refunded)", 
                    totalCost, topupAmount, Math.abs(remainingCost)
                );
            }

            // Store total cost 
           // Manager.rentRev = String.valueOf(totalCost);

            totalCostLabel.setText(costMessage);

        } catch (NumberFormatException e) {
            totalCostLabel.setText("Invalid Numeric Input");
        }
    }
    // Rent bikes method
    @FXML
    private void rentBikes() {
        // Validate all inputs
        String selectedStation = stationChoiceBox.getValue();
        String selectedDuration = rentalDurationChoiceBox.getValue();
        
        if (selectedStation == null || selectedDuration == null) {
            showAlert("Error", "Please select a station and rental duration");
            return;
        }

        int bikesToRent;
        try {
            bikesToRent = Integer.parseInt(bikeRentQuantityField.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter a valid number of bikes");
            return;
        }

        BikeStation station = findStationByName(selectedStation);
        
        // Validate rental
        if (station == null || bikesToRent <= 0 || bikesToRent > station.getAvailableBikes()) {
            showAlert("Error", "Invalid number of bikes to rent");
            return;
        }

        try {
            Field availableBikesField = BikeStation.class.getDeclaredField("availableBikes");
            availableBikesField.setAccessible(true);
            
            // Reduce available bikes
            int currentAvailable = station.getAvailableBikes();
            availableBikesField.setInt(station, currentAvailable - bikesToRent);

            // Calculate costs
            double hourlyRate = RENTAL_RATES.get(selectedDuration);
            double totalCost = bikesToRent * hourlyRate;
            double topupAmount = topup != null && !topup.isEmpty() 
                ? Double.parseDouble(topup) 
                : 0.0;
            double remainingCost = totalCost - topupAmount;
            Manager.addEntry(
                    "N/A",  // You can modify this to capture actual account revenue if needed
                    String.valueOf(totalCost), 
                    selectedStation
                );
            String confirmationMessage;
            if (remainingCost > 0) {
                confirmationMessage = String.format(
                    "Rented %d bike(s) from %s\n" +
                    "Total Cost: €%.2f\n" +
                    "Top-up Applied: €%.2f\n" +
                    "Remaining to be Charged: €%.2f", 
                    bikesToRent, selectedStation, totalCost, topupAmount, remainingCost
                );
            } else {
                confirmationMessage = String.format(
                    "Rented %d bike(s) from %s\n" +
                    "Total Cost: €%.2f\n" +
                    "Top-up Applied: €%.2f\n" +
                    "Excess Top-up: €%.2f (will be refunded)", 
                    bikesToRent, selectedStation, totalCost, topupAmount, Math.abs(remainingCost)
                );
            }

            // Show confirmation
            showAlert("Rental Successful", confirmationMessage);

            // Update display
            updateAvailableBikesDisplay();
        } catch (Exception e) {
            showAlert("Error", "Could not process rental");
            e.printStackTrace();
        }
    }
    // Find station by name helper method
    private BikeStation findStationByName(String name) {
        return stations.stream()
            .filter(station -> station.getName().equals(name))
            .findFirst()
            .orElse(null);
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

    // Alert helper method
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}