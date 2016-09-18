package infraestrutura.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component(value="booleanConverter")
public class BooleanConverter implements Converter {

	private static final String NAO = "Não";
	private static final String SIM = "Sim";

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			if (value.equals(SIM)) {
				return true;
			} else {
				return false;
			}
		}

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && (value instanceof String && !StringUtils.isBlank((String)value))) {
			if ((Boolean) value) {
				return SIM;
			} else {
				return NAO;
			}
		}
		return null;
	}

}
