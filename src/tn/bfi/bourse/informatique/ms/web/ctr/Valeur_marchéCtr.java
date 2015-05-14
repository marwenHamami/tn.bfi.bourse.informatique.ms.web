package tn.bfi.bourse.informatique.ms.web.ctr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import tn.bfi.bourse.informatique.ms.entity.Valeur_marché;
import tn.bfi.bourse.informatique.ms.local.Valeur_marchéEJBLocal;

@ManagedBean
@SessionScoped
public class Valeur_marchéCtr {

	private List<Valeur_marché> valeur_marché = new ArrayList<Valeur_marché>();
	private Valeur_marché selectedValeur_marché;
	private Valeur_marché valeur_marchés = new Valeur_marché();
	private int id;

	@EJB
	Valeur_marchéEJBLocal valeur_marchéLocal;

	@PostConstruct
	private void init() {
		valeur_marché = valeur_marchéLocal.findAll();
//		 Valeur_marché valeur_marché = new Valeur_marché();
//
//		 valeur_marché.setCode_interne((long) 500520003);
//		 valeur_marché.setLib_lon("ATB");
//		 valeur_marché.setCours_ref(23.20);
//		 valeur_marché.setDernier_cours(23.2);
//	     valeur_marché.setCode_isim((long) 65258652);
//		 valeur_marché.setQte(650.3);
//		 valeur_marché.setDate_dernier_cours(null);
//		 valeur_marché.setVariation(0.8);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		
//
//		 valeur_marché.setCode_interne((long) 100430003);
//		 valeur_marché.setLib_lon("BFI");
//		 valeur_marché.setCours_ref(83.00);
//		 valeur_marché.setDernier_cours(92.2);
//		 valeur_marché.setCode_isim((long) 851815124);
//		 valeur_marché.setQte(60.3);
//		 valeur_marché.setDate_dernier_cours(null);
//		 valeur_marché.setVariation(0.5);
//		
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		
//
//		 valeur_marché.setCode_interne((long) 500520003);
//		 valeur_marché.setLib_lon("Tunisair");
//		 valeur_marché.setCours_ref(3.20);
//     	 valeur_marché.setDernier_cours(30.03);
//		 valeur_marché.setCode_isim((long) 75221559);
//		 valeur_marché.setQte(654.3);
//		 valeur_marché.setDate_dernier_cours(null);
//		 valeur_marché.setVariation(1.3);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//
//
//		 valeur_marché.setCode_interne((long) 50052533);
//		 valeur_marché.setLib_lon("Agricolt");
//		 valeur_marché.setCours_ref(15.20);
//		 valeur_marché.setDernier_cours(7.2);
//		 valeur_marché.setCode_isim((long) 65252152);
//		 valeur_marché.setQte(850.3);
//		 valeur_marché.setDate_dernier_cours(null);
//		 valeur_marché.setVariation(0.5);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		
//		 valeur_marché.setCode_interne((long) 500520003);
//		 valeur_marché.setLib_lon("Délice Dannone");
//		 valeur_marché.setCours_ref(50.20);
//		 valeur_marché.setDernier_cours(70.30);
//		 valeur_marché.setCode_isim((long) 662528652);
//		 valeur_marché.setQte(15.3);
//		 valeur_marché.setDate_dernier_cours(null);
//		 valeur_marché.setVariation(1.5);
//		 valeur_marchéLocal.add(valeur_marché);
	}

	public String doRedirectToInfoValeur(ValueChangeEvent event) {
		int outcome = Integer.parseInt((String) event.getNewValue()) ;
		String redirectTo = "";
		selectedValeur_marché = valeur_marchéLocal.findById(outcome);
		redirectTo = "http://localhost:8383/tn.bfi.bourse.informatique.ms.web/infovaleurs.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(redirectTo);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return redirectTo;
	}

	public Valeur_marché getSelectedValeur_marché() {
		return selectedValeur_marché;
	}

	public void setSelectedValeur_marché(Valeur_marché selectedValeur_marché) {
		this.selectedValeur_marché = selectedValeur_marché;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Valeur_marché getValeur_marchés() {
		return valeur_marchés;
	}

	public void setValeur_marchés(Valeur_marché valeur_marchés) {
		this.valeur_marchés = valeur_marchés;
	}

	public List<Valeur_marché> getValeur_marché() {
		return valeur_marché;
	}

	public void setValeur_marché(List<Valeur_marché> valeur_marché) {
		this.valeur_marché = valeur_marché;
	}

}