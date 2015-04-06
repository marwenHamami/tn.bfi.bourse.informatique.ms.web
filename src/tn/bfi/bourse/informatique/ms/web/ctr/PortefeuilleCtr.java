package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Portefeuille;
import tn.bfi.bourse.informatique.ms.local.PortefeuilleEJBLocal;

@ManagedBean
@SessionScoped
public class PortefeuilleCtr {
	private Portefeuille selectedPrtefeuille;
	private Portefeuille portefeuille = new Portefeuille();
	private List<Portefeuille> portefeuilles = new ArrayList<Portefeuille>();

	@ManagedProperty(value = "#{userCtr}")
	UserCtr userCtr;
	@EJB
	PortefeuilleEJBLocal portefeuilleEJBLocal;

	public List<Portefeuille> doFindAll() {
		return portefeuilleEJBLocal.findProtefeuilleByClient(userCtr
				.getClient());
	}
	
	public String doRedirectToAddPortefeuille() {
		return "/client/ajouterPortefeuille.jsf?faces-redirect=true";
	}

	public String doAddPortefeuille() {
		portefeuille.setClient(userCtr.getClient());
		portefeuilleEJBLocal.add(portefeuille);
		portefeuille = new Portefeuille();
		return "/client/index.jsf?faces-redirect=true";
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

}
