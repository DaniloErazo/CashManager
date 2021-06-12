package model;

import java.util.ArrayList;

public class Saving extends MoneyManagement {
	
	private ArrayList<Movement> payments; //sorting
	private int count; //abonos
 
	public Saving() {}
	
	public Saving(String name, double max) {
		super(name, max);
		payments = new ArrayList<Movement>();
	}
	
	
	@Override
	public double[] getAnalysisData() {
		
		double[] data = new double[2];
		
		//calculate how much money is missing to complete the goal
		data[0] = super.getMaxAmount()- totalPayment();
		
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
