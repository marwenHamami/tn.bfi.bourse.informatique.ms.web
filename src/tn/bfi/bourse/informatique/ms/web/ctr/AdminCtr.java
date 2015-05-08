package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Client;
import tn.bfi.bourse.informatique.ms.local.AdminEJBLocal;

@SessionScoped
@ManagedBean
public class AdminCtr {
	private Client selectedClient;
	private Client client = new Client();
	private List<Client> clients = new ArrayList<Client>();

	@EJB
	AdminEJBLocal adminEJBLocal;

	@PostConstruct
	public void init() {
		clients = adminEJBLocal.findAll();
	}

	public void doApprouveClient(Client client) {
		adminEJBLocal.approuveClient(client);
		init();
	}
	
	public void doDeleteClient(Client client) {
		adminEJBLocal.delete(client);
		init();
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

	public AdminEJBLocal getAdminEJBLocal() {
		return adminEJBLocal;
	}

	public void setAdminEJBLocal(AdminEJBLocal adminEJBLocal) {
		this.adminEJBLocal = adminEJBLocal;
	}

}
