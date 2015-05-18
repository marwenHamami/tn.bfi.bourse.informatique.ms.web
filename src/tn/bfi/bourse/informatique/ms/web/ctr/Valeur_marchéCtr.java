package tn.bfi.bourse.informatique.ms.web.ctr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

	private List<Valeur_marché> hausse = new ArrayList<Valeur_marché>();
	private List<Valeur_marché> bass = new ArrayList<Valeur_marché>();

	@EJB
	Valeur_marchéEJBLocal valeur_marchéLocal;

	@PostConstruct
	private void init() {
		valeur_marché = valeur_marchéLocal.findAll();
		List<Valeur_marché> too = valeur_marché;
		Collections.sort(too);
		for (int i = 0; i < too.size(); i++) {
			if (i > 2)
				break;

			bass.add(too.get(i));
		}

		int j = 0;
		for (int i = too.size() - 1; i > 0; i--) {
			if (j > 2)
				break;
			j++;
			hausse.add(too.get(i));
		}
//		 Valeur_marché valeur_marché = new Valeur_marché();
//		
//		 valeur_marché.setLib_lon("ATB");
//		 valeur_marché.setHaut(4.32);
//		 valeur_marché.setBas(4.24);
//		 valeur_marché.setCours_ref(4.24);
//		 valeur_marché.setDernier_cours(4.00);
//		 valeur_marché.setCode_isim((long) 0003600350);
//		 valeur_marché.setQte(1899.0);
//		 valeur_marché.setDate_dernier_cours(new Date());
//		 valeur_marché.setVariation(6.30);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		
//		 valeur_marché.setLib_lon("BFI");
//		 valeur_marché.setHaut(40.32);
//		 valeur_marché.setBas(40.00);
//		 valeur_marché.setCours_ref(40.00);
//		 valeur_marché.setDernier_cours(40.22);
//		 valeur_marché.setCode_isim((long) 0001600350);
//		 valeur_marché.setQte(600.0);
//		 valeur_marché.setDate_dernier_cours(new Date());
//		 valeur_marché.setVariation(0.8);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		
//		 valeur_marché.setLib_lon("Tunisair");
//		 valeur_marché.setHaut(0.95);
//		 valeur_marché.setBas(0.94);
//		 valeur_marché.setCours_ref(0.91);
//		 valeur_marché.setDernier_cours(0.90);
//		 valeur_marché.setCode_isim((long) 0001200401);
//		 valeur_marché.setQte(41495.);
//		 valeur_marché.setDate_dernier_cours(new Date());
//		 valeur_marché.setVariation(5.3);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		
//		
//		
//		 valeur_marché.setLib_lon("Star");
//		 valeur_marché.setHaut(181.0);
//		 valeur_marché.setBas(179.0);
//		 valeur_marché.setCours_ref(180.0);
//		 valeur_marché.setDernier_cours(180.0);
//		 valeur_marché.setCode_isim((long) 0006060016);
//		 valeur_marché.setQte(40274.0);
//		 valeur_marché.setDate_dernier_cours(new Date());
//		 valeur_marché.setVariation(0.0);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		
//		
//		 valeur_marché.setLib_lon("DELICE HOLDING");
//		 valeur_marché.setHaut(16.88);
//     	 valeur_marché.setBas(16.55);
//		 valeur_marché.setCours_ref(16.55);
//		 valeur_marché.setDernier_cours(16.78);
//		 valeur_marché.setCode_isim((long) 0007670011);
//		 valeur_marché.setQte(11556.0);
//		 valeur_marché.setDate_dernier_cours(new Date());
//		 valeur_marché.setVariation(0.54);
//		 valeur_marchéLocal.add(valeur_marché);
//		 valeur_marché = new Valeur_marché();
//		 
//		 
//     	 valeur_marché.setLib_lon("ADWIA");
//     	 valeur_marché.setHaut(8.49);
//     	 valeur_marché.setBas(8.26);
//		 valeur_marché.setCours_ref(8.26);
//		 valeur_marché.setDernier_cours(8.25);
//		 valeur_marché.setCode_isim((long) 0007250012);
//		 valeur_marché.setQte(1129.0);
//		 valeur_marché.setDate_dernier_cours(new Date());
//		 valeur_marché.setVariation(2.78);
//		 valeur_marchéLocal.add(valeur_marché);
//		 

	}

	public String doRedirectToInfoValeur(ValueChangeEvent event) {
		int outcome = Integer.parseInt((String) event.getNewValue());
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

	public List<Valeur_marché> getHausse() {
		return hausse;
	}

	public void setHausse(List<Valeur_marché> hausse) {
		this.hausse = hausse;
	}

	public List<Valeur_marché> getBass() {
		return bass;
	}

	public void setBass(List<Valeur_marché> bass) {
		this.bass = bass;
	}

}