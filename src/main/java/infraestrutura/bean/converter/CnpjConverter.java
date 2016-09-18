/* 
 * Projeto: Sistema Portal de Compras - JAVA
 * 
 * Empresa Municipal de Inform�tica - EMPREL  
 * Rua 21 de Abril, 3370 � Torr�es � CEP: 50761-350
 * Recife � PE � Brasil
 * Fone: (81) 3232-7130
 * Fax: (81) 3232-7004
 * emprel-presidencia@recife.pe.gov.br
 *
 * Este software � confidencial e propriedade intelectual da
 * EMPREL. Voc� n�o deve utilizar indevidamente este produto 
 * em desacordo com as normas da institui��o.
 */
package infraestrutura.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.stereotype.Component;

/**
 * Conversor CNPJ
 */
@Component(value="cnpjConverter")
public class CnpjConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component,
	    String value) throws ConverterException {
	/*
	 * Ir� converter CNPJ formatado para um sem pontos, tra�o e barra. Ex.:
	 * 07.374.998/0001-33 torna-se 07374998000133.
	 */
	String cnpj = value;
	if (value != null && !value.equals(""))
	    cnpj = formatarCNPJ(value);

	return cnpj;
    }

    public String getAsString(FacesContext context, UIComponent component,
	    Object value) throws ConverterException {
	/*
	 * Ira converter CNPJ n formatado para um com pontos, traço e barra.
	 * Ex.: 07374998000133 torna-se 07.374.998/0001-33.
	 */
	String cnpj = (String) value;
	if (cnpj != null && cnpj.length() == 14)
	    cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "."
		    + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-"
		    + cnpj.substring(12, 14);

	return cnpj;
    }
    
    /**
     * Formata o cnpj para apenas nï¿½meros. Sem traï¿½os e barras.
     */
    public static final String formatarCNPJ(String value) {
	if (value != null && !value.equals(""))
	    return value.replaceAll("\\.", "").replaceAll("\\-", "")
		    .replaceAll("/", "");

	return null;
    }
}
