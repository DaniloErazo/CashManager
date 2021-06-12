package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import exceptions.ExistingCategoryException;



public class CashManager{
	
	private ArrayList<CreditAccount> creditAccounts;
	private ArrayList<SavingAccount> savingAccounts;
	private ArrayList<Debt> debts;
	private ArrayList<Saving> savings;
	private ArrayList<Category> categorySpend;
	private ArrayList<Category> categoryIncome;
	
	private User user;
	private Movement rootMovements; //This binary search three save the movements in general, They will show to the user in the main page
	
	
	public final static String SAVE_PATH_FILE_CREDIT = "data/creditAccounts.cmn";
	public final static String SAVE_PATH_FILE_SAVINGA = "data/savingAccounts.cmn";
	public final static String SAVE_PATH_FILE_DEBT = "data/debts.cmn";
	public final static String SAVE_PATH_FILE_SAVINGS = "data/savings.cmn";
	public final static String SAVE_PATH_FILE_CATEGORYS = "data/categoryS.cmn";
	public final static String SAVE_PATH_FILE_CATEGORYI = "data/categoryI.cmn";
	public final static String SAVE_PATH_FILE_MOVEMENTS = "data/movements.cmn";
	
	
	public CashManager() {
		creditAccounts = new ArrayList<>();
		savingAccounts = new ArrayList<>();
		debts = new ArrayList<>();
		savings = new ArrayList<>();
		categorySpend = new ArrayList<>();
		categoryIncome = new ArrayList<>();
	}
	
	
	public void saveData(String object) throws IOException  {
		ObjectOutputStream oos;
		if(object.equals("credit")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CREDIT));
			oos.writeObject(creditAccounts);
			oos.close();
		}else if (object.equals("savingA")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_SAVINGA));
			oos.writeObject(savingAccounts);
			oos.close();
		}else if (object.equals("debt")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_DEBT));
			oos.writeObject(debts);
			oos.close();
		}else if (object.equals("savings")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_SAVINGS));
			oos.writeObject(savings);
			oos.close();
		}else if(object.equals("categoryS")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CATEGORYS));
			oos.writeObject(categorySpend);
			oos.close();
		}else if(object.equals("categoryI")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CATEGORYS));
			oos.writeObject(categoryIncome);
			oos.close();
		}else if(object.equals("order")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CATEGORYI));
			oos.writeObject(categoryIncome);
			oos.close();
		}else if(object.equals("movements")) {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_MOVEMENTS));
			oos.writeObject(rootMovements);
			oos.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadData(String data) throws ClassNotFoundException, IOException {
		File f = null;
		if(data.equals("credit")) {
			f = new File(SAVE_PATH_FILE_CREDIT);
			if(f.exists()){
			      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			      creditAccounts = (ArrayList<CreditAccount>)ois.readObject();
			      
			      ois.close();
			    }
		}else if(data.equals("savingA")) {
			f= new File(SAVE_PATH_FILE_SAVINGA);
			if(f.exists()){
			      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			      savingAccounts = (ArrayList<SavingAccount>)ois.readObject();
			      ois.close();
			    }
		}else if(data.equals("debt")) {
			f = new File(SAVE_PATH_FILE_DEBT);
			if(f.exists()){
			      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			      debts = (ArrayList<Debt>)ois.readObject();
			      
			      ois.close();
			    }
		}else if(data.equals("savings")) {
			f = new File(SAVE_PATH_FILE_SAVINGS);
			if(f.exists()){
			      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			      savings = (ArrayList<Saving>)ois.readObject();
			      ois.close();
			    }
		}else if(data.equals("categoryS")) {
			f = new File(SAVE_PATH_FILE_CATEGORYS);
			if(f.exists()){
			      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			      categorySpend = (ArrayList<Category>)ois.readObject();
			      ois.close();
			    }
		}else if(data.equals("categoryI")) {
			f = new File(SAVE_PATH_FILE_CATEGORYI);
			if(f.exists()){
			      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			      categoryIncome = (ArrayList<Category>)ois.readObject();
			      ois.close();
			    }
		}else if(data.equals("movements")) {
			f = new File(SAVE_PATH_FILE_MOVEMENTS);
			if(f.exists()){
			      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			      rootMovements = (Movement)ois.readObject();
			      ois.close();
			    }
		}
	    
		 
	}
	
	
	
	//-------------------- Create accounts ----------------------------------
	public void createSavingAccount(String name, double money) throws IOException {
		SavingAccount newAccount = new SavingAccount(name, money);
		savingAccounts.add(newAccount);
		saveData("savingA");
	}
	
	public void createCreditAccount(String name, double interest, double quota) throws IOException {
		CreditAccount newAccount = new CreditAccount(name, interest, quota);
		creditAccounts.add(newAccount);
		saveData("credit");
	}
	
	public void createDebt (String name, double interest, int fee, double money) throws IOException {
		Debt newDebt = new Debt(name, money, interest, fee);
		debts.add(newDebt);
		saveData("debt");
	}
	
	public void createSaving(String name, double money) throws IOException {
		Saving newSaving = new Saving(name, money);
		savings.add(newSaving);
		saveData("savings");
	}
	
	/**
	 * 0 for saving account, 1 for credit, 2 for saving, 3 for debt
	 *
	 */
	public Account accountExist(int type, String name) {

		Account someaccount = null;
		switch (type) {
		case 0:
			for (int i = 0; i <savingAccounts.size(); i++) {
				if(savingAccounts.get(i).getName().equals(name)) {

					someaccount = savingAccounts.get(i);
				}
			}
			break;
		case 1:
			for (int i = 0; i <creditAccounts.size(); i++) {
				if(creditAccounts.get(i).getName().equals(name)) {
		
					someaccount=creditAccounts.get(i);
				}
			}
			break;

		default:
			break;
		}
		
		
		return someaccount;
	}
	
	public MoneyManagement accountExistM(int type, String name) {
		MoneyManagement someManagement = null;
		
		switch (type) {
		case 2:
			for (int i = 0; i <savings.size(); i++) {
				if(savings.get(i).getNameMoneyManagment().equals(name)) {
					
					someManagement= savings.get(i);
				}
			}
			break;
		case 3:
			for (int i = 0; i <debts.size(); i++) {
				if(debts.get(i).getNameMoneyManagment().equals(name)) {
					someManagement = debts.get(i);
				}
			}
			
			break;
		default:
			break;
		}
		
		return someManagement;
	}
	
	//-------------------------------------------------------------
	
	//----------------------- export all the data -----------------
	
	public void exportAllData(PrintWriter filePath) throws FileNotFoundException {
		//PrintWriter pw = new PrintWriter(new File(filePath+"/output.csv"));

		Thread saving = new Thread() {
			@Override
			public void run()	//Anonymous class overriding run() method of Thread class
			{
				for (int i = 0; i < savingAccounts.size(); i++) {
					savingAccounts.get(0).exportData(filePath);
				}
			}
		};
		saving.start();
		Thread credit = new Thread() {
			@Override
			public void run()	//Anonymous class overriding run() method of Thread class
			{
				for (int i = 0; i < creditAccounts.size(); i++) {
					creditAccounts.get(0).exportData(filePath);
				}
			}
		};
		credit.start();
		
		Thread debt = new Thread() {
			@Override
			public void run()	//Anonymous class overriding run() method of Thread class
			{
				for (int i = 0; i < debts.size(); i++) {
					debts.get(0).exportData(filePath);
				}
			}
		};
		debt.start();
		
		Thread savings = new Thread() {
			@Override
			public void run()	//Anonymous class overriding run() method of Thread class
			{
				for (int i = 0; i < CashManager.this.savings.size(); i++) {
					CashManager.this.savings.get(0).exportData(filePath);
				}
			}
		};
		savings.start();

	}
	
	
	//-------------------------------------------------------------
	
	//----------------------------- create categories ----------------------------
	
	public void categoryExist(String name, String type) throws ExistingCategoryException {
		
		switch (type) {
		case "INCOME":
			
			for (int i = 0; i < categoryIncome.size(); i++) {
				if(categoryIncome.get(i).getName().equals(name)) {
					throw new ExistingCategoryException(name);
				}
			}
			
			break;
		case "SPEND":
			
			for (int i = 0; i < categorySpend.size(); i++) {
				if(categorySpend.get(i).getName().equals(name)) {
					throw new ExistingCategoryException(name);
				}
			}
			
			break;
		default:
			break;
		}
		
	}
	
	public void createCateogry(String name, String type) throws IOException {
		
		Category newOne = new Category(name, CategoryType.valueOf(type));
		
		switch (type) {
		case "SPEND":
			categorySpend.add(newOne);
			saveData("categoryS");
			break;
		
		case "INCOME":
			categoryIncome.add(newOne);
			saveData("categoryI");
			break;

		default:
			break;
		}
		
	}
	
	public void setDefaultCategories() throws IOException {
		createCateogry("Comida", "SPEND");
		createCateogry("Transporte", "SPEND");
		createCateogry("Entretenimiento", "SPEND");
		createCateogry("Educación", "SPEND");
		createCateogry("Salud", "SPEND");
		
		createCateogry("Salario", "INCOME");
		createCateogry("Deuda a favor", "INCOME");
		createCateogry("Dinero extra", "INCOME");
		createCateogry("Otro", "INCOME");

	}
	
	
	//----------------------------------------------------------------------
	
	

	public ArrayList<CreditAccount> getCreditAccounts() {
		return creditAccounts;
	}

	public void setCreditAccounts(ArrayList<CreditAccount> creditAccounts) {
		this.creditAccounts = creditAccounts;
	}

	public ArrayList<SavingAccount> getSavingAccounts() {
		return savingAccounts;
	}

	public void setSavingAccounts(ArrayList<SavingAccount> savingAccounts) {
		this.savingAccounts = savingAccounts;
	}

	public ArrayList<Debt> getDebts() {
		return debts;
	}

	public void setDebts(ArrayList<Debt> debts) {
		this.debts = debts;
	}

	public ArrayList<Saving> getSavings() {
		return savings;
	}

	public void setSavings(ArrayList<Saving> savings) {
		this.savings = savings;
	}

	public ArrayList<Category> getCategorySpend() {
		return categorySpend;
	}

	public void setCategorySpend(ArrayList<Category> categorySpend) {
		this.categorySpend = categorySpend;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movement getRootMovements() {
		return rootMovements;
	}

	public void setRootMovements(Movement rootLastMovements) {
		this.rootMovements = rootLastMovements;
	}
	
	//Methods to add movements in the binary search tree---------------------------
	public void addMovement(Movement newMovement) {
		if (rootMovements == null) {
			rootMovements = newMovement;
		} 
		else {
			addMovement(rootMovements, newMovement);
		}
	}

	private void addMovement(Movement currentMovement, Movement newMovement) {
		int i = newMovement.getDate().compareTo(currentMovement.getDate());
		if (i == -1 || i == 0) { 
			if (currentMovement.getLeft() == null) {
				currentMovement.setLeft(newMovement);
			}
			else {
				addMovement(currentMovement.getLeft(), newMovement);
			}
		} 
		else {
			if (currentMovement.getRight() == null) {
				currentMovement.setRight(newMovement);
			} 
			else {
				addMovement(currentMovement.getRight(), newMovement);
			}
		}
	}
	// ----------------------------------------------------------------------------------

	// Methods to traverse the tree turning it into ArrayList----------------------------
	public ArrayList<Movement> inOrden() {
		ArrayList<Movement> movementsArrayList = new ArrayList<Movement>();
		inOrden2(rootMovements, movementsArrayList);
		return movementsArrayList;
	}

	private void inOrden2(Movement movement, ArrayList<Movement> movementsArrayList) {
		if (movement == null) {
			return;
		}
		inOrden2(movement.getLeft(), movementsArrayList);
		movementsArrayList.add(movement);
		inOrden2(movement.getRight(), movementsArrayList);
	}
	// ----------------------------------------------------------------------------------

	public ArrayList<Category> getCategoryIncome() {
		return categoryIncome;
	}

	public void setCategoryIncome(ArrayList<Category> categoryIncome) {
		this.categoryIncome = categoryIncome;
	}
	
}
