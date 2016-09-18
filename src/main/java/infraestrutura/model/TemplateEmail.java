package infraestrutura.model;

import infraestrutura.model.infra.Pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

/**
 * Template Email
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class TemplateEmail extends Pojo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(length=100)
	@Size(max=100)
	private String assunto;
	
	@Lob
	private String corpo;
	
	public TemplateEmail(){
		assunto = "";
		corpo = "";
	}
	
	public TemplateEmail(TemplateEmail outroTemplate){
		this.assunto = outroTemplate.getAssunto();
		this.corpo = outroTemplate.getCorpo();
	}

	/**
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}

	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	/**
	 * @return the corpo
	 */
	public String getCorpo() {
		return corpo;
	}

	/**
	 * @param corpo the corpo to set
	 */
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public boolean templatePreenchido(){
		return StringUtils.isNotBlank(corpo) || StringUtils.isNotBlank(assunto);
	}

}
