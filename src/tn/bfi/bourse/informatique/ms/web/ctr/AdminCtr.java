package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

import tn.bfi.bourse.informatique.ms.entity.Client;
import tn.bfi.bourse.informatique.ms.local.AdminEJBLocal;

public class AdminCtr {
	private Client selectedClient;
	private Client client = new Client();
	private List<Client> clients = new ArrayList<Client>();

	@ManagedProperty(value = "#{adminCtr}")
	AdminCtr adminCtr;
	@EJB
	AdminEJBLocal adminEJBLocal;

	public List<Client> doFindAll() {
		return adminEJBLocal.findUserByAdmin(adminCtr.getClient());
	}

	public String doRedirectToActiveruser() {
		return "/admin/operationsurcompte.jsf?faces-redirect=true";
	}
	
	public Client getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Client selectedClient) {
		this.selectedClient = selectedClient;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	

	public AdminCtr getAdminCtr() {
		return adminCtr;
	}

	public void setAdminCtr(AdminCtr adminCtr) {
		this.adminCtr = adminCtr;
	}

	public AdminEJBLocal getAdminEJBLocal() {
		return adminEJBLocal;
	}

	public void setAdminEJBLocal(AdminEJBLocal adminEJBLocal) {
		this.adminEJBLocal = adminEJBLocal;
	}
	
	
	
}
