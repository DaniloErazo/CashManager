package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class AccountTest {

	private Account testAccount;
	
	public void setupScenary1() throws FileNotFoundException {
		
		testAccount = new SavingAccount("Bancolombia", 400000);
		

		
		Movement testMove = new Movement("Bancolombia", 30000, "////", "X somos chavos", MovementType.INCOME, "any");
		
		testAccount.addMovement(testMove);
		
		
		
	}
	
	
	//Añadir libreria externa Apache Commons IO para poder verificar que los documentos sean los mismos, mientras verificación manual
	@Test
	public void exportData() throws FileNotFoundException {
		setupScenary1();
		
		File test = new File("C:/Users/vicen/Desktop/U");
		testAccount.exportData(test);
		
	}

}
