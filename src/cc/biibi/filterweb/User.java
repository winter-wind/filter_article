package cc.biibi.filterweb;

import java.util.List;

public class User {

	//�û���
	private String username;
	//�û���Ӧ��Ȩ��
	private List<Authority> authorities;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public User(String username, List<Authority> authorities) {
		super();
		this.username = username;
		this.authorities = authorities;
	}
	public User() {
		
	}
	
	
}
