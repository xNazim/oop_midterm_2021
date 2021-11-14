package sample;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class Sky extends Application{
	static Group root = new Group();
	static double width = 700;
	static double height = 600;
	static boolean species = false;

	public static void main(String[] args) {launch();}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Flock control");
		
		primaryStage.setOnCloseRequest(event0 -> System.exit(0));
		
		primaryStage.widthProperty().addListener(event1 -> width = primaryStage.getWidth());
		primaryStage.heightProperty().addListener(event2 -> height = primaryStage.getHeight());


		ToolBar settings = new ToolBar();		
		Label current = new Label("Current mode: one flock");
		
		Button mode = new Button("Switch mode");
		
		mode.setOnAction(event3 -> {
			if(species) {
				current.setText("Current mode: one flock");
			}else {
				current.setText("Current mode: many flocks");
			}
			species = !species;
		});
		
		settings.getItems().addAll(mode,current);
		root.getChildren().add(settings);


		Flock f = new Flock();
		f.draw(root);
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			public void run(){
		        f.update(species);
			}
		};


		timer.scheduleAtFixedRate(task, 0, 33L);
		
		Scene s = new Scene(root,width,height);
		primaryStage.setScene(s);
		primaryStage.show();
	}

}
