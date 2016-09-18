package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Disciplina;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("disciplinasMB")
@Scope(GenericMB.SESSAO)
public class DisciplinasMB extends GenericMB {

	private static final long serialVersionUID = 1L;
	
	private List<Disciplina> disciplinas;
	private SelecaoPojo<Disciplina> disciplinasSelecionadas;
	
	private Disciplina disciplina;

	public DisciplinasMB(){
		disciplinas = new ArrayList<Disciplina>();
		disciplinasSelecionadas = new SelecaoPojo<Disciplina>();
	}
	
	public String inicializar() {
		disciplinas = disciplinasService.listar();
		disciplinasSelecionadas.clear();
		selecionarTodos = false;
		operacao = Operacao.LISTAR;
		return AliasNavigation.DISCIPLINAS;
	}

	@Override
	public void prepararInclusao() {
		disciplina = new Disciplina();
	}
	
	public void salvar(){
		disciplinasService.salvar(disciplina);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void excluir(){
		disciplinasService.excluir(disciplinasSelecionadas.keySetSelected());
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public SelecaoPojo<Disciplina> getDisciplinasSelecionadas() {
		return disciplinasSelecionadas;
	}

	public void setDisciplinasSelecionadas(
			SelecaoPojo<Disciplina> disciplinasSelecionadas) {
		this.disciplinasSelecionadas = disciplinasSelecionadas;
	}
	
	public boolean isPodeExcluir(){
		return !disciplinasSelecionadas.keySetSelected().isEmpty() && isOperacaoListar();
	}
	
	public void selecionarTodos(AjaxBehaviorEvent event){
		if(selecionarTodos){
			for(Disciplina d : disciplinas){
				disciplinasSelecionadas.put(d, "true");
			}
		}else{
			disciplinasSelecionadas.clear();
		}
	}

}
