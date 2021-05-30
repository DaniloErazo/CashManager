package model;

import java.util.ArrayList;

public class SavingAccount extends Account{
	
	
	private double availableMoney;
	private Movement firstIncome;
	private ArrayList<Category> categoryIncome;
	
	public SavingAccount(String name, double money) {
		super(name);
		
		availableMoney = money;
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

	public double getAvailableMoney() {
		return availableMoney;
	}

	public void setAvailableMoney(double availableMoney) {
		this.availableMoney = availableMoney;
	}
}
