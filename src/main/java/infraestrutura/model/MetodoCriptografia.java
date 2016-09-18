package infraestrutura.model;

public enum MetodoCriptografia {

	SHA256("sha-256"), MD5("md5");

	private String metodo;

	MetodoCriptografia(String metodo){
		this.metodo = metodo;
	}

	public String getMetodo() {
		return metodo;
	}

}
