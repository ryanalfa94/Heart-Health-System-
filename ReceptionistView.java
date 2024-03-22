package application;

//Ryan Alfa

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.util.Random;

public class ReceptionistView {

	
	    public void display() {
	        Stage window = new Stage();
	        window.setTitle("Patient Intake Form");

	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setVgap(10);
	        grid.setHgap(10);

	        // Form fields and save button.
	        TextField firstNameField = new TextField();
	        TextField lastNameField = new TextField();
	        TextField emailField = new TextField();
	        TextField phoneField = new TextField();
	        TextArea healthHistoryArea = new TextArea();
	        TextField insuranceIDField = new TextField();

	        Button btnSave = new Button("Save");
	        
	        // on click event handler
	        btnSave.setOnAction(e -> {
	            String firstName = firstNameField.getText();
	            String lastName = lastNameField.getText();
	            String email = emailField.getText();
	            String phone = phoneField.getText();
	            String healthHistory = healthHistoryArea.getText();
	            String insuranceID = insuranceIDField.getText();

	            // Generate a 5-digit patient ID
	            int patientId = generatePatientId();

	            // Save to a file
	            savePatientInfo(patientId, firstName, lastName, email, phone, healthHistory, insuranceID);

	            // Close the window after saving
	            window.close();
	        });

	        // Add fields and button to the grid
	        grid.add(new Label("First Name:"), 0, 0);
	        grid.add(firstNameField, 1, 0);
	        grid.add(new Label("Last Name:"), 0, 1);
	        grid.add(lastNameField, 1, 1);
	        grid.add(new Label("Email:"), 0, 2);
	        grid.add(emailField, 1, 2);
	        grid.add(new Label("Phone Number:"), 0, 3);
	        grid.add(phoneField, 1, 3);
	        grid.add(new Label("Health History:"), 0, 4);
	        grid.add(healthHistoryArea, 1, 4);
	        grid.add(new Label("Insurance ID:"), 0, 5);
	        grid.add(insuranceIDField, 1, 5);
	        grid.add(btnSave, 1, 6);

	        Scene scene = new Scene(grid, 600, 450);
	        window.setScene(scene);
	        window.show();
	    }
	    // generates a unique ID
	    private int generatePatientId() {
	        Random random = new Random();
	        return 10000 + random.nextInt(90000);
	    }
	    // saving the patient info after its been entered. 
	    private void savePatientInfo(int patientId, String firstName, String lastName, String email, String phone, String healthHistory, String insuranceID) {
	        String filename = patientId + "_PatientInfo.txt";
	        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
	            writer.println("Patient ID: " + patientId);
	            writer.println("First Name: " + firstName);
	            writer.println("Last Name: " + lastName);
	            writer.println("Email: " + email);
	            writer.println("Phone Number: " + phone);
	            writer.println("Health History: " + healthHistory);
	            writer.println("Insurance ID: " + insuranceID);
	            writer.close();

	            // Show confirmation message
	            showAlert(Alert.AlertType.INFORMATION, "Information Saved", "Patient information saved successfully.");
	        } catch (Exception e) {
	            // Show error message
	            showAlert(Alert.AlertType.ERROR, "Error Saving Information", "There was an error saving patient information.");
	            e.printStackTrace();
	        }
	    }
	    // shows alerts 
	    private void showAlert(Alert.AlertType alertType, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
}