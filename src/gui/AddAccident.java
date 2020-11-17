package gui;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import classes.Accidents;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import server.Connect;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AddAccident extends Application{

	int numVehicles = 1; 
	@Override
	public void start(Stage primaryStage){
		Connect conn = new Connect();
		Connect.connect();
		Accidents addAccident = new Accidents();
		String vin[];
		String ssn[];
		Float damages[];
		String driver_ssn[];
		
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label lAInfo = new Label("Accident Information");
        Label lCity = new Label("City: ");
        TextField tCity = new TextField();
        Label lState = new Label("State: ");
        TextField tState = new TextField();
        Label lOwnerSsn = new Label("Your SSN: ");
        TextField tOwnerSsn = new TextField();
        Label lOwnerDamages = new Label("Your Vehicle Damages: ");
        TextField tOwnerDamages = new TextField();
        Label lOwnerVin= new Label("Your Vehicle Vin: ");
        TextField tOwnerVin= new TextField();
        
        Label lNthDriverSsn = new Label("Driver SSN: ");
        TextField tNthDriverSsn = new TextField();
        
        
        DatePicker datePicker = new DatePicker();
        HBox dateBox = new HBox(datePicker);
		Label lDate = new Label("Date of Accident");
        Button backMainMenuBtn = new Button();
        Button submitBtn = new Button();
        Button addVehicle = new Button();
        
        backMainMenuBtn.setText("Back To Main Menu");
        //return to Main menu
        backMainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
                MainMenu menu = new MainMenu();
                menu.start(primaryStage);
        	}
        });
        backMainMenuBtn.setStyle("-fx-background-color: #EB7B71");
        submitBtn.setText("Submit Claim");
        //set Values for Accident info and send to server
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {        		
                MainMenu menu = new MainMenu();                
               	//addAccident.setDriver(ssn);
               	
                //conn.addAccident(addAccident.getDate(), addAccident.getCity(), addAccident.getState(), vin, damages, driver_ssn, numVehicles);
               	menu.start(primaryStage);
        	}
        });
        addVehicle.setText("Add Vehicle Involved");
        addVehicle.setOnAction(new EventHandler<ActionEvent>() {
        	//numVehicles += 1
        	
            public void handle(ActionEvent e) {        		
            	numVehicles++;
            	GridPane grid2 = new GridPane();
            	grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(25, 25, 25, 25));
            	grid2.add(lNthDriverSsn, 0, 2);
            	grid2.add(tNthDriverSsn, 1, 2);
            	Scene moDriverScene = new Scene(grid2);
            	primaryStage.setScene(moDriverScene);
            	primaryStage.show();
        	}
        });
       
		//When date is chosen, set addAccident date value
		datePicker.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				LocalDate date = datePicker.getValue();
		        Date aDate = Date.valueOf(LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth())); 
				addAccident.setDate(aDate);
		        System.err.println("Selected date: " + addAccident.getDate());
		     }
		});
		
		Text scenetitle = new Text("Create An Accident Report");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		//grid.add(child, columnIndex, rowIndex);
		//grid.add(submitBtn, columnIndex, rowIndex);
		grid.add(backMainMenuBtn, 0, 0, 2, 1);
		grid.add(scenetitle, 0, 1, 3, 1);
	    grid.add(lAInfo, 0, 2);
	    grid.add(lCity, 0, 3);
	    grid.add(tCity, 1, 3);
	    grid.add(lState, 0, 4);
	    grid.add(tState, 1, 4);
		grid.add(lDate, 0, 5);
		grid.add(dateBox, 1, 5, 2, 1);	
		grid.add(lOwnerSsn, 5, 3);
		grid.add(tOwnerSsn, 6, 3);
		grid.add(lOwnerDamages, 5, 4);
	    grid.add(tOwnerDamages, 6, 4);
	    grid.add(lOwnerVin, 5, 5);
	    grid.add(tOwnerVin, 6, 5);
	    grid.add(addVehicle, 0, 6);
		
		
		
		Button otherDriverBtn = new Button();
		otherDriverBtn.setText("Add Another Involved Vehicle");
		Scene scene = new Scene(grid);		
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
	
}
