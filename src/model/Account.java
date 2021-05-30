package model;

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

	public void setSpend(Movement spend) {
		this.spend = spend;
	}

	public Movement getBetweenAccount() {
		return betweenAccount;
	}

	public void setBetweenAccount(Movement betweenAccount) {
		this.betweenAccount = betweenAccount;
	}

	
	
}
