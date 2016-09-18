package infraestrutura.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component(value="cpfConverter")
public class CpfConverter implements Converter {

    /**
     * {@inheritDoc}
     */
    public Object getAsObject(FacesContext context, UIComponent component,
	    String value) {

	/*
	 * Ir� converter CPF formatado para um sem pontos e tra�o. Ex.:
	 * 079.454.742-33 torna-se 07945474233.
	 */
	String cpf = value;
	if (!StringUtils.isBlank(value)) {
	    cpf = value.replace(".", "").replace("-", "");
	}
	return cpf;

    }

    /**
     * {@inheritDoc}
     */
    public String getAsString(FacesContext context, UIComponent component,
	    Object value) {
	// Retorna um CPF no formato ###.###.###-##
	String resultado = "";
	String cpf = (String) value;
	if (!StringUtils.isEmpty(cpf) && (cpf).length() >= 11) {
	    resultado = formatarCPF(cpf);
	}
	return resultado;
    }

    public static String formatarCPF(String cpf) {
	String cpfTamanhoCerto = cpf == null ? "" : cpf;
	if (cpfTamanhoCerto.length() < 11) {
	    cpfTamanhoCerto = StringUtils.leftPad(cpfTamanhoCerto, 11, '0');
	}
	StringBuilder cpfFormatadoBuilder = new StringBuilder();
	cpfFormatadoBuilder.append(cpfTamanhoCerto.substring(0, 3) + ".");
	cpfFormatadoBuilder.append(cpfTamanhoCerto.substring(3, 6) + ".");
	cpfFormatadoBuilder.append(cpfTamanhoCerto.substring(6, 9) + "-");
	cpfFormatadoBuilder.append(cpfTamanhoCerto.substring(9));
	return cpfFormatadoBuilder.toString();
    }
}
