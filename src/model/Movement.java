package model;

import java.sql.Date;

public class Movement {

	private MovementType type;
	private double amount;
	private Date date;
	private String description;
	private String code;
	private Movement nextIncome;
	private Movement nextSpend;
	private Movement right;
	private Movement left;

	public Movement(MovementType type, double amount, Date date, String description, String code, Movement nextIncome,
			Movement nextSpend) {
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.code = code;
		this.setNextIncome(nextIncome);
		this.setNextSpend(nextSpend);
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Movement getNextIncome() {
		return nextIncome;
	}

	public void setNextIncome(Movement nextIncome) {
		this.nextIncome = nextIncome;
	}

	public Movement getNextSpend() {
		return nextSpend;
	}

	public void setNextSpend(Movement nextSpend) {
		this.nextSpend = nextSpend;
	}

	public Movement getRight() {
		return right;
	}

	public void setRight(Movement right) {
		this.right = right;
	}

	public Movement getLeft() {
		return left;
	}

	public void setLeft(Movement left) {
		this.left = left;
	}

}
