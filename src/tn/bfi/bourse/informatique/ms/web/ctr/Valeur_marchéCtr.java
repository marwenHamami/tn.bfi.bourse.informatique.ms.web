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

import tn.bfi.bourse.informatique.ms.entity.Valeur_march�;
import tn.bfi.bourse.informatique.ms.local.Valeur_march�EJBLocal;

@ManagedBean
@SessionScoped
public class Valeur_march�Ctr {

	private List<Valeur_march�> valeur_march� = new ArrayList<Valeur_march�>();
	private Valeur_march� selectedValeur_march�;
	private Valeur_march� valeur_march�s = new Valeur_march�();
	private int id;

	@EJB
	Valeur_march�EJBLocal valeur_march�Local;

	@PostConstruct
	private void init() {
		valeur_march� = valeur_march�Local.findAll();
//		 Valeur_march� valeur_march� = new Valeur_march�();
//
//		 valeur_march�.setCode_interne((long) 500520003);
//		 valeur_march�.setLib_lon("ATB");
//		 valeur_march�.setCours_ref(23.20);
//		 valeur_march�.setDernier_cours(23.2);
//	     valeur_march�.setCode_isim((long) 65258652);
//		 valeur_march�.setQte(650.3);
//		 valeur_march�.setDate_dernier_cours(null);
//		 valeur_march�.setVariation(0.8);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		
//
//		 valeur_march�.setCode_interne((long) 100430003);
//		 valeur_march�.setLib_lon("BFI");
//		 valeur_march�.setCours_ref(83.00);
//		 valeur_march�.setDernier_cours(92.2);
//		 valeur_march�.setCode_isim((long) 851815124);
//		 valeur_march�.setQte(60.3);
//		 valeur_march�.setDate_dernier_cours(null);
//		 valeur_march�.setVariation(0.5);
//		
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		
//
//		 valeur_march�.setCode_interne((long) 500520003);
//		 valeur_march�.setLib_lon("Tunisair");
//		 valeur_march�.setCours_ref(3.20);
//     	 valeur_march�.setDernier_cours(30.03);
//		 valeur_march�.setCode_isim((long) 75221559);
//		 valeur_march�.setQte(654.3);
//		 valeur_march�.setDate_dernier_cours(null);
//		 valeur_march�.setVariation(1.3);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//
//
//		 valeur_march�.setCode_interne((long) 50052533);
//		 valeur_march�.setLib_lon("Agricolt");
//		 valeur_march�.setCours_ref(15.20);
//		 valeur_march�.setDernier_cours(7.2);
//		 valeur_march�.setCode_isim((long) 65252152);
//		 valeur_march�.setQte(850.3);
//		 valeur_march�.setDate_dernier_cours(null);
//		 valeur_march�.setVariation(0.5);
//		 valeur_march�Local.add(valeur_march�);
//		 valeur_march� = new Valeur_march�();
//		
//		 valeur_march�.setCode_interne((long) 500520003);
//		 valeur_march�.setLib_lon("D�lice Dannone");
//		 valeur_march�.setCours_ref(50.20);
//		 valeur_march�.setDernier_cours(70.30);
//		 valeur_march�.setCode_isim((long) 662528652);
//		 valeur_march�.setQte(15.3);
//		 valeur_march�.setDate_dernier_cours(null);
//		 valeur_march�.setVariation(1.5);
//		 valeur_march�Local.add(valeur_march�);
	}

	public String doRedirectToInfoValeur(ValueChangeEvent event) {
		int outcome = Integer.parseInt((String) event.getNewValue()) ;
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

}