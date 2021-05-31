package model;

import java.util.ArrayList;

public class CashManager {

	private ArrayList<CreditAccount> creditAccounts;
	private ArrayList<SavingAccount> savingAccounts;
	private ArrayList<Debt> debts;
	private ArrayList<Saving> savings;
	private ArrayList<Category> categorySpend;
	private Movement root;
	private User user;
	
	
	public CashManager() {
		creditAccounts = new ArrayList<>();
		savingAccounts = new ArrayList<>();
		debts = new ArrayList<>();
		savings = new ArrayList<>();
		categorySpend = new ArrayList<>();
	}
	
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
	public boolean accountExist(int type, String name) {
		boolean exist = false;
		switch (type) {
		case 0:
			for (int i = 0; i <savingAccounts.size(); i++) {
				if(savingAccounts.get(i).getName().equals(name)) {
					exist=true;
				}
			}
			break;
		case 1:
			for (int i = 0; i <creditAccounts.size(); i++) {
				if(creditAccounts.get(i).getName().equals(name)) {
					exist=true;
				}
			}
			break;
		case 2:
			for (int i = 0; i <savings.size(); i++) {
				if(savings.get(i).getNameMoneyManagment().equals(name)) {
					exist=true;
				}
			}
			break;
		case 3:
			for (int i = 0; i <debts.size(); i++) {
				if(debts.get(i).getNameMoneyManagment().equals(name)) {
					exist=true;
				}
			}
			break;
		default:
			break;
		}
		
		
		return exist;
	}

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

	public Movement getRoot() {
		return root;
	}

	public void setRoot(Movement root) {
		this.root = root;
	}

		//Methods to manage the binary search tree--------------------------------------------------
		public void addMovement(Movement newMovement) {
			if (root == null) {
				root = newMovement;
			}
			else {
				addMovement(root, newMovement);
			}
		}
		
	private void addMovement(Movement currentMovement, Movement newMovement) {
		int i = currentMovement.getDate().compareTo(newMovement.getDate());
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
		
	//----------------------------------------------------------------------------------

}
