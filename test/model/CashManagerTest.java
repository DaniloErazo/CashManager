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
	
	public void setupScenary3() { //Already exist a movement
		cashManager = new CashManager();
		Movement movement = new Movement("Nequi", 12000, "2021/05/30 22:40:15", "Compre unas luces led", MovementType.SPEND, "Gustos");
		cashManager.addMovement(movement);
		
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
	
	@Test
	public void addMovement() {
		setupScenary1();
		
		Movement movement = new Movement("Nequi", 12000, "2021/05/30 22:40:15", "Compre unas luces led", MovementType.SPEND, "Gustos");
		cashManager.addMovement(movement);
		
		assertEquals("Nequi", cashManager.getRootMovements().getAccount());
		assertEquals(12000, cashManager.getRootMovements().getAmount());
		assertEquals("2021/05/30 22:40:15", cashManager.getRootMovements().getDate());
		assertEquals("Compre unas luces led", cashManager.getRootMovements().getDescription());
		assertEquals(MovementType.SPEND, cashManager.getRootMovements().getType());
		assertEquals("Gustos", cashManager.getRootMovements().getCategory());
		
	}
	
	@Test
	public void addMovement2() {
		setupScenary3();
		
		Movement movement = new Movement("Daviplata", 8000, "2021/05/29 22:40:15", "Prima navideña", MovementType.INCOME, "Prima");
		cashManager.addMovement(movement);

		assertEquals("Daviplata", cashManager.getRootMovements().getLeft().getAccount());
		assertEquals(8000, cashManager.getRootMovements().getLeft().getAmount());
		assertEquals("2021/05/29 22:40:15", cashManager.getRootMovements().getLeft().getDate());
		assertEquals("Prima navideña", cashManager.getRootMovements().getLeft().getDescription());
		assertEquals(MovementType.INCOME, cashManager.getRootMovements().getLeft().getType());
		assertEquals("Prima", cashManager.getRootMovements().getLeft().getCategory());
	}

}
