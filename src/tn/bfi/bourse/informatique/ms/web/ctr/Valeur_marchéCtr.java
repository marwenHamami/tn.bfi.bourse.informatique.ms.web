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

import tn.bfi.bourse.informatique.ms.entity.Valeur_march�;
import tn.bfi.bourse.informatique.ms.local.Valeur_march�EJBLocal;

@ManagedBean
@SessionScoped
public class Valeur_march�Ctr {

	private List<Valeur_march�> valeur_march� = new ArrayList<Valeur_march�>();
	private Valeur_march� selectedValeur_march�;
	private Valeur_march� valeur_march�s = new Valeur_march�();
	private int id;

	private List<Valeur_march�> hausse = new ArrayList<Valeur_march�>();
	private List<Valeur_march�> bass = new ArrayList<Valeur_march�>();

	@EJB
	Valeur_march�EJBLocal valeur_march�Local;

	@PostConstruct
	private void init() {
		valeur_march� = valeur_march�Local.findAll();
		List<Valeur_march�> too = valeur_march�;
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
//		 Valeur_march� valeur_march� = new Valeur_march�();
//		
//		 valeur_march�.setLib_lon("ATB");
//		 valeur_march�.setHaut(4.32);
//		 valeur_march�.setBas(4.24);
//		 valeur_march�.setCours_ref(4.24);
//		 valeur_march�.setDernier_cours(4.00);
//		 valeur_march�.setCode_isim((long) 0003600350);
//		 valeur_march�.setQte(1899.0);
//		 valeur_march�.setDate_dernier_cours(new Date());
//		 valeur_march�.setVariation(6.30);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		
//		 valeur_march�.setLib_lon("BFI");
//		 valeur_march�.setHaut(40.32);
//		 valeur_march�.setBas(40.00);
//		 valeur_march�.setCours_ref(40.00);
//		 valeur_march�.setDernier_cours(40.22);
//		 valeur_march�.setCode_isim((long) 0001600350);
//		 valeur_march�.setQte(600.0);
//		 valeur_march�.setDate_dernier_cours(new Date());
//		 valeur_march�.setVariation(0.8);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		
//		 valeur_march�.setLib_lon("Tunisair");
//		 valeur_march�.setHaut(0.95);
//		 valeur_march�.setBas(0.94);
//		 valeur_march�.setCours_ref(0.91);
//		 valeur_march�.setDernier_cours(0.90);
//		 valeur_march�.setCode_isim((long) 0001200401);
//		 valeur_march�.setQte(41495.);
//		 valeur_march�.setDate_dernier_cours(new Date());
//		 valeur_march�.setVariation(5.3);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		
//		
//		
//		 valeur_march�.setLib_lon("Star");
//		 valeur_march�.setHaut(181.0);
//		 valeur_march�.setBas(179.0);
//		 valeur_march�.setCours_ref(180.0);
//		 valeur_march�.setDernier_cours(180.0);
//		 valeur_march�.setCode_isim((long) 0006060016);
//		 valeur_march�.setQte(40274.0);
//		 valeur_march�.setDate_dernier_cours(new Date());
//		 valeur_march�.setVariation(0.0);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		
//		
//		 valeur_march�.setLib_lon("DELICE HOLDING");
//		 valeur_march�.setHaut(16.88);
//     	 valeur_march�.setBas(16.55);
//		 valeur_march�.setCours_ref(16.55);
//		 valeur_march�.setDernier_cours(16.78);
//		 valeur_march�.setCode_isim((long) 0007670011);
//		 valeur_march�.setQte(11556.0);
//		 valeur_march�.setDate_dernier_cours(new Date());
//		 valeur_march�.setVariation(0.54);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		 
//		 
//     	 valeur_march�.setLib_lon("ADWIA");
//     	 valeur_march�.setHaut(8.49);
//     	 valeur_march�.setBas(8.26);
//		 valeur_march�.setCours_ref(8.26);
//		 valeur_march�.setDernier_cours(8.25);
//		 valeur_march�.setCode_isim((long) 0007250012);
//		 valeur_march�.setQte(1129.0);
//		 valeur_march�.setDate_dernier_cours(new Date());
//		 valeur_march�.setVariation(2.78);
//		 valeur_march�Local.add(valeur_march�);
//		 

	}

	public String doRedirectToInfoValeur(ValueChangeEvent event) {
		int outcome = Integer.parseInt((String) event.getNewValue());
		String redirectTo = "";
		selectedValeur_march� = valeur_march�Local.findById(outcome);
		redirectTo = "http://localhost:8383/tn.bfi.bourse.informatique.ms.web/infovaleurs.jsf";
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(redirectTo);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return redirectTo;
	}

	public Valeur_march� getSelectedValeur_march�() {
		return selectedValeur_march�;
	}

	public void setSelectedValeur_march�(Valeur_march� selectedValeur_march�) {
		this.selectedValeur_march� = selectedValeur_march�;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Valeur_march� getValeur_march�s() {
		return valeur_march�s;
	}

	public void setValeur_march�s(Valeur_march� valeur_march�s) {
		this.valeur_march�s = valeur_march�s;
	}

	public List<Valeur_march�> getValeur_march�() {
		return valeur_march�;
	}

	public void setValeur_march�(List<Valeur_march�> valeur_march�) {
		this.valeur_march� = valeur_march�;
	}

	public List<Valeur_march�> getHausse() {
		return hausse;
	}

	public void setHausse(List<Valeur_march�> hausse) {
		this.hausse = hausse;
	}

	public List<Valeur_march�> getBass() {
		return bass;
	}

	public void setBass(List<Valeur_march�> bass) {
		this.bass = bass;
	}

}