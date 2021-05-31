package model;

public class SavingAccount extends Account{
	
	
	private double availableMoney;
	private Movement firstIncome;
	
	public SavingAccount(String name, double money) {
		super(name);
		
		availableMoney = money;
	}

	public Movement getFirstIncome() {
		return firstIncome;
	}

	public void setFirstIncome(Movement firstIncome) {
		this.firstIncome = firstIncome;
	}

	

	public double getAvailableMoney() {
		return availableMoney;
	}

	public void setAvailableMoney(double availableMoney) {
		this.availableMoney = availableMoney;
	}
}
