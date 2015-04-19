package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Client;
import tn.bfi.bourse.informatique.ms.entity.Compte_espece;
import tn.bfi.bourse.informatique.ms.local.Compte_especeLocal;

@ManagedBean(name = "compteEspeceCtr")
@SessionScoped
public class Compte_especeCtr {
	private Client client = new Client();
	private List<Compte_espece> compte_especes = new ArrayList<Compte_espece>();
	private Compte_espece selectedCompte_espece = new Compte_espece();
	private Compte_espece compte_espece = new Compte_espece();

	@EJB
	Compte_especeLocal compte_especeLocal;

	@ManagedProperty(value = "#{userCtr}")
	UserCtr userCtr;

	@PostConstruct
	private void init() {
		client = userCtr.getClient();
		compte_especes = compte_especeLocal.findByClient(client);
	}

	public void doAdd() {
		compte_espece.setUser(client);
		compte_especeLocal.add(compte_espece);
		init();
	}

	public List<Compte_espece> getCompte_especes() {
		return compte_especes;
	}

	public void setCompte_especes(List<Compte_espece> compte_especes) {
		this.compte_especes = compte_especes;
	}

	public Compte_espece getSelectedCompte_espece() {
		return selectedCompte_espece;
	}

	public void setSelectedCompte_espece(Compte_espece selectedCompte_espece) {
		this.selectedCompte_espece = selectedCompte_espece;
	}

	public Compte_espece getCompte_espece() {
		return compte_espece;
	}

	public void setCompte_espece(Compte_espece compte_espece) {
		this.compte_espece = compte_espece;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setUserCtr(UserCtr userCtr) {
		this.userCtr = userCtr;
	}

}
