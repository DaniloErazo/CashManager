package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class Account {
	
	private String name;
	private Movement spend;
	private Movement betweenAccount;
	
	public Account(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movement getSpend() {
		return spend;
	}
	
	public void addSpend(Movement newMovement) {
		if(spend==null) {
			spend=newMovement;
		}else {
			addSpend(spend.getNextSpend(), newMovement);
		}
	}
	
	private void addSpend(Movement current, Movement newMovement) {
		if(current==null) {
			current=newMovement;
		}else {
			addSpend(current.getNextSpend(), newMovement);
		}
	}


	public void setSpend(Movement spend) {
		this.spend = spend;
	}

	public Movement getBetweenAccount() {
		return betweenAccount;
	}

	public void setBetweenAccount(Movement betweenAccount) {
		this.betweenAccount = betweenAccount;
	}
	
	
	public void exportData(File filePath) throws FileNotFoundException {
	PrintWriter pw = new PrintWriter(new File(filePath+"/output.csv"));
	Movement curr = spend;
	pw.println("Monto" + "," + "Categoría" + "," + "Fecha" + "," + "Descripción");
	while (curr != null) {
		pw.println(curr.getAmount() + "," + curr.getCategory() + "," + curr.getDate() + "," + curr.getDescription());
	      curr = curr.getNextSpend();
	    }
	
	pw.close();
	}

	
	
}
