package ui;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CashManager;

public class Main extends Application {
	
	private static MainController mainGUI;
	private static SecondaryController secondaryGUI;
	private static CashManager ppal;

	private boolean lock = false; 
	
	
	//fxml attribute preloader
	
	@FXML
	private Circle circle1;

    @FXML
    private Rectangle square1;

    @FXML
    private Circle circle2;

    @FXML
    private Rectangle square2;
    
    @FXML
    private Pane background;
    
    @FXML
    private BorderPane borderpane;

	public static void main(String[] args) {
		
		
		Locale.setDefault(new Locale("es", "CO"));
		ppal = new CashManager();
		ResourceBundle bundle = ResourceBundle.getBundle(("ui.Messages"), Locale.getDefault());
		mainGUI = new MainController(bundle, ppal); 

		secondaryGUI = new SecondaryController(bundle, ppal);

		
		launch(args);
		
	}
	
	public void setLock(boolean lock) {
		this.lock = lock;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		//Load internationalized String
		Locale.setDefault(new Locale("es", "CO"));
		ResourceBundle bundle = ResourceBundle.getBundle(("ui.Messages"), Locale.getDefault());
		
		
		//Create default categories
		if (ppal.getCategoryIncome().isEmpty() && ppal.getCategorySpend().isEmpty()) {
			ppal.setDefaultCategories();
		}
		FXMLLoader	fxmlLoader = null;
		
		
		//Load preload
		
		Stage secondaryStage = new Stage();
		FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("FirstPage.fxml"));
		fxmlLoader2.setResources(bundle);
		
		fxmlLoader2.setController(this);
		
		
		primaryStage.setResizable(false);
		
		Parent root3 = fxmlLoader2.load();
		
		//Setup loader
		
		setPReloader();
		
		Scene scene3 = new Scene(root3);
		secondaryStage.setScene(scene3);
		secondaryStage.setTitle("Cash Manager");
		secondaryStage.show();
		
		
		//load main page/lock
		

		if(!lock) {
			fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
			fxmlLoader.setResources(bundle);
			
			fxmlLoader.setController(mainGUI);
			
			primaryStage.setResizable(false);
			
			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cash Manager");
			//primaryStage.show();
			
		}
		else {
			fxmlLoader = new FXMLLoader(getClass().getResource("LockPage.fxml"));
			fxmlLoader.setResources(bundle);
			fxmlLoader.setController(secondaryGUI);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cash Manager");
			//primaryStage.show();
		}
		
		Timeline postLoader = new Timeline(
			    new KeyFrame(new Duration(5000), t -> {
			    	
			    	secondaryStage.close();
			    	primaryStage.show();
			      })
			  );
		postLoader.setCycleCount(1);
		postLoader.play();

		
		
	}
	
	public void setPReloader() {
		
		borderpane.setStyle("-fx-background-image: url(\"/ui/images/Background2-1.jpg\");-fx-background-size: 1100, 667;-fx-background-repeat: no-repeat;");
		
		
		String path = String.valueOf(this.getClass().getResource("/ui/images/coin.png"));
		
		String path2 = String.valueOf(this.getClass().getResource("/ui/images/wallet.png"));
		
		Image inCircle = new Image(path, 76, 76, false, false);
		
		Image square = new Image(path2, 76, 76, false, false);
		
		ImagePattern circles = new ImagePattern(inCircle, 0, 0, 1, 1, true);
		
		ImagePattern squares = new ImagePattern(square, 0, 0, 1, 1, true);
		
		circle1.setFill(circles);
		circle2.setFill(circles);
		square1.setFill(squares);
		square2.setFill(squares);
		
		TranslateTransition transition1 = createTransition(1, 1000, true, -120, circle1);
		transition1.play();
		TranslateTransition transition2 = createTransition(1, 1000, true, -120, circle2);
		transition2.play();
		TranslateTransition transition3 = createTransition(1, 1000, true, -60, square1);
		transition3.play();
		TranslateTransition transition4 = createTransition(1, 1000, true, -60, square2);
		transition4.play();
		
	}
	
	public TranslateTransition createTransition(int duration, int cycle, boolean reverse, int yPosition, Node node) {
		
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(duration));
		transition.setCycleCount(cycle);
		transition.setAutoReverse(reverse);
		transition.setToY(-yPosition);
		
		transition.setNode(node);
		
		return transition;
		
	}

}