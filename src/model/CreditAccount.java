package model;

public class CreditAccount extends Account implements Fee{

	private double interest;
	private double monthFee;
	private double maxQuota;
	
	public CreditAccount(String name, Movement firstIncon, Movement spend, Movement betweenAccount, double interest, double monthFee, double maxQuota) {
		super(name, firstIncon, spend, betweenAccount);
		this.interest = interest;
		this.monthFee = monthFee;
		this.maxQuota = maxQuota;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getMonthFee() {
		return monthFee;
	}

	public void setMonthFee(double monthFee) {
		this.monthFee = monthFee;
	}

	public double getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(double maxQuota) {
		this.maxQuota = maxQuota;
	}

	@Override
	public double calcultateMonthFee() {
		return 0;
	}

}
