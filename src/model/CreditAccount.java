package model;

import java.util.ArrayList;

public class CreditAccount extends Account implements Fee{

	private double interest;
	private double monthFee;
	private double maxQuota;
	
	public CreditAccount() {}
	
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
	
	
	//------------------ methods to analyze info ----------------------------
	
	public ArrayList<String[]> inOrdenDiff() {
		ArrayList<String[]> movementsArrayList = new ArrayList<String[]>();
		inOrden2Diff(super.getRoot(), movementsArrayList);
		return movementsArrayList;
	}
	
	private void inOrden2Diff(Movement movement, ArrayList<String[]> movementsArrayList) {
		if (movement == null) {
			return;
		}
		
		String[] categoryAmount = new String[2];
		inOrden2Diff(movement.getLeft(), movementsArrayList);
		categoryAmount[0]= movement.getCategory();
		categoryAmount[1]=String.valueOf(movement.getAmount());
		movementsArrayList.add(categoryAmount);
		inOrden2Diff(movement.getRight(), movementsArrayList);
	}
	
	
	@Override
	public double[] getAnalysisData() {
		
		return null;
		
	}

}
