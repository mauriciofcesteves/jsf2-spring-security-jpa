package infraestrutura.model;

import java.io.Serializable;

public enum SituacaoCaderneta implements Serializable{

	CADERNETA_COM_PROFESSOR("Caderneta com Professor"), AGUARDANDO_COORDENADOR("Aguardando Coordenador"), CADERNETA_COM_COORDENADOR("Caderneta com Coordenador"), CONCLUIDO("Concluído");
	
	String nome;
	
	SituacaoCaderneta(String nome){
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
