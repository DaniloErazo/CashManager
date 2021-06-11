package model;

public class User {
	
	private String name;
	private String password;
	private String keyQuestion;
	private String keyAnswer;
	
	public User(String p, String kq, String ka) {
		setPassword(p);
		setKeyQuestion(kq);
		setKeyAnswer(ka);
	}
	
	public User(String n) {
		setName(n);
	}
	
	public User() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeyQuestion() {
		return keyQuestion;
	}

	public void setKeyQuestion(String keyQuestion) {
		this.keyQuestion = keyQuestion;
	}

	public String getKeyAnswer() {
		return keyAnswer;
	}

	public void setKeyAnswer(String keyAnswer) {
		this.keyAnswer = keyAnswer;
	}
	
}
