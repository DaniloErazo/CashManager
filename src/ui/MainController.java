package ui;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.CashManager;
import model.Clock;


	public class MainController implements Initializable{
	

//------------------------------- MainPage.fxml ---------------------------------
	
	ResourceBundle bundle;
	private final CashManager cashManager;
	public MainController(ResourceBundle resource, CashManager ppal) {
		bundle=resource;
		cashManager = ppal;
		
	}
	
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
    private Label time;
    
    @FXML
    public void initialize() {
    	Clock clock = new Clock(time);
    	clock.start();
    }
    
    @FXML
    public void loadContents(ActionEvent actionEvent) throws IOException {
    	
    	 if (actionEvent.getSource() == normalAccount) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
    		fxmlLoader.setController(this);
    		fxmlLoader.setResources(bundle);
    	    Parent normalAccount = fxmlLoader.load();
    	    
     	    paneContents.toFront();
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(normalAccount);
         }
    	 
         if (actionEvent.getSource() == creditAccount) {
        	 
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreditPage.fxml"));
     		fxmlLoader.setController(this);
     		fxmlLoader.setResources(bundle);
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
         
         
         if(actionEvent.getSource()==movement){
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MovementPage.fxml"));
     		fxmlLoader.setController(this);
     		fxmlLoader.setResources(bundle);
     	    Parent movementScreen = fxmlLoader.load();
     	    
     	    paneOverview.setVisible(false);
     	    paneContents.setVisible(true);
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(movementScreen);
         }
         
         if(actionEvent.getSource()==movementAccounts){
             
        	 
        	 
         }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void initialize(URL location, ResourceBundle resources) {
		
    	userInfo.setPickOnBounds(true);
		
		userInfo.setOnMouseClicked(new EventHandler() {
	       
			@Override
			public void handle(Event event) {
				
				paneOverview.setVisible(true);
				paneContents.setVisible(false);
				
			}
	    });
		
	}
    
    public void sendAlert(String title, String text) {
		
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
    	
	}
    
  //-----------------------------------------------------------------------------------
    
  //---------------------------MovementPage.fxml --------------------------------------
    
    @FXML
    private TextField amountCashTxt;

    @FXML
    private TextField movementDescTxt;

    @FXML
    private SplitMenuButton typesMovementSplit;

    @FXML
    private SplitMenuButton categoriesMovementSplit;

    @FXML
    public void addMovement(ActionEvent event) {

    }
    
    
  //-----------------------------------------------------------------------------------
    
    
  //---------------------------AccountPage.fxml -----------------------------------
    
    @FXML
    public void openGraphicAnalysis(ActionEvent event) throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GraphicAnalysis.fxml"));
 		fxmlLoader.setController(this);
 		fxmlLoader.setResources(bundle);
 	    Parent analysisPage = fxmlLoader.load();
 	    
 	    paneContents.getChildren().clear();
 	    paneContents.setCenter(analysisPage);

    }
    
    @FXML
    public void createAccountBttn(ActionEvent event) throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateAccount.fxml"));
 		fxmlLoader.setController(this);
 		fxmlLoader.setResources(bundle);
 	    Parent createAccount = fxmlLoader.load();
 	    
 	    paneContents.getChildren().clear();
 	    paneContents.setCenter(createAccount);
    	

    }
    
  //-----------------------------------------------------------------------------------
    

  //---------------------------GraphicAnalysis.fxml -----------------------------------
    
    @FXML
    private Label finalBalance;

    @FXML
    private Label remainderBalance;

    @FXML
    private HBox graphicsHBox;
	
    
  //-----------------------------------------------------------------------------------
    
    //---------------------------CreateAccount.fxml ----------------------------------
    
    @FXML
    private MenuItem savingAccountC;

    @FXML
    private MenuItem creditAccountC;

    @FXML
    private MenuItem singleSavingAccount;

    @FXML
    private MenuItem debtAccountC;

    @FXML
    private GridPane createAccountGrid;

    
    @FXML
    private SplitMenuButton accountsDisplay;

    
    //USe gather method from LaCasaDorada to get the values inputted by the user
    
    @FXML
    public void createAccount(ActionEvent event) {
    	
    	ArrayList<String> dataGathered = gatherPrices(createAccountGrid);
    	
    	if(dataGathered.size()==2 && accountsDisplay.getText().equals(bundle.getString("saving.account"))) {
    	
    		try {
    			String name = dataGathered.get(0);
    			double money = Double.parseDouble(dataGathered.get(1));
    			System.out.println(money);
    			
    			if(name.isEmpty()) {
    				
    				sendAlert(bundle.getString("register.problem"), bundle.getString("name.missing"));
    				
        		}else {
        			
        			if(cashManager.accountExist(0, name)) {
        				
        				sendAlert(bundle.getString("register.problem"), bundle.getString("name.repeated.account"));
        				
        			}else {
        				
        				cashManager.createSavingAccount(name, money);
        				
        				sendAlert(bundle.getString("succesful.register"), bundle.getString("account.creation"));
        				
        			}
        			
        		}
    			
			} catch (NumberFormatException e) {
				
				sendAlert(bundle.getString("register.problem"), bundle.getString("double.parseexception"));
    			
    			((TextField)createAccountGrid.getChildren().get(3)).setText("");
			}
    		
    		
    	}else if(dataGathered.size()==3) {
    		
    		try {
				String name = dataGathered.get(0);
				double interest = Double.parseDouble(dataGathered.get(1));
				double quota = Double.parseDouble(dataGathered.get(2));
				
				if(name.isEmpty()) {
					sendAlert(bundle.getString("register.problem"), bundle.getString("name.missing"));
        		}else {
        			
        			if(cashManager.accountExist(1, name)) {
        				
        				sendAlert(bundle.getString("register.problem"), bundle.getString("name.repeated.account"));
        				
        			}else {
        				
        				cashManager.createCreditAccount(name, interest, quota);
        				
        				sendAlert(bundle.getString("succesful.register"), bundle.getString("account.creation"));
        				
        			}
        			
        		}
				
			} catch (NumberFormatException e) {
				
				
				sendAlert(bundle.getString("register.problem"), bundle.getString("double.parseexception"));
				
				
			}
    		
    	} else if(dataGathered.size()==4 && accountsDisplay.getText().equals(bundle.getString("debt"))) {
    		
    		try {
    			
    			String name = dataGathered.get(0);
    			double interest = Double.parseDouble(dataGathered.get(1));
    			int fee = Integer.parseInt(dataGathered.get(2));
    			double money = Double.parseDouble(dataGathered.get(3));
    			
    			if(name.isEmpty()) {
					sendAlert(bundle.getString("register.problem"), bundle.getString("name.missing"));
        		}else {
        			
        			if(cashManager.accountExist(3, name)) {
        				
        				sendAlert(bundle.getString("register.problem"), bundle.getString("name.repeated.account"));
        				
        			}else {
        				
        				cashManager.createDebt(name, interest, fee, money);
        				
        				sendAlert(bundle.getString("succesful.register"), bundle.getString("account.creation"));
        				
        			}
        			
        		}
    			
    			
				
			} catch (NumberFormatException e) {
				
				sendAlert(bundle.getString("register.problem"), bundle.getString("double.parseexception"));
			}
    		
    	} else if (dataGathered.size()==2) {
    		
    		String name = dataGathered.get(0);
			double money = Double.parseDouble(dataGathered.get(1));
			
			if(name.isEmpty()) {
				sendAlert(bundle.getString("register.problem"), bundle.getString("name.missing"));
    		}else {
    			
    			if(cashManager.accountExist(2, name)) {
    				
    				sendAlert(bundle.getString("register.problem"), bundle.getString("name.repeated.account"));
    				
    			}else {
    				
    				cashManager.createSaving(name, money);
    				
    				sendAlert(bundle.getString("succesful.register"), bundle.getString("account.creation"));
    				
    			}
    			
    		}
    		
    	}
    	
    }

    @FXML
    public void setAccountType(ActionEvent event) {
    	
    	Label name = new Label(bundle.getString("name"));
    	name.setTextFill(Color.WHITE);
    	TextField nameTxt = new TextField();
    	TextField interestTxt = new TextField();
    	Label interest = new Label(bundle.getString("interest"));
    	interest.setTextFill(Color.WHITE);
    	
    	GridPane.setMargin(interestTxt, new Insets(20,0,20, 0));
		GridPane.setMargin(interest, new Insets(20,0,20, 0));
		GridPane.setMargin(name, new Insets(0,0,20, 0));
		GridPane.setMargin(nameTxt, new Insets(0,0,20, 0));
    	
    	if (event.getSource() == savingAccountC) {
    		
    		accountsDisplay.setText(savingAccountC.getText());
    		
    		createAccountGrid.getChildren().clear();
    		
    		
    		Label moneyInAcc = new Label(bundle.getString("initial.money"));
    		
    		
    		TextField moneyTxt = new TextField();
    		
    		moneyInAcc.setTextFill(Color.WHITE);
    		
    		
    		createAccountGrid.add(name, 0, 0);
    		createAccountGrid.add(moneyInAcc, 0, 1);
    		createAccountGrid.add(nameTxt, 1, 0);
    		createAccountGrid.add(moneyTxt, 1, 1);
    		
         }
    	
    	if(event.getSource() == creditAccountC) {
    		accountsDisplay.setText(creditAccountC.getText());
    		
    		createAccountGrid.getChildren().clear();
    		
    		
    		Label quota = new Label(bundle.getString("max.quota"));	
    		TextField quotaTxt = new TextField();
    		quota.setTextFill(Color.WHITE);
    		
    		
    		createAccountGrid.add(name, 0, 0);
    		createAccountGrid.add(interest, 0, 1);
    		createAccountGrid.add(quota, 0, 2);
    		
    		createAccountGrid.add(nameTxt, 1, 0);
    		createAccountGrid.add(interestTxt, 1, 1);
    		createAccountGrid.add(quotaTxt, 1, 2);
    	}
    	if(event.getSource() == debtAccountC) {
    		accountsDisplay.setText(debtAccountC.getText());
    		
    		createAccountGrid.getChildren().clear();
    		
    		Label fee = new Label(bundle.getString("fees.number"));	
    		TextField feetxt = new TextField();
    		fee.setTextFill(Color.WHITE);
    		
    		Label money = new Label(bundle.getString("money.pay"));	
    		TextField moneytxt = new TextField();
    		money.setTextFill(Color.WHITE);
    		
    		GridPane.setMargin(feetxt, new Insets(0,0,20, 0));
    		GridPane.setMargin(fee, new Insets(0,0,20, 0));
    		GridPane.setMargin(money, new Insets(0,0,20, 0));
    		GridPane.setMargin(moneytxt, new Insets(0,0,20, 0));
    		
    		
    		
    		createAccountGrid.add(name, 0, 0);
    		createAccountGrid.add(interest, 0, 1);
    		createAccountGrid.add(fee, 0, 2);
    		createAccountGrid.add(money, 0, 3);
    		
    		createAccountGrid.add(nameTxt, 1, 0);
    		createAccountGrid.add(interestTxt, 1, 1);
    		createAccountGrid.add(feetxt, 1, 2);
    		createAccountGrid.add(moneytxt, 1, 3);
    		
    		
    	}
    	
    	if (event.getSource() == singleSavingAccount) {
    		
    		accountsDisplay.setText(singleSavingAccount.getText());
    		
    		createAccountGrid.getChildren().clear();
    		
    		Label money = new Label(bundle.getString("saving.goal"));	
    		TextField moneytxt = new TextField();
    		money.setTextFill(Color.WHITE);
    		
    		createAccountGrid.add(name, 0, 0);
    		createAccountGrid.add(money, 0, 1);
    		
    		createAccountGrid.add(nameTxt, 1, 0);
    		createAccountGrid.add(moneytxt, 1, 1);
			
		}

    }
    
    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren())
            if (GridPane.getColumnIndex(node) != null
                    && GridPane.getColumnIndex(node) != null
                    && GridPane.getRowIndex(node) == row
                    && GridPane.getColumnIndex(node) == col)
                return node;
        return null;
  }
    
    public ArrayList<String> gatherPrices(GridPane table) {
    	
    	ArrayList<String> data = new ArrayList<String>();
    	if(createAccountGrid.getChildren().size()>0){ // that means it contains a table    
    	    int num = createAccountGrid.getChildren().size();
    	    
    	    for(int i=0 ; i<num/2; i++){
    	        String account = ((TextField)getNodeFromGridPane (table, 1, i)).getText();
    	        
    	        data.add(account);
    	        
    	    }
    	}
    	return data;
    }

  //-----------------------------------------------------------------------------------

	    

}
