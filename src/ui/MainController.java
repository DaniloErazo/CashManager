package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable{
	
	@FXML
    private Label totalCash;

    @FXML
    private Label accountCount;

    @FXML
    private Label debtCount;
  
	@FXML
    private Button normalAccount;

    @FXML
    private Button creditAccount;

    @FXML
    private Button savingAccount;

    @FXML
    private Button debtAccount;

    @FXML
    private Button movement;
    
    @FXML
    private Button userInfo;

    @FXML
    private Button movementAccounts;

    @FXML
    private BorderPane paneOverview;
    
    @FXML
    private BorderPane paneContents;

    @FXML
    private TableView<?> lastMovementsTv;

    @FXML
    private TableColumn<?, ?> movementTc;

    @FXML
    private TableColumn<?, ?> amountTc;

    @FXML
    private TableColumn<?, ?> dateTc;

    @FXML
    private TableColumn<?, ?> descriptionTc;

    @FXML
    private TableColumn<?, ?> typeTc;
    
    @FXML
    public void loadContents(ActionEvent actionEvent) throws IOException {
    	
    	 if (actionEvent.getSource() == normalAccount) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
    		fxmlLoader.setController(this);
    	    Parent normalAccount = fxmlLoader.load();
    	    
     	    paneContents.toFront();
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(normalAccount);
         }
    	 
         if (actionEvent.getSource() == creditAccount) {
        	 
        	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreditPage.fxml"));
     		fxmlLoader.setController(this);
     	    Parent creditAccount = fxmlLoader.load();
     	    	
     	   paneOverview.setVisible(false);
    	    paneContents.setVisible(true);
    	    paneContents.getChildren().clear();
    	    paneContents.setCenter(creditAccount);
           
         }
         
         if (actionEvent.getSource() == savingAccount) {
             	         
         }
         
         if(actionEvent.getSource()==debtAccount){
             
         }
         
         if(actionEvent.getSource() == userInfo) {
        	 paneOverview.setVisible(true);
         }
         
         if(actionEvent.getSource()==movement){
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MovementPage.fxml"));
     		fxmlLoader.setController(this);
     	    Parent movementScreen = fxmlLoader.load();
     	    
     	    paneOverview.setVisible(false);
     	    paneContents.setVisible(true);
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(movementScreen);
         }
         
         if(actionEvent.getSource()==movementAccounts){
             
         }
    }
    
 
	public void initialize() {
	
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	    

}
