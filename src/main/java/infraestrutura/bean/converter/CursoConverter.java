package infraestrutura.bean.converter;

import infraestrutura.model.Curso;
import infraestrutura.service.CursosService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="cursoConverter")
public class CursoConverter implements Converter {

	@Autowired
	private CursosService cursosService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("") && !value.equals("Selecione")) {
			return cursosService.consultarPorId(Integer.parseInt(value));
		}

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Curso) {
			return String.valueOf(((Curso) value).getId());
		}
		return null;
	}

	public CursosService getCursosService() {
		return cursosService;
	}

	public void setCursosService(CursosService cursosService) {
		this.cursosService = cursosService;
	}

}
