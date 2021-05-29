package model;

import java.util.ArrayList;

public class CashManager {

	private ArrayList<CreditAccount> creditAccounts;
	private ArrayList<SavingAccount> savingAccounts;
	private ArrayList<Debt> debts;
	private ArrayList<Saving> savings;

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

}
