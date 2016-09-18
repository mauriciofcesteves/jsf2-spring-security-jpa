package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Coordenador;
import infraestrutura.model.Curso;
import infraestrutura.model.Disciplina;
import infraestrutura.service.CursosService;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("cursosMB")
@Scope(GenericMB.SESSAO)
public class CursosMB extends GenericMB {

	private static final long serialVersionUID = 1L;

	private List<Curso> cursos;
	private SelecaoPojo<Curso> cursosSelecionados;
	private Curso curso;
	
	private SelecaoPojo<Disciplina> disciplinasSelecionadas;
	private SelecaoPojo<Disciplina> disciplinasSelecionadasParaRemover;
	
	public CursosMB(){
		cursos = new ArrayList<Curso>();
		cursosSelecionados = new SelecaoPojo<Curso>();
		disciplinasSelecionadas = new SelecaoPojo<Disciplina>();
		disciplinasSelecionadasParaRemover = new SelecaoPojo<Disciplina>();
	}
	
	public String inicializar() {
		cursos = cursosService.listar();
		cursosSelecionados.clear();
		selecionarTodos = false;
		operacao = Operacao.LISTAR;
		return AliasNavigation.CURSOS;
	}
	
	@Override
	public void prepararInclusao() {
		curso = new Curso();
	}
	
	@Override
	public void prepararAlteracao() {
		curso = cursosService.consultarPorId(curso.getId());
	}
	
	
	public void salvar(){
		cursosService.salvar(curso);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void excluir(){
		cursosService.excluir(cursosSelecionados.keySetSelected());
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public CursosService getCursosService() {
		return cursosService;
	}

	public void setCursosService(CursosService cursosService) {
		this.cursosService = cursosService;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public SelecaoPojo<Curso> getCursosSelecionados() {
		return cursosSelecionados;
	}

	public void setCursosSelecionados(SelecaoPojo<Curso> cursosSelecionados) {
		this.cursosSelecionados = cursosSelecionados;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isSelecionarTodos() {
		return selecionarTodos;
	}

	public void setSelecionarTodos(boolean selecionarTodos) {
		this.selecionarTodos = selecionarTodos;
	}
	
	public void selecionarTodos(AjaxBehaviorEvent event){
		if(selecionarTodos){
			for(Curso c : cursos){
				cursosSelecionados.put(c, "true");
			}
		}else{
			cursosSelecionados.clear();
		}
	}
	
	public boolean isPodeExcluir(){
		return !cursosSelecionados.keySetSelected().isEmpty() && isOperacaoListar();
	}
	
	public List<Coordenador> getCoordenadores(){
		return coordenadoresService.listar();
	}

	public List<Disciplina> getDisciplinas(){
		List<Disciplina> disciplinas = disciplinasService.listar();
		if(curso != null){
			disciplinas.removeAll(curso.getDisciplinas());
		}
		return disciplinas;
	}

	public SelecaoPojo<Disciplina> getDisciplinasSelecionadas() {
		return disciplinasSelecionadas;
	}

	public void setDisciplinasSelecionadas(
			SelecaoPojo<Disciplina> disciplinasSelecionadas) {
		this.disciplinasSelecionadas = disciplinasSelecionadas;
	}

	public SelecaoPojo<Disciplina> getDisciplinasSelecionadasParaRemover() {
		return disciplinasSelecionadasParaRemover;
	}

	public void setDisciplinasSelecionadasParaRemover(
			SelecaoPojo<Disciplina> disciplinasSelecionadasParaRemover) {
		this.disciplinasSelecionadasParaRemover = disciplinasSelecionadasParaRemover;
	}
	
	public void adicionarDisciplina(){
		curso.getDisciplinas().addAll(disciplinasSelecionadas.keySetSelected());
		disciplinasSelecionadas.clear();
	}
	
	public void removerDisciplina(){
		curso.getDisciplinas().removeAll(disciplinasSelecionadasParaRemover.keySetSelected());
		disciplinasSelecionadasParaRemover.clear();
	}
	
}
