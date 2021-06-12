package model;

import org.apache.poi.ss.formula.functions.Finance;

public class Debt extends MoneyManagement implements Fee{

	private double interest;
	private int fees;
	private double balance;
	private int feesPayed;
	
	public Debt() {}
	
	public Debt(String name, double max, double interest, int fees) {
		super(name, max);
		this.interest=interest;
		this.fees=fees;
		feesPayed=0;
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
		
		double fee = Finance.pmt(interest, fees, - super.getMaxAmount());
		
		return fee;
		
	}
	
	
	

	public int getFeesPayed() {
		return feesPayed;
	}

	public void setFeesPayed(int feesPayed) {
		this.feesPayed = feesPayed;
	}
	
	/**
	 * 
	 *array, first position for paid interest, second for principal payment
	 */

	@Override
	public double[] getAnalysisData() {
		
		double[] data = new double[2];
		
		double capital = super.getMaxAmount()*-1;
		
		if(feesPayed!=0) {
			double interestPaid = Finance.ipmt(interest, feesPayed, fees, capital);
			
			double moneyPaid = Finance.ppmt(interest, feesPayed, fees, -super.getMaxAmount());
			data[0] = interestPaid;
			data[1] = moneyPaid;
		}else {
			
			data[0] = 0;
			data[1] = 0;
			
		}
		
		
		return data;
		
	}
	
	@Override
	public void addMovement(Movement newMovement) {
		feesPayed++;
		balance=totalPayment();
		super.addMovement(newMovement);
	}

}
