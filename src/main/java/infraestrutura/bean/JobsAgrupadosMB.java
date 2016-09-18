package infraestrutura.bean;

import infraestrutura.bean.dto.JobDTO;
import infraestrutura.bean.dto.JobsAgrupadosPorCoordenadorDTO;
import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("jobsAgrupadosMB")
@Scope(GenericMB.SESSAO)
public class JobsAgrupadosMB extends GenericMB {

	private static final long serialVersionUID = 1L;
	
	private List<Coordenador> coordenadores;
	private SelecaoPojo<Coordenador> coordenadoresSelecionados;
	
	private List<JobsAgrupadosPorCoordenadorDTO> jobs;
	private Job job;
	private HashMap<String, Object> filtro;

	private Coordenador coordenador;
	private String nomeCoordenador;
	private LocalDate dataInicioCadastro;
	private LocalDate dataFimCadastro;
	private LocalDate dataInicioPrevista;
	private LocalDate dataFimPrevista;
	private LocalDate dataInicioConclusao;
	private LocalDate dataFimConclusao;
	private String situacao;

	public JobsAgrupadosMB() {
		filtro = new HashMap<String, Object>();
		jobs = new ArrayList<JobsAgrupadosPorCoordenadorDTO>();
		coordenadoresSelecionados = new SelecaoPojo<Coordenador>();
	}

	public String inicializar() {
		limpar();
		coordenadores = this.coordenadoresService.listar();
//		jobs = jobsService.listaJobsAgrupadosPorCoordenador(filtro);
		operacao = Operacao.LISTAR;
		return AliasNavigation.JOBS_AGRUPADOS;
	}
	
	public void limpar() {
		coordenador = null;
		nomeCoordenador = null;
		dataInicioCadastro = null;
		dataFimCadastro = null;
		dataInicioPrevista = null;
		dataFimPrevista = null;
		dataInicioConclusao = null;
		dataFimConclusao = null;
		situacao = null;
		coordenadoresSelecionados.clear();
		selecionarTodos = false;
	}

	public List<JobsAgrupadosPorCoordenadorDTO> getJobs() {
		return jobs;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Coordenador> getCoordenadores(){
		return coordenadores;
	}

	public HashMap<String, Object> getFiltro() {
		return filtro;
	}

	public void setFiltro(HashMap<String, Object> filtro) {
		this.filtro = filtro;
	}

	public void listarJobsAgrupados() {
//		jobs = new ArrayList<JobsAgrupadosPorCoordenadorDTO>();
//		filtro.put("dataInicioCadastro", dataInicioCadastro);
//		filtro.put("dataFimCadastro", dataFimCadastro);
//		filtro.put("dataInicioPrevista", dataInicioPrevista);
//		filtro.put("dataFimPrevista", dataFimPrevista);
//		filtro.put("dataInicioConclusao", dataInicioConclusao);
//		filtro.put("dataFimConclusao", dataFimConclusao);
//
//		filtro.put("coordenadores", coordenadoresSelecionados.keySetSelected());
//		
//		//filtra pela situacao (todos, verde, amarelo, vermelho)
//		List<JobsAgrupadosPorCoordenadorDTO> retorno = jobsService.listaJobsAgrupadosPorCoordenador(filtro);
//
//		//se situacao for TODOS, traz tudo
//		if (situacao == null || "0".equals(situacao)) {
//			jobs = retorno;
//		} 
//		
//		//se situacao for verde,amarelo ou vermelho, entao itera para filtrar
//		else {
//			List<JobDTO> jobDTOs = null;
//			for (JobsAgrupadosPorCoordenadorDTO dto : retorno) {
//				jobDTOs = new ArrayList<JobDTO>();
//				for (JobDTO job : dto.getJobs()) {
//					if ("1".equals(situacao) && job.isEstaNoPrazo()) {
//						jobDTOs.add(job);
//					}
//					else if ("2".equals(situacao) && job.isEntregouMasAtrasou()) {
//						jobDTOs.add(job);
//					}
//					else if ("3".equals(situacao) && job.isAtrasadoNaoEntregou()) {
//						jobDTOs.add(job);
//					}
//				}
//				
//				dto.setJobs(jobDTOs);
//				if (!dto.getJobs().isEmpty()) {
//					jobs.add(dto);
//				}
//			}
//		} 
		 
	}

	public String getNomeCoordenador() {
		return nomeCoordenador;
	}

	public void setNomeCoordenador(String nomeCoordenador) {
		this.nomeCoordenador = nomeCoordenador;
	}

	public LocalDate getDataInicioCadastro() {
		return dataInicioCadastro;
	}

	public void setDataInicioCadastro(LocalDate dataInicioCadastro) {
		this.dataInicioCadastro = dataInicioCadastro;
	}

	public LocalDate getDataFimCadastro() {
		return dataFimCadastro;
	}

	public void setDataFimCadastro(LocalDate dataFimCadastro) {
		this.dataFimCadastro = dataFimCadastro;
	}

	public LocalDate getDataInicioPrevista() {
		return dataInicioPrevista;
	}

	public void setDataInicioPrevista(LocalDate dataInicioPrevista) {
		this.dataInicioPrevista = dataInicioPrevista;
	}

	public LocalDate getDataFimPrevista() {
		return dataFimPrevista;
	}

	public void setDataFimPrevista(LocalDate dataFimPrevista) {
		this.dataFimPrevista = dataFimPrevista;
	}

	public LocalDate getDataInicioConclusao() {
		return dataInicioConclusao;
	}

	public void setDataInicioConclusao(LocalDate dataInicioConclusao) {
		this.dataInicioConclusao = dataInicioConclusao;
	}

	public LocalDate getDataFimConclusao() {
		return dataFimConclusao;
	}

	public void setDataFimConclusao(LocalDate dataFimConclusao) {
		this.dataFimConclusao = dataFimConclusao;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public SelecaoPojo<Coordenador> getCoordenadoresSelecionados() {
		return coordenadoresSelecionados;
	}

	public void setCoordenadoresSelecionados(
			SelecaoPojo<Coordenador> coordenadoresSelecionados) {
		this.coordenadoresSelecionados = coordenadoresSelecionados;
	}

	public void setCoordenadores(List<Coordenador> coordenadores) {
		this.coordenadores = coordenadores;
	}
	
	public void selecionarTodos(AjaxBehaviorEvent event){
		if(selecionarTodos){
			for(Coordenador c : getCoordenadores()){
				coordenadoresSelecionados.put(c, "true");
			}
		}else{
			coordenadoresSelecionados.clear();
		}
	}

}
