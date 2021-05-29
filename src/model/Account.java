package model;

import java.util.ArrayList;

public class Account {
	
	private String name;
	private Movement spend;
	private Movement betweenAccount;
	private ArrayList<Category> categorySpend;

	
	public Account(String name, Movement firstIncon, Movement spend, Movement betweenAccount) {

		this.name = name;
		this.spend = spend;
		this.betweenAccount = betweenAccount;
		this.categorySpend = new ArrayList<Category>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movement getSpend() {
		return spend;
	}

	public void setSpend(Movement spend) {
		this.spend = spend;
	}

	public Movement getBetweenAccount() {
		return betweenAccount;
	}

	public void setBetweenAccount(Movement betweenAccount) {
		this.betweenAccount = betweenAccount;
	}

	public ArrayList<Category> getCategorySpend() {
		return categorySpend;
	}

	public void setCategorySpend(ArrayList<Category> categorySpend) {
		this.categorySpend = categorySpend;
	}
	
	
}
