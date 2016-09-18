package infraestrutura.bean;

import infraestrutura.bean.enumeration.AplicacaoAtiva;
import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.WarningMessage;
import infraestrutura.model.Usuario;
import infraestrutura.service.CadernetasService;
import infraestrutura.service.CoordenadoresService;
import infraestrutura.service.CursosService;
import infraestrutura.service.DisciplinasService;
import infraestrutura.service.ParametroSistemaService;
import infraestrutura.service.PerfilService;
import infraestrutura.service.ProfessoresService;
import infraestrutura.service.UsuarioService;
import infraestrutura.util.ManagedBeanUtil;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class GenericMB implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String SESSAO = "session";
	protected Operacao operacao;
	protected boolean selecionarTodos;
	protected AplicacaoAtiva aplicacao;
	
	public abstract String inicializar();

	@Autowired
	protected UsuarioService usuarioService;
	
	@Autowired
	protected PerfilService perfilService;
	
	@Autowired
	protected CursosService cursosService;
	
	@Autowired
	protected CoordenadoresService coordenadoresService;
	
	@Autowired
	protected DisciplinasService disciplinasService;
	
	@Autowired
	protected ProfessoresService professoresService;

//	@Autowired
//	protected JobsService jobsService;

	@Autowired
	protected CadernetasService cadernetasService;
	
	@Autowired
	protected ParametroSistemaService parametroSistemaService;
	
//	@Autowired
//	protected RelatoriosService relatoriosService;

	/**
	 * Retorna o usu�rio logado.
	 */
	public Usuario getUsuarioLogado() {
		return (Usuario)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
	}

	public boolean isOperacaoInserir() {
		return Operacao.INSERIR.equals(operacao);
	}
	
	public boolean isOperacaoAlterar() {
		return Operacao.ALTERAR.equals(operacao);
	}
	
	public boolean isOperacaoExcluir() {
		return Operacao.EXCLUIR.equals(operacao);
	}
	
	public boolean isOperacaoConsultar() {
		return Operacao.CONSULTAR.equals(operacao);
	}
	
	public boolean isOperacaoListar() {
		return Operacao.LISTAR.equals(operacao);
	}
	
	public int getTotalMensagens() {
		return FacesContext.getCurrentInstance().getMessageList().size();
	}

	/**
	 * Retorna a inst�ncia atual do managedbean identificado pelo nome
	 * informado.
	 */
	@SuppressWarnings("deprecation")
	protected Object getMB(String nomeMB) {
		return FacesContext.getCurrentInstance().getApplication()
				.getVariableResolver().resolveVariable(
						FacesContext.getCurrentInstance(), nomeMB);
	}
	
	/**
	 * Exibe a tela de inclus�o
	 */
	public void exibirCamposInclusao() {
		operacao = Operacao.INSERIR;
		prepararInclusao();
	}

	/**
	 * Exibe a tela de altera��o
	 */
	public void exibirCamposAlteracao() {
		operacao = Operacao.ALTERAR;
		prepararAlteracao();
	}
	
	/**
	 * Exibe a tela de exclus�o
	 */
	public void exibirCamposExclusao() {
		ManagedBeanUtil.adicionarMensagem(WarningMessage.DESEJA_EXCLUIR.getMensagem(), FacesMessage.SEVERITY_WARN);
		operacao = Operacao.EXCLUIR;
	}
	
	public String exibirPaginaInicial(){
		return AliasNavigation.HOME;
	}
	
	public void entrarAplicacaoCaderneta() {
		aplicacao = AplicacaoAtiva.CADERNETA;
	}
	
	public void entrarAplicacaoJobs() {
		aplicacao = AplicacaoAtiva.JOBS;
	}
	
	public String entrarHome() {
		aplicacao = AplicacaoAtiva.HOME;
		return exibirPaginaInicial();
	}
	
	public boolean isAplicacaoCaderneta() {
		return AplicacaoAtiva.CADERNETA.equals(aplicacao);
	}
	
	public boolean isAplicacaoJobs() {
		return AplicacaoAtiva.JOBS.equals(aplicacao);
	}
	
	public boolean isHome() {
		return AplicacaoAtiva.HOME.equals(aplicacao);
	}
	
	/**
	 * Chamado por padr�o ao exibir a tela de inclus�o
	 */
	public void prepararInclusao(){}
	
	/**
	 * Chamado por padr�o ao exibir a tela de altera��o
	 */
	public void prepararAlteracao(){}

	public boolean isSelecionarTodos() {
		return selecionarTodos;
	}

	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public CursosService getCursosService() {
		return cursosService;
	}

	public void setCursosService(CursosService cursosService) {
		this.cursosService = cursosService;
	}

	public CoordenadoresService getCoordenadoresService() {
		return coordenadoresService;
	}

	public void setCoordenadoresService(CoordenadoresService coordenadoresService) {
		this.coordenadoresService = coordenadoresService;
	}

	public DisciplinasService getDisciplinasService() {
		return disciplinasService;
	}

	public void setDisciplinasService(DisciplinasService disciplinasService) {
		this.disciplinasService = disciplinasService;
	}

	public ProfessoresService getProfessoresService() {
		return professoresService;
	}

	public void setProfessoresService(ProfessoresService professoresService) {
		this.professoresService = professoresService;
	}

	public CadernetasService getCadernetasService() {
		return cadernetasService;
	}

	public void setCadernetasService(CadernetasService cadernetasService) {
		this.cadernetasService = cadernetasService;
	}

	public ParametroSistemaService getParametroSistemaService() {
		return parametroSistemaService;
	}

	public void setParametroSistemaService(
			ParametroSistemaService parametroSistemaService) {
		this.parametroSistemaService = parametroSistemaService;
	}
	
}
