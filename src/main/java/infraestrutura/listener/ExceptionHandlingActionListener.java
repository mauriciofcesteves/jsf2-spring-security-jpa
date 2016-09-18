package infraestrutura.listener;

import infraestrutura.exception.IMessage;
import infraestrutura.exception.MensagemNegocio;
import infraestrutura.exception.NegocioException;
import infraestrutura.util.ManagedBeanUtil;

import javax.faces.application.FacesMessage.Severity;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import com.sun.faces.application.ActionListenerImpl;

/**
 * Listener para tratamento de exce��es de neg�cio.
 */
public class ExceptionHandlingActionListener extends ActionListenerImpl implements ActionListener {

	/**
	 * Processa o evento e trata excecao caso ocorra.
	 */
	@Override
	public void processAction(ActionEvent event) {
		try {
			super.processAction(event);
		} catch (Throwable e) {
			NegocioException exception = getApplicationRuntimeException(e);
			tratarExcecaoNegocio(exception);
		}
	}

	private NegocioException getApplicationRuntimeException(Throwable e) {
		NegocioException retorno = null;

		if (e != null) {
			if (e instanceof NegocioException) {
				retorno = (NegocioException) e;
			} else {
				retorno = getApplicationRuntimeException(e.getCause());
			}
		}
		return retorno;
	}

	private void tratarExcecaoNegocio(NegocioException exception) {
		if (exception != null) {
			NegocioException ne = (NegocioException) exception;
	
			for (MensagemNegocio negocio : ne.getMessages()) {
				addMessage(negocio.getMessage(), negocio.getMessage().getSeverity(), negocio.getArguments());
			}
		}
	}

	private void addMessage(IMessage mensagem, Severity severity, Object... args) {
		ManagedBeanUtil.adicionarMensagem(mensagem, severity, args);
	}

}
