package model;

public class MoneyManagement {
	
	private String nameMoneyManagment;
	private double maxAmount;
	private Movement root;
	
	public MoneyManagement(String name, double max) {
		
		nameMoneyManagment=name;
		maxAmount = max;
		
	}
	
	public String getNameMoneyManagment() {
		return nameMoneyManagment;
	}
	public void setNameMoneyManagment(String nameMoneyManagment) {
		this.nameMoneyManagment = nameMoneyManagment;
	}
	public Double getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}
	public Movement getRoot() {
		return root;
	}
	public void setRoot(Movement root) {
		this.root = root;
	}

}
