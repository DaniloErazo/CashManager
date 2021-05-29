package model;

import java.util.ArrayList;

public class SavingAccount extends Account{

	private Movement firstIncome;
	private ArrayList<Category> categoryIncome;
	
	public SavingAccount(String name, Movement firstIncon, Movement spend, Movement betweenAccount) {
		super(name, firstIncon, spend, betweenAccount);
		categoryIncome = new ArrayList<>();
	}

	public Movement getFirstIncome() {
		return firstIncome;
	}

	public void setFirstIncome(Movement firstIncome) {
		this.firstIncome = firstIncome;
	}

	public ArrayList<Category> getCategoryIncome() {
		return categoryIncome;
	}

	public void setCategoryIncome(ArrayList<Category> categoryIncome) {
		this.categoryIncome = categoryIncome;
	}
}
