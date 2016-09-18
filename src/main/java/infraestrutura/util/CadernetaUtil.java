package infraestrutura.util;

import infraestrutura.model.Caderneta;
import infraestrutura.model.Documento;
import infraestrutura.model.TemplateEmail;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

public class CadernetaUtil {

	public static final String FORMATO_DATA = "dd/MM/yyyy";
	public static final String FORMATO_ARQUIVO = "dd-MM-yyyy HH-mm-ss";
	
	private static final String RETIRADA = "retirada";
	private static final String DEVOLUCAO = "devolução";
	public static SimpleDateFormat formatadorDataProtocolo = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String REGEX = "(?i)";

	public static boolean isTemplatePreenchido(TemplateEmail email) {
		return email != null && StringUtils.isNotBlank(email.getAssunto()) && StringUtils.isNotBlank(email.getCorpo());
	}
	
	public static boolean validarEmail(String email){
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
	    Matcher m = p.matcher(email); 
	    return m.find();
	}
	
	/**
	 * Realiza o merge para o template 'Informar Protocolo Caderneta' para Professor.
	 */
	public static TemplateEmail mergeTemplateInformarProtocoloCadernetaProfessor(TemplateEmail template, Caderneta caderneta, boolean retirada) {
		TemplateEmail templateComMerge = new TemplateEmail(template);
		mergeCampoNome(templateComMerge, caderneta.getProfessor().getNome());
		mergeCampoDisciplina(templateComMerge, caderneta);
		
		if (retirada) {
			mergeCampoProtocolo(templateComMerge, caderneta.getProtocolo().toString());
			mergeCampoTipo(templateComMerge, RETIRADA);
		} else {
			mergeCampoTipo(templateComMerge, DEVOLUCAO);
			mergeCampoProtocolo(templateComMerge, caderneta.getProtocoloDevolucaoProfessor().toString());
		}
		
		mergeCampoDataFinalDisciplina(templateComMerge, caderneta);
		mergeCampoDataPrevista(templateComMerge, caderneta.getDataPrevistaDevolucaoProfessor());
		mergeCampoDataRetirada(templateComMerge, caderneta.getDataRetiradaProfessor());
		return templateComMerge;
	}
	
	/**
	 * Realiza o merge para o template 'Informar Protocolo Caderneta' para Coordenador.
	 */
	public static TemplateEmail mergeTemplateInformarProtocoloCadernetaCoordenador(TemplateEmail template, Caderneta caderneta, boolean retirada) {
		TemplateEmail templateComMerge = new TemplateEmail(template);
		mergeCampoNome(templateComMerge, caderneta.getCoordenador().getNome());
		mergeCampoDisciplina(templateComMerge, caderneta);
		
		if (retirada) {
			mergeCampoTipo(templateComMerge, RETIRADA);
			mergeCampoProtocolo(templateComMerge, caderneta.getProtocoloRetiradaCoordenador().toString());
		} else {
			mergeCampoTipo(templateComMerge, DEVOLUCAO);
			mergeCampoProtocolo(templateComMerge, caderneta.getProtocoloDevolucaoCoordenador().toString());
		}
		
		mergeCampoDataFinalDisciplina(templateComMerge, caderneta);
		mergeCampoDataPrevista(templateComMerge, caderneta.getDataPrevistaDevolucaoCoordenador());
		mergeCampoDataRetirada(templateComMerge, caderneta.getDataRetiradaCoordenador());
		return templateComMerge;
	}

	/**
	 * Realiza o merge para o template 'Solicitar Devolucao Caderneta' para Professor ou Coordenador.
	 */
	public static TemplateEmail mergeTemplateSolicitarDevolucaoCaderneta(TemplateEmail template, Caderneta caderneta, boolean professor) {
		TemplateEmail templateComMerge = new TemplateEmail(template);
		mergeCampoNome(templateComMerge, caderneta.getCoordenador().getNome());
		mergeCampoDisciplina(templateComMerge, caderneta);
		mergeCampoDataFinalDisciplina(templateComMerge, caderneta);
		
		if (professor) {
			mergeCampoDataPrevista(templateComMerge, caderneta.getDataPrevistaDevolucaoProfessor());
			mergeCampoDataRetirada(templateComMerge, caderneta.getDataRetiradaProfessor());
		} else {
			mergeCampoDataPrevista(templateComMerge, caderneta.getDataPrevistaDevolucaoCoordenador());
			mergeCampoDataRetirada(templateComMerge, caderneta.getDataRetiradaCoordenador());
		}
		return templateComMerge;
	}
	
