package ui;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import model.CashManager;

public class SecondaryController {
	
	//Alerts-------------------------------------------------------------------------
    public void errorAlert(String title, String text) {
		
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
    	
	}
	//--------------------------------------------------------------------------------
    
	// ---------------------------LockPage.fxml---------------------------------------
	@FXML
	private PasswordField passwordField;
    
    @FXML
    private BorderPane mainPanelLockScreen;
    
    @FXML
    private BorderPane mainPaneRecoverPassword;
    
    private CashManager cashManager;
    private MainController mainGUI;

	ResourceBundle bundle;
	
	public SecondaryController(ResourceBundle resource, CashManager cashManager) {
		this.cashManager = cashManager;
		bundle = resource;
		mainGUI = new MainController(resource, cashManager);
	}
		
    @FXML
    void logIn(ActionEvent event) throws Exception {
    	if (passwordField.getText().isEmpty()) {
    		new MainController().warningAlert(bundle.getString("password.alertTitle"), bundle.getString("password.paswordFieldEmptyMsg"));
		}
    	else {
        	if (passwordField.getText().equals(cashManager.getUser().getPassword())) {
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        		fxmlLoader.setResources(bundle);
        		fxmlLoader.setController(mainGUI);    	
        		Parent registerScreen = fxmlLoader.load();
            	
        		mainPanelLockScreen.getChildren().clear();
            	mainPanelLockScreen.setCenter(registerScreen);
    		}
        	else {
    			errorAlert(bundle.getString("password.alertTitle"), bundle.getString("password.errorMsg"));
    		}
		}
    }

	@FXML
	void recoverPassword(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RecoverPasswordPage.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setResources(bundle);
		Parent recoverPasswordScreen = fxmlLoader.load();
		mainPanelLockScreen.getChildren().clear();
		keyQuestionLbl.setText(cashManager.getUser().getKeyQuestion());
		mainPanelLockScreen.setCenter(recoverPasswordScreen);
	}
	// -----------------------------------------------------------------------------------

	//-------------------------RecoverPasswordPage.fxml----------------------------------------
	@FXML
	private Label keyQuestionLbl;

	@FXML
	private TextField answerEntered;

	@FXML
	private Label passwordRecoveredLbl;

	@FXML
	public void verifyAnswer(ActionEvent event) {
		if (answerEntered.getText().isEmpty()) {
			new MainController().warningAlert(bundle.getString("password.recoverMsg"), bundle.getString("recover.keyAnswerEnteredEmptyMsg"));
		}
		else {
			if (answerEntered.getText().equalsIgnoreCase(cashManager.getUser().getKeyAnswer())) {
				passwordRecoveredLbl.setText(bundle.getString("recover.passwordRecoveredMsg") + cashManager.getUser().getPassword());
			}
			else {
				errorAlert(bundle.getString("password.recoverMsg"), bundle.getString("recover.keyAnswerWrongMsg"));
			}
		}
	}

	@FXML
	void returnToLockPage(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LockPage.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setResources(bundle);
		Parent lockScreen = fxmlLoader.load();
		    	
		mainPaneRecoverPassword.getChildren().clear();
		mainPaneRecoverPassword.setCenter(lockScreen);
	}
	//-----------------------------------------------------------------------------------------
}
