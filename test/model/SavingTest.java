package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SavingTest {


	private Saving savingTest;
	
	public void setupScenary5() {
		savingTest = new Saving("Apartamento", 1000000);
		savingTest.addMovement(new Movement("zapatos", 100000, "", "", MovementType.PAYMENT));
	}
	
	@Test
	public void getAnalysisTest() {
		setupScenary5();
		
		double[] data = savingTest.getAnalysisData();
		
		assertEquals(900000, data[0]);
	}

}
