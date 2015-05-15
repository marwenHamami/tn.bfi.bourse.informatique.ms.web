package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Demande;
import tn.bfi.bourse.informatique.ms.local.DemandeEJBLocal;

@ManagedBean
@SessionScoped
public class DemandeCtr {
	
	private Demande selectedDemande;
	private Demande demande = new Demande();
	private List<Demande> demandes = new ArrayList<Demande>();
	
	@EJB
	DemandeEJBLocal demandeEJBLocal;
	
	@ManagedProperty(value = "#{userCtr}")
	UserCtr userCtr;

	
	public void init() {
		setDemandes(demandeEJBLocal.findAll());
	}

	public Demande getSelectedDemande() {
		return selectedDemande;
	}

	public void setSelectedDemande(Demande selectedDemande) {
		this.selectedDemande = selectedDemande;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public List<Demande> getDemandes() {
		return demandes;
	}

	public void setDemandes(List<Demande> demandes) {
		this.demandes = demandes;
	}
	
	public void doupdaate() {
		demandeEJBLocal.update(demande);
		init();
	}
	
	public void dodelete() {
		demandeEJBLocal.delete(demande);
		init();
	}
}
