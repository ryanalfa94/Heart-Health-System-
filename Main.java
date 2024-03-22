package application;
	
//Ryan Alfa

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
 @Override
 public void start(Stage primaryStage) {
     VBox root = new VBox(10);
     root.setAlignment(Pos.CENTER);
     
     Label greetingLabel = new Label("Welcome to Heart Health Imaging and Recording System ");
     
     // creating the three main buttons 
     Button btnPatientIntake = new Button("Patient Intake");
     Button btnCTScanTechView = new Button("CT Scan Tech View");
     Button btnPatientView = new Button("Patient View");

     // setting the on click event to the right method 
     btnPatientIntake.setOnAction(e -> openPatientIntakeView());
     btnCTScanTechView.setOnAction(e -> openCTScanTechView());
     btnPatientView.setOnAction(e -> openPatientView());

     root.getChildren().addAll(greetingLabel,btnPatientIntake, btnCTScanTechView, btnPatientView);

     Scene scene = new Scene(root, 600, 450);
     primaryStage.setTitle("Heart Health Imaging and Recording System");
     primaryStage.setScene(scene);
     primaryStage.show();
 }
 // displaying Patient Intake  
 private void openPatientIntakeView() {
	 ReceptionistView receptionistView = new ReceptionistView();
	 receptionistView.display();
 }
//displaying  CT Scan Tech View 
 private void openCTScanTechView() {
	 TechnicianView technicianView = new TechnicianView();
	 technicianView.display();
 }
//displaying Patient View 
 private void openPatientView() {
     PatientView patientView = new PatientView();
     patientView.display();
 }

 public static void main(String[] args) {
     launch(args);
 }
}

