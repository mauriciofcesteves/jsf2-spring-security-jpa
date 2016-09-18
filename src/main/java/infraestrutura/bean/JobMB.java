package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Job;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("jobMB")
@Scope(GenericMB.SESSAO)
public class JobMB extends GenericMB {

	private static final long serialVersionUID = 1L;
	
	private List<Job> jobs;
	private SelecaoPojo<Job> jobsSelecionados;
	private SelecaoPojo<Coordenador> coordenadoresSelecionados;
	private SelecaoPojo<Coordenador> coordenadoresSelecionadosParaRemover;
	private List<Coordenador> coordenadoresParaGravar;
	
	private Job job;

	public JobMB(){
		jobs = new ArrayList<Job>();
		jobsSelecionados = new SelecaoPojo<Job>();
		coordenadoresSelecionados = new SelecaoPojo<Coordenador>();
		coordenadoresSelecionadosParaRemover = new SelecaoPojo<Coordenador>();
		coordenadoresParaGravar = new ArrayList<Coordenador>();
	}
	
	public String inicializar() {
//		jobs = jobsService.listar();
		operacao = Operacao.LISTAR;
		jobsSelecionados.clear();
		coordenadoresSelecionados.clear();
		coordenadoresSelecionadosParaRemover.clear();
		coordenadoresParaGravar.clear();
		selecionarTodos = false;
		return AliasNavigation.JOBS;
	}
	
	public void selecionarTodos(AjaxBehaviorEvent event){
		if(selecionarTodos){
			for(Job j : jobs){
				jobsSelecionados.put(j, "true");
			}
		}else{
			jobsSelecionados.clear();
		}
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public void prepararInclusao() {
		job = new Job();
	}
	
	public void salvar(){
//		jobsService.salvar(job, coordenadoresParaGravar);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void excluir(){
//		jobsService.excluir(jobsSelecionados.keySetSelected());
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void adicionarCoordenadores() {
		coordenadoresParaGravar.addAll(coordenadoresSelecionados.keySetSelected());
		coordenadoresSelecionados.clear();
	}
	
	public void removerCoordenadores(){
		coordenadoresParaGravar.removeAll(coordenadoresSelecionadosParaRemover.keySetSelected());
		coordenadoresSelecionadosParaRemover.clear();
	}

	public SelecaoPojo<Job> getJobsSelecionados() {
		return jobsSelecionados;
	}

	public void setJobsSelecionados(
			SelecaoPojo<Job> jobsSelecionados) {
		this.jobsSelecionados = jobsSelecionados;
	}
	
	public boolean isPodeExcluir(){
		return !jobsSelecionados.keySetSelected().isEmpty() && isOperacaoListar();
	}

	public List<Coordenador> getCoordenadores(){
		List<Coordenador> coordenadores = coordenadoresService.listar();
		if (coordenadoresParaGravar != null) {
			coordenadores.removeAll(coordenadoresParaGravar);
		}
		return coordenadores;
	}

	public SelecaoPojo<Coordenador> getCoordenadoresSelecionados() {
		return coordenadoresSelecionados;
	}

	public void setCoordenadoresSelecionados(
			SelecaoPojo<Coordenador> coordenadoresSelecionados) {
		this.coordenadoresSelecionados = coordenadoresSelecionados;
	}

	public SelecaoPojo<Coordenador> getCoordenadoresSelecionadosParaRemover() {
		return coordenadoresSelecionadosParaRemover;
	}

	public void setCoordenadoresSelecionadosParaRemover(
			SelecaoPojo<Coordenador> coordenadoresSelecionadosParaRemover) {
		this.coordenadoresSelecionadosParaRemover = coordenadoresSelecionadosParaRemover;
	}

	public List<Coordenador> getCoordenadoresParaGravar() {
		return coordenadoresParaGravar;
	}

	public void setCoordenadoresParaGravar(List<Coordenador> coordenadoresParaGravar) {
		this.coordenadoresParaGravar = coordenadoresParaGravar;
	}

}
