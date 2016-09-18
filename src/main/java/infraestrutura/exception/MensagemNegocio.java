package infraestrutura.exception;

import infraestrutura.util.ManagedBeanUtil;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * Representa uma mensagem de erro de negócio.
 */
public class MensagemNegocio {

	private IMessage message;
	private Object[] arguments;
	private Integer validationOrder = Integer.MAX_VALUE;

	/**
	 * Cria uma nova instância desta classe.
	 * 
	 * @param message A mensagem de erro.
	 */
	public MensagemNegocio(IMessage message) {
		this.message = message;
	}

	/**
	 * Cria uma nova inst�ncia desta classe.
	 * 
	 * @param message A mensagem de erro.
	 * @param arguments Os argumentos para serem substitu��dos na mensagem de
	 *            erro.
	 */
	public MensagemNegocio(IMessage message, Object... arguments) {
		this(message);
		this.arguments = arguments;
	}

	/**
	 * Retorna a mensagem de erro.
	 * 
	 * @return Uma instância de IMessage.
	 */
	public IMessage getMessage() {
		return message;
	}

	/**
	 * Retorna a propriedade "validationOrder".
	 * 
	 * @return Uma instância de Integer.
	 */
	public Integer getValidationOrder() {
		return validationOrder;
	}

	/**
	 * Atribui a propriedade "validationOrder".
	 * 
	 * @param validationOrder Uma instância de Integer.
	 */
	public void setValidationOrder(Integer validationOrder) {
		this.validationOrder = validationOrder;
	}

	/**
	 * Retorna os argumentos para serem substituídos na mensagem de erro.
	 * 
	 * @return Um array de Object.
	 */
	public Object[] getArguments() {
		Object[] resolved = null;

		if (arguments != null) {
			resolved = new Object[arguments.length];

			for (int i = 0; i < arguments.length; i++) {
				if (arguments[i] instanceof IMessage) {
					resolved[i] = ManagedBeanUtil.getMessageBundle((IMessage) arguments[i]);
				} else {
					String message = getMessageBundle(arguments[i].toString());

					if (message.contains("not found")) {
						resolved[i] = arguments[i];
					} else {
						resolved[i] = message;
					}
				}
			}
		}
		return resolved;
	}

	private String getMessageBundle(String msg) {
		String sMessage = message.getKey();

		if (FacesContext.getCurrentInstance() != null) {
			Locale usrLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			if (usrLocale != null) {
				ResourceBundle bundle = ResourceBundle.getBundle(IMessage.BUNDLE_NAME,
						usrLocale);

				if (bundle != null) {
					try {
						sMessage = bundle.getString(msg);
					} catch (MissingResourceException e) {
						sMessage = "?? key (" + msg + ") not found ??";
					}
				}
			}
		}
		return sMessage;
	}
}
