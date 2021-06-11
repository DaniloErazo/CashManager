package ui;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import exceptions.ExistingCategoryException;
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
import javafx.scene.chart.PieChart;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import model.Account;
import javafx.stage.FileChooser;
import model.CashManager;
import model.CategoryType;
import model.CreditAccount;
import model.Debt;
import model.MoneyManagement;
import model.Import;
import model.Movement;
import model.MovementType;
import model.Saving;
import model.SavingAccount;
import model.User;
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
		creditAccount = new CreditAccount();
		savingAccount = new SavingAccount();
		saving = new Saving();
		debt = new Debt();
	}
	
	public MainController() {}
	
	@FXML
    private Label totalCash;

    @FXML
    private Label accountCount;

    @FXML
    private Label totalDebt;
  
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
    private TableColumn<Movement, String> accountTc;
    
    @FXML
    private TableColumn<Movement, String> amountTc;

    @FXML
    private TableColumn<Movement, String> dateTc;

    @FXML
    private TableColumn<Movement, String> descriptionTc;  
    
    @FXML
    private Label time;
    
    @FXML
    private Label welcomeLbl;
    
    private int accoutCountInt;
    
    private double totalCashDouble;
    
    private double totalDebtDouble;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    public void initialize(URL location, ResourceBundle resources) {
    	welcomeLbl.setText(bundle.getString("user.welcome") + " " + System.getProperty("user.name"));//it show a welcome message for the system current system user
    	Clock clock = new Clock(time);
    	clock.start();
    	
    	userInfo.setPickOnBounds(true);
		
		userInfo.setOnMouseClicked(new EventHandler() {
	       
			@Override
			public void handle(Event event) {
				
				paneOverview.setVisible(true);
				paneContents.setVisible(false);
				try {
					initializeTableViewOfMovements(); 
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}	
				totalCash.setText(String.valueOf(totalCashDouble));
				totalDebt.setText(String.valueOf(totalDebtDouble));
			}
	    });
		
		try {
			initializeTableViewOfMovements();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		totalDebt.setText(String.valueOf(totalDebtDouble));
		totalDebt.setText(String.valueOf(totalDebtDouble));
	}
    
    @FXML
    public void loadContents(ActionEvent actionEvent) throws IOException, InterruptedException {
    	
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
     	    initializeComboBoxTypesMovement();
     	    paneContents.getChildren().clear();
     	    paneContents.setCenter(movementScreen);
     	
         }
         
    }
    
    @FXML
    void importMovements(ActionEvent event) {    	
    	FileChooser fChooser = new FileChooser();
    	fChooser.setTitle("Importar datos");
    	File file = fChooser.showOpenDialog(paneOverview.getScene().getWindow());
    	if (file != null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Importar movimientos");
    	   	new Import(file.getAbsolutePath()).start();
    	   		alert.setContentText("Datos importados exitosamente");
    	   		alert.showAndWait();
    	}
    }
    
    @FXML
    void registerPassword(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PasswordPage.fxml"));
 		fxmlLoader.setController(this);
 		fxmlLoader.setResources(bundle);
 	    Parent passwordScreen = fxmlLoader.load();
 	    	
 	    paneOverview.setVisible(false);
	    paneContents.setVisible(true);
	    paneContents.getChildren().clear();
	    paneContents.setCenter(passwordScreen);
    }
    
	private void initializeTableViewOfMovements() throws FileNotFoundException {
    	ObservableList<Movement> observableList = null;
    	if (cashManager.inOrden().size() != 0) {
        	observableList = FXCollections.observableArrayList(cashManager.inOrden());
		}

		lastMovementsTv.setItems(observableList);
		typeTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("type"));
		accountTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("account"));
		amountTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("amount"));
	    dateTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("date"));
		descriptionTc.setCellValueFactory(new PropertyValueFactory<Movement,String>("description"));
	}
    
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
    private ComboBox<String> accountOptions;

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
    
    @FXML
    private Button addCategorybtn;
    
    @FXML
    private Label movementCategoryLbl;
    
    
    
    private Calendar calendar;
    
    private int typeAccount;

    @FXML
    public void addMovement(ActionEvent event) throws InterruptedException {
    	if (accountOptions.getSelectionModel().getSelectedItem() == null && 
    		amountCashTxt.getText().isEmpty() && 
    		typesMovement.getSelectionModel().getSelectedItem() == null &&
    		categoriesMovement.getSelectionModel().getSelectedItem() == null) { //All blanks are empty
    		
    		warningAlert(bundle.getString("movement.addMovementTitle"),bundle.getString("movement.addMovementWarningMsg"));
		}
    	else if (accountOptions.getSelectionModel().getSelectedItem() == null) { //An account has not been selected
			warningAlert(bundle.getString("movement.accountTitle"), bundle.getString("movement.accountWarningMsg"));
		}
    	else if (amountCashTxt.getText() .isEmpty() || Double.parseDouble(amountCashTxt.getText()) == 0) { //The amount field is empty or it has as value zero
    		warningAlert(bundle.getString("movement.amountTitle"), bundle.getString("movement.amountWarningMsg"));
		}
    	else if (typesMovement.getSelectionModel().getSelectedItem() == null) { //A movement type has not been selected
			warningAlert(bundle.getString("movement.typeTitle"), bundle.getString("movement.typeWarningMsg"));
		}
    	else if (typesMovement.getSelectionModel().getSelectedIndex() == 2 || typesMovement.getSelectionModel().getSelectedIndex() == 3) { //between accounts or payment
    		String account = accountOptions.getSelectionModel().getSelectedItem();
            double amount = Double.parseDouble(amountCashTxt.getText());
        	MovementType type = MovementType.values()[typesMovement.getSelectionModel().getSelectedIndex()];
        	String dateStr = date.getText();
        	String description = movementDescTxt.getText();
        	Movement movement;
        	switch (typeAccount) {
			case 0: //saving account
				movement = new Movement(account, amount, dateStr, description, type);
				savingAccount.addMovement(movement);
				if (typesMovement.getSelectionModel().getSelectedIndex() == 0) {//income
					totalCashDouble = totalCashDouble + amount;
				}
				else if (typesMovement.getSelectionModel().getSelectedIndex() == 1) {//spend
					totalDebtDouble = totalDebtDouble - amount;
					totalCashDouble = totalCashDouble - amount;
				}
				break;
			case 1: //credit account
				movement = new Movement(account, amount, dateStr, description, type);
				creditAccount.addMovement(movement);
				break;	
			case 2: //saving 
				movement = new Movement(account, amount, dateStr, description, type);
				saving.addMovement(movement);
				totalCashDouble = totalCashDouble + amount;
				break;
			case 3: //debt
				movement = new Movement(account, amount, dateStr, description, type);
				debt.addMovement(movement);
				totalDebtDouble = totalDebtDouble + amount;
				totalCashDouble = totalCashDouble - amount;
				break;
			}
        	
        	movement = new Movement(account, amount, dateStr, description, type);
        	cashManager.addMovement(movement);//The new movement is added in the "main" binary search tree too
        	sendAlert(bundle.getString("movement.addMovementTitle"), bundle.getString("movement.addedSuccesfullyMsg"));
		}
    	else if (categoriesMovement.getSelectionModel().getSelectedItem() == null) { //A movement category has not been selected
    		warningAlert(bundle.getString("movement.categoryTitle"), bundle.getString("movement.categoryWarningMsg"));
		}
    	else {

        	String account = accountOptions.getSelectionModel().getSelectedItem();
            double amount = Double.parseDouble(amountCashTxt.getText());
        	MovementType type = MovementType.values()[typesMovement.getSelectionModel().getSelectedIndex()];
        	String category = categoriesMovement.getSelectionModel().getSelectedItem();
        	String dateStr = date.getText();
        	String description = movementDescTxt.getText();
        	Movement movement;
        	if (categoriesMovement.getSelectionModel().getSelectedIndex() == 1 && typeAccount != 1 && typeAccount != 3) { //income and it is not a credit account or debt
				totalCashDouble =+ amount;
				totalCash.setText(String.valueOf(totalCashDouble));
			}
        	switch (typeAccount) {
			case 0: //saving account
				movement = new Movement(account, amount, dateStr, description, type, category);
				savingAccount.addMovement(movement);
				if (typesMovement.getSelectionModel().getSelectedIndex() == 0) {//income
					totalCashDouble = totalCashDouble + amount;
				}
				else if (typesMovement.getSelectionModel().getSelectedIndex() == 1) {//spend
					totalDebtDouble = totalDebtDouble - amount;
					totalCashDouble = totalCashDouble - amount;
				}
				break;
			case 1: //credit account
				movement = new Movement(account, amount, dateStr, description, type, category);
				creditAccount.addMovement(movement);
				break;	
			case 2: //saving 
				movement = new Movement(account, amount, dateStr, description, type, category);
				saving.addMovement(movement);
				totalCashDouble = totalCashDouble + amount;
				break;
			case 3: //debt
				movement = new Movement(account, amount, dateStr, description, type, category);
				debt.addMovement(movement);
				totalDebtDouble = totalDebtDouble + amount;
				totalCashDouble = totalCashDouble - amount;
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
    	ObservableList<String> types = FXCollections.observableArrayList(bundle.getString("accountType.savingAccount"),bundle.getString("accountType.creditAccount"),
    			                                                                          bundle.getString("accountType.saving"),bundle.getString("accountType.debt"));
    	typesAccount.setItems(types);
    }
    
    
    //This method initialize the combo box where are all enable account for a type account selected
    @FXML
    void initializeComboBoxAccountOptions(ActionEvent event) {
    	ArrayList<String> accountNameList = new ArrayList<>();
    	
    	switch (typesAccount.getSelectionModel().getSelectedIndex()) {
		case 0: 
			typeAccount = 0;
	    	for (int i = 0; i < cashManager.getSavingAccounts().size(); i++) {
				accountNameList.add(i, cashManager.getSavingAccounts().get(i).getName());
			}
			break;
		case 1:
			typeAccount = 1;
	    	for (int i = 0; i < cashManager.getCreditAccounts().size(); i++) {
				accountNameList.add(i, cashManager.getCreditAccounts().get(i).getName());
			}
			break;
		case 2: 
			typeAccount = 2;
	    	for (int i = 0; i < cashManager.getSavings().size(); i++) {
				accountNameList.add(i, cashManager.getSavings().get(i).getNameMoneyManagment());
			}
			break;
		case 3: 
			typeAccount = 3;
	    	for (int i = 0; i < cashManager.getDebts().size(); i++) {
				accountNameList.add(i, cashManager.getDebts().get(i).getNameMoneyManagment());
			}
			break;
    	}	
    	
	    ObservableList<String> accounts = FXCollections.observableArrayList(accountNameList);
    	accountOptions.setItems(accounts);
	}
    
    public void setVisibleRowCategory(boolean visible) {
    	movementCategoryLbl.setVisible(visible);
    	categoriesMovement.setVisible(visible);
    	addCategorybtn.setVisible(visible);
    }
    
    public void initializeComboBoxTypesMovement() {
    	ObservableList<String> types = FXCollections.observableArrayList(bundle.getString("movement.typeIncome"),bundle.getString("movement.typeSpend"),
    																	 bundle.getString("movement.typeBetweenAccount"),bundle.getString("movement.typePayment"));
        typesMovement.setItems(types);
    }
     
    @FXML
    void initializeComboBoxCategoriesMovement(ActionEvent event) {
    	ArrayList<String> categoriesNameList = new ArrayList<>();
    	switch (typesMovement.getSelectionModel().getSelectedIndex()) {
		case 0:
			setVisibleRowCategory(true);
	    	for (int i = 0; i < cashManager.getCategoryIncome().size(); i++) {
				categoriesNameList.add(i,cashManager.getCategoryIncome().get(i).getName());
			}
			break;
		case 1:
			setVisibleRowCategory(true);
	    	for (int i = 0; i < cashManager.getCategorySpend().size(); i++) {
				categoriesNameList.add(i,cashManager.getCategorySpend().get(i).getName());
			}
			break;
		case 2:
			setVisibleRowCategory(false);
			break;
    	case 3:
    		setVisibleRowCategory(false);
			break;
		}
    	
    	ObservableList<String> types = FXCollections.observableArrayList(categoriesNameList);
    	categoriesMovement.setItems(types);
    }
    

    @FXML
    public void addCategory(ActionEvent event) throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCategory.fxml"));
   		fxmlLoader.setController(this);
   		fxmlLoader.setResources(bundle);
   	    Parent category = fxmlLoader.load();
   	    setTypesCategory();
  	    paneContents.setVisible(true);
  	    paneContents.getChildren().clear();
  	    paneContents.setCenter(category);
    	
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
 	    setPieChart1();
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
    private PieChart pieChart1;

    @FXML
    private PieChart pieChart2;

    
    public void setPieChart1() {
    	
    	DecimalFormat df = new DecimalFormat("#");
		df.setMaximumFractionDigits(0);
    	
		if(accountActual instanceof Saving && accountActual!=null) {
			double[] data = accountActual.getAnalysisData();
			double missing = data[0];
			double paid = ((Saving) accountActual).totalPayment();
			
			df.format(missing);
			remainderBalance.setText(String.valueOf(missing));
			ObservableList<PieChart.Data> pieChartData = 
					FXCollections.observableArrayList(
	            new PieChart.Data(bundle.getString("saving.paid"), paid),
	            new PieChart.Data(bundle.getString("saving.left"), missing));
			
			pieChart1.setData(pieChartData);
	        pieChart1.setTitle(bundle.getString("saving.title") + ": " + accountActual.getNameMoneyManagment());
		}
 	
		if(accountActual instanceof Debt && accountActual!=null) {
			double[] data = accountActual.getAnalysisData();
			double interestPaid = data[0];
			double totalPaid = data[1];
			
			double totalInterest =((Debt)accountActual).getInterest()*accountActual.getMaxAmount();
			double totalDebt = accountActual.getMaxAmount();
			
			double remainder = ((totalDebt*((Debt)accountActual).getInterest())+totalDebt)-(interestPaid+totalPaid);
			double balance = interestPaid+totalPaid;
			
			df.format(interestPaid);
			df.format(totalPaid);
			df.format(totalInterest);
			df.format(totalDebt);
			
			remainderBalance.setText(String.valueOf(remainder));
			finalBalance.setText(String.valueOf(balance));
			
			ObservableList<PieChart.Data> pieChartData1 = 
					FXCollections.observableArrayList(
	            new PieChart.Data(bundle.getString("interest.paid"), interestPaid),
	            new PieChart.Data(bundle.getString("interest.left"), totalInterest-interestPaid));
			
			ObservableList<PieChart.Data> pieChartData2 = 
					FXCollections.observableArrayList(
	            new PieChart.Data(bundle.getString("money.paid"), totalPaid),
	            new PieChart.Data(bundle.getString("money.left"), totalDebt-totalPaid));
			
			pieChart1.setData(pieChartData1);
	        pieChart1.setTitle(bundle.getString("debt.interest.title") + ": " + accountActual.getNameMoneyManagment());
	        
	        pieChart2.setData(pieChartData2);
	        pieChart1.setTitle(bundle.getString("debt.money.title") + ": " + accountActual.getNameMoneyManagment());
		}
    	
    }
	
    
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
    			
    			if(name.isEmpty()) {
    				
    				warningAlert(bundle.getString("register.problem"), bundle.getString("name.missing"));
    				
        		}else {
        			
        			if(cashManager.accountExist(0, name)!=null) {
        				
        				warningAlert(bundle.getString("register.problem"), bundle.getString("name.repeated.account"));
        				
        			}else {
        				
        				cashManager.createSavingAccount(name, money);
        				
        				accoutCountInt++;
        				accountCount.setText(String.valueOf(accoutCountInt));
        				
        				totalCashDouble = totalCashDouble + money;
        				
        				sendAlert(bundle.getString("succesful.register"), bundle.getString("account.creation"));
        				
        			}
        			
        		}
    			
			} catch (NumberFormatException e) {
				
				warningAlert(bundle.getString("register.problem"), bundle.getString("double.parseexception"));
    			
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
        				
        				accoutCountInt++;
        				accountCount.setText(String.valueOf(accoutCountInt));
        		
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
        				accoutCountInt++;
        				accountCount.setText(String.valueOf(accoutCountInt));
        				totalDebtDouble = totalDebtDouble + money;
        				totalCashDouble = totalCashDouble - money;
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
    				
    				accoutCountInt++;
    				accountCount.setText(String.valueOf(accoutCountInt));
    				
    				totalCashDouble = totalCashDouble + money;
    				
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

  //--------------------------PasswordPage.fxml----------------------------------------
  @FXML
  private TextArea registerKeyQuestion;

  @FXML
  private TextField registerPassword;

  @FXML
  private TextField registerKeyAnswer;

  @FXML
  void savePassword(ActionEvent event) {
	  if (registerPassword.getText().isEmpty()) {
		  warningAlert(bundle.getString("password.registerTitle"), bundle.getString("password.registerWarningMsg"));
	  }
	  else if (registerKeyQuestion.getText().isEmpty()) {
		  warningAlert(bundle.getString("password.keyQuestionTitle"), bundle.getString("password.keyQuestionWarningMsg"));
	  }
	  else if (registerKeyAnswer.getText().isEmpty()) {
		  warningAlert(bundle.getString("password.answerKeyQuestionTitle"), bundle.getString("password.answerKeyQuestionWarningMsg"));
	  }	
	  else {
//		  String name;

		  String password = registerPassword.getText();
		  String keyQestion = registerKeyQuestion.getText();
		  String keyAnswer = registerKeyAnswer.getText();
		  cashManager.setUser(new User(password, keyQestion, keyAnswer));
		  new Main().setLock(true);
		  sendAlert(bundle.getString("password.registerTitle"), bundle.getString("password.registeredSuccesfully"));
	  }
  }
  //----------------------------------------------------------------------------------------   
  
  
  //-------------------------------------- create category--------------------------------------
  
  @FXML
  private ComboBox<String> categoryType;

  @FXML
  private TextField categoryNameTxt;
  

  @FXML
  public void createCategory(ActionEvent event) {
	  
	  String name = categoryNameTxt.getText();
	  String type = categoryType.getValue();

	  
	  if(name.isEmpty()) {
		  warningAlert(bundle.getString("error"), bundle.getString("name.missing"));
	  }
		  
	  if(type==null) {
		  warningAlert(bundle.getString("error"), bundle.getString("category.type.missing"));
	  }else {
		  
		  try {
			  
			cashManager.categoryExist(name, type);
			cashManager.createCateogry(name, type);
					 
			sendAlert(bundle.getString("succesful.register"), bundle.getString("category.creation") );
			
			}catch (ExistingCategoryException e) {
			warningAlert(bundle.getString("error"), bundle.getString("existing.category"));
			}
	  }
		  
  }	
 

  public void setTypesCategory() {
	  ObservableList<String> types = FXCollections.observableArrayList(CategoryType.values()[0].name(),CategoryType.values()[1].name());
	  categoryType.setItems(types);
  }
  
  //---------------------------------------------------------------------------------------
}
