package ui;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class SecondaryController {

	// ---------------------------LockPage.fxml---------------------------------------
	@FXML
	private PasswordField passwordField;
    
    @FXML
    private BorderPane mainPanelLockScreen;
    
    @FXML
    private BorderPane mainPaneRecoverPassword;

	ResourceBundle bundle;
	
	public SecondaryController(ResourceBundle resource) {
		bundle = resource;
	}

	@FXML
	void recoverPassword(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RecoverPasswordPage.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setResources(bundle);
		Parent recoverPasswordScreen = fxmlLoader.load();

		mainPanelLockScreen.getChildren().clear();
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
	void verifyAnswer(ActionEvent event) {
		  
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
