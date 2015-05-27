package tn.bfi.bourse.informatique.ms.web.ctr;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.bfi.bourse.informatique.ms.entity.Admin;
import tn.bfi.bourse.informatique.ms.entity.Client;
import tn.bfi.bourse.informatique.ms.entity.Compte_espece;
import tn.bfi.bourse.informatique.ms.local.Compte_especeLocal;
import tn.bfi.bourse.informatique.ms.local.UserEJBLocal;

@ManagedBean
@SessionScoped
public class UserCtr {
	private String login;
	private String password;
	private Client client = new Client();
	private String validator = "";
	private Long rib;
	private Compte_espece compteEspece;

	private Compte_espece MyCompteEspece = new Compte_espece();
	@EJB
	UserEJBLocal userEJBLocal;
	@EJB
	Compte_especeLocal compte_especeLocal;

	@PostConstruct
	private void init() {
		Client client = new Client();
		client.setLogin("marwen");
		client.setPassword("marwen");
		// userEJBLocal.registrationClient(client);
	}

	public String doLogin() {
		if (userEJBLocal.authentification(login, password) == null) {
			login = "";
			password = "";
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error",
					"login ou mot de passe incorrecte"));
			return null;
		} else if ((client = (Client) userEJBLocal.authentification(login,
				password)) instanceof Client) {
			login = "";
			password = "";
			MyCompteEspece = compte_especeLocal.findByClients(client);
			return "/client/index.jsf?faces-redirect=true";
		} else if (userEJBLocal.authentification(login, password) instanceof Admin) {
			login = "admin";
			password = "admin";
			return "/admin/operationsurcompte.jsf?faces-redirect=true";
		} else {
			login = "";
			password = "";
			return "/personneMoral/index.jsf?faces-redirect=true";
		}
	}

	public String doRegistration() {
		if (password.equals(client.getPassword())) {
			if ((compteEspece = compte_especeLocal.findByRib(rib)) == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Error",
						"Votre RIB est incorrecte"));
				return null;
			} else {
				userEJBLocal.registrationClient(client);
				compteEspece.setUser(client);
				compte_especeLocal.update(compteEspece);
				return "/client/verification.jsf?faces-redirect=true";
			}
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

	public Long getRib() {
		return rib;
	}

	public void setRib(Long rib) {
		this.rib = rib;
	}

	public Compte_espece getMyCompteEspece() {
		return MyCompteEspece;
	}

	public void setMyCompteEspece(Compte_espece myCompteEspece) {
		MyCompteEspece = myCompteEspece;
	}

}
