package infraestrutura.bean.converter;

import infraestrutura.model.SituacaoCaderneta;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

@Component(value="situacaoCadernetaConverter")
public class SituacaoCadernetaConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			return ((SituacaoCaderneta)value).getNome().toUpperCase();
		}
		return null;
	}
	
}
