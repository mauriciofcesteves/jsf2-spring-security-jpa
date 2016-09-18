package infraestrutura.bean.converter;

import infraestrutura.util.ManagedBeanUtil;
import infraestrutura.util.MessageResourcesConstants;

import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.validator.ValidatorException;

import org.joda.time.LocalDate;
import org.primefaces.component.inputmask.InputMask;
import org.springframework.stereotype.Component;

@Component(value="localDateConverter")
public class LocalDateConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && value.length() == 10) {
			String[] arrayData = value.split("/");
			
			int dia = Integer.valueOf(arrayData[0]);
			int mes = Integer.valueOf(arrayData[1]);
			int ano = Integer.valueOf(arrayData[2]);
			return new LocalDate(new GregorianCalendar(ano, mes-1, dia));
		}

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			String[] arrayData = value.toString().split("-");

			int ano = Integer.valueOf(arrayData[0]);
			int mes = Integer.valueOf(arrayData[1]);
			int dia = Integer.valueOf(arrayData[2]);
			LocalDate data = new LocalDate(ano, mes, dia);
			return data.toString("dd/MM/yyyy");
		}
		return null;
	}
	
	public void validarData(FacesContext context, UIComponent component, Object value){
		String data = ((String)((InputMask) component).getSubmittedValue());
		try{
			String[] arrayData = data.split("/");
			
			int dia = Integer.valueOf(arrayData[0]);
			int mes = Integer.valueOf(arrayData[1]);
			int ano = Integer.valueOf(arrayData[2]);
			new LocalDate(ano, mes, dia);
		}catch(Throwable e){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ManagedBeanUtil.formatarMensagem(ManagedBeanUtil.getMessage(MessageResourcesConstants.DATA_INVALIDA),data),ManagedBeanUtil.formatarMensagem(ManagedBeanUtil.getMessage(MessageResourcesConstants.DATA_INVALIDA),data)));
		}
	}

}
