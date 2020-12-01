package gui;

import javafx.application.Application;
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
        //create buttons for App Navigation
        
        //StackPane root = new StackPane();
        //root.getChildren().add(addAccidentBtn);
        //primaryStage.setScene(new Scene(root, 1000, 950));
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(100, 100, 100, 100));
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        Button addAccidentBtn= new Button("Add An Accident");
        Button searchByCriteriaBtn = new Button("Search By Criteria Range");
        Button searchByIdBtn = new Button("Search By Accident ID");                
        
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
               	//aa.show();
				//actiontarget.setFill(Color.FIREBRICK);
                //actiontarget.setText("Sign in button pressed");
            }
        });
        
        grid.add(addAccidentBtn, 0, 1, 3, 1);
        grid.add(searchByCriteriaBtn, 0, 3, 3, 1);
        grid.add(searchByIdBtn, 0, 2, 3, 1);
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

               
        Scene scene = new Scene(grid, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}