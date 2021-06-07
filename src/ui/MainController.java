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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import model.Account;

import javafx.stage.FileChooser;
import model.AccountType;

import model.CashManager;
import model.CreditAccount;
import model.Debt;

import model.MoneyManagement;

import model.Import;

import model.Movement;
import model.MovementType;
import model.Saving;
import model.SavingAccount;
import thread.Clock;
import thread.ExportData;


public class MainController implements Initializable{
	

	//------------------------------- MainPage.fxml ---------------------------------
	

	private Account actualAccount;
	private MoneyManagement accountActual;
	
	ResourceBundle bundle;
	
	private  CashManager cashManager;
	private  CreditAccount creditAccount;
	private  SavingAccount savingAccount;
	private  Saving saving;
	private  Debt debt;
	
	
	public MainController(ResourceBundle resource, CashManager ppal) {
		bundle = resource;
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
    private Button creditAccountBtn;

    @FXML
    private Button savingAccountBtn;

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
    private TableView<Movement> lastMovementsTv;

    @FXML
    private TableColumn<Movement, String> typeTc;
    
    @FXML
    private TableColumn<Movement, String> amountTc;

    @FXML
    private TableColumn<Movement, String> dateTc;

    @FXML
    private TableColumn<Movement, String> descriptionTc;  
    
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
    	    paneOverview.setVisible(false);


    	    paneContents.setVisible(true);

     	    paneContents.toFront();
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(normalAccount);
         }
    	 
         if (actionEvent.getSource() == creditAccountBtn) {
        	 
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
         
         if (actionEvent.getSource() == savingAccountBtn) {
        	 
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
     	    initializeComboBoxTypesAccount();
     	    initializeComboBoxAccountOptions();
     	    initializeComboBoxTypesMovement();
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(movementScreen);
         }
         
    }
    
//	private void initializeTableViewOfMovements() throws FileNotFoundException {
//    	ObservableList<Movement> observableList;
//    	observableList = FXCollections.observableArrayList();
//    	
//		lastMovementsTv.setItems(observableList);
//		typeTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("account"));
//		amountTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("amount"));
//	    dateTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("date"));
//		descriptionTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("description"));
//	}
    
    public void sendAlert(String title, String text) {
		
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
    	
	}
    
    public void warningAlert(String title, String text) {
		
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
    	
	}
    
    
  //-----------------------------------------------------------------------------------
    
  //---------------------------MovementPage.fxml --------------------------------------
    @FXML
    private ComboBox<String> typesAccount;
    
    @FXML
    private ComboBox<CreditAccount> accountOptions;

    @FXML
    private TextField amountCashTxt;
    
    @FXML
    private TextField date;

    @FXML
    private TextArea movementDescTxt;

    @FXML
    private ComboBox<String> typesMovement;

    @FXML
    private ComboBox<String> categoriesMovement;
    
    private Calendar calendar;

    @FXML
    public void addMovement(ActionEvent event) {
    	if (typesAccount.getSelectionModel().getSelectedItem() == null && 
    		accountOptions.getSelectionModel().getSelectedItem().getName() == null && 
    		amountCashTxt.getText().isEmpty() || Double.parseDouble(amountCashTxt.getText()) == 0 && 
    		typesMovement.getSelectionModel().getSelectedItem() == null &&
    		categoriesMovement.getSelectionModel().getSelectedItem() == null) { //All blanks are empty
    		
    		warningAlert(bundle.getString("movement.addMovementTitle"),bundle.getString("movement.addMovementWarningMsg"));
		}
    	else if (typesAccount.getSelectionModel().getSelectedItem() == null) { //An account type has not been selected
			warningAlert(bundle.getString("movement.typesAccountTitle"), bundle.getString("movement.typesAccountWarningMsg"));
		}
    	else if (accountOptions.getSelectionModel().getSelectedItem().getName() == null) { //An account has not been selected
			warningAlert(bundle.getString("movement.accountTitle"), bundle.getString("movement.accountWarningMsg"));
		}
    	else if (amountCashTxt.getText() .isEmpty() || Double.parseDouble(amountCashTxt.getText()) == 0) { //The amount field is empty or it has as value zero
    		warningAlert("movement.amountTitle", "movement.amountWarningMsg");
		}
    	else if (typesMovement.getSelectionModel().getSelectedItem() == null) { //A movement type has not been selected
			warningAlert(bundle.getString("movement.typeTitle"), bundle.getString("movement.typeWarningMsg"));
		}
    	else if (categoriesMovement.getSelectionModel().getSelectedItem() == null) { //A movement category has not been selected
    		warningAlert(bundle.getString("movement.categoryTitle"), bundle.getString("movement.categoryWarningMsg"));
		}
    	else {
        	String accountType = typesAccount.getSelectionModel().getSelectedItem();//I will use this line to show only the account that corresponds with the type 
        	String account = accountOptions.getSelectionModel().getSelectedItem().getName();
            double amount = Double.parseDouble(amountCashTxt.getText());
        	MovementType type = MovementType.values()[typesMovement.getSelectionModel().getSelectedIndex()];
        	String category = categoriesMovement.getSelectionModel().getSelectedItem();
        	String dateStr = date.getText();
        	String description = movementDescTxt.getText();
        	Movement movement;
        	
        	switch (typesAccount.getSelectionModel().getSelectedIndex()) {
			case 0: //credit account
				movement = new Movement(account, amount, dateStr, description, type, category);
				creditAccount.addMovement(movement);
				break;	
			case 1: //saving account
				movement = new Movement(account, amount, dateStr, description, type, category);
				savingAccount.addMovement(movement);
				break;
			case 2: //saving 
				movement = new Movement(account, amount, dateStr, description, type, category);
				
				break;
			case 3: //debt
				movement = new Movement(account, amount, dateStr, description, type, category);
				
				break;
			default:
				break;
			}
    
        	movement = new Movement(account, amount, dateStr, description, type, category);
        	cashManager.addMovement(movement);//The new movement is added in the "main" binary search tree too
        	sendAlert(bundle.getString("movement.addMovementTitle"), bundle.getString("movement.addedSuccesfullyMsg"));
		}
    }

