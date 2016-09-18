package infraestrutura.bean.dto;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class DashboardDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String coordenador;
	private Integer qtdeJobs;
	private Integer qtdeJobsConcluidos;
	private Integer qtdeJobsConcluidosComAtraso;
	private Integer qtdeJobsNaoConcluidos;
	private BufferedImage image;

	public String getCoordenador() {
		return coordenador;
	}
	
	public void setCoordenador(String coordenador) {
		this.coordenador = coordenador;
	}

	public int getQtdeJobs() {
		return qtdeJobs;
	}

	public void setQtdeJobs(int qtdeJobs) {
		this.qtdeJobs = qtdeJobs;
	}

	public int getQtdeJobsConcluidos() {
		return qtdeJobsConcluidos;
	}

	public void setQtdeJobsConcluidos(int qtdeJobsConcluidos) {
		this.qtdeJobsConcluidos = qtdeJobsConcluidos;
	}

	public int getQtdeJobsConcluidosComAtraso() {
		return qtdeJobsConcluidosComAtraso;
	}

	public void setQtdeJobsConcluidosComAtraso(int qtdeJobsConcluidosComAtraso) {
		this.qtdeJobsConcluidosComAtraso = qtdeJobsConcluidosComAtraso;
	}

	public int getQtdeJobsNaoConcluidos() {
		return qtdeJobsNaoConcluidos;
	}

	public void setQtdeJobsNaoConcluidos(int qtdeJobsNaoConcluidos) {
		this.qtdeJobsNaoConcluidos = qtdeJobsNaoConcluidos;
	}

	public double getPercentualConcluido(){
		if(getQtdeJobs()>0){
			return ((double)getQtdeJobsConcluidos())/getQtdeJobs()*100;
		}
		return 100.0;
	}
	
	public double getPercentualConcluidoComAtraso(){
		if(getQtdeJobs()>0){
			return ((double)getQtdeJobsConcluidosComAtraso())/getQtdeJobs()*100;
		}
		return 0.0;
	}
	
	public double getPercentualNaoConcluido(){
		if(getQtdeJobs()>0){
			return ((double)getQtdeJobsNaoConcluidos())/getQtdeJobs()*100;
		}
		return 0.0;
	}

	public void setQtdeJobs(Integer qtdeJobs) {
		this.qtdeJobs = qtdeJobs;
	}

	public void setQtdeJobsConcluidos(Integer qtdeJobsConcluidos) {
		this.qtdeJobsConcluidos = qtdeJobsConcluidos;
	}

	public void setQtdeJobsConcluidosComAtraso(Integer qtdeJobsConcluidosComAtraso) {
		this.qtdeJobsConcluidosComAtraso = qtdeJobsConcluidosComAtraso;
	}

	public void setQtdeJobsNaoConcluidos(Integer qtdeJobsNaoConcluidos) {
		this.qtdeJobsNaoConcluidos = qtdeJobsNaoConcluidos;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
