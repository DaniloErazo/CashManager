package model;

public class Debt extends MoneyManagement implements Fee {

	private double interest;
	private int fees;
	private double balance;
	
	public Debt(String name, double max, double interest, int fees) {
		super(name, max);
		this.interest=interest;
		this.fees=fees;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public double calcultateMonthFee() {
		return 0;
	}

}
