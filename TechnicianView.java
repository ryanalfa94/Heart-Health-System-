package application;

//Ryan Alfa

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;

public class TechnicianView {
    public void display() {
        Stage window = new Stage();
        window.setTitle("CT Scan Technician View");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        // Form fields
        TextField patientIdField = new TextField();
        TextField totalAgatstonScoreField = new TextField();
        TextField lmScoreField = new TextField();
        TextField ladScoreField = new TextField();
        TextField lcxScoreField = new TextField();
        TextField rcaScoreField = new TextField();
        TextField pdaScoreField = new TextField();
        
        // save button and event handler.
        Button btnSave = new Button("Save");
        btnSave.setOnAction(e -> {
            String patientId = patientIdField.getText();
            String totalAgatstonScore = totalAgatstonScoreField.getText();
            String lmScore = lmScoreField.getText();
            String ladScore = ladScoreField.getText();
            String lcxScore = lcxScoreField.getText();
            String rcaScore = rcaScoreField.getText();
            String pdaScore = pdaScoreField.getText();
            
            // checking if all the fields are filled.
            if (!areFieldsValid(patientId, totalAgatstonScore, lmScore, ladScore, lcxScore, rcaScore, pdaScore)) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled out.");
                return; // Stop the method here if the fields are not valid.
            }

            // making sure that a PatientInfo file exists for the entered patient ID
            File patientInfoFile = new File(patientId + "_PatientInfo.txt");
            if (patientInfoFile.exists()) {
                // Save CT Scan results
                saveCTResults(patientId, totalAgatstonScore, lmScore, ladScore, lcxScore, rcaScore, pdaScore);
                window.close(); // Close the window after saving
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Patient ID not found or invalid.");
            }
        });

        // Add fields and button to the grid
        grid.add(new Label("Patient ID:"), 0, 0);
        grid.add(patientIdField, 1, 0);
        grid.add(new Label("The total Agatston CAC score:"), 0, 1);
        grid.add(totalAgatstonScoreField, 1, 1);
        grid.add(new Label("Vessel level Agatston CAC score"), 0, 2);
        grid.add(new Label("LM:"), 0, 3);
        grid.add(lmScoreField, 1, 3);
        grid.add(new Label("LAD:"), 0, 4);
        grid.add(ladScoreField, 1, 4);
        grid.add(new Label("LCX:"), 0, 5);
        grid.add(lcxScoreField, 1, 5);
        grid.add(new Label("RCA:"), 0, 6);
        grid.add(rcaScoreField, 1, 6);
        grid.add(new Label("PDA:"), 0, 7);
        grid.add(pdaScoreField, 1, 7);
        grid.add(btnSave, 1, 8);

        Scene scene = new Scene(grid, 600, 450);
        window.setScene(scene);
        window.show();
    }

    // this function write the entered data to the ct file.
    private void saveCTResults(String patientId, String totalAgatstonScore, String lmScore, String ladScore, String lcxScore, String rcaScore, String pdaScore) {
        String filename = patientId + "CTResults.txt";
        try (PrintWriter writer = new PrintWriter(filename, "UTF-8")) {
            writer.println("Patient ID: " + patientId);
            writer.println("The total Agatston CAC score: " + totalAgatstonScore);
            writer.println("LM Score: " + lmScore);
            writer.println("LAD Score: " + ladScore);
            writer.println("LCX Score: " + lcxScore);
            writer.println("RCA Score: " + rcaScore);
            writer.println("PDA Score: " + pdaScore);
            writer.flush();
            
            //alert the technician if the update is successful or no 
            showAlert(Alert.AlertType.INFORMATION, "Success", "CT Scan results saved successfully.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error Saving CT Scan Results", "There was an error saving the CT Scan results.");
            e.printStackTrace();
        }
    }

    // displays an alert
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // this function makes sure every field is filled before saving the file.
    private boolean areFieldsValid(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false; // Found an empty field, return false.
            }
        }
        return true; // All fields are filled.
    }
}

