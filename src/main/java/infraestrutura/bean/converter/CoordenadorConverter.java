package infraestrutura.bean.converter;

import infraestrutura.model.Coordenador;
import infraestrutura.service.CoordenadoresService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="coordenadorConverter")
public class CoordenadorConverter implements Converter {

	@Autowired
	private CoordenadoresService coordenadoresService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("") && !value.equals("Selecione") && !value.equals("TODOS")) {
			return coordenadoresService.consultarPorId(Integer.valueOf(value));
		}

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Coordenador) {
			return String.valueOf(((Coordenador) value).getId());
		}
		return null;
	}

	public CoordenadoresService getCoordenadoresService() {
		return coordenadoresService;
	}

	public void setCoordenadoresService(CoordenadoresService coordenadoresService) {
		this.coordenadoresService = coordenadoresService;
	}

}
