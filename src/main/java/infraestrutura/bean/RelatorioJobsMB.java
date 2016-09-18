package infraestrutura.bean;

import infraestrutura.bean.dto.DashboardDTO;
import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Curso;
import infraestrutura.model.Disciplina;
import infraestrutura.model.Documento;
import infraestrutura.util.CadernetaUtil;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("relatorioJobsMB")
@Scope(GenericMB.SESSAO)
public class RelatorioJobsMB extends GenericMB {

	private static final long serialVersionUID = 1L;

	private List<Coordenador> coordenadores;
	private SelecaoPojo<Coordenador> coordenadoresSelecionados;
	
	private LocalDate dataInicioCadastro;
	private LocalDate dataFimCadastro;
	
	List<DashboardDTO> dashboard;

	public RelatorioJobsMB(){
		dashboard = new ArrayList<DashboardDTO>();
		coordenadoresSelecionados = new SelecaoPojo<Coordenador>();
	}

	private void limparDados(){
		coordenadoresSelecionados.clear();
		dataInicioCadastro = null;
		dataFimCadastro = null;
		selecionarTodos = false;
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

	@Override
	public String inicializar() {
		operacao = Operacao.RELATORIO;
		coordenadores = this.coordenadoresService.listar();
		limparDados();
		return AliasNavigation.RELATORIO_JOBS;
	}

	public List<Curso> getCursos(){
		return this.cursosService.listar();
	}

	public List<Coordenador> getCoordenadores(){
		return coordenadores;
	}

	public List<Disciplina> getDisciplinas(){
		return this.disciplinasService.listar();
	}

	public void consultar(){
//		dashboard = jobsService.getDashboard(coordenadoresSelecionados.keySetSelected(), dataInicioCadastro, dataFimCadastro);
	}

	public boolean isRelatorioProfessor() {
		return Operacao.RELATORIO_PROFESSOR.equals(operacao);
	}

	public boolean isRelatorioCoordenador() {
		return Operacao.RELATORIO_COORDENADOR.equals(operacao);
	}

	public void download(){
		consultar();
		try {
//			Documento documento = relatoriosService.gerarRelatorioJobs(dashboard);
//			CadernetaUtil.download(documento);
			ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			ManagedBeanUtil.adicionarMensagem(ErrorMessage.ERRO_GERACAO_RELATORIO.getMensagem(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public SelecaoPojo<Coordenador> getCoordenadoresSelecionados() {
		return coordenadoresSelecionados;
	}

	public void setCoordenadoresSelecionados(
			SelecaoPojo<Coordenador> coordenadoresSelecionados) {
		this.coordenadoresSelecionados = coordenadoresSelecionados;
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

	public List<DashboardDTO> getDashboard() {
		return dashboard;
	}

	public void setDashboard(List<DashboardDTO> dashboard) {
		this.dashboard = dashboard;
	}

}
