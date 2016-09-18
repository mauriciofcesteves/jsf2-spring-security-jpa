package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.infra.SelecaoPojo;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Perfil;
import infraestrutura.model.Usuario;
import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("manterUsuariosMB")
@Scope(GenericMB.SESSAO)
public class ManterUsuariosMB extends GenericMB {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private SelecaoPojo<Usuario> usuariosSelecionados;
	private Perfil perfil;
	
	public ManterUsuariosMB() {
		usuarios = new ArrayList<Usuario>();
		usuariosSelecionados = new SelecaoPojo<Usuario>();
	}
	
	public String inicializar() {
		operacao = Operacao.LISTAR;
		usuarios = usuarioService.listar();
		usuariosSelecionados.clear();
		return AliasNavigation.USUARIOS;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getNomeUsuario() {
		if(!isUsuarioLogado()){
			usuario = getUsuarioLogado();
		}
		return usuario.getNome();
	}
	
	@Override
	public void prepararInclusao() {
		perfil = null;
		usuario = new Usuario();
	}
	
	@Override
	public void prepararAlteracao() {
		super.prepararAlteracao();
		for(Perfil p : usuario.getPerfis()){
			perfil = p;
			break;
		}
	}
	
	public void excluir(){
		usuarioService.excluir(usuariosSelecionados.keySetSelected());
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isUsuarioLogado(){
		return usuario != null;
	}
	
	public void salvar(){
		usuario.adicionarPerfil(perfil);
		usuarioService.salvar(usuario);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public SelecaoPojo<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(SelecaoPojo<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}
	
	public boolean isPodeExcluir(){
		return !usuariosSelecionados.keySetSelected().isEmpty() && isOperacaoListar();
	}
	
	public List<Perfil> getPerfis(){
		List<Perfil> perfis = perfilService.listar();
		for(Iterator<Perfil> i = perfis.iterator();i.hasNext();){
			Perfil p = i.next();
			if(p.isCoordenador()){
				i.remove();
			}
		}
		return perfis;
	}
	
	public void selecionarTodos(AjaxBehaviorEvent event){
		if(selecionarTodos){
			for(Usuario u : usuarios){
				usuariosSelecionados.put(u, "true");
			}
		}else{
			usuariosSelecionados.clear();
		}
	}
	
}
