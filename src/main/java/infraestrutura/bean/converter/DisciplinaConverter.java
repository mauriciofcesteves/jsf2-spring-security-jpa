package infraestrutura.bean.converter;

import infraestrutura.model.Disciplina;
import infraestrutura.service.DisciplinasService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="disciplinaConverter")
public class DisciplinaConverter implements Converter {

	@Autowired
	private DisciplinasService disciplinasService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("")  && !value.equals("Selecione")) {
			return disciplinasService.consultarPorId(Integer.valueOf(value));
		}

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Disciplina) {
			return String.valueOf(((Disciplina) value).getId());
		}
		return null;
	}

	public DisciplinasService getDisciplinasService() {
		return disciplinasService;
	}

	public void setDisciplinasService(DisciplinasService disciplinasService) {
		this.disciplinasService = disciplinasService;
	}

}
