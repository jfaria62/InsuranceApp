package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.Connect;
 
public class MainMenu extends Application {
	public static void main(String[] args) {
		Connect conn = new Connect();
		conn.connect();
		launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Auto Insurance App");
        //create buttons for App Navigation
        
        StackPane root = new StackPane();
        //root.getChildren().add(addAccidentBtn);
        //primaryStage.setScene(new Scene(root, 1000, 950));
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(100, 100, 100, 100));
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        Button addAccidentBtn= new Button();
        addAccidentBtn.setText("Add An Accident");
        addAccidentBtn.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent e) {
                AddAccident aa = new AddAccident();
               	aa.start(primaryStage);
               	//aa.show();
				//actiontarget.setFill(Color.FIREBRICK);
                //actiontarget.setText("Sign in button pressed");
            }
        });
        grid.add(addAccidentBtn, 0, 3);
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        TextField pwBox = new TextField();
        grid.add(pwBox, 1, 2);
        
        Scene scene = new Scene(grid, 600, 575);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}