package infraestrutura.bean.dto;

import infraestrutura.model.Job;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class JobDTO {

	private Job job;

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getQtdDiasAtrasados() {
		LocalDate hoje = new LocalDate();
		if (hoje.isAfter(job.getDataPrevista()) && job.getDataConclusao() == null) {
			return calcularAtrasoEmDiasOuMeses(hoje);
		}
		return null;
	}

	public String getQtdDiasAtrasou() {
		if (job.getDataConclusao() != null && job.getDataConclusao().isAfter(job.getDataPrevista())) {
			return calcularAtrasoEmDiasOuMeses(job.getDataConclusao());
		}
		return null;
	}

	private String calcularAtrasoEmDiasOuMeses(LocalDate data) {
		int quantidade = Days.daysBetween(job.getDataPrevista(), data).getDays();
		
		//retorna que atrasou 1 dia
		if (quantidade == 1) {
			return quantidade+" dia";
		}

		//retorna que atrasou X dias
		else {
			return quantidade+" dias";
		}
	}

	public boolean isAtrasadoNaoEntregou() {
		LocalDate hoje = new LocalDate();
		if (hoje.isAfter(job.getDataPrevista()) && job.getDataConclusao() == null) {
			return true;
		}
		return false;
	}
	
	public boolean isEntregouMasAtrasou() {
		if (job.getDataConclusao() != null && job.getDataConclusao().isAfter(job.getDataPrevista())) {
			return true;
		}
		return false;
	}
	
	public boolean isEstaNoPrazo() {
		if (!isAtrasadoNaoEntregou() && !isEntregouMasAtrasou()) {
			return true;
		}
		return false;
	}
}
