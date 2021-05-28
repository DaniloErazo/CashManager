package ui;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static MainController mainGUI;
	

	public static void main(String[] args) {
		mainGUI= new MainController(); 
		
		launch(args);
		
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Locale.setDefault(new Locale("es", "CO"));
		
		ResourceBundle bundle = ResourceBundle.getBundle(("ui.Messages"), Locale.getDefault());
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
		
		fxmlLoader.setResources(bundle);
		
		fxmlLoader.setController(mainGUI);
		
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Charts");
		primaryStage.show();
		
	}

}
