package model;

import java.util.ArrayList;

public class Saving extends MoneyManagement {
 
	private ArrayList<Movement> payments; //sorting
	private int count; //abonos
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
