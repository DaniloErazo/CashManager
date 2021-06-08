package model;

public class Category {

	private String name;
	private int count;
	private CategoryType type;
	private Category rootMovement;
	
	public Category(String name, CategoryType type) {
		
		this.name=name;
		this.setType(type);
		
	}

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

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

}
