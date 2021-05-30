package ui;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CashManager;

public class Main extends Application {
	
	private static MainController mainGUI;
	private static CashManager ppal;

	public static void main(String[] args) {
		Locale.setDefault(new Locale("es", "CO"));
		
		ppal = new CashManager();
		
		ResourceBundle bundle = ResourceBundle.getBundle(("ui.Messages"), Locale.getDefault());
		mainGUI= new MainController(bundle, ppal); 
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Locale.setDefault(new Locale("es", "CO"));
		
		ResourceBundle bundle = ResourceBundle.getBundle(("ui.Messages"), Locale.getDefault());
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
		
		fxmlLoader.setResources(bundle);
		
		fxmlLoader.setController(mainGUI);
		
		primaryStage.setResizable(false);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Charts");
		primaryStage.show();
		
	}

}
