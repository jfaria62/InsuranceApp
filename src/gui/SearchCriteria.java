package gui;


import java.sql.Date;
import java.time.LocalDate;

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
import javafx.stage.Stage;
import server.Connect;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SearchCriteria extends Application{
	Accidents[] data;
	String city = null;
	String state = null;
	Float minAvg = null;
	Float maxAvg = null;
	Float minTot = null;
	Float maxTot = null;
	Date minDate = null;
	Date maxDate = null;
			
	@Override
	public void start(Stage primaryStage){
		Connect conn = new Connect();
		
		final ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList();
		list.setPrefSize(200, 250);
		        
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Label lAvgRange= new Label("Select Avg Damage Range");
		TextField tMinAvg = new TextField();
		TextField tMaxAvg = new TextField();
		Label lTotRange = new Label("Select Total Damage Range");
		TextField tMinTot = new TextField();
		TextField tMaxTot = new TextField();
		Label lToLabel = new Label(" To ");
		Label lToLabel2 = new Label(" To ");
		Label lToLabel3 = new Label(" To ");
		tMinAvg.setPromptText("Min");
		tMaxAvg.setPromptText("Max");
		tMinTot.setPromptText("Min");
		tMaxTot.setPromptText("Max");
		DatePicker datePicker = new DatePicker();
		DatePicker datePicker2 = new DatePicker();//can't add same DatePicker to different HBox's
        Label lDateRange = new Label("Select Date Range of Accident");
		Button searchBtn = new Button("Search");
		Button returnMainBtn = new Button("Return To Main Menu");
		Text scenetitle = new Text("Search For Accidents");
		
		datePicker.getEditor().setDisable(true);
		datePicker2.getEditor().setDisable(true);
		
		returnMainBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				MainMenu menu = new MainMenu();
				menu.start(primaryStage);
			}
		});  
	 
		//FOLLOWING LISTENERS ALLOW FOR ONLY NUMERIC USER INPUT
		tMinAvg.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {                	
                	tMinAvg.clear();
                }
                
            }
        });
		
		tMaxAvg.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {                	
                	tMaxAvg.clear();
                }
            }
        });
		
		tMinTot.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {                	
                	tMinTot.clear();
                }
            }
        });
		
		tMaxTot.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {                	
                	tMaxTot.clear();
                }
            }
        });
		
		datePicker.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				LocalDate date = datePicker.getValue();
		        Date aDate = Date.valueOf(LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth())); 
				minDate = aDate;		       
		     }
		});
		
		datePicker2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				LocalDate date2 = datePicker2.getValue();		        
        		Date aDate = Date.valueOf(LocalDate.of(date2.getYear(), date2.getMonth(), date2.getDayOfMonth())); 
        		maxDate = aDate;	       
		     }
		});
		
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
            public void handle(ActionEvent e) {
        		int i = 0;
        		//reset vars in case user deleted since last search click
        		minAvg = null;
        		maxAvg = null;
        		minTot = null;
        		maxTot = null;
        		data = null;
        		grid.getChildren().remove(list);
        		items.clear();
        		
        		if(!tMinAvg.getText().isEmpty()) {
        			minAvg = Float.parseFloat(tMinAvg.getText());            		
        		}
            	if(!tMaxAvg.getText().isEmpty()) {
            		maxAvg = Float.parseFloat(tMaxAvg.getText());            		      		
        		}
            	if(!tMinTot.getText().isEmpty()) {
        			minTot = Float.parseFloat(tMinTot.getText());            		
        		}
            	if(!tMaxTot.getText().isEmpty()) {
            		maxTot = Float.parseFloat(tMaxTot.getText());            		      		
        		}
            	
            	data = conn.getByCriteria(minDate, maxDate, minAvg, maxAvg, minTot, maxTot);
            	if(data == null) {
            		Label lNoResults = new Label("No Results");
            		grid.add(lNoResults, 0, 6);
            	}else {
            		while(i < data.length) {
                		items.add("ID: " + data[i].getID() 
                				+ "\t Accident Date: " + data[i].getDate() + "\t City: " 
                				+ data[i].getCity() + "\t State: " + data[i].getState());                	
                    	i++;
                	}
                	list.setItems(items);        		
                	grid.add(list, 0, 6,5,1);
                }
           	}
    	});      
		
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		grid.add(returnMainBtn, 0, 0);
		grid.add(scenetitle, 0, 1, 3, 1);
		grid.add(lAvgRange, 0, 2);
		grid.add(tMinAvg, 1, 2);
		grid.add(lToLabel, 2, 2);
		grid.add(tMaxAvg, 3, 2);
		grid.add(lTotRange, 0, 3);
		grid.add(tMinTot, 1, 3);
		grid.add(lToLabel2, 2, 3);		
		grid.add(tMaxTot, 3, 3);
		grid.add(lDateRange, 0, 4);
		grid.add(datePicker, 1, 4);
		grid.add(lToLabel3, 2, 4);
		grid.add(datePicker2 , 3, 4);
		grid.add(searchBtn, 0, 5);
		
		Scene scene = new Scene(grid, 700, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
