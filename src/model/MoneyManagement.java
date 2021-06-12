package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class MoneyManagement implements Analysis, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public Movement getFirst() {
		return first;
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
	
	public double totalPayment() {
		double paid = 0;
		if (first!=null) {
			paid = first.getAmount();
			paid += totalPayment(first.getNext());
		}
		return paid;
	}
	
	private double totalPayment(Movement current) {
		double paid = 0;
		if (current != first) {
			paid = current.getAmount();
			current = current.getNext();
			paid += totalPayment(current);
		}
		return paid;
	}

	// Method to add a movement in the double linked list of movements-----------------------
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
	
	//Methods to traverse the list--------------------------------------------------
	private boolean empty() {
		if (first == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Movement> showMovements() {
		ArrayList<Movement> movements = new ArrayList<>();
		if (!empty()) {
			movements.add(first);
			movements.add(showMovements2(first));
		}
		return movements;
	}
	
	private Movement showMovements2(Movement aux) {
		Movement movement = null;
		if (aux != null) {
			movement = aux.getNext();
		}
		return movement;
	}
	//------------------------------------------------------------------------------
}
