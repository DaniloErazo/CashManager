package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MoneyManagement {

	private String nameMoneyManagment;
	private double maxAmount;
	private Movement root;
	private Movement first;

	public MoneyManagement() {
	}

	public MoneyManagement(String name, double max) {
		nameMoneyManagment = name;
		maxAmount = max;

	}

	public String getNameMoneyManagment() {
		return nameMoneyManagment;
	}

	public void setNameMoneyManagment(String nameMoneyManagment) {
		this.nameMoneyManagment = nameMoneyManagment;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Movement getRoot() {
		return root;
	}

	public void setRoot(Movement root) {
		this.root = root;
	}

	public void exportData(File filePath) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(filePath + "/output.csv"));

		pw.println("Monto" + "," + "Categoría" + "," + "Fecha" + "," + "Descripción");

		printExport(root, pw);

		pw.close();
	}

	public void exportData(PrintWriter pw) {
		printExport(root, pw);

		pw.close();
	}

	public void printExport(Movement root, PrintWriter pw) {
		if (root == null) {

		} 
		else {
			printExport(root.getLeft(), pw);
			pw.println(root.exportMovement());
			printExport(root.getRight(), pw);
		}

	}

	// Method to add a movement in the double linked list of-----------------------
	public void addMovement(Movement newMovement) {

		if (first == null) {
			first = newMovement;
			first.setNext(newMovement);
			first.setPrevious(newMovement);
		} 
		else {
			Movement lastMovement = first.getPrevious();
			newMovement.setNext(first);
			newMovement.setPrevious(lastMovement);
			first.setPrevious(newMovement);
			lastMovement.setNext(newMovement);
		}
	}
	//------------------------------------------------------------------------------

}
