package gui;

import classes.Accidents;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import server.Connect;

public class SearchById extends Application {
	private Accidents[] records;
	private int aid = 0;
	
	@Override
	public void start(Stage primaryStage){
		Connect conn = new Connect();		
		final ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList();
		//list.setSize(250, 250);      
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Button returnMainBtn = new Button("Return To Main Menu");
        Button searchBtn = new Button("Search");
        Label lResults = new Label();
        final Label lAid = new Label("Accident ID:");
        Label lLocation = new Label();
        
        TextField tAid = new TextField();
        lResults.setFont(new Font("Arial", 20));
        lLocation.setFont(new Font("Arial", 15));
        list.setMinWidth(500);
        
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
        		int i = 0; 
        		//reset vars in case user deleted since last search click
        		aid = 0; 
        		
        		grid.getChildren().remove(list);
        		items.clear();
        		lResults.setText("");
        		lLocation.setText("");
        		//if user entered an accident ID
        		if(!tAid.getText().isEmpty()) {
        			aid = Integer.parseInt(tAid.getText()); 
        			records = conn.getAccidentsById(aid);  
        			//if the query returned any results
        			if(records == null) {
        				lResults.setText("No Results");
                	}
            		else{
            			lResults.setText("Search Results");
            			lLocation.setText("City: " + records[0].getCity() + " \tState: " + records[0].getState());
    	        		while(i < records.length) {
    	        			
    	        			items.add("Driver SSN: " + records[i].getDriver() + "\t\tDamages: " + records[i].getDamages()
    	        					 + "\t\tVIN: " + records[i].getVin()); 
    	        			i++;
    	               	}   
    	            	list.setItems(items);
    	        		grid.add(list, 0, 4);    	            
            		}
        		}
        		else {
        			lResults.setText("Must Enter Accident ID");
        		}        		
        	}
    	});  
        
        //only allow ints in tAid
        tAid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d+")) {                	
                	tAid.clear();
                }
            }
        });
        
        //return to main menu
        returnMainBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				MainMenu menu = new MainMenu();
				menu.start(primaryStage);
			}
		});  
        grid.add(lAid, 0, 0);
        grid.add(tAid, 0, 1);
        grid.add(lResults, 0, 2);
        grid.add(lLocation, 0, 3);    	
        grid.add(returnMainBtn, 0, 5);        
        grid.add(searchBtn, 1, 1);

        Scene scene = new Scene(grid, 700, 600);
            
        primaryStage.setScene(scene);
		primaryStage.show();
        
	}

}
