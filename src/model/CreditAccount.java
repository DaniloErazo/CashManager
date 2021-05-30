package model;

public class CreditAccount extends Account implements Fee{

	private double interest;
	private double monthFee;
	private double maxQuota;
	
	public CreditAccount(String name, double interest, double maxQuota) {
		super(name);
		this.interest = interest;
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
