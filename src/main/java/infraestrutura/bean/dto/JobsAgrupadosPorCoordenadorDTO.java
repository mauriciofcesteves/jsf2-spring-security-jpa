package infraestrutura.bean.dto;

import infraestrutura.model.Coordenador;

import java.util.ArrayList;
import java.util.List;

public class JobsAgrupadosPorCoordenadorDTO {

	private String nomeCoordenador;
	private Coordenador coordenador;
	private List<JobDTO> jobs;

	public String getNomeCoordenador() {
		return nomeCoordenador;
	}
	
	public void setNomeCoordenador(String nomeCoordenador) {
		this.nomeCoordenador = nomeCoordenador;
	}
	
	public Coordenador getCoordenador() {
		return coordenador;
	}
	
	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public List<JobDTO> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobDTO> jobs) {
		this.jobs = jobs;
	}

	public void adicionarJob(JobDTO dto) {
		if (jobs == null) {
			jobs = new ArrayList<JobDTO>();
		}
		jobs.add(dto);
	}
}
