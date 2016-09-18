package infraestrutura.bean.converter;

import infraestrutura.model.Professor;
import infraestrutura.service.ProfessoresService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="professorConverter")
public class ProfessorConverter implements Converter {

	@Autowired
	private ProfessoresService professoresService;
	
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.equals("") && !value.equals("Selecione")) {
			return professoresService.consultarPorId(Integer.valueOf(value));
		}

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null && value instanceof Professor) {
			return String.valueOf(((Professor) value).getId());
		}
		return null;
	}

	public ProfessoresService getProfessoresService() {
		return professoresService;
	}

	public void setProfessoresService(ProfessoresService professoresService) {
		this.professoresService = professoresService;
	}

}
