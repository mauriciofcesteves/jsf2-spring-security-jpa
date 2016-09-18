package infraestrutura.util;

import infraestrutura.exception.IMessage;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class ManagedBeanUtil {

	public static void adicionarMensagem(String mensagem, Severity severity) {
		FacesMessage facesMessage = new FacesMessage();
		facesMessage.setSeverity(severity);
		facesMessage.setDetail(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public static void adicionarMensagem(IMessage mensagem, Severity severity, Object... args) {
		if (args == null) {
			adicionarMensagem(mensagem.getMensagem(), severity);
		} else {
			FacesMessage facesMsg = new FacesMessage();
			facesMsg.setDetail(formatarMensagem(mensagem.getMensagem(), args));
			facesMsg.setSeverity(mensagem.getSeverity());
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
		}
	}
	
	public static String formatarMensagem(String mensagem, Object... arguments) {
		MessageFormat formatter = new MessageFormat("");
		formatter.applyPattern(mensagem);
		return formatter.format(arguments);
	}
	
	public static String getMessage(String keyMessage){
		String sMessage = "";
		if (keyMessage != null) {
			ResourceBundle bundle = ResourceBundle.getBundle(IMessage.BUNDLE_NAME);
			
			if (bundle != null) {
				try {
					sMessage = bundle.getString(keyMessage);
				} catch(MissingResourceException e) {
					e.printStackTrace();
				}
			}
		}
		return sMessage;
	}
	
	public static String getMessageBundle(IMessage message) {
		return getMessage(message.getKey());
	}
}
