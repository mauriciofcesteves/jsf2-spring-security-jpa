package infraestrutura.bean;

import infraestrutura.bean.enumeration.AplicacaoAtiva;
import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.Usuario;
import infraestrutura.util.ManagedBeanUtil;

import javax.faces.application.FacesMessage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("usuarioMB")
@Scope(GenericMB.SESSAO)
public class UsuarioMB extends GenericMB {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	private String senha;
	private String confirmacaoSenha;
	
	public UsuarioMB(){
		aplicacao = AplicacaoAtiva.HOME;
	}

	public String inicializar() {
		usuario = getUsuarioLogado();
		operacao = Operacao.CONSULTAR;
		return AliasNavigation.PERFIL;
	}

	public String getNomeUsuario() {
		if(!isUsuarioLogado()){
			usuario = getUsuarioLogado();
		}
		return usuario.getNome();
	}

	public void exibirCamposAlteracao() {
		operacao = Operacao.ALTERAR;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	public void salvar(){
		usuarioService.salvar(usuario);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}
	
	public void salvarSenha(){
		usuarioService.salvarSenha(usuario, senha, confirmacaoSenha);
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

}
