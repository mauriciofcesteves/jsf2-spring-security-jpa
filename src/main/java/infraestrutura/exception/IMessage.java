package infraestrutura.exception;

import javax.faces.application.FacesMessage.Severity;

public interface IMessage {

	public static final String BUNDLE_NAME = "MessageResources";

	/**
	 * Retorna a chave que ir� localizar o valor no bundle.
	 */
	public String getKey();

	/**
	 * Retorna o Label da mensagem.
	 */
	public String getMensagem();

	/**
	 * Retorna se a Severidade � Erro, Warning ou Info.
	 */
	public Severity getSeverity();

}
