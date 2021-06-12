package model;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.poi.ss.formula.functions.Finance;
import org.junit.jupiter.api.Test;

class DebtTest {

	private Debt debtTest;
	
	public void setupScenary4() {
		debtTest = new Debt("Zapatos", 120000, 0.03, 12);
		debtTest.addMovement(new Movement("Zapatos", 40000, "///", "none", MovementType.PAYMENT));
	}
	
	@Test
	public void addMovementTest() {
		setupScenary4();
		Movement added = debtTest.getFirst();
		
		assertEquals(1, debtTest.getFeesPayed());
		assertEquals(40000, added.getAmount());
		assertEquals(40000, debtTest.totalPayment());
		
		
	}
	
	@Test
	public void getAnalysisData() {
		setupScenary4();
		
		double[] analysis = debtTest.getAnalysisData();
		
		double interestPaid = Finance.ipmt(0.03, 1, 12, -120000);
		double capitalPaid = Finance.ppmt(0.03, 1, 12, -120000);
		
		
		double totalInterest =((Debt)debtTest).getInterest()*debtTest.getMaxAmount();
		double totalDebt = debtTest.getMaxAmount()+totalInterest;
		
		double remainder = totalDebt-(interestPaid+capitalPaid);
		double balance = interestPaid+capitalPaid;
		
		System.out.println("interes pagado: " + interestPaid + " capital pagado: " + capitalPaid);
		System.out.println("falta: " + remainder + " balance general: "+ balance);
		
		assertEquals(interestPaid, analysis[0]);
		assertEquals(capitalPaid, analysis[1]);
	}

}
