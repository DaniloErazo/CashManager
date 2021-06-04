package ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import model.Account;
import model.CashManager;
import model.Clock;
import model.CreditAccount;
import model.Debt;
import model.MoneyManagement;
import model.Movement;
import model.MovementType;
import model.Saving;
import model.SavingAccount;


public class MainController implements Initializable{
	

	//------------------------------- MainPage.fxml ---------------------------------
	
	ResourceBundle bundle;
	private final CashManager cashManager;
	private Account actualAccount;
	private MoneyManagement accountActual;
	
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
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    public void initialize(URL location, ResourceBundle resources) {
    	Clock clock = new Clock(time);
    	clock.start();
    	userInfo.setPickOnBounds(true);
		
		userInfo.setOnMouseClicked(new EventHandler() {
	       
			@Override
			public void handle(Event event) {
				
				paneOverview.setVisible(true);
				paneContents.setVisible(false);
				
			}
	    });
		
	}
    
    @FXML
    public void loadContents(ActionEvent actionEvent) throws IOException {
    	
    	 if (actionEvent.getSource() == normalAccount) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AccountPage.fxml"));
    		fxmlLoader.setController(this);
    		fxmlLoader.setResources(bundle);
    	    Parent normalAccount = fxmlLoader.load();
    	    loadSavingAccounts();
     	    paneContents.toFront();
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(normalAccount);
         }
    	 
         if (actionEvent.getSource() == creditAccount) {
        	 
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreditPage.fxml"));
     		fxmlLoader.setController(this);
     		fxmlLoader.setResources(bundle);
     	    Parent creditAccount = fxmlLoader.load();
     	    loadCreditAccounts();
     	   paneOverview.setVisible(false);
    	    paneContents.setVisible(true);
    	    paneContents.getChildren().clear();
    	    paneContents.setCenter(creditAccount);
           
         }
         
         if (actionEvent.getSource() == savingAccount) {
        	 
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SavingsPage.fxml"));
      		fxmlLoader.setController(this);
      		fxmlLoader.setResources(bundle);
      	    Parent creditAccount = fxmlLoader.load();
      	    loadSavings();
      	    paneOverview.setVisible(false);
     	    paneContents.setVisible(true);
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(creditAccount);
             	         
         }
         
         if(actionEvent.getSource()==debtAccount){
        	 
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DebtsPage.fxml"));
       		fxmlLoader.setController(this);
       		fxmlLoader.setResources(bundle);
       	    Parent creditAccount = fxmlLoader.load();
       	    loadDebts();
       	   paneOverview.setVisible(false);
      	    paneContents.setVisible(true);
      	    paneContents.getChildren().clear();
      	    paneContents.setCenter(creditAccount);
             
         }
         
         
         if(actionEvent.getSource()==movement){
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MovementPage.fxml"));
     		fxmlLoader.setController(this);
     		fxmlLoader.setResources(bundle);
     	    Parent movementScreen = fxmlLoader.load();
     	    
     	    paneOverview.setVisible(false);
     	    paneContents.setVisible(true);
     	    setDate();
     	    initializeComboBoxAccountOptions();
     	    initializeComboBoxTypesMovement();
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(movementScreen);
         }
         
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
    private ComboBox<String> accountOptions;

    @FXML
    private TextField amountCashTxt;

    @FXML
    private TextField movementDescTxt;

    @FXML
    private ComboBox<String> typesMovement;

    @FXML
    private ComboBox<String> categoriesMovement;

    @FXML
    private TextField date;
    
    private Calendar calendar;

    @FXML
    public void addMovement(ActionEvent event) {
    	String account = accountOptions.getSelectionModel().getSelectedItem();
    	double amount = Double.parseDouble(amountCashTxt.getText());
    	String category = categoriesMovement.getSelectionModel().getSelectedItem();
    	String dateStr = date.getText();
    	String description = movementDescTxt.getText();
    	MovementType type = MovementType.values()[typesMovement.getSelectionModel().getSelectedIndex()];
    	Movement movement = new Movement(account, amount, dateStr, description, type, category);
    	cashManager.addMovement(movement);
    }

    @FXML
    public void removeMovement(ActionEvent event) {

    }
    
    public void setDate() {
    	calendar = Calendar.getInstance(); //It brings the system time and date as a Calendar Object
    	 String timeStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(calendar.getTime());//.getTime() returns a Date object that represents this Calendar's time value
    	 date.setText(timeStr);
    }
    
