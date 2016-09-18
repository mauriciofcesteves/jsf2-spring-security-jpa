package infraestrutura.bean.converter;

import infraestrutura.model.Perfil;
import infraestrutura.service.PerfilService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="perfilConverter")
public class PerfilConverter implements Converter {

	@Autowired
	private PerfilService perfilService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("") && !value.equals("Selecione")) {
			return perfilService.consultarPorNome(value);
		}

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Perfil) {
			return String.valueOf(((Perfil) value).getNome());
		}
		return null;
	}

}
