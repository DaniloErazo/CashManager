package model;

import java.util.ArrayList;

public class Saving extends MoneyManagement {
	
	private ArrayList<Movement> payments; //sorting
	private int count; //abonos
 
	public Saving() {}
	
	public Saving(String name, double max) {
		super(name, max);
	}
	
	public void addPay(Movement pay) {
		
		payments.add(pay);
	}
	
	public double totalPayment() {
		
		double payment=0;
		
		for (int i = 0; i < payments.size(); i++) {
			payment+=payments.get(i).getAmount();
		}
		
		return payment;
		
	}
	
	@Override
	public double[] getAnalysisData() {
		
		double[] data = new double[2];
		
		data[1] = super.getMaxAmount()- totalPayment();
		
		return data;
		
	}
	
	public ArrayList<Movement> getPayments() {
		return payments;
	}
	public void setPayments(ArrayList<Movement> payments) {
		this.payments = payments;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
		
}
