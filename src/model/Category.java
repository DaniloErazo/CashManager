package model;

public class Category {

	private String name;
	private int count;
	private Category rootMovement;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getRootMovement() {
		return rootMovement;
	}

	public void setRootMovement(Category rootMovement) {
		this.rootMovement = rootMovement;
	}

}
