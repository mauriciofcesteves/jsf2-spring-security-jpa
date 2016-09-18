package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.enumeration.OperacaoCaderneta;
import infraestrutura.bean.infra.Filtro;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.ErrorMessage;
import infraestrutura.exception.InfoMessage;
import infraestrutura.exception.NegocioException;
import infraestrutura.model.Caderneta;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Curso;
import infraestrutura.model.Disciplina;
import infraestrutura.model.ParametroSistema;
import infraestrutura.model.Professor;
import infraestrutura.model.SituacaoCaderneta;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;

import org.hsqldb.lib.StringUtil;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("cadernetasMB")
@Scope(GenericMB.SESSAO)
public class CadernetasMB extends GenericMB {

	private static final long serialVersionUID = 1L;
	
	private OperacaoCaderneta operacaoCaderneta;
	
	private List<Caderneta> cadernetas;
	private SelecaoPojo<Caderneta> cadernetasSelecionadas;
	
	private Caderneta caderneta;
	
	private Filtro filtroDevolucaoProfessor;
	private Filtro filtroRetiradaCoordenador;
	private Filtro filtroDevolucaoCoordenador;
	
	private String loginCoordenador;
	private String senhaCoordenador;

	public CadernetasMB(){
		cadernetas = new ArrayList<Caderneta>();
		cadernetasSelecionadas = new SelecaoPojo<Caderneta>();
		
		filtroDevolucaoProfessor =  new Filtro();
		filtroDevolucaoProfessor.addOrder("protocolo", false);
		filtroDevolucaoProfessor.put("situacao", SituacaoCaderneta.CADERNETA_COM_PROFESSOR);
		
		filtroRetiradaCoordenador =  new Filtro();
		filtroRetiradaCoordenador.addOrder("protocolo", false);
		filtroRetiradaCoordenador.put("situacao", SituacaoCaderneta.AGUARDANDO_COORDENADOR);
		
		filtroDevolucaoCoordenador =  new Filtro();
		filtroDevolucaoCoordenador.addOrder("protocolo", false);
		filtroDevolucaoCoordenador.put("situacao", SituacaoCaderneta.CADERNETA_COM_COORDENADOR);
	}
	
	private void limparDados(){
		cadernetasSelecionadas.clear();
		selecionarTodos = false;
		caderneta = null;
		loginCoordenador = null;
		senhaCoordenador = null;
	}
	
	public String inicializar() {
		operacao = Operacao.LISTAR;
		operacaoCaderneta = OperacaoCaderneta.CADASTRAR;
		cadernetas = cadernetasService.listarOrdenada();
		limparDados();
		return AliasNavigation.MANTER_CADERNETAS;
	}
	
	public String inicializarDevolucaoProfessor(){
		operacao = Operacao.LISTAR;
		operacaoCaderneta = OperacaoCaderneta.REGISTRAR_DEVOLUCAO_PROFESSOR;
		cadernetas = cadernetasService.consultarPorFiltro(filtroDevolucaoProfessor);
		limparDados();
		return AliasNavigation.MANTER_CADERNETAS;
	}
	
	public String inicializarRetiradaCoordenador(){
		operacao = Operacao.LISTAR;
		operacaoCaderneta = OperacaoCaderneta.REGISTRAR_RETIRADA_COORDENADOR;
		cadernetas = cadernetasService.consultarPorFiltro(filtroRetiradaCoordenador);
		limparDados();
		return AliasNavigation.MANTER_CADERNETAS;
	}
	
	public String inicializarDevolucaoCoordenador(){
		operacao = Operacao.LISTAR;
		operacaoCaderneta = OperacaoCaderneta.REGISTRAR_DEVOLUCAO_COORDENADOR;
		cadernetas = cadernetasService.consultarPorFiltro(filtroDevolucaoCoordenador);
		limparDados();
		return AliasNavigation.MANTER_CADERNETAS;
	}
	
	public void selecionarTodos(AjaxBehaviorEvent event){
		if(selecionarTodos){
			for(Caderneta c : cadernetas){
				if(c.isCadernetaComProfessor()){
					cadernetasSelecionadas.put(c, "true");
				}
			}
		}else{
			cadernetasSelecionadas.clear();
		}
	}

	@Override
	public void exibirCamposInclusao() {
		if(parametroSistemaService.getParametroSistema() == null){
			throw new NegocioException(ErrorMessage.PARAMETRO_SISTEMA_NAO_CONFIGURADO);
		}
		super.exibirCamposInclusao();
	}
	
	@Override
	public void prepararInclusao() {
		caderneta = new Caderneta();
		caderneta.setProtocolo(cadernetasService.gerarProtocolo());
		caderneta.setDataRetiradaProfessor(new LocalDate());
	}
	
	@Override
	public void prepararAlteracao() {
		if(isOperacaoRegistrarDevolucaoProfessor()){
			caderneta.setProtocoloDevolucaoProfessor(cadernetasService.gerarProtocolo());
		}
		
		if(isOperacaoRegistrarRetiradaCoordenador()){
			caderneta.setProtocoloRetiradaCoordenador(cadernetasService.gerarProtocolo());
		}
		
		if(isOperacaoRegistrarDevolucaoCoordenador()){
			caderneta.setProtocoloDevolucaoCoordenador(cadernetasService.gerarProtocolo());
		}
	}
	