    @FXML
    public void removeMovement(ActionEvent event) {
    	
    }
    
    public void setDate() {
    	calendar = Calendar.getInstance(); //It brings the system time and date as a Calendar Object
    	 String timeStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(calendar.getTime());//.getTime() returns a Date object that represents this Calendar's time value
    	 date.setText(timeStr);
    }
    
    public void initializeComboBoxTypesAccount() {
    	ObservableList<String> types = FXCollections.observableArrayList(AccountType.values()[0].name(),AccountType.values()[1].name(),
    																	 AccountType.values()[2].name(),AccountType.values()[3].name());
    	typesAccount.setItems(types);
    }
    
    public void initializeComboBoxAccountOptions() {
    	ObservableList<CreditAccount> accounts = FXCollections.observableArrayList(cashManager.getCreditAccounts());
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
    private Button exportSavingAccount;
    
    @FXML
    private Button exportCreditAcc;
    
    @FXML
    private Button exportDebtAcc;
    
    @FXML
    private Button exportSavingAcc;

    @FXML
    private Button exportAll;
    
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
		
		if((event.getSource() == exportSavingAccount) || (event.getSource() == exportCreditAcc)) {
			try {
				actualAccount.exportData(selectedDirectory);
				sendAlert(bundle.getString("success.export.title"), bundle.getString("export.success"));
			} catch (FileNotFoundException e) {
				sendAlert(bundle.getString("error"), bundle.getString("export.failed"));
				e.printStackTrace();
			}
		
		}
		if(event.getSource() == exportDebtAcc || event.getSource() == exportSavingAcc) {
			try {
				accountActual.exportData(selectedDirectory);
				sendAlert(bundle.getString("success.export.title"), bundle.getString("export.success"));
			} catch (FileNotFoundException e) {
				sendAlert(bundle.getString("error"), bundle.getString("export.failed"));
				e.printStackTrace();
			}
		}
		
		if(event.getSource() == exportAll) {
			try {
				File exportFile = new File(selectedDirectory+"/output.csv");
				ExportData exportAll = new ExportData(cashManager, this, exportFile);
				exportAll.start();
			} catch (Exception e) {
				sendAlert(bundle.getString("error"), bundle.getString("export.failed"));
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
    
  //Imports----------------------------------------------------------------------------
  @FXML
  public void importData(ActionEvent event) throws IOException {
    	
	  FileChooser fChooser = new FileChooser();
	  fChooser.setTitle("Importar datos");
	  File file = fChooser.showOpenDialog(paneOverview.getScene().getWindow());
	  if (file != null) {
		  Alert alert = new Alert(AlertType.INFORMATION);
   	   	  alert.setTitle("Importar datos de clientes");
   	      new Import(file.getAbsolutePath()).start();
   	    	 alert.setContentText("Datos importados exitosamente");
   	    	 alert.showAndWait();
	  }
  }
  //-----------------------------------------------------------------------------------
    
}
