package infraestrutura.exception;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

public enum ErrorMessage implements IMessage {

	PROFISSAO_JA_CADASTRADA("erro_profissao_ja_cadastrada"), //
	DATA_JA_CADASTRADA("erro_data_ja_cadastrada"), //
	ERRO_EMAIL_JA_CADASTRADO("erro_email_ja_cadastrado"), //
	ERRO_EMAIL_INVALIDO("erro_email_invalido"), //
	ERRO_CAMPO_OBRIGATORIO("erro_campo_obrigatorio"),
	ERRO_EMAIL_NAO_ENVIADO("erro_email_nao_enviado"),
	PROFISSAO_EM_USO("erro_profissao_em_uso"),
	ERRO_EMAIL_INFORMADO_NAO_ENVIADO("erro_email_informado_nao_enviado"),
	ERRO_COORDENADOR_INVALIDO("erro_coordenador_invalido"),
	ERRO_DATA_MENOR_ATUAL("erro_data_menor_atual"),
	ERRO_DATA_MAIOR_ATUAL("erro_data_maior_atual"),
	DATA_FINAL_MENOR_INICIAL("data_final_menor_inicial"),
	ERRO_DEVOLUCAO_ANTES_TERMINO_DISCIPLINA("erro_devolucao_antes_termino_disciplina"),
	PARAMETRO_SISTEMA_NAO_CONFIGURADO("parametro_sistema_nao_configurado"),
	USUARIO_NAO_PODE_SER_DELETADO("usuario_nao_pode_ser_deletado"),
	SENHA_NAO_CONFEREM("senha_nao_conferem"),
	CAMPO_JA_CADASTRADO("campo_ja_cadastrado"),
	ERRO_GERACAO_RELATORIO("erro_geracao_relatorio"),
	DATA_INVALIDA("data_invalida")
	;

	private String key;
	
	private ErrorMessage(String key) {
		this.key = key;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getKey() {
		return key;
	}

	/**
	 * {@inheritDoc}
	 */
	public Severity getSeverity() {
		return FacesMessage.SEVERITY_ERROR;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getMensagem() {
		ResourceBundle bundle = ResourceBundle.getBundle(IMessage.BUNDLE_NAME);
		return bundle.getString(key);
	}
}
