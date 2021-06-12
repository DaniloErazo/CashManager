package model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class Account implements Analysis {

	private String name;
	private Movement betweenAccount;
	private Movement root;

	public Account() {} //Default constructor
	
	public Account(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Movement getRoot() {
		return root;
	}



	public Movement getBetweenAccount() {
		return betweenAccount;
	}

	public void setBetweenAccount(Movement betweenAccount) {
		this.betweenAccount = betweenAccount;
	}
	
	
	public void exportData(File filePath) throws FileNotFoundException {
	PrintWriter pw = new PrintWriter(new File(filePath+"/output.csv"));

	pw.println("Monto" + "," + "Categoría" + "," + "Fecha" + "," + "Descripción");
	
	
	printExport(root, pw);
	
	pw.close();
	}
	
	public void exportData(PrintWriter pw) {
		printExport(root, pw);
		
		pw.close();
	}
	
	public void printExport(Movement root, PrintWriter pw){
	    if (root == null) {
	    	
	    }else {
	    	printExport(root.getLeft(), pw);
		    pw.println(root.exportMovement());
		    printExport(root.getRight(), pw);
	    }
  
	}

	// Methods to add movements in the binary search tree--------------------------------------------------
	public void addMovement(Movement newMovement) {
		if (root == null) {
			root = newMovement;
		} 
		else {
			addMovement(root, newMovement);
		}
	}

	private void addMovement(Movement currentMovement, Movement newMovement) {
		int i = newMovement.getDate().compareTo(currentMovement.getDate());
		if (i == -1 || i == 0) { 
			if (currentMovement.getLeft() == null) {
				currentMovement.setLeft(newMovement);
			}
			else {
				addMovement(currentMovement.getLeft(), newMovement);
			}
		} 
		else {
			if (currentMovement.getRight() == null) {
				currentMovement.setRight(newMovement);
			} 
			else {
				addMovement(currentMovement.getRight(), newMovement);
			}
		}
	}
	// ----------------------------------------------------------------------------------

	//Methods to traverse the tree turning it into ArrayList----------------------------
	public ArrayList<Movement> inOrden() {
		ArrayList<Movement> movementsArrayList = new ArrayList<Movement>();
		inOrden2(root, movementsArrayList);
		return movementsArrayList;
	}

	private void inOrden2(Movement movement, ArrayList<Movement> movementsArrayList) {
		if (movement == null) {
			return;
		}
		inOrden2(movement.getLeft(), movementsArrayList);
		movementsArrayList.add(movement);
		inOrden2(movement.getRight(), movementsArrayList);
	}
	// ----------------------------------------------------------------------------------

}
