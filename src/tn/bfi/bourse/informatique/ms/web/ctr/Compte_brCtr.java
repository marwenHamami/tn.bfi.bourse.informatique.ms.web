package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import tn.bfi.bourse.informatique.ms.entity.User;
import tn.bfi.bourse.informatique.ms.local.UserEJBLocal;


public class Compte_brCtr {

	private User selectedUser;
	private User user = new User();
	private List<User> users = new ArrayList<User>();

	@ManagedProperty(value = "#{userCtr}")
	UserCtr userCtr;
	@EJB
	UserEJBLocal userEJBLocal;
	public User getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public UserCtr getUserCtr() {
		return userCtr;
	}
	public void setUserCtr(UserCtr userCtr) {
		this.userCtr = userCtr;
	}
	public UserEJBLocal getUserEJBLocal() {
		return userEJBLocal;
	}
	public void setUserEJBLocal(UserEJBLocal userEJBLocal) {
		this.userEJBLocal = userEJBLocal;
	}
	
	

}
