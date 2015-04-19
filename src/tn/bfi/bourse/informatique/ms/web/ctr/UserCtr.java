package tn.bfi.bourse.informatique.ms.web.ctr;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.bfi.bourse.informatique.ms.entity.Admin;
import tn.bfi.bourse.informatique.ms.entity.Client;
import tn.bfi.bourse.informatique.ms.local.UserEJBLocal;

@ManagedBean
@SessionScoped
public class UserCtr {
	private String login;
	private String password;
	private Client client = new Client();
	private String validator = "";
	@EJB
	UserEJBLocal userEJBLocal;

	@PostConstruct
	private void init() {
		Client client = new Client();
		client.setLogin("aziz");
		client.setPassword("aziz");
		// userEJBLocal.registrationClient(client);
	}

	public String doLogin() {
		if (userEJBLocal.authentification(login, password) == null) {
			login = "";
			password = "";
			return null;
		} else if ((client = (Client) userEJBLocal.authentification(login,
				password)) instanceof Client) {
			login = "";
			password = "";
			return "/client/index.jsf?faces-redirect=true";
		} else if (userEJBLocal.authentification(login, password) instanceof Admin) {
			login = "";
			password = "";
			return "/admin/index.jsf?faces-redirect=true";
		} else {
			login = "";
			password = "";
			return "/personneMoral/index.jsf?faces-redirect=true";
		}
	}

	public String doRegistration() {
		if (password.equals(client.getPassword())) {
			userEJBLocal.registrationClient(client);
			return "/client/verification.jsf?faces-redirect=true";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error",
					"password failed"));
			client = new Client();
			return null;
		}
	}

	public void doVerifLogin() {
		if (userEJBLocal.verifLogin(login)) {
			validator = "deja utiliser";
		} else {
			validator = "OK";
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

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

}
