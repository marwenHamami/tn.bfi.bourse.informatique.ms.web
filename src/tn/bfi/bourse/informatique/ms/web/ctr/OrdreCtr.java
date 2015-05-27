package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.bfi.bourse.informatique.ms.entity.Demande;
import tn.bfi.bourse.informatique.ms.entity.Execution;
import tn.bfi.bourse.informatique.ms.entity.Hmvt;
import tn.bfi.bourse.informatique.ms.entity.Ordre;
import tn.bfi.bourse.informatique.ms.entity.Portefeuille;
import tn.bfi.bourse.informatique.ms.entity.Valeur_marché;
import tn.bfi.bourse.informatique.ms.local.DemandeEJBLocal;
import tn.bfi.bourse.informatique.ms.local.ExecutionEJBLocal;
import tn.bfi.bourse.informatique.ms.local.HmvtEJBLocal;
import tn.bfi.bourse.informatique.ms.local.OrdreEJBLocal;
import tn.bfi.bourse.informatique.ms.local.PortefeuilleEJBLocal;
import tn.bfi.bourse.informatique.ms.local.Valeur_marchéEJBLocal;

@ManagedBean
@SessionScoped
public class OrdreCtr {

	private Ordre selectedOrdre = new Ordre();
	private Ordre ordre = new Ordre();
	private Demande demande = new Demande();
	private List<Ordre> ordres = new ArrayList<Ordre>();

	private List<Ordre> ordres2 = new ArrayList<Ordre>();
	private Valeur_marché valeur_marché = new Valeur_marché();

	private int id_port;
	private int id_valeur;
	private int id_portefeuille;

	@EJB
	Valeur_marchéEJBLocal valeurMarcheLocal;

	@EJB
	DemandeEJBLocal demandeEJBLocal;

	@EJB
	OrdreEJBLocal ordreEJBLocal;

	@EJB
	PortefeuilleEJBLocal protefeuilleEJBlocal;

	@EJB
	HmvtEJBLocal hmvtLocal;

	@EJB
	ExecutionEJBLocal executionEJBLocal;

	@ManagedProperty(value = "#{userCtr}")
	UserCtr userCtr;

	public void init() {
		ordres = ordreEJBLocal.findAll();
	}

	public void doAdd() {
		// demande
		demande.setOrdres(null);

		// demandeEJBLocal.add(demande);

		// ordre
		ordre.setDemande(demande);
		ordre.setCanal_saisie("achat");
		ordre.setCompte_br(userCtr.getClient().getCompte_br());
		ordre.setDate_cours(new Date());
		ordre.setDate_cours(new Date());
		ordre.setExecutions(null);
		ordre.setMontanat_net(demande.getMontant_net());
		ordre.setQt_initial(demande.getQt_initial());
		ordre.setValeur_marché(valeur_marché);
		ordres2.add(ordre);
		// ordreEJBLocal.passerordre(ordre);
		//
		// // mouvement
		// Hmvt hmvt = new Hmvt();
		// hmvt.setCanal("internet");
		// hmvt.setDate(new Date());
		// hmvt.setExecutions(null);
		// hmvt.setMontant(demande.getMontant_net());
		// Portefeuille p = new Portefeuille();
		// p.setId(id_portefeuille);
		// hmvt.setPortfeuille(p);
		// hmvtLocal.add(hmvt);
		//
		// // execution
		// Execution execution = new Execution();
		// execution.setCours(valeur_marché.getCours_ref());
		// execution.setDate(new Date());
		// execution.setHmvt(hmvt);
		// execution.setMontant_net(demande.getMontant_net());
		// execution.setOrdre(ordre);
		// execution.setQuantity(demande.getQt_initial());
		// executionEJBLocal.add(execution);
		//
		ordre = new Ordre();
		demande = new Demande();
	}

	public void doValider(Ordre ordre) {
		demandeEJBLocal.add(ordre.getDemande());
		ordreEJBLocal.passerordre(ordre);

		Hmvt hmvt = new Hmvt();
		hmvt.setCanal("internet");
		hmvt.setDate(new Date());
		hmvt.setExecutions(null);
		hmvt.setMontant(ordre.getDemande().getMontant_net());
		Portefeuille p = new Portefeuille();
		p.setId(id_portefeuille);
		hmvt.setPortfeuille(p);
		hmvtLocal.add(hmvt);

		// execution
		Execution execution = new Execution();
		execution.setCours(ordre.getValeur_marché().getCours_ref());
		execution.setDate(new Date());
		execution.setHmvt(hmvt);
		execution.setMontant_net(ordre.getDemande().getMontant_net());
		execution.setOrdre(ordre);
		execution.setQuantity(ordre.getDemande().getQt_initial());
		executionEJBLocal.add(execution);

		ordres2.remove(ordre);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Succes", "ordre :"
						+ ordre.getValeur_marché().getLib_lon() + " , Qte :"
						+ ordre.getQt_initial() + " a ete BIEN execute"));
	}

	public void doDeleteOrdre(Ordre ordre) {
		ordres2.remove(ordre);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null,
				new FacesMessage("Delete", "ordre :"
						+ ordre.getValeur_marché().getLib_lon() + " , Qte :"
						+ ordre.getQt_initial() + " a ete supprimer"));
	}

	public void doUpdate(Ordre ordre) {
		selectedOrdre = ordre;
	}

	public void doModifier() {
		ordres2.remove(selectedOrdre);
		selectedOrdre.getDemande().setMontant_net(
				selectedOrdre.getValeur_marché().getCours_ref()
						* selectedOrdre.getQt_initial());
		selectedOrdre.setMontanat_net(selectedOrdre.getValeur_marché()
				.getCours_ref() * selectedOrdre.getQt_initial());
		ordres2.add(selectedOrdre);

	}

	public void doCalculerMontant() {
		valeur_marché = valeurMarcheLocal.findById(id_valeur);
		demande.setMontant_net(valeur_marché.getCours_ref()
				* demande.getQt_initial());
	}

	public Ordre getSelectedOrdre() {
		return selectedOrdre;
	}

	public void setSelectedOrdre(Ordre selectedOrdre) {
		this.selectedOrdre = selectedOrdre;
	}

	public Ordre getOrdre() {
		return ordre;
	}

	public void setOrdre(Ordre ordre) {
		this.ordre = ordre;
	}

	public List<Ordre> getOrdres() {
		return ordres;
	}

	public void setOrdres(List<Ordre> ordres) {
		this.ordres = ordres;
	}

	public void doadd() {
		ordreEJBLocal.passerordre(ordre);
		init();
	}

	public int getId_port() {
		return id_port;
	}

	public void setId_port(int id_port) {
		this.id_port = id_port;
	}

	public int getId_valeur() {
		return id_valeur;
	}

	public void setId_valeur(int id_valeur) {
		this.id_valeur = id_valeur;
	}

	public Demande getDemande() {
		return demande;
	}

	public void setDemande(Demande demande) {
		this.demande = demande;
	}

	public void setUserCtr(UserCtr userCtr) {
		this.userCtr = userCtr;
	}

	public Valeur_marché getValeur_marché() {
		return valeur_marché;
	}

	public void setValeur_marché(Valeur_marché valeur_marché) {
		this.valeur_marché = valeur_marché;
	}

	public int getId_portefeuille() {
		return id_portefeuille;
	}

	public void setId_portefeuille(int id_portefeuille) {
		this.id_portefeuille = id_portefeuille;
	}

	public List<Ordre> getOrdres2() {
		return ordres2;
	}

	public void setOrdres2(List<Ordre> ordres2) {
		this.ordres2 = ordres2;
	}

}
