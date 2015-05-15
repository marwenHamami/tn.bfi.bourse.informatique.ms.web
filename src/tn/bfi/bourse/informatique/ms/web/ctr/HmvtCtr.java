package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Execution;
import tn.bfi.bourse.informatique.ms.entity.Hmvt;
import tn.bfi.bourse.informatique.ms.local.ExecutionEJBLocal;
import tn.bfi.bourse.informatique.ms.local.HmvtEJBLocal;

@ManagedBean
@SessionScoped
public class HmvtCtr {

	private Hmvt hmvt = new Hmvt();
	private List<Hmvt> hmvts = new ArrayList<Hmvt>();
	private List<Execution> executions = new ArrayList<Execution>();

	@EJB
	HmvtEJBLocal hmvtEJBLocal;
	@EJB
	ExecutionEJBLocal executionEJBLocal;
	@ManagedProperty(value = "#{userCtr}")
	UserCtr userCtr;

	@PostConstruct
	public void init() {
		// hmvts = hmvtEJBLocal.findByClient(userCtr.getClient());
		executions = executionEJBLocal.findByClient(userCtr.getClient());
	}

	public List<Hmvt> getHmvts() {
		return hmvts;
	}

	public void setHmvts(List<Hmvt> hmvts) {
		this.hmvts = hmvts;
	}

	public Hmvt getHmvt() {
		return hmvt;
	}

	public void setHmvt(Hmvt hmvt) {
		this.hmvt = hmvt;
	}

	public void setUserCtr(UserCtr userCtr) {
		this.userCtr = userCtr;
	}

	public List<Execution> getExecutions() {
		return executions;
	}

	public void setExecutions(List<Execution> executions) {
		this.executions = executions;
	}
	
	

}
