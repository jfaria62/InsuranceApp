package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class MainMenu extends Application {
	public static void main(String[] args) {
		launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Auto Insurance App");
        
        //create grid to organize GUI elements on
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(100, 100, 100, 100));
        
        //create buttons for App Navigation        
        Button addAccidentBtn= new Button("Add An Accident");
        Button searchByCriteriaBtn = new Button("Search By Criteria Range");
        Button searchByIdBtn = new Button("Search By Accident ID");                
        Button exitBtn = new Button("Exit App");
        Text scenetitle = new Text("Welcome");
        Text btnDescription = new Text("Search for accident information");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        btnDescription.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        exitBtn.setOnAction(new EventHandler<ActionEvent>() {        	 
            @Override
            public void handle(ActionEvent e) {
            	Platform.exit();
            	System.exit(0);
            }
        });

        searchByIdBtn.setOnAction(new EventHandler<ActionEvent>() {        	 
            @Override
            public void handle(ActionEvent e) {
                SearchById sId = new SearchById();
               	sId.start(primaryStage);               	
            }
        });
        
        addAccidentBtn.setOnAction(new EventHandler<ActionEvent>() {        	 
            @Override
            public void handle(ActionEvent e) {
                AddAccident aa = new AddAccident();
               	aa.start(primaryStage);               	
            }
        });
        
        searchByCriteriaBtn.setOnAction(new EventHandler<ActionEvent>() {        	 
            @Override
            public void handle(ActionEvent e) {
                SearchCriteria sc = new SearchCriteria();
               	sc.start(primaryStage);
            }
        });
        grid.add(scenetitle, 0, 0, 2, 1);

        grid.add(exitBtn, 5, 0);
        grid.add(addAccidentBtn, 0, 1, 3, 1);
        grid.add(btnDescription, 0, 10, 4, 1);
        grid.add(searchByCriteriaBtn, 0, 11, 2, 1);
        grid.add(searchByIdBtn, 3, 11, 2, 1);

               
        Scene scene = new Scene(grid, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}