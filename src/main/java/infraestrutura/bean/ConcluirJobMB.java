package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.Filtro;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Job;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("concluirJobMB")
@Scope(GenericMB.SESSAO)
public class ConcluirJobMB extends GenericMB {

	private static final long serialVersionUID = 1L;
	
	private List<Job> jobs;
	
	private Job job;

	public ConcluirJobMB(){
		jobs = new ArrayList<Job>();
	}

	@Override
	public void prepararAlteracao() {
		job.setDataConclusao(new LocalDate());
	}

	public String inicializar() {
		Filtro filtro = new Filtro();
		filtro.put("coordenador.matricula", getUsuarioLogado().getLogin());
//		jobs = jobsService.consultarPorFiltro(filtro);
		operacao = Operacao.LISTAR;
		selecionarTodos = false;
		return AliasNavigation.CONCLUIR_JOBS;
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

	public void salvar(){
//		jobsService.alterar(job);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public List<Coordenador> getCoordenadores(){
		return coordenadoresService.listar();
	}

}
