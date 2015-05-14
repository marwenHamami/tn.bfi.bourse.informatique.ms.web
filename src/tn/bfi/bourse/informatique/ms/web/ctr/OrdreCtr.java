package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import tn.bfi.bourse.informatique.ms.entity.Ordre;
import tn.bfi.bourse.informatique.ms.local.OrdreEJBLocal;

public class OrdreCtr {

	private Ordre selectedOrdre;
	private Ordre ordre = new Ordre();
	private List<Ordre> ordres = new ArrayList<Ordre>();
	
	@EJB
	OrdreEJBLocal ordreEJBLocal;
	
	public void init() {
		ordres = ordreEJBLocal.findAll();
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
	
	

}
