package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Liste;
import tn.bfi.bourse.informatique.ms.local.ListeEJBLocal;

@ManagedBean
@SessionScoped
public class ListeCtr {

	private Liste selectedListe;
	private Liste liste = new Liste();
	private List<Liste> listes = new ArrayList<Liste>();

	@EJB
	ListeEJBLocal listeEJBLocal;

	public void init() {
		listes = listeEJBLocal.findAll();
	}

	public String doRedirectToAddListe() {
		return "/client/ajoutliste.jsf?faces-redirect=true";
	}

	public String doAddListe() {
		listeEJBLocal.addliste(liste);
		liste = new Liste();
		return "/client/liste%20valeurs.jsf?faces-redirect=true";
	}
	
	public String dodeleteListe() {
		listeEJBLocal.deleteliste(liste);
		return "/client/liste%20valeurs.jsf?faces-redirect=true";
	}

	public Liste getSelectedListe() {
		return selectedListe;
	}

	public void setSelectedListe(Liste selectedListe) {
		this.selectedListe = selectedListe;
	}

	public Liste getListe() {
		return liste;
	}

	public void setListe(Liste liste) {
		this.liste = liste;
	}

	public List<Liste> getListes() {
		return listes;
	}

	public void setListes(List<Liste> listes) {
		this.listes = listes;
	}

}
