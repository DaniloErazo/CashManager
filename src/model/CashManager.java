package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import exceptions.ExistingCategoryException;

public class CashManager {

	private ArrayList<CreditAccount> creditAccounts;
	private ArrayList<SavingAccount> savingAccounts;
	private ArrayList<Debt> debts;
	private ArrayList<Saving> savings;
	private ArrayList<Category> categorySpend;
	private ArrayList<Category> categoryIncome;
	
	private User user;
	private Movement rootMovements; //This binary search three save the movements in general, They will show to the user in the main page
	
	public CashManager() {
		creditAccounts = new ArrayList<>();
		savingAccounts = new ArrayList<>();
		debts = new ArrayList<>();
		savings = new ArrayList<>();
		categorySpend = new ArrayList<>();
		categoryIncome = new ArrayList<>();
	}
	
	
	//-------------------- Create accounts ----------------------------------
	public void createSavingAccount(String name, double money) {
		SavingAccount newAccount = new SavingAccount(name, money);
		savingAccounts.add(newAccount);
	}
	
	public void createCreditAccount(String name, double interest, double quota) {
		CreditAccount newAccount = new CreditAccount(name, interest, quota);
		creditAccounts.add(newAccount);
	}
	
	public void createDebt (String name, double interest, int fee, double money) {
		Debt newDebt = new Debt(name, money, interest, fee);
		debts.add(newDebt);
	}
	
	public void createSaving(String name, double money) {
		Saving newSaving = new Saving(name, money);
		savings.add(newSaving);
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
	
	public void createCateogry(String name, String type) {
		
		Category newOne = new Category(name, CategoryType.valueOf(type));
		
		switch (type) {
		case "SPEND":
			categorySpend.add(newOne);
			break;
		
		case "INCOME":
			categoryIncome.add(newOne);
			break;

		default:
			break;
		}
		
	}
	
	public void setDefaultCategories() {
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