	/**
	 * Realiza o merge para o template 'Informar Prazo Extrapolado'.
	 */
	public static TemplateEmail mergeTemplateInformarPrazoExtrapolado(TemplateEmail template, Caderneta caderneta) {
		TemplateEmail templateComMerge = new TemplateEmail(template);
		mergeCampoCoordenador(templateComMerge, caderneta);
		mergeCampoProfessor(templateComMerge, caderneta);
		mergeCampoDisciplina(templateComMerge, caderneta);
		mergeCampoDataFinalDisciplina(templateComMerge, caderneta);
		mergeCampoDataPrevista(templateComMerge, caderneta.getDataPrevistaDevolucaoProfessor());
		mergeCampoDataRetirada(templateComMerge, caderneta.getDataRetiradaProfessor());
		mergeCampoDataHoje(templateComMerge);
		return templateComMerge;
	}
	
	/**
	 * Realiza o merge para o template 'Informar Coordenador Caderneta Disponivel'.
	 */
	public static TemplateEmail mergeTemplateInformarCoordenadorCadernetaDisponivel(TemplateEmail template, Caderneta caderneta) {
		TemplateEmail templateComMerge = new TemplateEmail(template);
		mergeCampoCoordenador(templateComMerge, caderneta);
		mergeCampoProfessor(templateComMerge, caderneta);
		mergeCampoDisciplina(templateComMerge, caderneta);
		mergeCampoDataPrevista(templateComMerge, caderneta.getDataPrevistaDevolucaoCoordenador());
		return templateComMerge;
	}
	
	/**
	 * Realiza o merge para o template 'Informar Gestor Operacional'.
	 */
	public static TemplateEmail mergeTemplateInformarGestorOperacional(TemplateEmail template, Caderneta caderneta) {
		TemplateEmail templateComMerge = new TemplateEmail(template);
		mergeCampoCoordenador(templateComMerge, caderneta);
		mergeCampoDisciplina(templateComMerge, caderneta);
		return templateComMerge;
	}
	
	private static void mergeCampoNome(TemplateEmail template, String nome) {
		//merge da #DISCIPLINA para corpo do email
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"nome", nome));
		
		//merge da #DISCIPLINA em assunto do email
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"nome", nome));
		
	}
	
	private static void mergeCampoDisciplina(TemplateEmail template, Caderneta caderneta) {
		//merge da #DISCIPLINA para corpo do email
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"disciplina", caderneta.getDisciplina().getNome()));

		//merge da #DISCIPLINA em assunto do email
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"disciplina", caderneta.getDisciplina().getNome()));

	}
	
	private static void mergeCampoProtocolo(TemplateEmail template, String protocolo) {
		//merge da #PROTOCOLO para corpo do email
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"protocolo", protocolo));
		
		//merge da #PROTOCOLO em assunto do email
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"protocolo", protocolo));
	}
	
	private static void mergeCampoTipo(TemplateEmail template, String tipo) {
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"tipo", tipo));
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"tipo", tipo));
	}
	
	private static void mergeCampoDataFinalDisciplina(TemplateEmail template, Caderneta caderneta) {
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"data_final_disciplina", caderneta.getDataFinalDisciplina().toString(FORMATO_DATA)));
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"data_final_disciplina", caderneta.getDataFinalDisciplina().toString(FORMATO_DATA)));
	}
	
	/**
	 * Pode ser a data de previsão do professor ou coordenado.
	 */
	private static void mergeCampoDataPrevista(TemplateEmail template, LocalDate dataPrevisao) {
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"data_prevista", dataPrevisao.toString(FORMATO_DATA)));
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"data_prevista", dataPrevisao.toString(FORMATO_DATA)));
	}
	
	/**
	 * Pode ser a data de retirada do professor ou coordenado.
	 */
	private static void mergeCampoDataRetirada(TemplateEmail template, LocalDate dataRetirada) {
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"data_retirada", dataRetirada.toString(FORMATO_DATA)));
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"data_retirada", dataRetirada.toString(FORMATO_DATA)));
	}

	private static void mergeCampoCoordenador(TemplateEmail template, Caderneta caderneta) {
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"coordenador", caderneta.getCoordenador().getNome()));
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"coordenador", caderneta.getCoordenador().getNome()));
	}
	
	private static void mergeCampoProfessor(TemplateEmail template, Caderneta caderneta) {
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"professor", caderneta.getProfessor().getNome()));
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"professor", caderneta.getProfessor().getNome()));
	}
	
	private static void mergeCampoDataHoje(TemplateEmail template) {
		String hoje = new LocalDate().toString(FORMATO_DATA);
		template.setCorpo(template.getCorpo().replaceAll("#"+REGEX+"data_hoje", hoje));
		template.setAssunto(template.getAssunto().replaceAll("#"+REGEX+"data_hoje", hoje));
	}
	
	public static String getDataFormatada(LocalDate data){
		if(data != null){
			return data.toString(FORMATO_DATA);
		}
		return "";
	}
	
	public static String getRelatorio(String nomeRelatorio){
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/relatorios/"+nomeRelatorio+".jasper");
	}
	
	public static void download(Documento documento) throws IOException{
		FacesContext facesContext =  FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + documento.getNome() + "\";");
		response.setContentLength(documento.getConteudo().length);
		response.getOutputStream().write(documento.getConteudo(), 0, documento.getConteudo().length);
		facesContext.responseComplete();
	}
}
