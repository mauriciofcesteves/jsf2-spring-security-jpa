package infraestrutura.bean;

import infraestrutura.bean.enumeration.Operacao;
import infraestrutura.bean.navigation.AliasNavigation;
import infraestrutura.exception.InfoMessage;
import infraestrutura.model.ParametroSistema;
import infraestrutura.util.ManagedBeanUtil;
import infraestrutura.util.MessageResourcesConstants;

import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("parametroSistemaMB")
@Scope(GenericMB.SESSAO)
public class ParametroSistemaMB extends GenericMB {

	private static final long serialVersionUID = 1L;

	private ParametroSistema parametro;
	
	enum OperacaoParametroSistema{
		PARAMETROS_GERAIS, EMAIL, TEMPLATES;
	};
	
	private OperacaoParametroSistema operacaoParametroSistema;
	
	@Override
	public String inicializar() {
		operacao = Operacao.INSERIR;
		operacaoParametroSistema = OperacaoParametroSistema.PARAMETROS_GERAIS;
		parametro = parametroSistemaService.getParametroSistema();
		if(parametro == null){
			parametro = new ParametroSistema();
		}
		return AliasNavigation.MANTER_PARAMETROS_SISTEMA;
	}
	
	public String inicializarEmail(){
		operacao = Operacao.INSERIR;
		operacaoParametroSistema = OperacaoParametroSistema.EMAIL;
		parametro = parametroSistemaService.getParametroSistema();
		if(parametro == null){
			parametro = new ParametroSistema();
		}
		return AliasNavigation.MANTER_PARAMETROS_SISTEMA_EMAILS;
	}
	
	public String inicializarTemplatesEmail(){
		operacao = Operacao.INSERIR;
		operacaoParametroSistema = OperacaoParametroSistema.TEMPLATES;
		parametro = parametroSistemaService.getParametroSistema();
		if(parametro == null){
			parametro = new ParametroSistema();
		}
		return AliasNavigation.MANTER_PARAMETROS_SISTEMA_TEMPLATES_EMAIL;
	}

	public void salvar(){
		if(OperacaoParametroSistema.TEMPLATES.equals(operacaoParametroSistema)){
			parametroSistemaService.salvarTemplates(parametro);
		}else if(OperacaoParametroSistema.EMAIL.equals(operacaoParametroSistema)){
			parametroSistemaService.salvarEmail(parametro);
		}
		else{
			parametroSistemaService.salvar(parametro);
		}
		inicializar();
		ManagedBeanUtil.adicionarMensagem(InfoMessage.OPERACAO_SUCESSO.getMensagem(), FacesMessage.SEVERITY_INFO);
	}

	public void cancelar(){
		parametro = parametroSistemaService.getParametroSistema();
	}
	
	public void testarConfiguracao(){
//		EmailUtil.enviarEmailTratandoErro(parametro,ManagedBeanUtil.getMessage(MessageResourcesConstants.TESTE_CONFIGURACAO_EMAIL), ManagedBeanUtil.getMessage(MessageResourcesConstants.TESTE_CONFIGURACAO_EMAIL),parametro.getEmail());
	}
	
	/**
	 * @return the parametro
	 */
	public ParametroSistema getParametro() {
		return parametro;
	}

	/**
	 * @param parametro the parametro to set
	 */
	public void setParametro(ParametroSistema parametro) {
		this.parametro = parametro;
	}

	public SelectItem[] getProtocolos() {
		SelectItem [] retorno = new SelectItem[3];
		retorno[0] = new SelectItem("", "Selecione");
//		retorno[1] = new SelectItem(EmailUtil.SSL, "SSL");
//		retorno[2] = new SelectItem(EmailUtil.TLS, "TLS");
		return retorno;
	}

	public OperacaoParametroSistema getOperacaoParametroSistema() {
		return operacaoParametroSistema;
	}

	public void setOperacaoParametroSistema(
			OperacaoParametroSistema operacaoParametroSistema) {
		this.operacaoParametroSistema = operacaoParametroSistema;
	}

}