    public void initializeComboBoxAccountOptions() {
    	ObservableList<String> accounts = null;
     	for (int i = 0; i < cashManager.getCreditAccounts().size(); i++) {
     		accounts = FXCollections.observableArrayList(cashManager.getCreditAccounts().get(i).getName());
     	}
     	accountOptions.setItems(accounts);
    }
    
    public void initializeComboBoxTypesMovement() {
    	ObservableList<String> types = FXCollections.observableArrayList(MovementType.values()[0].name(),MovementType.values()[1].name(),
    																	 MovementType.values()[2].name(),MovementType.values()[3].name());
        typesMovement.setItems(types);
    }
    
    public void initializeComboBoxCategoriesMovement() {
    	
    }
    
    
  //-----------------------------------------------------------------------------------
    
    
  //---------------------------AccountPage.fxml -----------------------------------
    
    @FXML
    private SplitMenuButton displaySavingAccount;
    @FXML
    private SplitMenuButton displayCreditAccounts;
    @FXML
    private SplitMenuButton displayDebts;
    
    @FXML
    private SplitMenuButton displaySavings;
    
    @FXML
    private Button movementSavingAcc;
    
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
    
    public void loadSavingAccounts() {
    	ArrayList<SavingAccount> accounts = cashManager.getSavingAccounts();
    	for (int i = 0; i < accounts.size(); i++) {
			MenuItem typeItem = new MenuItem(accounts.get(i).getName());
			
			typeItem.addEventHandler(ActionEvent.ACTION, (e) -> {
		        displaySavingAccount.setText(typeItem.getText());
		        actualAccount = cashManager.accountExist(0, typeItem.getText());
		    });
			
			displaySavingAccount.getItems().add(typeItem);
		}
    	
    }
    
    public void loadCreditAccounts() {
    	ArrayList<CreditAccount> accounts = cashManager.getCreditAccounts();
    	for (int i = 0; i < accounts.size(); i++) {
			MenuItem typeItem = new MenuItem(accounts.get(i).getName());
			
			typeItem.addEventHandler(ActionEvent.ACTION, (e) -> {
		        displayCreditAccounts.setText(typeItem.getText());
		        actualAccount = cashManager.accountExist(1, typeItem.getText());
		    });
			
			displayCreditAccounts.getItems().add(typeItem);
		}
    	
    }
    
    public void loadDebts() {
    	ArrayList<Debt> accounts = cashManager.getDebts();
    	for (int i = 0; i < accounts.size(); i++) {
			MenuItem typeItem = new MenuItem(accounts.get(i).getNameMoneyManagment());
			
			typeItem.addEventHandler(ActionEvent.ACTION, (e) -> {
		        displayDebts.setText(typeItem.getText());
		        accountActual = cashManager.accountExistM(3, typeItem.getText());
		    });
			
			displayDebts.getItems().add(typeItem);
		}
    	
    }
    
    public void loadSavings() {
    	ArrayList<Saving> accounts = cashManager.getSavings();
    	for (int i = 0; i < accounts.size(); i++) {
			MenuItem typeItem = new MenuItem(accounts.get(i).getNameMoneyManagment());
			
			typeItem.addEventHandler(ActionEvent.ACTION, (e) -> {
		        displaySavings.setText(typeItem.getText());
		        accountActual = cashManager.accountExistM(2, typeItem.getText());
		    });
			
			displaySavings.getItems().add(typeItem);
		}
    	
    }
    
    public void exportMovements(ActionEvent event) {
    	DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle(bundle.getString("select.export.path"));
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        Window primaryStage = null;
		File selectedDirectory = chooser.showDialog(primaryStage);
		
		if(event.getSource() == movementSavingAcc) {
			try {
				actualAccount.exportData(selectedDirectory);
				sendAlert(bundle.getString("success.export.file"), bundle.getString("export.success"));
			} catch (FileNotFoundException e) {
				sendAlert(bundle.getString("error"), bundle.getString("export.failed"));
				e.printStackTrace();
			}
		}
		
		
         
		
		
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
        			
        			if(cashManager.accountExist(0, name)!=null) {
        				
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
        			
        			if(cashManager.accountExist(1, name)!=null) {
        				
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
        			
        			if(cashManager.accountExistM(3, name)!=null) {
        				
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
    			
    			if(cashManager.accountExistM(2, name)!=null) {
    				
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

	public Account getActualAccount() {
		return actualAccount;
	}

	public void setActualAccount(Account actualAccount) {
		this.actualAccount = actualAccount;
	}

  //-----------------------------------------------------------------------------------

	    

}
