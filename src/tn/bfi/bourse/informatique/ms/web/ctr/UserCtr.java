package tn.bfi.bourse.informatique.ms.web.ctr;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Admin;
import tn.bfi.bourse.informatique.ms.entity.Client;
import tn.bfi.bourse.informatique.ms.entity.User;
import tn.bfi.bourse.informatique.ms.local.UserEJBLocal;

@ManagedBean
@SessionScoped
public class UserCtr {
	private String login;
	private String password;
	private Client client;
	@EJB
	UserEJBLocal userEJBLocal;

	public String doLogin() {
		if (userEJBLocal.authentification(login, password) == null) {
			return null;
		} else if ((client = (Client) userEJBLocal.authentification(login,
				password)) instanceof Client) {

			return "/client/index.jsf?faces-redirect=true";
		} else if (userEJBLocal.authentification(login, password) instanceof Admin) {
			return "/admin/index.jsf?faces-redirect=true";
		} else {
			return "/personneMoral/index.jsf?faces-redirect=true";
		}

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