	public void salvar(){
		cadernetasService.salvar(caderneta);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void registrarDevolucaoProfessor(){
		cadernetasService.registrarDevolucaoProfessor(caderneta);
		inicializarDevolucaoProfessor();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void registrarRetiradaCoordenador(){
		cadernetasService.registrarRetiradaCoordenador(caderneta, loginCoordenador, senhaCoordenador);
		inicializarRetiradaCoordenador();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void registrarDevolucaoCoordenador(){
		cadernetasService.registrarDevolucaoCoordenador(caderneta);
		inicializarDevolucaoCoordenador();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void excluir(){
		cadernetasService.excluir(cadernetasSelecionadas.keySetSelected());
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public boolean isPodeExcluir(){
		return !cadernetasSelecionadas.keySetSelected().isEmpty() && isOperacaoListar();
	}

	public Caderneta getCaderneta() {
		return caderneta;
	}

	public void setCaderneta(Caderneta caderneta) {
		this.caderneta = caderneta;
	}

	public List<Caderneta> getCadernetas() {
		return cadernetas;
	}

	public void setCadernetas(List<Caderneta> cadernetas) {
		this.cadernetas = cadernetas;
	}

	public SelecaoPojo<Caderneta> getCadernetasSelecionadas() {
		return cadernetasSelecionadas;
	}

	public void setCadernetasSelecionadas(
			SelecaoPojo<Caderneta> cadernetasSelecionadas) {
		this.cadernetasSelecionadas = cadernetasSelecionadas;
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
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		if(caderneta != null && caderneta.getCurso() != null  && caderneta.getCurso().ehValido()){
			disciplinas.addAll(caderneta.getCurso().getDisciplinas());
		}
		return disciplinas;
	}
	
	public void cursoSelecionado(){
		if(caderneta != null && caderneta.getCurso() != null && caderneta.getCurso().ehValido()){
			caderneta.setCoordenador(caderneta.getCurso().getCoordenador());
		}
	}
	
	public void calcularDataPrevistaEntregaProfessor(){
		ParametroSistema parametroSistema = parametroSistemaService.getParametroSistema();
		int prazoProfessor = 0;
		if(!StringUtil.isEmpty(parametroSistema.getPrazoProfessor())){
			prazoProfessor = Integer.valueOf(parametroSistema.getPrazoProfessor());
		}
		
		if(caderneta != null){
			if(caderneta.getDataFinalDisciplina() != null){
				caderneta.setDataPrevistaDevolucaoProfessor(caderneta.getDataFinalDisciplina().plusDays(prazoProfessor));
			}else{
				caderneta.setDataPrevistaDevolucaoProfessor(null);
			}
		}
		
	}
	
	public void calcularDataPrevistaEntregaCoordenador(){
		ParametroSistema parametroSistema = parametroSistemaService.getParametroSistema();
		int prazoCoordenador = 0;
		if(!StringUtil.isEmpty(parametroSistema.getPrazoCoordenador())){
			prazoCoordenador = Integer.valueOf(parametroSistema.getPrazoCoordenador());
		}
		
		if(caderneta != null){
			if(caderneta.getDataDevolucaoProfessor() != null){
				caderneta.setDataPrevistaDevolucaoCoordenador(caderneta.getDataDevolucaoProfessor().plusDays(prazoCoordenador));
			}else{
				caderneta.setDataPrevistaDevolucaoCoordenador(null);
			}
		}
		
	}

	public OperacaoCaderneta getOperacaoCaderneta() {
		return operacaoCaderneta;
	}

	public void setOperacaoCaderneta(OperacaoCaderneta operacaoCaderneta) {
		this.operacaoCaderneta = operacaoCaderneta;
	}

	public Filtro getFiltroDevolucaoProfessor() {
		return filtroDevolucaoProfessor;
	}

	public void setFiltroDevolucaoProfessor(Filtro filtroDevolucaoProfessor) {
		this.filtroDevolucaoProfessor = filtroDevolucaoProfessor;
	}
	
	public boolean isOperacaoRegistrarDevolucaoProfessor() {
		return OperacaoCaderneta.REGISTRAR_DEVOLUCAO_PROFESSOR.equals(operacaoCaderneta);
	}
	
	public boolean isOperacaoRegistrarDevolucaoCoordenador() {
		return OperacaoCaderneta.REGISTRAR_DEVOLUCAO_COORDENADOR.equals(operacaoCaderneta);
	}
	
	public boolean isOperacaoRegistrarRetiradaCoordenador() {
		return OperacaoCaderneta.REGISTRAR_RETIRADA_COORDENADOR.equals(operacaoCaderneta);
	}
	
	public boolean isOperacaoCadastrar() {
		return OperacaoCaderneta.CADASTRAR.equals(operacaoCaderneta);
	}

	public String getSenhaCoordenador() {
		return senhaCoordenador;
	}

	public void setSenhaCoordenador(String senhaCoordenador) {
		this.senhaCoordenador = senhaCoordenador;
	}

	public Filtro getFiltroRetiradaCoordenador() {
		return filtroRetiradaCoordenador;
	}

	public void setFiltroRetiradaCoordenador(Filtro filtroRetiradaCoordenador) {
		this.filtroRetiradaCoordenador = filtroRetiradaCoordenador;
	}

	public String getLoginCoordenador() {
		return loginCoordenador;
	}

	public void setLoginCoordenador(String loginCoordenador) {
		this.loginCoordenador = loginCoordenador;
	}

}
