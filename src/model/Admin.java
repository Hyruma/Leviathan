package model;

import javax.persistence.*;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	@Column(unique= true, nullable= false)
	private String username;
	@Column(unique= true, nullable= false)
	private String password;

	public Admin(){
	}

	public Admin(String username, String password){
		this.username= username;
		this.password= password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean checkPassword(String password){
		return this.password.equals(password);
	}
	
	@Override
	public int hashCode(){
		return this.username.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		Admin that= (Admin) o;
		return this.username.equals(that.getUsername());
	}
}
