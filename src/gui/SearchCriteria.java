package gui;


import java.sql.Date;
import javafx.application.Application;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SearchCriteria extends Application{
	Date accidentDate;
	String city;
	String state;
	Float damages[] = new Float[10];
	
	@Override
	public void start(Stage primaryStage){
		Connect conn = new Connect();
		Connect.connect();
		
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
		
		DatePicker datePicker = new DatePicker();
		DatePicker datePicker2 = new DatePicker();//can't add same DatePicker to different HBox's
        HBox minDateBox = new HBox(datePicker);
		Label lDateRange = new Label("Select Date Range of Accident");
		HBox maxDateBox = new HBox(datePicker2);
		
		Button searchBtn = new Button("Search");
		Text scenetitle = new Text("Search For Accidents");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 2, 0, 3, 1);
		grid.add(lAvgRange, 0, 1);
		grid.add(tMinAvg, 1, 1);
		grid.add(lToLabel, 2, 1);
		grid.add(tMaxAvg, 3, 1);
		grid.add(lTotRange, 0, 2);
		grid.add(tMinTot, 1, 2);
		grid.add(lToLabel2, 2, 2);		
		grid.add(tMaxTot, 3, 2);
		grid.add(lDateRange, 0, 3);
		grid.add(minDateBox, 1, 3);
		grid.add(lToLabel3, 2, 3);
		grid.add(maxDateBox, 3, 3);
		
		
		Scene scene = new Scene(grid, 700, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
