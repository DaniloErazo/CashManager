package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CashManagerTest {
	
	private CashManager cashManager;
	
	public void setupScenary1() {
		cashManager = new CashManager();
	}
	
	public void setupScenary2() {
		cashManager = new CashManager();
		
		cashManager.createSavingAccount("Bancolombia", 400000);
		cashManager.createCreditAccount("Davivienda", 0.56, 3000000);
		cashManager.createDebt("Zapatos", 0.03, 12, 220000);
		cashManager.createSaving("Apartamento", 4000000);
		
		
	}
	
	@Test
	public void createSavingAccountTest() {
		setupScenary1();
		
		cashManager.createSavingAccount("Bancolombia", 400000);
		
		String name = cashManager.getSavingAccounts().get(0).getName();
		double money = cashManager.getSavingAccounts().get(0).getAvailableMoney();
		
		assertEquals(name, "Bancolombia");
		assertEquals(400000, money);
		assertEquals(1, cashManager.getSavingAccounts().size());
		
	}
	
	@Test
	public void createCreditAccountTest() {
		
		setupScenary1();
		
		cashManager.createCreditAccount("Davivienda", 0.56, 3000000);
		String name = cashManager.getCreditAccounts().get(0).getName();
		double money = cashManager.getCreditAccounts().get(0).getMaxQuota();
		double interest = cashManager.getCreditAccounts().get(0).getInterest();
		
		assertEquals(name, "Davivienda");
		assertEquals(3000000, money);
		assertEquals(interest, 0.56);
		assertEquals(1, cashManager.getCreditAccounts().size());
	}
	
	@Test 
	public void createDebtTest() {
		
		setupScenary1();
		
		cashManager.createDebt("Zapatos", 0.03, 12, 220000);
		
		String name = cashManager.getDebts().get(0).getNameMoneyManagment();
		double interest = cashManager.getDebts().get(0).getInterest();
		int fees = cashManager.getDebts().get(0).getFees();
		double money = cashManager.getDebts().get(0).getMaxAmount();
		
		assertEquals(name, "Zapatos");
		assertEquals(interest, 0.03);
		assertEquals(fees, 12);
		assertEquals(money, 220000);
		assertEquals(1, cashManager.getDebts().size());
		
	}
	
	@Test
	
	public void createSingleSavingTest() {
		setupScenary1();
		
		cashManager.createSaving("Apartamento", 4000000);
		
		String name = cashManager.getSavings().get(0).getNameMoneyManagment();
		double money = cashManager.getSavings().get(0).getMaxAmount();
		
		
		assertEquals(name, "Apartamento");
		assertEquals(money, 4000000);
		assertEquals(1, cashManager.getSavings().size());
		
	}
	
	@Test
	public void accountExistTest() {
		
		setupScenary2();
		
		boolean normalAccount = cashManager.accountExist(0, "Bancolombia");
		boolean creditAccount = cashManager.accountExist(1, "Davivienda");
		boolean saving = cashManager.accountExist(2, "Apartamento");
		boolean debt = cashManager.accountExist(3, "Zapatos");
		boolean creditAccount2 = cashManager.accountExist(1, "Bancolombia");
		
		assertEquals(true, normalAccount);
		assertEquals(true, creditAccount);
		assertEquals(true, saving);
		assertEquals(true, debt);
		assertEquals(false, creditAccount2);
		
	}
	


}
