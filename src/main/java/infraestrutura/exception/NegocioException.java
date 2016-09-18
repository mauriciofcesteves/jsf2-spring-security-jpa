package infraestrutura.exception;

import infraestrutura.util.ManagedBeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Validacao de negocio.
 */
@SuppressWarnings("serial")
public class NegocioException extends RuntimeException {

	private List<MensagemNegocio> messages = new ArrayList<MensagemNegocio>();
	private Object[] arguments;
	
	public NegocioException(){
		super();
	}

	public NegocioException(IMessage objectMessage) {
		super(objectMessage.getMensagem());
		messages.add(new MensagemNegocio(objectMessage));
	}
	
	public NegocioException(IMessage objectMessage, Object... arguments) {
		super(objectMessage.getMensagem());
		this.arguments = arguments;
		messages.add(new MensagemNegocio(objectMessage, arguments));
	}

	public NegocioException(List<MensagemNegocio> messages) {
		super(messages.get(0).getMessage().getMensagem());
		this.messages = messages;
	}

	public List<MensagemNegocio> getMessages() {
		return messages;
	}
	
	public void addMessage(MensagemNegocio mensagemNegocio){
		messages.add(mensagemNegocio);
	}
	
	public void addMessage(IMessage objectMessage, Object... arguments){
		messages.add(new MensagemNegocio(objectMessage, arguments));
	}
	
	public boolean hasException(){
		return messages != null && !messages.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getArguments() {
		Object[] resolved = null;

		if (arguments != null) {
			resolved = new Object[arguments.length];

			for (int i = 0; i < arguments.length; i++) {
				if (arguments[i] instanceof IMessage) {
					resolved[i] = ManagedBeanUtil.getMessageBundle((IMessage) arguments[i]);
				} else {
					resolved[i] = arguments[i];
				}
			}
		}
		return resolved;
	}
}
