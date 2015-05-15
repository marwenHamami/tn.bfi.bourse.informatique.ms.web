package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Liste;
import tn.bfi.bourse.informatique.ms.entity.ListeValeurMarche;
import tn.bfi.bourse.informatique.ms.entity.Valeur_march�;
import tn.bfi.bourse.informatique.ms.local.ListeEJBLocal;
import tn.bfi.bourse.informatique.ms.local.ListeValeurMarcheEJBLocal;
import tn.bfi.bourse.informatique.ms.local.Valeur_march�EJBLocal;

@ManagedBean
@SessionScoped
public class ListeCtr {

	private Liste selectedListe;
	private Liste liste = new Liste();
	private List<Liste> listes = new ArrayList<Liste>();

	private int id_liste;
	private int id_valeur;

	private Liste ListSelectionner = new Liste();
	private Valeur_march� selectedValeurMarche = new Valeur_march�();

	private List<Valeur_march�> listValeurMarche = new ArrayList<Valeur_march�>();

	@EJB
	ListeEJBLocal listeEJBLocal;
	@EJB
	Valeur_march�EJBLocal valeur_marcheLocol;
	@EJB
	ListeValeurMarcheEJBLocal listeValeurMarcheLocal;

	@PostConstruct
	public void init() {
		listes = listeEJBLocal.findAll();
	}

	public void doAjouter() {
		selectedValeurMarche = valeur_marcheLocol.findById(id_valeur);
		if(selectedValeurMarche== null){
			System.err.println("aucune liste selectionner");
			return;
		}
		for (Valeur_march� valeur_march� : listValeurMarche) {
			if(valeur_march�.equals(selectedValeurMarche))
				return;
		}
		ListeValeurMarche listeValeurMarche = new ListeValeurMarche(
				ListSelectionner, selectedValeurMarche);
		listeValeurMarcheLocal.add(listeValeurMarche);
		doAfficher();
	}

	public void doAfficher() {
		listValeurMarche = new ArrayList<Valeur_march�>();
		ListSelectionner = listeEJBLocal.findById(id_liste);
		System.err.println(ListSelectionner.getLibile());
		List<ListeValeurMarche> list = listeValeurMarcheLocal
				.findListByListe(ListSelectionner);
		for (ListeValeurMarche lis : list) {
			listValeurMarche.add(lis.getValeur_march�());
		}
	}

	public String doRedirectToAddListe() {
		return "/client/ajoutliste.jsf?faces-redirect=true";
	}

	public String doAddListe() {
		listeEJBLocal.addliste(liste);
		liste = new Liste();
		init();
		return "/client/liste%20valeurs.jsf?faces-redirect=true";
	}

	public String dodeleteListe() {
		listeEJBLocal.deleteliste(liste);
		init();
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

	public int getId_liste() {
		return id_liste;
	}

	public void setId_liste(int id_liste) {
		this.id_liste = id_liste;
	}

	public int getId_valeur() {
		return id_valeur;
	}

	public void setId_valeur(int id_valeur) {
		this.id_valeur = id_valeur;
	}

	public Liste getListSelectionner() {
		return ListSelectionner;
	}

	public void setListSelectionner(Liste listSelectionner) {
		ListSelectionner = listSelectionner;
	}

	public Valeur_march� getSelectedValeurMarche() {
		return selectedValeurMarche;
	}

	public void setSelectedValeurMarche(Valeur_march� selectedValeurMarche) {
		this.selectedValeurMarche = selectedValeurMarche;
	}

	public List<Valeur_march�> getListValeurMarche() {
		return listValeurMarche;
	}

	public void setListValeurMarche(List<Valeur_march�> listValeurMarche) {
		this.listValeurMarche = listValeurMarche;
	}

}
