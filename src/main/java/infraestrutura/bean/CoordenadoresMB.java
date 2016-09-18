package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Coordenador;
import infraestrutura.service.CoordenadoresService;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("coordenadoresMB")
@Scope(GenericMB.SESSAO)
public class CoordenadoresMB extends GenericMB {

	private static final long serialVersionUID = 1L;
	
	private List<Coordenador> coordenadores;
	private SelecaoPojo<Coordenador> coordenadoresSelecionados;
	
	private Coordenador coordenador;

	public CoordenadoresMB(){
		coordenadores = new ArrayList<Coordenador>();
		coordenadoresSelecionados = new SelecaoPojo<Coordenador>();
	}
	
	public String inicializar() {
		coordenadores = coordenadoresService.listar();
		operacao = Operacao.LISTAR;
		coordenadoresSelecionados.clear();
		selecionarTodos = false;
		return AliasNavigation.COORDENADORES;
	}
	
	public void selecionarTodos(AjaxBehaviorEvent event){
		if(selecionarTodos){
			for(Coordenador c : coordenadores){
				coordenadoresSelecionados.put(c, "true");
			}
		}else{
			coordenadoresSelecionados.clear();
		}
	}

	@Override
	public void prepararInclusao() {
		coordenador = new Coordenador();
	}
	
	public void salvar(){
		coordenadoresService.salvar(coordenador);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void excluir(){
		coordenadoresService.excluir(coordenadoresSelecionados.keySetSelected());
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public boolean isPodeExcluir(){
		return !coordenadoresSelecionados.keySetSelected().isEmpty() && isOperacaoListar();
	}

	public List<Coordenador> getCoordenadores() {
		return coordenadores;
	}

	public void setCoordenadores(List<Coordenador> coordenadores) {
		this.coordenadores = coordenadores;
	}

	public SelecaoPojo<Coordenador> getCoordenadoresSelecionados() {
		return coordenadoresSelecionados;
	}

	public void setCoordenadoresSelecionados(
			SelecaoPojo<Coordenador> coordenadoresSelecionados) {
		this.coordenadoresSelecionados = coordenadoresSelecionados;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public void setCoordenadoresService(CoordenadoresService coordenadoresService) {
		this.coordenadoresService = coordenadoresService;
	}

}
