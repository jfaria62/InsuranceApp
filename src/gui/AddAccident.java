package gui;

import java.sql.Date;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import server.Connect;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AddAccident extends Application{

	int numVehicles;
	Date accidentDate;
	String city;
	String state;
	String vin[] = new String[10];
	String ssn[] = new String[10];
	Float damages[] = new Float[10];
	String driver_ssn[] = new String[10];
	
	@Override
	public void start(Stage primaryStage) {		
		Connect conn = new Connect();
		numVehicles = 0;
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
        Label lOwnerSsn = new Label("Your SSN: \n###-##-#### format");
        TextField tOwnerSsn = new TextField();
        Label lOwnerDamages = new Label("Your Vehicle Damages: ");
        TextField tOwnerDamages = new TextField();
        Label lOwnerVin= new Label("Your Vehicle Vin: ");
        TextField tOwnerVin= new TextField();
       
        Label lNthDriverSsn = new Label("Driver SSN: \n###-##-#### format");
        TextField tNthDriverSsn = new TextField();
        Label lNthDriverDamages = new Label("Vehicle Damages: ");
        TextField tNthDriverDamages = new TextField();
        Label lNthDriverVin= new Label("Vehicle Vin: ");
        TextField tNthDriverVin = new TextField();
        
       
        DatePicker datePicker = new DatePicker();
        HBox dateBox = new HBox(datePicker);
		Label lDate = new Label("Date of Accident");
        Button backMainMenuBtn = new Button("Back To Main Menu");
        Button submitBtn = new Button("Submit");
        Button addVehicleBtn = new Button("Add Vehicle Involved");
        Button submitNewDriverBtn = new Button("Submit Driver");
        Button cancelBtn = new Button("Cancel");        
                
        //add all elements to current scene
  		Text scenetitle = new Text("Create An Accident Report");
  		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
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
  	    grid.add(addVehicleBtn, 0, 6);	
  	    grid.add(submitBtn, 6, 6);
  	    
  	    //make sure ssn is formatted correctly
  	    tNthDriverSsn.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                String s = tNthDriverSsn.getText().toUpperCase();
                tNthDriverSsn.setText(s); 
                
                if (tNthDriverSsn.getText().length() == 3) {
                    s = tNthDriverSsn.getText() + "-";
                    tNthDriverSsn.setText(s);
                }
                if (tNthDriverSsn.getText().length() == 6) {
                    s = tNthDriverSsn.getText() + "-";
                    tNthDriverSsn.setText(s);
                }
                if (tNthDriverSsn.getText().length() > 11) {
                	s = tNthDriverSsn.getText().substring(0, 11);
                	tNthDriverSsn.setText(s);
                }
            }
        });
  	    
  	    //set limit to vin number input to 12 chars
  	    tNthDriverVin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                String s = tNthDriverVin.getText().toUpperCase();
                tNthDriverVin.setText(s); 
                if (tNthDriverVin.getText().length() > 12) {
                    s = tNthDriverVin.getText().substring(0, 12).toUpperCase();
                    tNthDriverVin.setText(s);
                }
            }
        });
  	    
  		//make sure ssn is formatted correctly
  	    tOwnerSsn.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                String s = tOwnerSsn.getText().toUpperCase();
                tOwnerSsn.setText(s); 
                
                if (tOwnerSsn.getText().length() == 3) {
                    s = tOwnerSsn.getText() + "-";
                    tOwnerSsn.setText(s);
                }
                if (tOwnerSsn.getText().length() == 6) {
                    s = tOwnerSsn.getText() + "-";
                    tOwnerSsn.setText(s);
                }
                if (tOwnerSsn.getText().length() > 11) {
                	s = tOwnerSsn.getText().substring(0, 11);
                	tOwnerSsn.setText(s);
                }
            }
        });
  	    
  	    //set limit to vin number input to 12 chars
  	    tOwnerVin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                String s = tOwnerVin.getText().toUpperCase();
                tOwnerVin.setText(s); 
                if (tOwnerVin.getText().length() > 12) {
                    s = tOwnerVin.getText().substring(0, 12).toUpperCase();
                    tOwnerVin.setText(s);
                }
            }
        });
  	    
        //makes sure State text stays below 2 characters and capitalizes
        tState.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                String s = tState.getText().toUpperCase();
                tState.setText(s); 
                if (tState.getText().length() > 2) {
                    s = tState.getText().substring(0, 2).toUpperCase();
                    tState.setText(s);
                }
            }
        });
        
        //return to Main menu
        backMainMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
                MainMenu menu = new MainMenu();
                menu.start(primaryStage);
        	}
        });        
       
        //add another vehicle to the involvement
        addVehicleBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {        		
            	GridPane grid2 = new GridPane();            	
            	grid2.setAlignment(Pos.CENTER);
                grid2.setHgap(10);
                grid2.setVgap(10);
                grid2.setPadding(new Insets(25, 25, 25, 25));
            	grid2.add(lNthDriverSsn, 0, 2);
            	grid2.add(tNthDriverSsn, 1, 2);
            	grid2.add(lNthDriverVin, 0, 3);
            	grid2.add(tNthDriverVin, 1, 3);
            	grid2.add(lNthDriverDamages, 0, 4);
            	grid2.add(tNthDriverDamages, 1, 4);
            	grid2.add(submitNewDriverBtn, 0, 6);
            	grid2.add(cancelBtn, 3, 6);
            	Scene moDriverScene = new Scene(grid2, 700, 600);
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
				accidentDate = aDate;
			}
		});
		
		//makes sure damages field is numeric
		tOwnerDamages.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {                	
                    tOwnerDamages.setText(oldValue);
                    lOwnerDamages.setText("*Only Dollar Amount Damages*");
                }
                else {
                	lOwnerDamages.setText("Your Vehicle Damages: ");
                }
            }
        });
		
		//makes sure other driver damages field is numeric
		tNthDriverDamages.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {                	
                	tNthDriverDamages.clear();
                	lNthDriverDamages.setText("*Only Dollar Amount Damages*");
                }
                else {
                	lNthDriverDamages.setText("Vehicle Damages: ");
                }
            }
        });
		
		
		
		Button otherDriverBtn = new Button();
		otherDriverBtn.setText("Add Another Involved Vehicle");
		Scene scene = new Scene(grid, 700, 600);
		
		//set what happens on Cancel Button Click
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {			
            @Override
			public void handle(ActionEvent e) {
            	lNthDriverSsn.setText("Driver SSN: ");                		
            	lNthDriverDamages.setText("Vehicle Damages: ");                		
            	lNthDriverVin.setText("Vehicle Vin: ");                		
            	lNthDriverSsn.setTextFill(Color.BLACK);                		
            	lNthDriverDamages.setTextFill(Color.BLACK);                		
            	lNthDriverVin.setTextFill(Color.BLACK);
            	tNthDriverSsn.clear();
            	tNthDriverDamages.clear();
            	tNthDriverVin.clear();            	
            	primaryStage.setScene(scene);
            	primaryStage.show();    
            }
            
		});
		
		 //set Values for Accident info and send to server
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {        		
        		lOwnerSsn.setText("Driver SSN: \n###-##-#### format");                		
            	lOwnerDamages.setText("Vehicle Damages: ");                		
            	lOwnerVin.setText("Vehicle Vin: "); 
            	lCity.setText("City: ");
	            lState.setText("State: ");
	            lDate.setText("*Date of Accident: ");
            	lOwnerSsn.setTextFill(Color.BLACK);                		
            	lOwnerDamages.setTextFill(Color.BLACK);                		
            	lOwnerVin.setTextFill(Color.BLACK);
	        	lCity.setTextFill(Color.BLACK);
	        	lState.setTextFill(Color.BLACK);
	        	lDate.setTextFill(Color.BLACK);
	            
            	if(tOwnerSsn.getText().isEmpty()) {
        			lOwnerSsn.setTextFill(Color.RED);
            		lOwnerSsn.setText("*Driver SSN Required");                		
        		}
            	if(tOwnerDamages.getText().isEmpty()) {
        			lOwnerDamages.setTextFill(Color.RED);
            		lOwnerDamages.setText("*Vehicle Damages Required");                		
        		}
            	if(tOwnerVin.getText().isEmpty()) {
            		lOwnerVin.setTextFill(Color.RED);
            		lOwnerVin.setText("*Vehicle VIN Required");
        		}
            	if(tCity.getText().isEmpty()) {
            		lCity.setTextFill(Color.RED);
            		lCity.setText("*City Required");
        		}
            	if(tState.getText().isEmpty()) {
            		lState.setTextFill(Color.RED);
            		lState.setText("*State Required");
        		}
            	if(dateBox.getChildren( ) != null ) {
            		lDate.setTextFill(Color.RED);
            		lDate.setText("*Date Required");
            	}
            	//successful data entry and submission
            	if(!tOwnerVin.getText().isEmpty() && !tOwnerDamages.getText().isEmpty() && !tOwnerSsn.getText().isEmpty()
            			&& !tState.getText().isEmpty() && !tCity.getText().isEmpty() && dateBox.getChildren( ) != null ){
            		
            		city = tCity.getText();
            		state = tState.getText();            		
            		ssn[numVehicles] = tOwnerSsn.getText();
            		vin[numVehicles] = tOwnerVin.getText();
                	damages[numVehicles] = Float.parseFloat(tOwnerDamages.getText());
                	System.out.print("\nAccident Info \ncity/state: " + city + state 
                			+ "\ndate: " + accidentDate);
                	for(int i = 0; i < numVehicles; i++) {
                		System.out.print("\nDriverInfo \nSSN: " + ssn[i] + "\nvin: " + vin[i]
                				+ "\ndamages: " + damages[i]);                            	
                	}
                	//conn.addAccident(accidentDate, city, state, vin, damages, driver_ssn, numVehicles);
                	
                	//System.out.print("numV " + numVehicles + "\nssn " + ssn[numVehicles] + "\nvin "+ vin[numVehicles]+ "\ndamages "+ damages[numVehicles]);
                	MainMenu menu = new MainMenu();  
            		menu.start(primaryStage);           
            	}
        	}
        });       
        
		// Collect and check data entered for a NEW Driver
		submitNewDriverBtn.setOnAction(new EventHandler<ActionEvent>() {			
            @Override
			public void handle(ActionEvent e) {
            	//reset labels
            	lNthDriverSsn.setText("Driver SSN: \n###-##-#### format");                		
            	lNthDriverDamages.setText("Vehicle Damages: ");                		
            	lNthDriverVin.setText("Vehicle Vin: ");                		
            	lNthDriverSsn.setTextFill(Color.BLACK);                		
            	lNthDriverDamages.setTextFill(Color.BLACK);                		
            	lNthDriverVin.setTextFill(Color.BLACK);
            	//check for text entry
            	if(tNthDriverSsn.getText().isEmpty()) {
        			lNthDriverSsn.setTextFill(Color.RED);
            		lNthDriverSsn.setText("*Driver SSN Required");                		
        		}
            	if(tNthDriverDamages.getText().isEmpty()) {
        			lNthDriverDamages.setTextFill(Color.RED);
            		lNthDriverDamages.setText("*Vehicle Damages Required");                		
        		}
            	if(tNthDriverVin.getText().isEmpty()) {
            		lNthDriverVin.setTextFill(Color.RED);
            		lNthDriverVin.setText("*Vehicle VIN Required");
        		}
            	//successful data entry and submission
            	if(!tNthDriverVin.getText().isEmpty() && !tNthDriverDamages.getText().isEmpty() && !tNthDriverSsn.getText().isEmpty()) {
            		ssn[numVehicles] = tNthDriverSsn.getText();
            		vin[numVehicles] = tNthDriverVin.getText();
                	damages[numVehicles] = Float.parseFloat(tNthDriverDamages.getText());
                	//reset fields for next driver info input
                	lNthDriverSsn.setText("Driver SSN: ");                		
                	lNthDriverDamages.setText("Vehicle Damages: ");                		
                	lNthDriverVin.setText("Vehicle Vin: "); 
                	tNthDriverSsn.clear();
                	tNthDriverDamages.clear();
                	tNthDriverVin.clear();
                	
                	System.out.print(ssn[numVehicles] + "\nvin "+ vin[numVehicles]+ "\ndamages "+ damages[numVehicles]);
                	numVehicles++;
                	primaryStage.setScene(scene);
                	primaryStage.show();                
            	}            	
        	}
        });//end submitNewDriverBtn logic
        
		primaryStage.setScene(scene);
		primaryStage.show();
	}		
}
