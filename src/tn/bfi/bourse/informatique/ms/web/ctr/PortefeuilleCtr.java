package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Execution;
import tn.bfi.bourse.informatique.ms.entity.Portefeuille;
import tn.bfi.bourse.informatique.ms.local.ExecutionEJBLocal;
import tn.bfi.bourse.informatique.ms.local.HmvtEJBLocal;
import tn.bfi.bourse.informatique.ms.local.PortefeuilleEJBLocal;

@ManagedBean
@SessionScoped
public class PortefeuilleCtr {
	private Portefeuille selectedPrtefeuille;
	private Portefeuille portefeuille = new Portefeuille();
	private List<Portefeuille> portefeuilles = new ArrayList<Portefeuille>();
	private List<Execution> executions = new ArrayList<Execution>();

	private Double somme=0.0;

	@ManagedProperty(value = "#{userCtr}")
	UserCtr userCtr;
	@EJB
	PortefeuilleEJBLocal portefeuilleEJBLocal;
	@EJB
	ExecutionEJBLocal executionEJBLocal;
	@EJB
	HmvtEJBLocal hmvtEJBLocal;

	public List<Portefeuille> doFindAll() {
		return portefeuilles = portefeuilleEJBLocal
				.findProtefeuilleByClient(userCtr.getClient());
	}

	public String doRedirectToAddPortefeuille() {

		return "/client/ajouterPortefeuille.jsf?faces-redirect=true";
	}

	public String doRedirectToInfoPortfeuil(Portefeuille portefeuille) {
		selectedPrtefeuille = portefeuille;
		executions = executionEJBLocal.findByPortefeuil(selectedPrtefeuille);
		somme = 0.0;
		for (Execution execution : executions) {
			try {
				somme += execution.getMontant_net();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		return "/client/infoportefeuilles.jsf?faces-redirect=true";
	}

	public String doAddPortefeuille() {
		portefeuille.setClient(userCtr.getClient());
		portefeuille.setCompte_br(userCtr.getClient().getCompte_br());
		portefeuille.setCompte_espece(null);
		// portefeuille.setHmvt(null);
		portefeuilleEJBLocal.add(portefeuille);
		portefeuille = new Portefeuille();
		return "/client/index.jsf?faces-redirect=true";
	}

	public void dodelete() {
		portefeuilleEJBLocal.delete(portefeuille);
	}

	public Portefeuille getSelectedPrtefeuille() {
		return selectedPrtefeuille;
	}

	public void setSelectedPrtefeuille(Portefeuille selectedPrtefeuille) {
		this.selectedPrtefeuille = selectedPrtefeuille;
	}

	public List<Portefeuille> getPortefeuilles() {
		return portefeuilles;
	}

	public void setPortefeuilles(List<Portefeuille> portefeuilles) {
		this.portefeuilles = portefeuilles;
	}

	public Portefeuille getPortefeuille() {
		return portefeuille;
	}

	public void setPortefeuille(Portefeuille portefeuille) {
		this.portefeuille = portefeuille;
	}

	public void setUserCtr(UserCtr userCtr) {
		this.userCtr = userCtr;
	}

	public List<Execution> getExecutions() {
		return executions;
	}

	public void setExecutions(List<Execution> executions) {
		this.executions = executions;
	}

	public Double getSomme() {
		return somme;
	}

	public void setSomme(Double somme) {
		this.somme = somme;
	}

}
