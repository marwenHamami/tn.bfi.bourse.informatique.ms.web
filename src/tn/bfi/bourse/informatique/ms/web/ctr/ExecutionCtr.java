package tn.bfi.bourse.informatique.ms.web.ctr;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.bfi.bourse.informatique.ms.entity.Execution;
import tn.bfi.bourse.informatique.ms.local.ExecutionEJBLocal;


@ManagedBean
@SessionScoped
public class ExecutionCtr {
	private Execution selectedExecution;
	private Execution execution = new Execution();
	private List<Execution> executions = new ArrayList<Execution>();
	
	@EJB
	ExecutionEJBLocal executionLocal;
	
	public void init() {
		setExecutions(executionLocal.findAll());
	}

	public Execution getSelectedExecution() {
		return selectedExecution;
	}

	public void setSelectedExecution(Execution selectedExecution) {
		this.selectedExecution = selectedExecution;
	}

	public Execution getExecution() {
		return execution;
	}

	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	public List<Execution> getExecutions() {
		return executions;
	}

	public void setExecutions(List<Execution> executions) {
		this.executions = executions;
	}

}
