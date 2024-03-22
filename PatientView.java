package application;

// Ryan Alfa

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PatientView {

    public void display() {
        Stage window = new Stage();
        window.setTitle("Patient View");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        // Form fields
        TextField patientIdField = new TextField();
        Button btnView = new Button("View Results");

        btnView.setOnAction(e -> {
            String patientId = patientIdField.getText();
            showPatientInfo(patientId);
        });

        // Add fields and button to the grid
        grid.add(new Label("Patient ID:"), 0, 0);
        grid.add(patientIdField, 1, 0);
        grid.add(btnView, 1, 1);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
    }

    //displaying the patient infos by opening both files.
    private void showPatientInfo(String patientId) {
        String patientInfoFilename = patientId + "_PatientInfo.txt";
        String ctResultsFilename = patientId + "CTResults.txt";

        String patientInfo = readFile(patientInfoFilename);
        String ctResults = readFile(ctResultsFilename);

        // checking if any of the files are empty before displaying
        if (patientInfo == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No such patient ID: " + patientId);
            return;
        }

        if (patientInfo.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Patient information is empty for Patient ID: " + patientId);
            return;
        }

        if (ctResults == null || ctResults.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "CT scan data not available yet for Patient ID: " + patientId);
            return;
        }
        
        
        Stage resultWindow = new Stage();
        resultWindow.setTitle("Patient Results");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Get the first name from the file content
        String patientName = patientInfo.split("\n")[1].split(": ")[1].trim(); 
        
        // forming the labels and fields needed
        Label greetingLabel = new Label("Hello " + patientName);
        greetingLabel.setAlignment(Pos.CENTER);
        TextField totalAgatstonScoreField = new TextField();
        TextField lmScoreField = new TextField();
        TextField ladScoreField = new TextField();
        TextField lcxScoreField = new TextField();
        TextField rcaScoreField = new TextField();
        TextField pdaScoreField = new TextField();
        
        
        if (ctResults != null && !ctResults.isEmpty()) {
        	// saving all the content of file ct file in a string and seprate them by new line 
        	// since it will b easier for me to manipulate it later 
            String[] lines = ctResults.split("\n");
            for (String line : lines) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String label = parts[0].trim();
                    String value = parts[1].trim();
                    
                    //setting the correct value to the correct field.
                    switch (label) {
                        case "The total Agatston CAC score":
                            totalAgatstonScoreField.setText(value);
                            break;
                        case "LM Score":
                            lmScoreField.setText(value);
                            break;
                        case "LAD Score":
                            ladScoreField.setText(value);
                            break;
                        case "LCX Score":
                            lcxScoreField.setText(value);
                            break;
                        case "RCA Score":
                            rcaScoreField.setText(value);
                            break;
                        case "PDA Score":
                            pdaScoreField.setText(value);
                            break;
                        // Add more cases as needed for other fields
                    }
                }
            }
        }
        
        
        // making the labels required. 
        grid.add(greetingLabel, 0, 0, 2, 1);
        grid.add(new Label("The total Agatston CAC score"), 0, 1);
        grid.add(totalAgatstonScoreField, 1, 1);
        grid.add(new Label("LM:"), 0, 2);
        grid.add(lmScoreField, 1, 2);
        grid.add(new Label("LAD:"), 0, 3);
        grid.add(ladScoreField, 1, 3);
        grid.add(new Label("LCX:"), 0, 4);
        grid.add(lcxScoreField, 1, 4);
        grid.add(new Label("RCA:"), 0, 5);
        grid.add(rcaScoreField, 1, 5);
        grid.add(new Label("PDA:"), 0, 6);
        grid.add(pdaScoreField, 1, 6);
        
        // make the labels not editable
        totalAgatstonScoreField.setEditable(false);
        lmScoreField.setEditable(false);
        ladScoreField.setEditable(false);
        lcxScoreField.setEditable(false);
        rcaScoreField.setEditable(false);
        pdaScoreField.setEditable(false);

        Scene scene = new Scene(grid, 400, 350);
        resultWindow.setScene(scene);
        resultWindow.show();
    }

    // reading the file 
    private String readFile(String filename) {
        File file = new File(filename);
        //System.out.println("Current working directory: " + System.getProperty("user.dir"));
        if (!file.exists()) {
            return null; // File does not exist
        }

        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            // This exception should not occur since we've already checked that the file exists.
            System.out.println("File not found: " + filename);
            return null;
        }

        return content.toString();
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
