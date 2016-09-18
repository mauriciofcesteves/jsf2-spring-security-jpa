package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.Filtro;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Caderneta;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Curso;
import infraestrutura.model.Disciplina;
import infraestrutura.model.Documento;
import infraestrutura.model.Professor;
import infraestrutura.model.SituacaoCaderneta;
import infraestrutura.util.CadernetaUtil;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

@Component("relatoriosMB")
@Scope(GenericMB.SESSAO)
public class RelatoriosMB extends GenericMB {

	private static final long serialVersionUID = 1L;

	private List<Caderneta> cadernetas;

	private Filtro filtro;
	private LocalDate dataPrevista; 

	public RelatoriosMB(){
		cadernetas = new ArrayList<Caderneta>();
		filtro =  new Filtro();
	}

	public void preProcessarPDF(Object document) {
		Document doc = (Document) document;
		doc.setPageSize(PageSize.A4.rotate());
	}

	public String inicializarRelatorioProfessor() {
		operacao = Operacao.RELATORIO_PROFESSOR;
		inicializar();
		return AliasNavigation.RELATORIO_CADERNETA_PROFESSOR;
	}

	public String inicializarRelatorioCoordenador() {
		operacao = Operacao.RELATORIO_COORDENADOR;
		inicializar();
		return AliasNavigation.RELATORIO_CADERNETA_COORDENADOR;
	}

	@Override
	public String inicializar() {
		consultar();
		return null;
	}

	public List<Caderneta> getCadernetas() {
		return cadernetas;
	}

	public void setCadernetas(List<Caderneta> cadernetas) {
		this.cadernetas = cadernetas;
	}

	public List<Curso> getCursos(){
		return this.cursosService.listar();
	}

	public List<Professor> getProfessores(){
		return this.professoresService.listar();
	}

	public List<Coordenador> getCoordenadores(){
		return this.coordenadoresService.listar();
	}

	public List<Disciplina> getDisciplinas(){
		return this.disciplinasService.listar();
	}

	public void consultar(){
		ajustarFiltrosAntesDaPesquisa();

		if (isRelatorioProfessor()) {
			filtro.put("situacao", SituacaoCaderneta.CADERNETA_COM_PROFESSOR);
		}
		else if (isRelatorioCoordenador()) {
			filtro.put("situacao", SituacaoCaderneta.CADERNETA_COM_COORDENADOR);
		}

		cadernetas = cadernetasService.consultarPorFiltro(filtro);
	}

	private void ajustarFiltrosAntesDaPesquisa() {
		if (dataPrevista != null) {
			if (isRelatorioProfessor()) {
				filtro.put("dataPrevistaDevolucaoProfessor", dataPrevista);
			} else if (isRelatorioCoordenador()) {
				filtro.put("dataPrevistaDevolucaoCoordenador", dataPrevista);
			}
		}else{
			filtro.clear();
		}
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public LocalDate getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public boolean isRelatorioProfessor() {
		return Operacao.RELATORIO_PROFESSOR.equals(operacao);
	}

	public boolean isRelatorioCoordenador() {
		return Operacao.RELATORIO_COORDENADOR.equals(operacao);
	}

	public void download(){
		try {
//			Documento documento = relatoriosService.gerarRelatorioCadernetas(cadernetas, isRelatorioProfessor());
//			CadernetaUtil.download(documento);
			ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			ManagedBeanUtil.adicionarMensagem(ErrorMessage.ERRO_GERACAO_RELATORIO.getMensagem(), FacesMessage.SEVERITY_ERROR);
		}
	}

}
