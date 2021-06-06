package model;

public class Movement {
	
	private String account;
	private double amount;
	private String date;
	private String description;
	private MovementType type;
	private String category;
//	private String code;
//	private Movement nextIncome;
//	private Movement nextSpend;
	private Movement left, right;

	public Movement(String account, double amount, String date, String description, MovementType type, String category) {
		this.setAccount(account);
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.type = type;
		this.setCategory(category);
//		this.code = code;
//		this.setNextIncome(nextIncome);
//		this.setNextSpend(nextSpend);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

